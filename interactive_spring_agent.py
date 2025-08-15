#!/usr/bin/env python3
"""
Interactive Spring Boot API Agent

A user-friendly interface for the Spring Boot API Analyzer that allows
natural language queries about your Spring Boot APIs.
"""

from spring_boot_api_analyzer import SpringBootApiAgent
import json
import sys
from typing import Dict, Any, List


class InteractiveSpringBootAgent:
    """Interactive interface for Spring Boot API analysis"""
    
    def __init__(self, project_path: str, base_url: str = "http://localhost:8080"):
        self.agent = SpringBootApiAgent(project_path, base_url)
        self.project_path = project_path
        self.base_url = base_url
        
    def start(self):
        """Start the interactive session"""
        print("🚀 Starting Interactive Spring Boot API Agent...")
        print("📁 Analyzing project at:", self.project_path)
        
        try:
            self.agent.initialize()
            print("\n✅ Analysis complete! You can now ask questions about your Spring Boot API.\n")
            self._show_help()
            self._interactive_loop()
        except Exception as e:
            print(f"❌ Error initializing agent: {e}")
            sys.exit(1)
    
    def _interactive_loop(self):
        """Main interactive loop"""
        while True:
            try:
                user_input = input("\n🤖 Ask me about your API (or 'help' for commands, 'quit' to exit): ").strip()
                
                if user_input.lower() in ['quit', 'exit', 'q']:
                    print("👋 Goodbye!")
                    break
                elif user_input.lower() in ['help', 'h']:
                    self._show_help()
                elif user_input.lower() == 'summary':
                    self._show_summary()
                elif user_input.lower().startswith('endpoints'):
                    self._handle_endpoints_query(user_input)
                elif user_input.lower().startswith('models'):
                    self._handle_models_query(user_input)
                elif user_input.lower().startswith('mandatory'):
                    self._handle_mandatory_fields_query(user_input)
                elif user_input.lower().startswith('impact'):
                    self._handle_impact_analysis_query(user_input)
                elif user_input.lower().startswith('request'):
                    self._handle_request_response_query(user_input)
                elif user_input.lower().startswith('response'):
                    self._handle_request_response_query(user_input)
                elif user_input.lower().startswith('curl'):
                    self._handle_curl_query(user_input)
                elif user_input.lower() == 'docs':
                    self._generate_documentation()
                else:
                    self._handle_natural_language_query(user_input)
                    
            except KeyboardInterrupt:
                print("\n👋 Goodbye!")
                break
            except Exception as e:
                print(f"❌ Error: {e}")
    
    def _show_help(self):
        """Show available commands"""
        help_text = """
📋 Available Commands:

🔍 General Queries:
  • summary                     - Show project summary
  • endpoints                   - List all API endpoints
  • models                      - List all data models
  • docs                        - Generate full API documentation

🎯 Specific Queries:
  • endpoints <method>          - Filter endpoints by HTTP method (GET, POST, etc.)
  • endpoints <path>            - Find endpoints containing path
  • models <name>               - Get details about a specific model
  • mandatory <model>           - Get mandatory fields for a model
  • request <endpoint>          - Get request info for an endpoint
  • response <endpoint>         - Get response info for an endpoint
  • curl <endpoint> <method>    - Get CURL command for an endpoint
  • curl all                    - Get all CURL commands
  • impact <model> <field> <action> - Analyze impact of field changes

💬 Natural Language:
  You can also ask questions like:
  • "What are the mandatory fields for User model?"
  • "What does the POST /users endpoint expect?"
  • "What happens if I add a new field to UserDTO?"
  • "Show me all GET endpoints"
  • "Give me the CURL command for POST /users"
  • "Show me all CURL commands"

🚪 Exit:
  • quit, exit, q              - Exit the agent
  • help, h                    - Show this help
        """
        print(help_text)
    
    def _show_summary(self):
        """Show project summary"""
        if not self.agent.analysis_data:
            print("❌ No analysis data available")
            return
        
        summary = self.agent.analysis_data["summary"]
        print("\n📊 Project Summary:")
        print(f"  • Total Endpoints: {summary['total_endpoints']}")
        print(f"  • Total Models: {summary['total_models']}")
        print(f"  • Endpoints by Method:")
        for method, count in summary['endpoints_by_method'].items():
            print(f"    - {method}: {count}")
    
    def _handle_endpoints_query(self, query: str):
        """Handle endpoints-related queries"""
        parts = query.split()
        
        if len(parts) == 1:
            # Show all endpoints
            endpoints = self.agent.get_endpoint_info()
            self._display_endpoints(endpoints)
        else:
            filter_term = parts[1].upper()
            
            # Check if it's an HTTP method
            if filter_term in ['GET', 'POST', 'PUT', 'DELETE', 'PATCH']:
                endpoints = self.agent.get_endpoint_info(method=filter_term)
                print(f"\n🔍 {filter_term} Endpoints:")
            else:
                # Treat as path filter
                endpoints = self.agent.get_endpoint_info(path=parts[1])
                print(f"\n🔍 Endpoints containing '{parts[1]}':")
            
            self._display_endpoints(endpoints)
    
    def _handle_models_query(self, query: str):
        """Handle models-related queries"""
        parts = query.split()
        
        if len(parts) == 1:
            # Show all models
            if not self.agent.analysis_data:
                print("❌ No analysis data available")
                return
            
            models = self.agent.analysis_data["models"]
            print("\n📋 Data Models:")
            for model_name, model_info in models.items():
                print(f"\n  🏷️  {model_name}")
                print(f"     Package: {model_info['package']}")
                print(f"     Fields: {len(model_info['fields'])}")
                if model_info['parent_class']:
                    print(f"     Extends: {model_info['parent_class']}")
        else:
            # Show specific model
            model_name = parts[1]
            self._display_model_details(model_name)
    
    def _handle_mandatory_fields_query(self, query: str):
        """Handle mandatory fields queries"""
        parts = query.split()
        
        if len(parts) < 2:
            print("❌ Please specify a model name: mandatory <ModelName>")
            return
        
        model_name = parts[1]
        mandatory_fields = self.agent.get_mandatory_fields(model_name)
        
        if mandatory_fields:
            print(f"\n🔒 Mandatory fields for {model_name}:")
            for field in mandatory_fields:
                print(f"  • {field}")
        else:
            print(f"ℹ️  No mandatory fields found for {model_name} (or model doesn't exist)")
    
    def _handle_impact_analysis_query(self, query: str):
        """Handle impact analysis queries"""
        parts = query.split()
        
        if len(parts) < 4:
            print("❌ Usage: impact <ModelName> <fieldName> <add|remove|modify>")
            return
        
        model_name = parts[1]
        field_name = parts[2]
        change_type = parts[3].lower()
        
        if change_type not in ['add', 'remove', 'modify']:
            print("❌ Change type must be: add, remove, or modify")
            return
        
        impact = self.agent.analyze_field_change_impact(model_name, field_name, change_type)
        self._display_impact_analysis(impact, model_name, field_name, change_type)
    
    def _handle_request_response_query(self, query: str):
        """Handle request/response queries"""
        parts = query.split()
        
        if len(parts) < 2:
            print("❌ Please specify an endpoint: request|response <endpoint_path>")
            return
        
        endpoint_path = parts[1]
        method = parts[2] if len(parts) > 2 else "GET"
        
        info = self.agent.get_request_response_info(endpoint_path, method)
        
        if info:
            self._display_request_response_info(info)
        else:
            print(f"❌ No information found for endpoint: {method} {endpoint_path}")
    
    def _handle_curl_query(self, query: str):
        """Handle CURL command queries"""
        parts = query.split()
        
        if len(parts) < 2:
            print("❌ Usage: curl <endpoint_path> <method> OR curl all")
            return
        
        if parts[1].lower() == 'all':
            # Show all CURL commands
            curl_commands = self.agent.get_all_curl_commands()
            if curl_commands:
                print("\n🌐 CURL Commands for All Endpoints:")
                print("=" * 50)
                for endpoint_key, curl_command in curl_commands.items():
                    print(f"\n📡 {endpoint_key}")
                    print("```bash")
                    print(curl_command)
                    print("```")
            else:
                print("❌ No endpoints found")
        else:
            # Show CURL for specific endpoint
            endpoint_path = parts[1]
            method = parts[2] if len(parts) > 2 else "GET"
            
            curl_command = self.agent.get_curl_command(endpoint_path, method)
            
            if "not found" in curl_command.lower():
                print(f"❌ {curl_command}")
            else:
                print(f"\n📡 CURL Command for {method} {endpoint_path}:")
                print("```bash")
                print(curl_command)
                print("```")
                print("\n💡 Tips:")
                print("  • Replace YOUR_TOKEN with actual authorization token")
                print("  • Replace YOUR_VALUE with actual header values")
                print("  • Modify sample values in request body as needed")
                print(f"  • Base URL is set to: {self.base_url}")
    
    def _handle_natural_language_query(self, query: str):
        """Handle natural language queries"""
        query_lower = query.lower()
        
        # Pattern matching for common questions
        if "mandatory" in query_lower and ("field" in query_lower or "required" in query_lower):
            # Extract model name
            words = query.split()
            for word in words:
                if word.endswith("DTO") or word.endswith("Entity") or word[0].isupper():
                    mandatory_fields = self.agent.get_mandatory_fields(word)
                    if mandatory_fields:
                        print(f"\n🔒 Mandatory fields for {word}:")
                        for field in mandatory_fields:
                            print(f"  • {field}")
                        return
            
            print("❌ Could not identify model name in your query")
        
        elif "endpoint" in query_lower and ("expect" in query_lower or "request" in query_lower):
            # Try to extract endpoint info
            if "post" in query_lower:
                endpoints = self.agent.get_endpoint_info(method="POST")
                print("\n📤 POST Endpoints and their expected requests:")
                self._display_endpoints_with_requests(endpoints)
            else:
                print("ℹ️  Please be more specific about which endpoint you're asking about")
        
        elif "get" in query_lower and "endpoint" in query_lower:
            endpoints = self.agent.get_endpoint_info(method="GET")
            print("\n📥 GET Endpoints:")
            self._display_endpoints(endpoints)
        
        elif "add" in query_lower and "field" in query_lower:
            print("ℹ️  To analyze field addition impact, use: impact <ModelName> <fieldName> add")
        
        elif "curl" in query_lower and ("command" in query_lower or "show" in query_lower):
            if "all" in query_lower:
                self._handle_curl_query("curl all")
            else:
                print("ℹ️  To get CURL commands, use: curl <endpoint> <method> or curl all")
        
        else:
            print("🤔 I didn't understand that query. Try 'help' to see available commands.")
    
    def _display_endpoints(self, endpoints: List[Dict[str, Any]]):
        """Display endpoints in a formatted way"""
        if not endpoints:
            print("  No endpoints found")
            return
        
        for endpoint in endpoints:
            print(f"\n  🌐 {endpoint['method']} {endpoint['path']}")
            print(f"     Controller: {endpoint['controller_class']}.{endpoint['method_name']}")
            
            if endpoint['request_body_type']:
                print(f"     Request Body: {endpoint['request_body_type']}")
            
            if endpoint['response_type']:
                print(f"     Response: {endpoint['response_type']}")
            
            if endpoint['path_variables']:
                print(f"     Path Variables: {', '.join(endpoint['path_variables'])}")
            
            if endpoint['query_parameters']:
                print(f"     Query Params: {', '.join(endpoint['query_parameters'])}")
    
    def _display_endpoints_with_requests(self, endpoints: List[Dict[str, Any]]):
        """Display endpoints with detailed request information"""
        mappings = self.agent.analysis_data["analysis"]["endpoint_request_response_mapping"]
        
        for endpoint in endpoints:
            endpoint_key = f"{endpoint['method']} {endpoint['path']}"
            
            # Find corresponding mapping
            mapping = next((m for m in mappings if m["endpoint"] == endpoint_key), None)
            
            print(f"\n  🌐 {endpoint_key}")
            
            if mapping and mapping.get("request_fields"):
                print("     Expected Request Fields:")
                for field in mapping["request_fields"]:
                    mandatory = " (Required)" if field["is_mandatory"] else ""
                    print(f"       • {field['name']}: {field['type']}{mandatory}")
            elif endpoint['request_body_type']:
                print(f"     Request Body Type: {endpoint['request_body_type']}")
            else:
                print("     No request body expected")
    
    def _display_model_details(self, model_name: str):
        """Display detailed information about a model"""
        if not self.agent.analysis_data or model_name not in self.agent.analysis_data["models"]:
            print(f"❌ Model '{model_name}' not found")
            return
        
        model = self.agent.analysis_data["models"][model_name]
        
        print(f"\n🏷️  Model: {model_name}")
        print(f"   Package: {model['package']}")
        
        if model['parent_class']:
            print(f"   Extends: {model['parent_class']}")
        
        if model['annotations']:
            print(f"   Annotations: {', '.join(model['annotations'])}")
        
        print("   Fields:")
        for field in model['fields']:
            mandatory = " (Mandatory)" if field['is_mandatory'] else ""
            validations = f" [{', '.join(field['validation_rules'])}]" if field['validation_rules'] else ""
            default = f" = {field['default_value']}" if field['default_value'] else ""
            
            print(f"     • {field['name']}: {field['type']}{mandatory}{validations}{default}")
    
    def _display_request_response_info(self, info: Dict[str, Any]):
        """Display request/response information"""
        print(f"\n🌐 Endpoint: {info['endpoint']}")
        
        if info.get('request_body'):
            print(f"\n📤 Request Body Type: {info['request_body']}")
            
            if info.get('request_fields'):
                print("   Request Fields:")
                for field in info['request_fields']:
                    mandatory = " (Required)" if field['is_mandatory'] else ""
                    validations = f" [{', '.join(field['validation_rules'])}]" if field['validation_rules'] else ""
                    print(f"     • {field['name']}: {field['type']}{mandatory}{validations}")
        
        if info.get('response_type'):
            print(f"\n📥 Response Type: {info['response_type']}")
        
        if info.get('path_variables'):
            print(f"\n🛤️  Path Variables: {', '.join(info['path_variables'])}")
        
        if info.get('query_parameters'):
            print(f"\n❓ Query Parameters: {', '.join(info['query_parameters'])}")
        
        if info.get('request_headers'):
            print(f"\n📋 Required Headers: {', '.join(info['request_headers'])}")
    
    def _display_impact_analysis(self, impact: Dict[str, Any], model_name: str, field_name: str, change_type: str):
        """Display impact analysis results"""
        print(f"\n🔍 Impact Analysis: {change_type.title()} field '{field_name}' in {model_name}")
        
        if impact['affected_endpoints']:
            print("\n🌐 Affected Endpoints:")
            for endpoint in impact['affected_endpoints']:
                print(f"  • {endpoint}")
        else:
            print("\n✅ No endpoints directly affected")
        
        if impact['validation_impact']:
            print("\n⚠️  Validation Impact:")
            for validation in impact['validation_impact']:
                print(f"  • {validation}")
        
        if impact['recommendations']:
            print("\n💡 Recommendations:")
            for rec in impact['recommendations']:
                print(f"  • {rec}")
    
    def _generate_documentation(self):
        """Generate and display API documentation"""
        print("\n📚 Generating API Documentation...")
        docs = self.agent.generate_api_documentation()
        
        # Save to file
        doc_file = "api_documentation.md"
        with open(doc_file, 'w') as f:
            f.write(docs)
        
        print(f"✅ Documentation saved to: {doc_file}")
        print("\nPreview (first 50 lines):")
        print("-" * 50)
        
        lines = docs.split('\n')
        for i, line in enumerate(lines[:50]):
            print(line)
        
        if len(lines) > 50:
            print(f"\n... and {len(lines) - 50} more lines in the file")


def main():
    """Main function"""
    if len(sys.argv) < 2:
        print("Usage: python interactive_spring_agent.py <spring_boot_project_path> [base_url]")
        print("Example: python interactive_spring_agent.py /path/to/project http://localhost:8080")
        sys.exit(1)
    
    project_path = sys.argv[1]
    base_url = sys.argv[2] if len(sys.argv) > 2 else "http://localhost:8080"
    
    agent = InteractiveSpringBootAgent(project_path, base_url)
    agent.start()


if __name__ == "__main__":
    main()