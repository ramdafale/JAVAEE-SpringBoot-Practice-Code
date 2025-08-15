#!/usr/bin/env python3
"""
Spring Boot API Analyzer Agent

This agent analyzes Spring Boot applications to provide comprehensive information about:
- API endpoints and their request/response structures
- Mandatory fields and validation rules
- Impact analysis for field changes
- Request/Response examples
"""

import os
import re
import json
import ast
from typing import Dict, List, Optional, Any, Set
from dataclasses import dataclass, asdict
from pathlib import Path
import argparse


@dataclass
class FieldInfo:
    """Information about a field in a model/DTO"""
    name: str
    type: str
    is_mandatory: bool = False
    validation_rules: List[str] = None
    default_value: Any = None
    description: str = ""
    
    def __post_init__(self):
        if self.validation_rules is None:
            self.validation_rules = []


@dataclass
class ApiEndpoint:
    """Information about an API endpoint"""
    path: str
    method: str
    controller_class: str
    method_name: str
    request_body_type: Optional[str] = None
    response_type: Optional[str] = None
    path_variables: List[str] = None
    query_parameters: List[str] = None
    request_headers: List[str] = None
    endpoint_type: str = "REST"  # REST or GraphQL
    graphql_operation: Optional[str] = None  # query, mutation, subscription
    graphql_fields: List[str] = None
    
    def __post_init__(self):
        if self.path_variables is None:
            self.path_variables = []
        if self.query_parameters is None:
            self.query_parameters = []
        if self.request_headers is None:
            self.request_headers = []
        if self.graphql_fields is None:
            self.graphql_fields = []


@dataclass
class GraphQLSchema:
    """Information about GraphQL schema"""
    queries: List[Dict[str, Any]] = None
    mutations: List[Dict[str, Any]] = None
    subscriptions: List[Dict[str, Any]] = None
    types: Dict[str, Dict[str, Any]] = None
    
    def __post_init__(self):
        if self.queries is None:
            self.queries = []
        if self.mutations is None:
            self.mutations = []
        if self.subscriptions is None:
            self.subscriptions = []
        if self.types is None:
            self.types = {}


@dataclass
class ModelInfo:
    """Information about a model/DTO class"""
    class_name: str
    package: str
    fields: List[FieldInfo]
    parent_class: Optional[str] = None
    annotations: List[str] = None
    
    def __post_init__(self):
        if self.annotations is None:
            self.annotations = []


class SpringBootApiAnalyzer:
    """Main analyzer class for Spring Boot APIs"""
    
    def __init__(self, project_path: str):
        self.project_path = Path(project_path)
        self.models: Dict[str, ModelInfo] = {}
        self.endpoints: List[ApiEndpoint] = []
        self.graphql_schema: Optional[GraphQLSchema] = None
        self.validation_annotations = {
            '@NotNull', '@NotEmpty', '@NotBlank', '@Valid', '@Required',
            '@Min', '@Max', '@Size', '@Pattern', '@Email', '@Positive',
            '@PositiveOrZero', '@Negative', '@NegativeOrZero', '@Past',
            '@PastOrPresent', '@Future', '@FutureOrPresent'
        }
        
    def analyze_project(self) -> Dict[str, Any]:
        """Analyze the entire Spring Boot project"""
        print("🔍 Starting Spring Boot API analysis...")
        
        # Find and analyze Java files
        java_files = list(self.project_path.rglob("*.java"))
        
        for java_file in java_files:
            self._analyze_java_file(java_file)
        
        # Analyze GraphQL schema files
        self._analyze_graphql_schema()
        
        # Generate comprehensive report
        return self._generate_report()
    
    def _analyze_java_file(self, file_path: Path):
        """Analyze a single Java file"""
        try:
            with open(file_path, 'r', encoding='utf-8') as f:
                content = f.read()
            
            # Determine file type and analyze accordingly
            # Check GraphQL first since GraphQL controllers are also regular controllers
            if self._is_graphql_controller(content):
                self._analyze_graphql_controller(content, file_path)
            elif self._is_controller(content):
                self._analyze_controller(content, file_path)
            elif self._is_model_or_dto(content):
                self._analyze_model(content, file_path)
                
        except Exception as e:
            print(f"⚠️  Error analyzing {file_path}: {e}")
    
    def _is_controller(self, content: str) -> bool:
        """Check if the file is a Spring Boot controller"""
        controller_patterns = [
            r'@RestController',
            r'@Controller',
            r'@RequestMapping'
        ]
        return any(re.search(pattern, content) for pattern in controller_patterns)
    
    def _is_graphql_controller(self, content: str) -> bool:
        """Check if the file is a GraphQL controller"""
        graphql_patterns = [
            r'@QueryMapping',
            r'@MutationMapping',
            r'@SubscriptionMapping',
            r'@SchemaMapping',
            r'@Controller.*GraphQL',
            r'GraphQLQueryResolver',
            r'GraphQLMutationResolver',
            r'GraphQLSubscriptionResolver'
        ]
        return any(re.search(pattern, content) for pattern in graphql_patterns)
    
    def _is_model_or_dto(self, content: str) -> bool:
        """Check if the file is a model or DTO"""
        model_patterns = [
            r'@Entity',
            r'@Document',
            r'@Embeddable',
            r'class\s+\w+.*\{',  # Any class
        ]
        return any(re.search(pattern, content) for pattern in model_patterns)
    
    def _analyze_controller(self, content: str, file_path: Path):
        """Analyze a Spring Boot controller"""
        # Extract class name
        class_match = re.search(r'class\s+(\w+)', content)
        if not class_match:
            return
        
        class_name = class_match.group(1)
        
        # Extract base request mapping
        base_mapping = ""
        base_mapping_match = re.search(r'@RequestMapping\s*\(\s*["\']([^"\']+)["\']', content)
        if base_mapping_match:
            base_mapping = base_mapping_match.group(1)
        
        # Find all endpoint methods
        method_patterns = [
            (r'@GetMapping\s*\(\s*["\']([^"\']*)["\'].*?\)\s*(?:public\s+)?(\w+(?:<[^>]+>)?)\s+(\w+)\s*\([^)]*\)', 'GET'),
            (r'@PostMapping\s*\(\s*["\']([^"\']*)["\'].*?\)\s*(?:public\s+)?(\w+(?:<[^>]+>)?)\s+(\w+)\s*\([^)]*\)', 'POST'),
            (r'@PutMapping\s*\(\s*["\']([^"\']*)["\'].*?\)\s*(?:public\s+)?(\w+(?:<[^>]+>)?)\s+(\w+)\s*\([^)]*\)', 'PUT'),
            (r'@DeleteMapping\s*\(\s*["\']([^"\']*)["\'].*?\)\s*(?:public\s+)?(\w+(?:<[^>]+>)?)\s+(\w+)\s*\([^)]*\)', 'DELETE'),
            (r'@PatchMapping\s*\(\s*["\']([^"\']*)["\'].*?\)\s*(?:public\s+)?(\w+(?:<[^>]+>)?)\s+(\w+)\s*\([^)]*\)', 'PATCH'),
        ]
        
        for pattern, http_method in method_patterns:
            matches = re.finditer(pattern, content, re.DOTALL)
            for match in matches:
                path = base_mapping + match.group(1)
                return_type = match.group(2)
                method_name = match.group(3)
                
                # Extract method details
                method_start = match.start()
                method_content = self._extract_method_content(content, method_start)
                
                endpoint = ApiEndpoint(
                    path=path,
                    method=http_method,
                    controller_class=class_name,
                    method_name=method_name,
                    response_type=return_type
                )
                
                # Analyze method parameters
                self._analyze_method_parameters(method_content, endpoint)
                
                self.endpoints.append(endpoint)
    
    def _analyze_graphql_controller(self, content: str, file_path: Path):
        """Analyze a GraphQL controller"""
        # Extract class name
        class_match = re.search(r'class\s+(\w+)', content)
        if not class_match:
            return
        
        class_name = class_match.group(1)
        
        # Find GraphQL operations
        graphql_patterns = [
            (r'@QueryMapping\s*(?:\([^)]*\))?\s*(?:public\s+)?(\w+(?:<[^>]+>)?)\s+(\w+)\s*\([^)]*\)', 'query'),
            (r'@MutationMapping\s*(?:\([^)]*\))?\s*(?:public\s+)?(\w+(?:<[^>]+>)?)\s+(\w+)\s*\([^)]*\)', 'mutation'),
            (r'@SubscriptionMapping\s*(?:\([^)]*\))?\s*(?:public\s+)?(\w+(?:<[^>]+>)?)\s+(\w+)\s*\([^)]*\)', 'subscription'),
            (r'@SchemaMapping\s*(?:\([^)]*\))?\s*(?:public\s+)?(\w+(?:<[^>]+>)?)\s+(\w+)\s*\([^)]*\)', 'query')
        ]
        
        for pattern, operation_type in graphql_patterns:
            matches = re.finditer(pattern, content, re.DOTALL)
            for match in matches:
                return_type = match.group(1)
                method_name = match.group(2)
                
                # Extract method details
                method_start = match.start()
                method_content = self._extract_method_content(content, method_start)
                
                # Create GraphQL endpoint
                endpoint = ApiEndpoint(
                    path="/graphql",  # Standard GraphQL endpoint
                    method="POST",
                    controller_class=class_name,
                    method_name=method_name,
                    response_type=return_type,
                    endpoint_type="GraphQL",
                    graphql_operation=operation_type
                )
                
                # Extract GraphQL-specific information
                self._analyze_graphql_method_parameters(method_content, endpoint)
                
                self.endpoints.append(endpoint)
    
    def _analyze_graphql_method_parameters(self, method_content: str, endpoint: ApiEndpoint):
        """Analyze GraphQL method parameters"""
        # Extract @Argument parameters
        arguments = re.findall(r'@Argument(?:\s*\(\s*["\']([^"\']+)["\']\s*\))?\s+\w+\s+(\w+)', method_content)
        for arg in arguments:
            arg_name = arg[0] if arg[0] else arg[1]
            endpoint.graphql_fields.append(arg_name)
        
        # Extract return type fields (simplified)
        if endpoint.response_type:
            # This would ideally parse the actual GraphQL schema
            # For now, we'll extract basic field information
            endpoint.graphql_fields.extend(self._extract_graphql_return_fields(endpoint.response_type))
    
    def _extract_graphql_return_fields(self, return_type: str) -> List[str]:
        """Extract fields from GraphQL return type"""
        # This is a simplified implementation
        # In a real scenario, you'd parse the actual GraphQL schema
        common_fields = {
            'User': ['id', 'username', 'email', 'firstName', 'lastName'],
            'UserDTO': ['id', 'username', 'email', 'firstName', 'lastName'],
            'CreateUserRequest': ['username', 'email', 'firstName', 'lastName', 'password'],
            'String': [],
            'Boolean': [],
            'Int': [],
            'ID': []
        }
        
        # Remove generic types
        clean_type = re.sub(r'<[^>]+>', '', return_type)
        clean_type = re.sub(r'List|Optional|ResponseEntity', '', clean_type).strip()
        
        return common_fields.get(clean_type, [])
    
    def _analyze_graphql_schema(self):
        """Analyze GraphQL schema files"""
        # Look for .graphqls, .gql, or schema files
        schema_files = []
        schema_files.extend(list(self.project_path.rglob("*.graphqls")))
        schema_files.extend(list(self.project_path.rglob("*.gql")))
        schema_files.extend(list(self.project_path.rglob("**/schema.graphql")))
        schema_files.extend(list(self.project_path.rglob("**/schema/**/*.graphql")))
        
        if not schema_files:
            return
        
        self.graphql_schema = GraphQLSchema()
        
        for schema_file in schema_files:
            try:
                with open(schema_file, 'r', encoding='utf-8') as f:
                    schema_content = f.read()
                
                self._parse_graphql_schema_content(schema_content)
                
            except Exception as e:
                print(f"⚠️  Error analyzing GraphQL schema {schema_file}: {e}")
    
    def _parse_graphql_schema_content(self, content: str):
        """Parse GraphQL schema content"""
        if not self.graphql_schema:
            return
        
        # Extract Query type
        query_match = re.search(r'type\s+Query\s*\{([^}]+)\}', content, re.DOTALL)
        if query_match:
            query_fields = self._extract_graphql_fields(query_match.group(1))
            self.graphql_schema.queries.extend(query_fields)
        
        # Extract Mutation type
        mutation_match = re.search(r'type\s+Mutation\s*\{([^}]+)\}', content, re.DOTALL)
        if mutation_match:
            mutation_fields = self._extract_graphql_fields(mutation_match.group(1))
            self.graphql_schema.mutations.extend(mutation_fields)
        
        # Extract Subscription type
        subscription_match = re.search(r'type\s+Subscription\s*\{([^}]+)\}', content, re.DOTALL)
        if subscription_match:
            subscription_fields = self._extract_graphql_fields(subscription_match.group(1))
            self.graphql_schema.subscriptions.extend(subscription_fields)
        
        # Extract custom types
        type_matches = re.finditer(r'type\s+(\w+)\s*\{([^}]+)\}', content, re.DOTALL)
        for type_match in type_matches:
            type_name = type_match.group(1)
            if type_name not in ['Query', 'Mutation', 'Subscription']:
                type_fields = self._extract_graphql_fields(type_match.group(2))
                self.graphql_schema.types[type_name] = {
                    'fields': type_fields,
                    'kind': 'OBJECT'
                }
        
        # Extract input types
        input_matches = re.finditer(r'input\s+(\w+)\s*\{([^}]+)\}', content, re.DOTALL)
        for input_match in input_matches:
            input_name = input_match.group(1)
            input_fields = self._extract_graphql_fields(input_match.group(2))
            self.graphql_schema.types[input_name] = {
                'fields': input_fields,
                'kind': 'INPUT_OBJECT'
            }
    
    def _extract_graphql_fields(self, fields_content: str) -> List[Dict[str, Any]]:
        """Extract fields from GraphQL type definition"""
        fields = []
        
        # Match field definitions: fieldName(args): ReturnType
        field_pattern = r'(\w+)(?:\s*\([^)]*\))?\s*:\s*([^!\n]+)(!?)'
        
        for match in re.finditer(field_pattern, fields_content):
            field_name = match.group(1)
            field_type = match.group(2).strip()
            is_required = bool(match.group(3))
            
            fields.append({
                'name': field_name,
                'type': field_type,
                'required': is_required,
                'args': self._extract_field_arguments(fields_content, field_name)
            })
        
        return fields
    
    def _extract_field_arguments(self, content: str, field_name: str) -> List[Dict[str, str]]:
        """Extract arguments for a GraphQL field"""
        # Look for field with arguments: fieldName(arg1: Type, arg2: Type): ReturnType
        pattern = rf'{field_name}\s*\(([^)]+)\)\s*:'
        match = re.search(pattern, content)
        
        if not match:
            return []
        
        args_content = match.group(1)
        args = []
        
        # Parse arguments: argName: ArgType
        arg_pattern = r'(\w+)\s*:\s*([^,)]+)'
        for arg_match in re.finditer(arg_pattern, args_content):
            arg_name = arg_match.group(1)
            arg_type = arg_match.group(2).strip()
            
            args.append({
                'name': arg_name,
                'type': arg_type
            })
        
        return args
    
    def _extract_method_content(self, content: str, start_pos: int) -> str:
        """Extract the full method content including annotations"""
        lines = content[:start_pos].split('\n')
        method_lines = []
        
        # Go backwards to find method start (including annotations)
        i = len(lines) - 1
        while i >= 0:
            line = lines[i].strip()
            if line.startswith('@') or 'public' in line or 'private' in line or 'protected' in line:
                method_lines.insert(0, line)
                i -= 1
            else:
                break
        
        # Find method end (closing brace)
        remaining_content = content[start_pos:]
        brace_count = 0
        method_end = 0
        
        for i, char in enumerate(remaining_content):
            if char == '{':
                brace_count += 1
            elif char == '}':
                brace_count -= 1
                if brace_count == 0:
                    method_end = i + 1
                    break
        
        method_lines.append(remaining_content[:method_end])
        return '\n'.join(method_lines)
    
    def _analyze_method_parameters(self, method_content: str, endpoint: ApiEndpoint):
        """Analyze method parameters to extract request information"""
        # Extract @RequestBody
        request_body_match = re.search(r'@RequestBody\s+(\w+(?:<[^>]+>)?)', method_content)
        if request_body_match:
            endpoint.request_body_type = request_body_match.group(1)
        
        # Extract @PathVariable
        path_vars = re.findall(r'@PathVariable(?:\s*\(\s*["\']([^"\']+)["\']\s*\))?\s+\w+\s+(\w+)', method_content)
        for path_var in path_vars:
            var_name = path_var[0] if path_var[0] else path_var[1]
            endpoint.path_variables.append(var_name)
        
        # Extract @RequestParam
        request_params = re.findall(r'@RequestParam(?:\s*\(\s*["\']([^"\']+)["\']\s*\))?\s+\w+\s+(\w+)', method_content)
        for param in request_params:
            param_name = param[0] if param[0] else param[1]
            endpoint.query_parameters.append(param_name)
        
        # Extract @RequestHeader
        headers = re.findall(r'@RequestHeader(?:\s*\(\s*["\']([^"\']+)["\']\s*\))?\s+\w+\s+(\w+)', method_content)
        for header in headers:
            header_name = header[0] if header[0] else header[1]
            endpoint.request_headers.append(header_name)
    
    def _analyze_model(self, content: str, file_path: Path):
        """Analyze a model or DTO class"""
        # Extract class name and package
        class_match = re.search(r'class\s+(\w+)', content)
        if not class_match:
            return
        
        class_name = class_match.group(1)
        
        package_match = re.search(r'package\s+([^;]+);', content)
        package = package_match.group(1) if package_match else ""
        
        # Extract class annotations
        class_annotations = re.findall(r'@(\w+)(?:\([^)]*\))?', content)
        
        # Extract fields
        fields = self._extract_fields(content)
        
        # Extract parent class
        parent_match = re.search(r'extends\s+(\w+)', content)
        parent_class = parent_match.group(1) if parent_match else None
        
        model_info = ModelInfo(
            class_name=class_name,
            package=package,
            fields=fields,
            parent_class=parent_class,
            annotations=class_annotations
        )
        
        self.models[class_name] = model_info
    
    def _extract_fields(self, content: str) -> List[FieldInfo]:
        """Extract field information from a class"""
        fields = []
        
        # Pattern to match field declarations with annotations
        field_pattern = r'(?:(@[^\n]+)\s+)?(?:private|protected|public)?\s+(\w+(?:<[^>]+>)?)\s+(\w+)(?:\s*=\s*([^;]+))?;'
        
        matches = re.finditer(field_pattern, content, re.MULTILINE)
        
        for match in matches:
            annotations_str = match.group(1) or ""
            field_type = match.group(2)
            field_name = match.group(3)
            default_value = match.group(4)
            
            # Parse annotations
            validation_rules = []
            is_mandatory = False
            
            for annotation in self.validation_annotations:
                if annotation in annotations_str:
                    validation_rules.append(annotation)
                    if annotation in ['@NotNull', '@NotEmpty', '@NotBlank']:
                        is_mandatory = True
            
            field_info = FieldInfo(
                name=field_name,
                type=field_type,
                is_mandatory=is_mandatory,
                validation_rules=validation_rules,
                default_value=default_value
            )
            
            fields.append(field_info)
        
        return fields
    
    def _generate_report(self) -> Dict[str, Any]:
        """Generate comprehensive analysis report"""
        report = {
            "summary": {
                "total_endpoints": len(self.endpoints),
                "total_models": len(self.models),
                "endpoints_by_method": self._count_endpoints_by_method(),
                "endpoints_by_type": self._count_endpoints_by_type(),
            },
            "endpoints": [asdict(endpoint) for endpoint in self.endpoints],
            "models": {name: asdict(model) for name, model in self.models.items()},
            "analysis": {
                "mandatory_fields_by_model": self._get_mandatory_fields_by_model(),
                "validation_summary": self._get_validation_summary(),
                "endpoint_request_response_mapping": self._get_endpoint_mappings()
            }
        }
        
        # Add GraphQL schema information if available
        if self.graphql_schema:
            report["graphql_schema"] = asdict(self.graphql_schema)
            report["summary"]["graphql_operations"] = {
                "queries": len(self.graphql_schema.queries),
                "mutations": len(self.graphql_schema.mutations),
                "subscriptions": len(self.graphql_schema.subscriptions),
                "types": len(self.graphql_schema.types)
            }
        
        return report
    
    def _count_endpoints_by_method(self) -> Dict[str, int]:
        """Count endpoints by HTTP method"""
        counts = {}
        for endpoint in self.endpoints:
            counts[endpoint.method] = counts.get(endpoint.method, 0) + 1
        return counts
    
    def _count_endpoints_by_type(self) -> Dict[str, int]:
        """Count endpoints by type (REST vs GraphQL)"""
        counts = {}
        for endpoint in self.endpoints:
            endpoint_type = endpoint.endpoint_type
            counts[endpoint_type] = counts.get(endpoint_type, 0) + 1
        return counts
    
    def _get_mandatory_fields_by_model(self) -> Dict[str, List[str]]:
        """Get mandatory fields for each model"""
        mandatory_fields = {}
        for model_name, model in self.models.items():
            mandatory_fields[model_name] = [
                field.name for field in model.fields if field.is_mandatory
            ]
        return mandatory_fields
    
    def _get_validation_summary(self) -> Dict[str, Any]:
        """Get validation rules summary"""
        validation_counts = {}
        for model in self.models.values():
            for field in model.fields:
                for rule in field.validation_rules:
                    validation_counts[rule] = validation_counts.get(rule, 0) + 1
        
        return {
            "validation_rule_usage": validation_counts,
            "models_with_validation": len([
                model for model in self.models.values()
                if any(field.validation_rules for field in model.fields)
            ])
        }
    
    def _get_endpoint_mappings(self) -> List[Dict[str, Any]]:
        """Get request/response mappings for endpoints"""
        mappings = []
        for endpoint in self.endpoints:
            mapping = {
                "endpoint": f"{endpoint.method} {endpoint.path}",
                "request_body": endpoint.request_body_type,
                "response_type": endpoint.response_type,
                "path_variables": endpoint.path_variables,
                "query_parameters": endpoint.query_parameters,
                "request_headers": endpoint.request_headers
            }
            
            # Add detailed field information if model exists
            if endpoint.request_body_type and endpoint.request_body_type in self.models:
                model = self.models[endpoint.request_body_type]
                mapping["request_fields"] = [asdict(field) for field in model.fields]
                mapping["mandatory_request_fields"] = [
                    field.name for field in model.fields if field.is_mandatory
                ]
            
            mappings.append(mapping)
        
        return mappings


class CurlCommandGenerator:
    """Generator for CURL commands based on API endpoints"""
    
    def __init__(self, base_url: str = "http://localhost:8080"):
        self.base_url = base_url.rstrip('/')
    
    def generate_curl_command(self, endpoint: Dict[str, Any], model_info: Dict[str, Any] = None, graphql_schema: Dict[str, Any] = None) -> str:
        """Generate a CURL command for an API endpoint"""
        method = endpoint['method']
        path = endpoint['path']
        endpoint_type = endpoint.get('endpoint_type', 'REST')
        
        # Handle GraphQL endpoints differently
        if endpoint_type == 'GraphQL':
            return self._generate_graphql_curl_command(endpoint, model_info, graphql_schema)
        
        # Build the URL for REST endpoints
        url = f"{self.base_url}{path}"
        
        # Start building the CURL command
        curl_parts = [f"curl -X {method}"]
        
        # Add headers
        headers = []
        if endpoint.get('request_headers'):
            for header in endpoint['request_headers']:
                if header.lower() == 'authorization':
                    headers.append('-H "Authorization: Bearer YOUR_TOKEN"')
                else:
                    headers.append(f'-H "{header}: YOUR_VALUE"')
        
        # Add Content-Type for requests with body
        if endpoint.get('request_body_type') and method in ['POST', 'PUT', 'PATCH']:
            headers.append('-H "Content-Type: application/json"')
        
        # Add headers to curl command
        curl_parts.extend(headers)
        
        # Add request body for POST, PUT, PATCH
        if endpoint.get('request_body_type') and method in ['POST', 'PUT', 'PATCH']:
            sample_body = self._generate_sample_request_body(endpoint, model_info)
            if sample_body:
                # Format JSON properly for curl
                json_body = json.dumps(sample_body, indent=2)
                curl_parts.append(f"-d '{json_body}'")
        
        # Replace path variables with examples
        final_url = self._replace_path_variables(url, endpoint.get('path_variables', []))
        
        # Add query parameters if any
        if endpoint.get('query_parameters'):
            query_params = self._generate_sample_query_params(endpoint['query_parameters'])
            if query_params:
                final_url += f"?{query_params}"
        
        # Add the URL
        curl_parts.append(f'"{final_url}"')
        
        return ' \\\n  '.join(curl_parts)
    
    def _generate_graphql_curl_command(self, endpoint: Dict[str, Any], model_info: Dict[str, Any] = None, graphql_schema: Dict[str, Any] = None) -> str:
        """Generate a CURL command for a GraphQL endpoint"""
        operation_type = endpoint.get('graphql_operation', 'query')
        method_name = endpoint['method_name']
        response_type = endpoint.get('response_type', 'String')
        
        # Build GraphQL query/mutation
        graphql_query = self._build_graphql_query(operation_type, method_name, endpoint, graphql_schema)
        
        # Build the CURL command
        curl_parts = ["curl -X POST"]
        
        # Add headers
        curl_parts.append('-H "Content-Type: application/json"')
        curl_parts.append('-H "Accept: application/json"')
        
        # Add GraphQL query as JSON payload
        graphql_payload = {
            "query": graphql_query,
            "variables": self._generate_graphql_variables(endpoint, graphql_schema)
        }
        
        json_body = json.dumps(graphql_payload, indent=2)
        curl_parts.append(f"-d '{json_body}'")
        
        # Add the GraphQL endpoint URL
        graphql_url = f"{self.base_url}/graphql"
        curl_parts.append(f'"{graphql_url}"')
        
        return ' \\\n  '.join(curl_parts)
    
    def _build_graphql_query(self, operation_type: str, method_name: str, endpoint: Dict[str, Any], graphql_schema: Dict[str, Any] = None) -> str:
        """Build a GraphQL query/mutation string"""
        # Get field information
        fields = endpoint.get('graphql_fields', [])
        response_type = endpoint.get('response_type', 'String')
        
        # Generate return fields based on response type
        return_fields = self._generate_graphql_return_fields(response_type, graphql_schema)
        
        # Generate arguments
        args = self._generate_graphql_arguments(endpoint, graphql_schema)
        
        # Build the query string
        if operation_type == 'mutation':
            query = f"mutation {{\n  {method_name}"
        elif operation_type == 'subscription':
            query = f"subscription {{\n  {method_name}"
        else:  # query
            query = f"query {{\n  {method_name}"
        
        # Add arguments if any
        if args:
            query += f"({args})"
        
        # Add return fields
        if return_fields:
            query += f" {{\n{return_fields}\n  }}"
        
        query += "\n}"
        
        return query
    
    def _generate_graphql_arguments(self, endpoint: Dict[str, Any], graphql_schema: Dict[str, Any] = None) -> str:
        """Generate GraphQL arguments string"""
        fields = endpoint.get('graphql_fields', [])
        
        if not fields:
            return ""
        
        args = []
        for field in fields:
            # Generate sample values based on field name
            if 'id' in field.lower():
                args.append(f'{field}: $id')
            elif 'email' in field.lower():
                args.append(f'{field}: $email')
            elif 'name' in field.lower():
                args.append(f'{field}: $name')
            else:
                args.append(f'{field}: ${field}')
        
        return ', '.join(args)
    
    def _generate_graphql_variables(self, endpoint: Dict[str, Any], graphql_schema: Dict[str, Any] = None) -> Dict[str, Any]:
        """Generate GraphQL variables"""
        fields = endpoint.get('graphql_fields', [])
        variables = {}
        
        for field in fields:
            if 'id' in field.lower():
                variables['id'] = "1"
            elif 'email' in field.lower():
                variables['email'] = "user@example.com"
            elif 'username' in field.lower():
                variables['username'] = "sampleuser"
            elif 'name' in field.lower():
                if 'first' in field.lower():
                    variables['firstName'] = "John"
                elif 'last' in field.lower():
                    variables['lastName'] = "Doe"
                else:
                    variables['name'] = "Sample Name"
            elif 'password' in field.lower():
                variables['password'] = "securePassword123"
            else:
                variables[field] = f"sample_{field.lower()}"
        
        return variables
    
    def _generate_graphql_return_fields(self, response_type: str, graphql_schema: Dict[str, Any] = None) -> str:
        """Generate GraphQL return fields"""
        # Remove generic types and clean up
        clean_type = re.sub(r'<[^>]+>', '', response_type)
        clean_type = re.sub(r'List|Optional|ResponseEntity', '', clean_type).strip()
        
        # Common field mappings
        field_mappings = {
            'User': ['id', 'username', 'email', 'firstName', 'lastName'],
            'UserDTO': ['id', 'username', 'email', 'firstName', 'lastName'],
            'CreateUserRequest': ['username', 'email', 'firstName', 'lastName'],
            'String': [],
            'Boolean': [],
            'Int': [],
            'ID': []
        }
        
        fields = field_mappings.get(clean_type, ['id', 'name'])
        
        if not fields:
            return ""
        
        # Format fields with proper indentation
        formatted_fields = []
        for field in fields:
            formatted_fields.append(f"    {field}")
        
        return '\n'.join(formatted_fields)
    
    def _generate_sample_request_body(self, endpoint: Dict[str, Any], model_info: Dict[str, Any]) -> Dict[str, Any]:
        """Generate a sample request body based on the model"""
        if not endpoint.get('request_body_type') or not model_info:
            return {}
        
        request_type = endpoint['request_body_type']
        if request_type not in model_info:
            return {}
        
        model = model_info[request_type]
        sample_body = {}
        
        for field in model['fields']:
            field_name = field['name']
            field_type = field['type']
            is_mandatory = field['is_mandatory']
            
            # Generate sample values based on field type and name
            sample_value = self._generate_sample_value(field_name, field_type, is_mandatory)
            
            # Include mandatory fields and some optional ones for completeness
            if is_mandatory or field_name in ['id', 'name', 'email', 'username']:
                sample_body[field_name] = sample_value
        
        return sample_body
    
    def _generate_sample_value(self, field_name: str, field_type: str, is_mandatory: bool):
        """Generate sample values based on field name and type"""
        field_name_lower = field_name.lower()
        
        # Type-based defaults
        if 'String' in field_type:
            if 'email' in field_name_lower:
                return "user@example.com"
            elif 'name' in field_name_lower:
                if 'first' in field_name_lower:
                    return "John"
                elif 'last' in field_name_lower:
                    return "Doe"
                else:
                    return "Sample Name"
            elif 'username' in field_name_lower:
                return "sampleuser"
            elif 'password' in field_name_lower:
                return "securePassword123"
            elif 'phone' in field_name_lower:
                return "+1234567890"
            elif 'department' in field_name_lower:
                return "Engineering"
            else:
                return f"sample_{field_name_lower}"
        
        elif 'Long' in field_type or 'Integer' in field_type or 'int' in field_type:
            if 'id' in field_name_lower:
                return 1
            elif 'age' in field_name_lower:
                return 25
            elif 'count' in field_name_lower or 'number' in field_name_lower:
                return 10
            else:
                return 1
        
        elif 'boolean' in field_type or 'Boolean' in field_type:
            if 'active' in field_name_lower or 'enabled' in field_name_lower:
                return True
            else:
                return False
        
        elif 'LocalDateTime' in field_type or 'Date' in field_type:
            if 'birth' in field_name_lower:
                return "1990-01-15T00:00:00"
            else:
                return "2024-01-15T10:30:00"
        
        elif 'List' in field_type or 'Array' in field_type:
            return []
        
        else:
            return f"sample_{field_name_lower}"
    
    def _replace_path_variables(self, url: str, path_variables: List[str]) -> str:
        """Replace path variables with sample values"""
        for var in path_variables:
            if 'id' in var.lower():
                url = url.replace(f'{{{var}}}', '1')
            elif 'name' in var.lower():
                url = url.replace(f'{{{var}}}', 'sample')
            else:
                url = url.replace(f'{{{var}}}', f'sample_{var}')
        return url
    
    def _generate_sample_query_params(self, query_params: List[str]) -> str:
        """Generate sample query parameters"""
        params = []
        for param in query_params:
            param_lower = param.lower()
            if 'page' in param_lower:
                params.append(f"{param}=0")
            elif 'size' in param_lower or 'limit' in param_lower:
                params.append(f"{param}=10")
            elif 'query' in param_lower or 'search' in param_lower:
                params.append(f"{param}=searchterm")
            elif 'sort' in param_lower:
                params.append(f"{param}=name")
            elif 'filter' in param_lower:
                params.append(f"{param}=active")
            else:
                params.append(f"{param}=samplevalue")
        
        return '&'.join(params)


class SpringBootApiAgent:
    """Interactive agent for Spring Boot API analysis"""
    
    def __init__(self, project_path: str, base_url: str = "http://localhost:8080"):
        self.analyzer = SpringBootApiAnalyzer(project_path)
        self.analysis_data = None
        self.curl_generator = CurlCommandGenerator(base_url)
    
    def initialize(self):
        """Initialize the agent by analyzing the project"""
        print("🚀 Initializing Spring Boot API Agent...")
        self.analysis_data = self.analyzer.analyze_project()
        print("✅ Analysis complete!")
    
    def get_endpoint_info(self, path: str = None, method: str = None) -> List[Dict[str, Any]]:
        """Get information about specific endpoints"""
        if not self.analysis_data:
            return []
        
        endpoints = self.analysis_data["endpoints"]
        
        if path:
            endpoints = [ep for ep in endpoints if path in ep["path"]]
        
        if method:
            endpoints = [ep for ep in endpoints if ep["method"].upper() == method.upper()]
        
        return endpoints
    
    def get_request_response_info(self, endpoint_path: str, method: str = "GET") -> Dict[str, Any]:
        """Get detailed request/response information for an endpoint"""
        mappings = self.analysis_data["analysis"]["endpoint_request_response_mapping"]
        
        for mapping in mappings:
            if endpoint_path in mapping["endpoint"] and method.upper() in mapping["endpoint"]:
                return mapping
        
        return {}
    
    def get_mandatory_fields(self, model_name: str) -> List[str]:
        """Get mandatory fields for a specific model"""
        if not self.analysis_data or model_name not in self.analysis_data["models"]:
            return []
        
        model = self.analysis_data["models"][model_name]
        return [field["name"] for field in model["fields"] if field["is_mandatory"]]
    
    def analyze_field_change_impact(self, model_name: str, field_name: str, change_type: str) -> Dict[str, Any]:
        """Analyze the impact of adding/removing/modifying a field"""
        impact = {
            "affected_endpoints": [],
            "validation_impact": [],
            "recommendations": []
        }
        
        if not self.analysis_data:
            return impact
        
        # Find endpoints that use this model
        for mapping in self.analysis_data["analysis"]["endpoint_request_response_mapping"]:
            if (mapping["request_body"] == model_name or 
                mapping["response_type"] == model_name):
                impact["affected_endpoints"].append(mapping["endpoint"])
        
        # Analyze validation impact
        if model_name in self.analysis_data["models"]:
            model = self.analysis_data["models"][model_name]
            existing_field = next((f for f in model["fields"] if f["name"] == field_name), None)
            
            if change_type == "add":
                impact["recommendations"].extend([
                    "Consider if the new field should be mandatory",
                    "Update API documentation",
                    "Consider backward compatibility for existing clients"
                ])
            elif change_type == "remove" and existing_field:
                if existing_field["is_mandatory"]:
                    impact["validation_impact"].append("Removing mandatory field - will break validation")
                impact["recommendations"].extend([
                    "Ensure no client dependencies on this field",
                    "Consider deprecation period before removal",
                    "Update API documentation"
                ])
            elif change_type == "modify" and existing_field:
                impact["recommendations"].extend([
                    "Verify type compatibility with existing data",
                    "Update validation rules if necessary",
                    "Test with existing client integrations"
                ])
        
        return impact
    
    def get_curl_command(self, endpoint_path: str, method: str = "GET") -> str:
        """Get CURL command for a specific endpoint"""
        if not self.analysis_data:
            return "No analysis data available"
        
        # Find the endpoint
        endpoint = None
        for ep in self.analysis_data["endpoints"]:
            if ep['path'] == endpoint_path and ep['method'].upper() == method.upper():
                endpoint = ep
                break
        
        if not endpoint:
            return f"Endpoint not found: {method} {endpoint_path}"
        
        graphql_schema_data = asdict(self.analyzer.graphql_schema) if self.analyzer.graphql_schema else None
        return self.curl_generator.generate_curl_command(endpoint, self.analysis_data["models"], graphql_schema_data)
    
    def get_all_curl_commands(self) -> Dict[str, str]:
        """Get CURL commands for all endpoints"""
        if not self.analysis_data:
            return {}
        
        curl_commands = {}
        graphql_schema_data = asdict(self.analyzer.graphql_schema) if self.analyzer.graphql_schema else None
        
        for endpoint in self.analysis_data["endpoints"]:
            endpoint_key = f"{endpoint['method']} {endpoint['path']}"
            if endpoint.get('endpoint_type') == 'GraphQL':
                endpoint_key = f"GraphQL {endpoint.get('graphql_operation', 'query')} {endpoint['method_name']}"
            
            curl_commands[endpoint_key] = self.curl_generator.generate_curl_command(
                endpoint, self.analysis_data["models"], graphql_schema_data
            )
        
        return curl_commands

    def generate_api_documentation(self) -> str:
        """Generate comprehensive API documentation"""
        if not self.analysis_data:
            return "No analysis data available"
        
        doc = []
        doc.append("# Spring Boot API Documentation\n")
        
        # Summary
        summary = self.analysis_data["summary"]
        doc.append(f"## Summary")
        doc.append(f"- **Total Endpoints:** {summary['total_endpoints']}")
        doc.append(f"- **Total Models:** {summary['total_models']}")
        doc.append(f"- **Endpoints by Method:** {summary['endpoints_by_method']}\n")
        
        # Endpoints
        doc.append("## API Endpoints\n")
        for endpoint in self.analysis_data["endpoints"]:
            doc.append(f"### {endpoint['method']} {endpoint['path']}")
            doc.append(f"- **Controller:** {endpoint['controller_class']}")
            doc.append(f"- **Method:** {endpoint['method_name']}")
            
            if endpoint['request_body_type']:
                doc.append(f"- **Request Body:** {endpoint['request_body_type']}")
            
            if endpoint['response_type']:
                doc.append(f"- **Response Type:** {endpoint['response_type']}")
            
            if endpoint['path_variables']:
                doc.append(f"- **Path Variables:** {', '.join(endpoint['path_variables'])}")
            
            if endpoint['query_parameters']:
                doc.append(f"- **Query Parameters:** {', '.join(endpoint['query_parameters'])}")
            
            # Add CURL command
            curl_command = self.curl_generator.generate_curl_command(endpoint, self.analysis_data["models"])
            doc.append(f"\n**CURL Command:**")
            doc.append("```bash")
            doc.append(curl_command)
            doc.append("```")
            
            doc.append("")
        
        # Models
        doc.append("## Data Models\n")
        for model_name, model in self.analysis_data["models"].items():
            doc.append(f"### {model_name}")
            doc.append(f"- **Package:** {model['package']}")
            
            if model['parent_class']:
                doc.append(f"- **Extends:** {model['parent_class']}")
            
            doc.append("- **Fields:**")
            for field in model['fields']:
                mandatory = " (Mandatory)" if field['is_mandatory'] else ""
                validations = f" - Validations: {', '.join(field['validation_rules'])}" if field['validation_rules'] else ""
                doc.append(f"  - `{field['name']}`: {field['type']}{mandatory}{validations}")
            
            doc.append("")
        
        # CURL Commands Section
        doc.append("## CURL Commands\n")
        doc.append("Ready-to-use CURL commands for all endpoints:\n")
        
        curl_commands = self.get_all_curl_commands()
        for endpoint_key, curl_command in curl_commands.items():
            doc.append(f"### {endpoint_key}")
            doc.append("```bash")
            doc.append(curl_command)
            doc.append("```\n")
        
        return "\n".join(doc)


def main():
    """Main function for command-line usage"""
    parser = argparse.ArgumentParser(description="Spring Boot API Analyzer Agent")
    parser.add_argument("project_path", help="Path to the Spring Boot project")
    parser.add_argument("--output", "-o", help="Output file for the analysis report")
    parser.add_argument("--format", choices=["json", "markdown"], default="json", 
                       help="Output format (json or markdown)")
    
    args = parser.parse_args()
    
    # Initialize and run analysis
    agent = SpringBootApiAgent(args.project_path)
    agent.initialize()
    
    if args.format == "markdown":
        output = agent.generate_api_documentation()
    else:
        output = json.dumps(agent.analysis_data, indent=2)
    
    if args.output:
        with open(args.output, 'w') as f:
            f.write(output)
        print(f"📄 Analysis saved to {args.output}")
    else:
        print(output)


if __name__ == "__main__":
    main()