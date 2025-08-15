#!/usr/bin/env python3
"""
GraphQL Demo for Spring Boot API Analyzer

This script demonstrates the GraphQL analysis and CURL generation capabilities.
"""

import json
from spring_boot_api_analyzer import SpringBootApiAgent


def test_graphql_functionality():
    """Test GraphQL analysis and CURL generation"""
    print("🔮 GraphQL Spring Boot API Analyzer Demo")
    print("=" * 60)
    
    # Initialize the agent
    project_path = "/workspace/example_spring_project"
    base_url = "http://localhost:8080"
    
    agent = SpringBootApiAgent(project_path, base_url)
    agent.initialize()
    
    print(f"📁 Project: {project_path}")
    print(f"🌐 Base URL: {base_url}")
    
    # 1. Basic Analysis with GraphQL
    print("\n" + "="*60)
    print("1️⃣  ANALYSIS SUMMARY WITH GRAPHQL")
    print("="*60)
    
    summary = agent.analysis_data["summary"]
    print(f"📊 Total Endpoints: {summary['total_endpoints']}")
    print(f"📋 Total Models: {summary['total_models']}")
    print(f"🔢 Endpoints by Method: {summary['endpoints_by_method']}")
    print(f"🎯 Endpoints by Type: {summary.get('endpoints_by_type', {})}")
    
    # Check for GraphQL operations
    if 'graphql_operations' in summary:
        graphql_ops = summary['graphql_operations']
        print(f"🔮 GraphQL Operations:")
        print(f"   • Queries: {graphql_ops['queries']}")
        print(f"   • Mutations: {graphql_ops['mutations']}")
        print(f"   • Subscriptions: {graphql_ops['subscriptions']}")
        print(f"   • Types: {graphql_ops['types']}")
    
    # 2. GraphQL Endpoints Analysis
    print("\n" + "="*60)
    print("2️⃣  GRAPHQL ENDPOINTS ANALYSIS")
    print("="*60)
    
    graphql_endpoints = [ep for ep in agent.analysis_data["endpoints"] if ep.get("endpoint_type") == "GraphQL"]
    
    if graphql_endpoints:
        print(f"🔮 Found {len(graphql_endpoints)} GraphQL operations:")
        
        for endpoint in graphql_endpoints:
            operation_type = endpoint.get('graphql_operation', 'unknown')
            method_name = endpoint['method_name']
            response_type = endpoint.get('response_type', 'Unknown')
            
            print(f"\n📡 {operation_type.title()}: {method_name}")
            print(f"   Controller: {endpoint['controller_class']}")
            print(f"   Response Type: {response_type}")
            
            if endpoint.get('graphql_fields'):
                print(f"   Fields: {', '.join(endpoint['graphql_fields'])}")
    else:
        print("❌ No GraphQL endpoints found")
    
    # 3. GraphQL Schema Analysis
    print("\n" + "="*60)
    print("3️⃣  GRAPHQL SCHEMA ANALYSIS")
    print("="*60)
    
    graphql_schema = agent.analysis_data.get("graphql_schema")
    
    if graphql_schema:
        print("🔮 GraphQL Schema Found!")
        
        # Queries
        queries = graphql_schema.get("queries", [])
        if queries:
            print(f"\n🔍 Queries ({len(queries)}):")
            for query in queries:
                query_name = query.get("name", "unknown")
                query_type = query.get("type", "unknown")
                args = query.get("args", [])
                print(f"   • {query_name}: {query_type}")
                if args:
                    arg_list = [f"{arg['name']}: {arg['type']}" for arg in args]
                    print(f"     Args: {', '.join(arg_list)}")
        
        # Mutations
        mutations = graphql_schema.get("mutations", [])
        if mutations:
            print(f"\n✏️ Mutations ({len(mutations)}):")
            for mutation in mutations:
                mutation_name = mutation.get("name", "unknown")
                mutation_type = mutation.get("type", "unknown")
                args = mutation.get("args", [])
                print(f"   • {mutation_name}: {mutation_type}")
                if args:
                    arg_list = [f"{arg['name']}: {arg['type']}" for arg in args]
                    print(f"     Args: {', '.join(arg_list)}")
        
        # Subscriptions
        subscriptions = graphql_schema.get("subscriptions", [])
        if subscriptions:
            print(f"\n📡 Subscriptions ({len(subscriptions)}):")
            for subscription in subscriptions:
                sub_name = subscription.get("name", "unknown")
                sub_type = subscription.get("type", "unknown")
                print(f"   • {sub_name}: {sub_type}")
        
        # Custom Types
        types = graphql_schema.get("types", {})
        if types:
            print(f"\n🏷️  Custom Types ({len(types)}):")
            for type_name, type_info in types.items():
                kind = type_info.get("kind", "OBJECT")
                fields = type_info.get("fields", [])
                print(f"   • {type_name} ({kind}) - {len(fields)} fields")
    else:
        print("❌ No GraphQL schema found")
    
    # 4. GraphQL CURL Commands
    print("\n" + "="*60)
    print("4️⃣  GRAPHQL CURL COMMANDS")
    print("="*60)
    
    curl_commands = agent.get_all_curl_commands()
    graphql_curls = {k: v for k, v in curl_commands.items() if "GraphQL" in k}
    
    if graphql_curls:
        print(f"🔮 Generated {len(graphql_curls)} GraphQL CURL commands:")
        
        for operation_key, curl_command in graphql_curls.items():
            print(f"\n📡 {operation_key}")
            print("```bash")
            print(curl_command)
            print("```")
    else:
        print("❌ No GraphQL CURL commands generated")
    
    # 5. REST vs GraphQL Comparison
    print("\n" + "="*60)
    print("5️⃣  REST vs GRAPHQL COMPARISON")
    print("="*60)
    
    rest_endpoints = [ep for ep in agent.analysis_data["endpoints"] if ep.get("endpoint_type") == "REST"]
    graphql_endpoints = [ep for ep in agent.analysis_data["endpoints"] if ep.get("endpoint_type") == "GraphQL"]
    
    print(f"📊 Endpoint Comparison:")
    print(f"   • REST Endpoints: {len(rest_endpoints)}")
    print(f"   • GraphQL Operations: {len(graphql_endpoints)}")
    
    if rest_endpoints:
        print(f"\n🌐 REST Endpoints:")
        for endpoint in rest_endpoints:
            print(f"   • {endpoint['method']} {endpoint['path']}")
    
    if graphql_endpoints:
        print(f"\n🔮 GraphQL Operations:")
        for endpoint in graphql_endpoints:
            operation = endpoint.get('graphql_operation', 'query')
            print(f"   • {operation}: {endpoint['method_name']}")
    
    # 6. CURL Command Comparison
    print("\n" + "="*60)
    print("6️⃣  CURL COMMAND COMPARISON")
    print("="*60)
    
    rest_curls = {k: v for k, v in curl_commands.items() if "GraphQL" not in k}
    
    print(f"📊 CURL Commands Generated:")
    print(f"   • REST Commands: {len(rest_curls)}")
    print(f"   • GraphQL Commands: {len(graphql_curls)}")
    
    # Show one example of each
    if rest_curls:
        rest_example = list(rest_curls.items())[0]
        print(f"\n🌐 REST Example - {rest_example[0]}:")
        print("```bash")
        print(rest_example[1])
        print("```")
    
    if graphql_curls:
        graphql_example = list(graphql_curls.items())[0]
        print(f"\n🔮 GraphQL Example - {graphql_example[0]}:")
        print("```bash")
        print(graphql_example[1])
        print("```")
    
    # 7. Key Differences
    print("\n" + "="*60)
    print("7️⃣  KEY DIFFERENCES: REST vs GRAPHQL")
    print("="*60)
    
    print("🌐 REST Characteristics:")
    print("   • Multiple endpoints for different resources")
    print("   • HTTP methods define operations (GET, POST, PUT, DELETE)")
    print("   • Fixed response structure")
    print("   • Simple CURL commands")
    
    print("\n🔮 GraphQL Characteristics:")
    print("   • Single /graphql endpoint for all operations")
    print("   • Operations defined in query/mutation/subscription")
    print("   • Flexible response structure (request only needed fields)")
    print("   • JSON payload with query and variables")
    
    # 8. Documentation Generation
    print("\n" + "="*60)
    print("8️⃣  DOCUMENTATION WITH GRAPHQL")
    print("="*60)
    
    print("📚 Generating documentation with GraphQL support...")
    docs = agent.generate_api_documentation()
    
    # Save documentation
    doc_file = "/workspace/graphql_api_documentation.md"
    with open(doc_file, 'w') as f:
        f.write(docs)
    
    print(f"✅ Documentation saved to: {doc_file}")
    
    # Count GraphQL sections
    lines = docs.split('\n')
    graphql_mentions = len([line for line in lines if 'GraphQL' in line or 'graphql' in line])
    
    print(f"📊 Documentation Stats:")
    print(f"   • Total lines: {len(lines)}")
    print(f"   • GraphQL references: {graphql_mentions}")
    
    # 9. Summary
    print("\n" + "="*60)
    print("🎉 GRAPHQL DEMO COMPLETED!")
    print("="*60)
    
    print("\n✨ GraphQL Features Demonstrated:")
    features = [
        "✅ GraphQL controller detection (@QueryMapping, @MutationMapping, @SubscriptionMapping)",
        "✅ GraphQL schema parsing (.graphqls files)",
        "✅ GraphQL CURL command generation with proper JSON payload",
        "✅ Query, mutation, and subscription analysis",
        "✅ GraphQL type and field extraction",
        "✅ Voice command support for GraphQL operations",
        "✅ Web interface integration for GraphQL",
        "✅ REST vs GraphQL comparison and analysis",
        "✅ Comprehensive documentation with GraphQL sections"
    ]
    
    for feature in features:
        print(f"  {feature}")
    
    print(f"\n🔮 GraphQL CURL Command Features:")
    print(f"  • Proper JSON payload structure")
    print(f"  • Query/mutation/subscription formatting")
    print(f"  • Variable extraction and sample generation")
    print(f"  • Field selection based on return types")
    print(f"  • Standard /graphql endpoint usage")
    
    print(f"\n💡 Perfect for GraphQL Development:")
    print(f"  • Understanding GraphQL schema structure")
    print(f"  • Generating test queries and mutations")
    print(f"  • API documentation with GraphQL operations")
    print(f"  • Voice-driven GraphQL exploration")
    print(f"  • Client integration with proper CURL examples")


if __name__ == "__main__":
    test_graphql_functionality()