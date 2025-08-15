#!/usr/bin/env python3
"""
Test script specifically for CURL command generation functionality
"""

import json
from spring_boot_api_analyzer import SpringBootApiAgent


def test_curl_generation():
    """Test CURL command generation"""
    print("🧪 Testing CURL Command Generation")
    print("=" * 50)
    
    # Initialize the agent
    project_path = "/workspace/example_spring_project"
    base_url = "http://localhost:8080"
    
    agent = SpringBootApiAgent(project_path, base_url)
    agent.initialize()
    
    print(f"📁 Project: {project_path}")
    print(f"🌐 Base URL: {base_url}")
    
    # Show summary
    summary = agent.analysis_data["summary"]
    print(f"\n📊 Found {summary['total_endpoints']} endpoints")
    
    # Show all endpoints first
    print("\n🔍 All Detected Endpoints:")
    for endpoint in agent.analysis_data["endpoints"]:
        print(f"  • {endpoint['method']} {endpoint['path']}")
    
    # Generate CURL commands for all endpoints
    print("\n📡 CURL Commands:")
    print("=" * 30)
    
    curl_commands = agent.get_all_curl_commands()
    
    for endpoint_key, curl_command in curl_commands.items():
        print(f"\n🌐 {endpoint_key}")
        print("```bash")
        print(curl_command)
        print("```")
    
    # Test specific endpoint CURL generation
    print("\n🎯 Testing Specific Endpoint CURL Generation:")
    print("-" * 50)
    
    # Test GET endpoint
    get_curl = agent.get_curl_command("/api/users/{id}", "GET")
    print(f"\n📥 GET /api/users/{{id}}:")
    print("```bash")
    print(get_curl)
    print("```")
    
    # Test POST endpoint (should have request body)
    post_curl = agent.get_curl_command("/api/users", "POST")
    print(f"\n📤 POST /api/users:")
    print("```bash")
    print(post_curl)
    print("```")
    
    # Show sample request body analysis
    print(f"\n🔍 Request Body Analysis for POST /api/users:")
    mappings = agent.analysis_data["analysis"]["endpoint_request_response_mapping"]
    
    for mapping in mappings:
        if "POST /api/users" in mapping["endpoint"]:
            print(f"  Request Body Type: {mapping.get('request_body')}")
            if mapping.get('request_fields'):
                print("  Required Fields:")
                for field in mapping['request_fields']:
                    if field['is_mandatory']:
                        print(f"    • {field['name']}: {field['type']}")
            break
    
    print("\n✅ CURL generation test completed!")


if __name__ == "__main__":
    test_curl_generation()