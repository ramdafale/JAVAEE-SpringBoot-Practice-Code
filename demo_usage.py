#!/usr/bin/env python3
"""
Demo Usage Script for Spring Boot API Analyzer

This script demonstrates how to use the Spring Boot API Analyzer programmatically
to analyze a Spring Boot project and extract various types of information.
"""

import json
import sys
from pathlib import Path
from spring_boot_api_analyzer import SpringBootApiAgent


def demo_basic_analysis():
    """Demonstrate basic analysis capabilities"""
    print("🚀 Spring Boot API Analyzer Demo")
    print("=" * 50)
    
    # Initialize the agent with the example project
    project_path = "/workspace/example_spring_project"
    agent = SpringBootApiAgent(project_path)
    
    print(f"📁 Analyzing project: {project_path}")
    agent.initialize()
    
    # Get basic summary
    summary = agent.analysis_data["summary"]
    print(f"\n📊 Project Summary:")
    print(f"   • Total Endpoints: {summary['total_endpoints']}")
    print(f"   • Total Models: {summary['total_models']}")
    print(f"   • Endpoints by Method: {summary['endpoints_by_method']}")
    
    return agent


def demo_endpoint_analysis(agent):
    """Demonstrate endpoint analysis"""
    print("\n🌐 Endpoint Analysis")
    print("-" * 30)
    
    # Get all endpoints
    endpoints = agent.get_endpoint_info()
    
    for endpoint in endpoints:
        print(f"\n🔗 {endpoint['method']} {endpoint['path']}")
        print(f"   Controller: {endpoint['controller_class']}.{endpoint['method_name']}")
        
        if endpoint['request_body_type']:
            print(f"   Request Body: {endpoint['request_body_type']}")
        
        if endpoint['response_type']:
            print(f"   Response: {endpoint['response_type']}")
        
        if endpoint['path_variables']:
            print(f"   Path Variables: {', '.join(endpoint['path_variables'])}")
        
        if endpoint['query_parameters']:
            print(f"   Query Parameters: {', '.join(endpoint['query_parameters'])}")


def demo_model_analysis(agent):
    """Demonstrate model analysis"""
    print("\n🏷️  Model Analysis")
    print("-" * 25)
    
    models = agent.analysis_data["models"]
    
    for model_name, model_info in models.items():
        print(f"\n📋 {model_name}")
        print(f"   Package: {model_info['package']}")
        print(f"   Fields: {len(model_info['fields'])}")
        
        # Show mandatory fields
        mandatory_fields = [f['name'] for f in model_info['fields'] if f['is_mandatory']]
        if mandatory_fields:
            print(f"   Mandatory Fields: {', '.join(mandatory_fields)}")
        
        # Show validation rules
        validation_count = sum(len(f['validation_rules']) for f in model_info['fields'])
        if validation_count > 0:
            print(f"   Validation Rules: {validation_count} total")


def demo_mandatory_fields_analysis(agent):
    """Demonstrate mandatory fields analysis"""
    print("\n🔒 Mandatory Fields Analysis")
    print("-" * 35)
    
    models = agent.analysis_data["models"]
    
    for model_name in models.keys():
        mandatory_fields = agent.get_mandatory_fields(model_name)
        if mandatory_fields:
            print(f"\n📌 {model_name}:")
            for field in mandatory_fields:
                print(f"   • {field}")


def demo_request_response_mapping(agent):
    """Demonstrate request/response mapping"""
    print("\n📤📥 Request/Response Mapping")
    print("-" * 40)
    
    # Get POST endpoints (which typically have request bodies)
    post_endpoints = agent.get_endpoint_info(method="POST")
    
    for endpoint in post_endpoints:
        endpoint_path = endpoint['path']
        info = agent.get_request_response_info(endpoint_path, "POST")
        
        if info:
            print(f"\n🌐 POST {endpoint_path}")
            
            if info.get('request_body'):
                print(f"   📤 Request Body: {info['request_body']}")
                
                if info.get('request_fields'):
                    print("   📝 Required Fields:")
                    for field in info['request_fields']:
                        if field['is_mandatory']:
                            validations = f" [{', '.join(field['validation_rules'])}]" if field['validation_rules'] else ""
                            print(f"      • {field['name']}: {field['type']}{validations}")
            
            if info.get('response_type'):
                print(f"   📥 Response: {info['response_type']}")


def demo_impact_analysis(agent):
    """Demonstrate impact analysis"""
    print("\n🔍 Impact Analysis Demo")
    print("-" * 30)
    
    # Analyze impact of removing a mandatory field
    model_name = "CreateUserRequest"
    field_name = "email"
    change_type = "remove"
    
    impact = agent.analyze_field_change_impact(model_name, field_name, change_type)
    
    print(f"\n📊 Impact of removing '{field_name}' from {model_name}:")
    
    if impact['affected_endpoints']:
        print("   🌐 Affected Endpoints:")
        for endpoint in impact['affected_endpoints']:
            print(f"      • {endpoint}")
    
    if impact['validation_impact']:
        print("   ⚠️  Validation Impact:")
        for validation in impact['validation_impact']:
            print(f"      • {validation}")
    
    if impact['recommendations']:
        print("   💡 Recommendations:")
        for rec in impact['recommendations']:
            print(f"      • {rec}")
    
    # Analyze impact of adding a new field
    print(f"\n📊 Impact of adding new field 'middleName' to {model_name}:")
    impact_add = agent.analyze_field_change_impact(model_name, "middleName", "add")
    
    if impact_add['recommendations']:
        print("   💡 Recommendations:")
        for rec in impact_add['recommendations']:
            print(f"      • {rec}")


def demo_validation_analysis(agent):
    """Demonstrate validation analysis"""
    print("\n✅ Validation Analysis")
    print("-" * 25)
    
    validation_summary = agent.analysis_data["analysis"]["validation_summary"]
    
    print("📋 Validation Rule Usage:")
    for rule, count in validation_summary["validation_rule_usage"].items():
        print(f"   • {rule}: {count} times")
    
    print(f"\n📊 Models with validation: {validation_summary['models_with_validation']}")


def demo_documentation_generation(agent):
    """Demonstrate documentation generation"""
    print("\n📚 Documentation Generation")
    print("-" * 35)
    
    docs = agent.generate_api_documentation()
    
    # Save to file
    doc_file = "/workspace/generated_api_docs.md"
    with open(doc_file, 'w') as f:
        f.write(docs)
    
    print(f"✅ Documentation generated and saved to: {doc_file}")
    
    # Show preview
    lines = docs.split('\n')
    print("\n📖 Documentation Preview (first 20 lines):")
    print("-" * 50)
    for i, line in enumerate(lines[:20]):
        print(line)
    
    if len(lines) > 20:
        print(f"\n... and {len(lines) - 20} more lines in the file")


def demo_json_export(agent):
    """Demonstrate JSON export"""
    print("\n💾 JSON Export")
    print("-" * 15)
    
    # Export full analysis to JSON
    json_file = "/workspace/analysis_export.json"
    
    with open(json_file, 'w') as f:
        json.dump(agent.analysis_data, f, indent=2)
    
    print(f"✅ Full analysis exported to: {json_file}")
    
    # Show file size
    file_size = Path(json_file).stat().st_size
    print(f"📊 File size: {file_size} bytes")


def demo_specific_queries(agent):
    """Demonstrate specific query capabilities"""
    print("\n🎯 Specific Query Examples")
    print("-" * 35)
    
    # Query 1: Find all GET endpoints
    get_endpoints = agent.get_endpoint_info(method="GET")
    print(f"\n🔍 Found {len(get_endpoints)} GET endpoints:")
    for endpoint in get_endpoints:
        print(f"   • GET {endpoint['path']}")
    
    # Query 2: Find endpoints with path variables
    endpoints_with_path_vars = [
        ep for ep in agent.analysis_data["endpoints"] 
        if ep['path_variables']
    ]
    print(f"\n🔍 Found {len(endpoints_with_path_vars)} endpoints with path variables:")
    for endpoint in endpoints_with_path_vars:
        print(f"   • {endpoint['method']} {endpoint['path']} - Variables: {', '.join(endpoint['path_variables'])}")
    
    # Query 3: Find models with the most validation rules
    models_with_validation = []
    for model_name, model_info in agent.analysis_data["models"].items():
        validation_count = sum(len(f['validation_rules']) for f in model_info['fields'])
        if validation_count > 0:
            models_with_validation.append((model_name, validation_count))
    
    models_with_validation.sort(key=lambda x: x[1], reverse=True)
    print(f"\n🔍 Models ranked by validation rules:")
    for model_name, count in models_with_validation:
        print(f"   • {model_name}: {count} validation rules")


def main():
    """Main demo function"""
    try:
        # Run all demo functions
        agent = demo_basic_analysis()
        demo_endpoint_analysis(agent)
        demo_model_analysis(agent)
        demo_mandatory_fields_analysis(agent)
        demo_request_response_mapping(agent)
        demo_impact_analysis(agent)
        demo_validation_analysis(agent)
        demo_specific_queries(agent)
        demo_documentation_generation(agent)
        demo_json_export(agent)
        
        print("\n🎉 Demo completed successfully!")
        print("\nYou can now:")
        print("• Check the generated documentation: /workspace/generated_api_docs.md")
        print("• Review the JSON export: /workspace/analysis_export.json")
        print("• Run the interactive agent: python interactive_spring_agent.py /workspace/example_spring_project")
        
    except Exception as e:
        print(f"❌ Demo failed with error: {e}")
        import traceback
        traceback.print_exc()
        sys.exit(1)


if __name__ == "__main__":
    main()