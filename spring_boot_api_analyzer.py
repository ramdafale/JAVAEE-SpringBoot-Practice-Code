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
    
    def __post_init__(self):
        if self.path_variables is None:
            self.path_variables = []
        if self.query_parameters is None:
            self.query_parameters = []
        if self.request_headers is None:
            self.request_headers = []


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
        
        # Generate comprehensive report
        return self._generate_report()
    
    def _analyze_java_file(self, file_path: Path):
        """Analyze a single Java file"""
        try:
            with open(file_path, 'r', encoding='utf-8') as f:
                content = f.read()
            
            # Determine file type and analyze accordingly
            if self._is_controller(content):
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
        return {
            "summary": {
                "total_endpoints": len(self.endpoints),
                "total_models": len(self.models),
                "endpoints_by_method": self._count_endpoints_by_method(),
            },
            "endpoints": [asdict(endpoint) for endpoint in self.endpoints],
            "models": {name: asdict(model) for name, model in self.models.items()},
            "analysis": {
                "mandatory_fields_by_model": self._get_mandatory_fields_by_model(),
                "validation_summary": self._get_validation_summary(),
                "endpoint_request_response_mapping": self._get_endpoint_mappings()
            }
        }
    
    def _count_endpoints_by_method(self) -> Dict[str, int]:
        """Count endpoints by HTTP method"""
        counts = {}
        for endpoint in self.endpoints:
            counts[endpoint.method] = counts.get(endpoint.method, 0) + 1
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


class SpringBootApiAgent:
    """Interactive agent for Spring Boot API analysis"""
    
    def __init__(self, project_path: str):
        self.analyzer = SpringBootApiAnalyzer(project_path)
        self.analysis_data = None
    
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