#!/usr/bin/env python3
"""
Voice-Enabled Spring Boot API Analyzer

This module adds voice interaction capabilities to the Spring Boot API analyzer,
allowing users to speak their queries and receive audio responses with comprehensive
API information including CURL commands, field details, and internal integrations.
"""

import speech_recognition as sr
import pyttsx3
import threading
import queue
import json
import re
from typing import Dict, List, Any, Optional
from spring_boot_api_analyzer import SpringBootApiAgent
import time


class VoiceSpringBootAgent:
    """Voice-enabled Spring Boot API analyzer"""
    
    def __init__(self, project_path: str, base_url: str = "http://localhost:8080"):
        self.agent = SpringBootApiAgent(project_path, base_url)
        self.project_path = project_path
        self.base_url = base_url
        
        # Initialize speech recognition
        self.recognizer = sr.Recognizer()
        self.microphone = sr.Microphone()
        
        # Initialize text-to-speech
        self.tts_engine = pyttsx3.init()
        self.tts_engine.setProperty('rate', 150)  # Speed of speech
        self.tts_engine.setProperty('volume', 0.8)  # Volume level
        
        # Voice processing queue
        self.voice_queue = queue.Queue()
        self.is_listening = False
        
        # Calibrate microphone
        self._calibrate_microphone()
    
    def _calibrate_microphone(self):
        """Calibrate microphone for ambient noise"""
        print("🎤 Calibrating microphone for ambient noise...")
        with self.microphone as source:
            self.recognizer.adjust_for_ambient_noise(source, duration=1)
        print("✅ Microphone calibrated!")
    
    def initialize(self):
        """Initialize the agent and analysis"""
        print("🚀 Initializing Voice-Enabled Spring Boot API Agent...")
        self.agent.initialize()
        
        # Analyze internal API integrations
        self._analyze_internal_integrations()
        
        self.speak("Spring Boot API analyzer initialized successfully. You can now ask questions using voice commands.")
        print("✅ Voice agent ready! Say 'help' to hear available commands.")
    
    def speak(self, text: str):
        """Convert text to speech"""
        print(f"🔊 Speaking: {text}")
        self.tts_engine.say(text)
        self.tts_engine.runAndWait()
    
    def listen_for_command(self) -> Optional[str]:
        """Listen for voice command"""
        try:
            print("🎤 Listening... (speak now)")
            with self.microphone as source:
                # Listen for audio with timeout
                audio = self.recognizer.listen(source, timeout=5, phrase_time_limit=10)
            
            print("🔄 Processing speech...")
            # Recognize speech using Google Speech Recognition
            command = self.recognizer.recognize_google(audio).lower()
            print(f"📝 Recognized: '{command}'")
            return command
            
        except sr.WaitTimeoutError:
            print("⏰ Listening timeout - no speech detected")
            return None
        except sr.UnknownValueError:
            print("❌ Could not understand audio")
            self.speak("Sorry, I couldn't understand what you said. Please try again.")
            return None
        except sr.RequestError as e:
            print(f"❌ Speech recognition error: {e}")
            self.speak("Sorry, there was an error with speech recognition.")
            return None
    
    def start_voice_interaction(self):
        """Start the voice interaction loop"""
        self.speak("Voice interaction started. Say 'stop listening' to exit.")
        
        while True:
            try:
                command = self.listen_for_command()
                
                if command is None:
                    continue
                
                if self._is_exit_command(command):
                    self.speak("Goodbye!")
                    break
                
                # Process the voice command
                response = self.process_voice_command(command)
                
                if response:
                    self.speak(response)
                
            except KeyboardInterrupt:
                self.speak("Voice interaction stopped.")
                break
            except Exception as e:
                print(f"❌ Error in voice interaction: {e}")
                self.speak("Sorry, there was an error processing your request.")
    
    def _is_exit_command(self, command: str) -> bool:
        """Check if command is an exit command"""
        exit_phrases = [
            "stop listening", "exit", "quit", "goodbye", 
            "stop", "end session", "bye"
        ]
        return any(phrase in command for phrase in exit_phrases)
    
    def process_voice_command(self, command: str) -> str:
        """Process voice command and return response"""
        command = command.lower().strip()
        
        try:
            # Help command
            if "help" in command:
                return self._get_help_response()
            
            # Summary command
            elif "summary" in command or "overview" in command:
                return self._get_summary_response()
            
            # Endpoint queries
            elif "endpoint" in command or "api" in command:
                return self._process_endpoint_query(command)
            
            # CURL command queries
            elif "curl" in command or "command" in command:
                return self._process_curl_query(command)
            
            # Field queries
            elif "field" in command or "parameter" in command:
                return self._process_field_query(command)
            
            # Model queries
            elif "model" in command or "dto" in command:
                return self._process_model_query(command)
            
            # Integration queries
            elif "integration" in command or "internal" in command:
                return self._process_integration_query(command)
            
            # Mandatory fields
            elif "mandatory" in command or "required" in command:
                return self._process_mandatory_fields_query(command)
            
            # Impact analysis
            elif "impact" in command or "change" in command:
                return self._process_impact_query(command)
            
            else:
                return "I didn't understand that command. Say 'help' to hear available commands."
        
        except Exception as e:
            print(f"❌ Error processing command: {e}")
            return "Sorry, there was an error processing your request."
    
    def _get_help_response(self) -> str:
        """Get help response"""
        return """Here are the available voice commands:
        
        Say 'summary' for project overview.
        Say 'show endpoints' to list all API endpoints.
        Say 'curl command for post users' to get curl commands.
        Say 'fields for user model' to see model fields.
        Say 'mandatory fields for create user request' for required fields.
        Say 'internal integrations' to see API dependencies.
        Say 'impact of removing email field' for change analysis.
        Say 'stop listening' to exit.
        """
    
    def _get_summary_response(self) -> str:
        """Get project summary response"""
        if not self.agent.analysis_data:
            return "No analysis data available."
        
        summary = self.agent.analysis_data["summary"]
        return f"""Project summary: Found {summary['total_endpoints']} API endpoints and {summary['total_models']} data models. 
        Endpoints by method: {', '.join([f'{method} {count}' for method, count in summary['endpoints_by_method'].items()])}."""
    
    def _process_endpoint_query(self, command: str) -> str:
        """Process endpoint-related queries"""
        if "show" in command or "list" in command:
            endpoints = self.agent.get_endpoint_info()
            if not endpoints:
                return "No endpoints found."
            
            endpoint_list = []
            for ep in endpoints[:5]:  # Limit to first 5 for voice
                endpoint_list.append(f"{ep['method']} {ep['path']}")
            
            response = f"Found {len(endpoints)} endpoints. Here are the first few: " + ", ".join(endpoint_list)
            if len(endpoints) > 5:
                response += f". And {len(endpoints) - 5} more."
            
            return response
        
        # Extract HTTP method from command
        method = self._extract_http_method(command)
        if method:
            endpoints = self.agent.get_endpoint_info(method=method)
            if endpoints:
                endpoint_list = [f"{ep['path']}" for ep in endpoints[:3]]
                return f"Found {len(endpoints)} {method} endpoints: " + ", ".join(endpoint_list)
            else:
                return f"No {method} endpoints found."
        
        return "Please specify what you want to know about endpoints."
    
    def _process_curl_query(self, command: str) -> str:
        """Process CURL command queries"""
        if "all" in command:
            curl_commands = self.agent.get_all_curl_commands()
            return f"Generated {len(curl_commands)} curl commands. Check the console for details."
        
        # Extract endpoint and method from command
        endpoint_path, method = self._extract_endpoint_from_command(command)
        
        if endpoint_path and method:
            curl_command = self.agent.get_curl_command(endpoint_path, method)
            if "not found" not in curl_command.lower():
                # Provide a summary instead of the full curl command for voice
                return f"Curl command generated for {method} {endpoint_path}. The command includes proper headers and sample request body. Check the console for the full command."
            else:
                return f"Endpoint {method} {endpoint_path} not found."
        
        return "Please specify the endpoint and method for the curl command."
    
    def _process_field_query(self, command: str) -> str:
        """Process field-related queries"""
        model_name = self._extract_model_name(command)
        
        if model_name:
            if model_name in self.agent.analysis_data.get("models", {}):
                model = self.agent.analysis_data["models"][model_name]
                field_count = len(model["fields"])
                mandatory_count = len([f for f in model["fields"] if f["is_mandatory"]])
                
                return f"Model {model_name} has {field_count} fields, with {mandatory_count} mandatory fields."
            else:
                return f"Model {model_name} not found."
        
        return "Please specify which model you want to know about."
    
    def _process_model_query(self, command: str) -> str:
        """Process model-related queries"""
        if "show" in command or "list" in command:
            models = self.agent.analysis_data.get("models", {})
            if models:
                model_names = list(models.keys())[:3]  # Limit for voice
                return f"Found {len(models)} models: " + ", ".join(model_names)
            else:
                return "No models found."
        
        model_name = self._extract_model_name(command)
        if model_name and model_name in self.agent.analysis_data.get("models", {}):
            model = self.agent.analysis_data["models"][model_name]
            return f"Model {model_name} is in package {model['package']} and has {len(model['fields'])} fields."
        
        return "Please specify which model you want to know about."
    
    def _process_integration_query(self, command: str) -> str:
        """Process internal integration queries"""
        integrations = getattr(self, 'internal_integrations', {})
        
        if not integrations:
            return "No internal API integrations detected."
        
        total_integrations = sum(len(calls) for calls in integrations.values())
        return f"Found {total_integrations} internal API calls across {len(integrations)} controllers. Check console for detailed integration map."
    
    def _process_mandatory_fields_query(self, command: str) -> str:
        """Process mandatory fields queries"""
        model_name = self._extract_model_name(command)
        
        if model_name:
            mandatory_fields = self.agent.get_mandatory_fields(model_name)
            if mandatory_fields:
                return f"Mandatory fields for {model_name}: " + ", ".join(mandatory_fields)
            else:
                return f"No mandatory fields found for {model_name}."
        
        return "Please specify which model you want to check for mandatory fields."
    
    def _process_impact_query(self, command: str) -> str:
        """Process impact analysis queries"""
        # Extract model name and field name from command
        model_name = self._extract_model_name(command)
        
        if model_name:
            # Simple impact analysis
            if "remove" in command or "delete" in command:
                change_type = "remove"
            elif "add" in command:
                change_type = "add"
            else:
                change_type = "modify"
            
            # Extract field name (simplified)
            words = command.split()
            field_name = "email"  # Default for demo
            for word in words:
                if word in ["email", "username", "password", "name", "id"]:
                    field_name = word
                    break
            
            impact = self.agent.analyze_field_change_impact(model_name, field_name, change_type)
            
            affected_count = len(impact.get('affected_endpoints', []))
            recommendations_count = len(impact.get('recommendations', []))
            
            return f"Impact analysis for {change_type}ing {field_name} from {model_name}: {affected_count} endpoints affected, {recommendations_count} recommendations provided. Check console for details."
        
        return "Please specify the model and field for impact analysis."
    
    def _extract_http_method(self, command: str) -> Optional[str]:
        """Extract HTTP method from command"""
        methods = ["get", "post", "put", "delete", "patch"]
        for method in methods:
            if method in command:
                return method.upper()
        return None
    
    def _extract_endpoint_from_command(self, command: str) -> tuple:
        """Extract endpoint path and method from command"""
        method = self._extract_http_method(command) or "GET"
        
        # Common endpoint patterns
        if "user" in command:
            if "create" in command or "post" in command:
                return "/api/users", "POST"
            elif "update" in command or "put" in command:
                return "/api/users/{id}", "PUT"
            elif "delete" in command:
                return "/api/users/{id}", "DELETE"
            else:
                return "/api/users/{id}", "GET"
        
        return None, method
    
    def _extract_model_name(self, command: str) -> Optional[str]:
        """Extract model name from command"""
        # Check for common model names
        models = ["UserDTO", "CreateUserRequest", "User"]
        
        for model in models:
            if model.lower() in command:
                return model
        
        # Check for partial matches
        if "user" in command:
            if "create" in command or "request" in command:
                return "CreateUserRequest"
            else:
                return "UserDTO"
        
        return None
    
    def _analyze_internal_integrations(self):
        """Analyze internal API integrations within the codebase"""
        print("🔍 Analyzing internal API integrations...")
        
        self.internal_integrations = {}
        
        # Look for RestTemplate, WebClient, and Feign client usage
        java_files = list(self.agent.analyzer.project_path.rglob("*.java"))
        
        for java_file in java_files:
            try:
                with open(java_file, 'r', encoding='utf-8') as f:
                    content = f.read()
                
                integrations = []
                
                # Look for RestTemplate calls
                rest_template_calls = re.findall(
                    r'restTemplate\.(get|post|put|delete|exchange)\s*\([^)]*"([^"]+)"', 
                    content, re.IGNORECASE
                )
                for method, url in rest_template_calls:
                    integrations.append({
                        'type': 'RestTemplate',
                        'method': method.upper(),
                        'url': url,
                        'line': self._find_line_number(content, f'restTemplate.{method}')
                    })
                
                # Look for WebClient calls
                webclient_calls = re.findall(
                    r'webClient\.(get|post|put|delete)\s*\(\)\s*\.uri\s*\([^)]*"([^"]+)"', 
                    content, re.IGNORECASE
                )
                for method, url in webclient_calls:
                    integrations.append({
                        'type': 'WebClient',
                        'method': method.upper(),
                        'url': url,
                        'line': self._find_line_number(content, f'webClient.{method}')
                    })
                
                # Look for Feign client interfaces
                feign_methods = re.findall(
                    r'@(Get|Post|Put|Delete)Mapping\s*\([^)]*"([^"]+)"\s*\)', 
                    content, re.IGNORECASE
                )
                for method, path in feign_methods:
                    if '@FeignClient' in content:
                        integrations.append({
                            'type': 'FeignClient',
                            'method': method.upper(),
                            'url': path,
                            'line': self._find_line_number(content, f'@{method}Mapping')
                        })
                
                # Look for external API calls in URLs
                external_urls = re.findall(
                    r'https?://[^\s"\']+', content
                )
                for url in external_urls:
                    integrations.append({
                        'type': 'External API',
                        'method': 'UNKNOWN',
                        'url': url,
                        'line': self._find_line_number(content, url)
                    })
                
                if integrations:
                    self.internal_integrations[java_file.name] = integrations
                    
            except Exception as e:
                print(f"⚠️  Error analyzing {java_file}: {e}")
        
        print(f"✅ Found integrations in {len(self.internal_integrations)} files")
    
    def _find_line_number(self, content: str, search_text: str) -> int:
        """Find line number of text in content"""
        lines = content.split('\n')
        for i, line in enumerate(lines, 1):
            if search_text in line:
                return i
        return 0
    
    def get_integration_summary(self) -> Dict[str, Any]:
        """Get summary of internal integrations"""
        if not hasattr(self, 'internal_integrations'):
            return {}
        
        summary = {
            'total_files_with_integrations': len(self.internal_integrations),
            'integration_types': {},
            'external_apis': [],
            'internal_calls': []
        }
        
        for file_name, integrations in self.internal_integrations.items():
            for integration in integrations:
                int_type = integration['type']
                summary['integration_types'][int_type] = summary['integration_types'].get(int_type, 0) + 1
                
                if integration['type'] == 'External API':
                    summary['external_apis'].append(integration['url'])
                else:
                    summary['internal_calls'].append({
                        'file': file_name,
                        'method': integration['method'],
                        'url': integration['url'],
                        'type': integration['type']
                    })
        
        return summary


def main():
    """Main function for voice-enabled agent"""
    import sys
    
    if len(sys.argv) < 2:
        print("Usage: python voice_spring_agent.py <spring_boot_project_path> [base_url]")
        print("Example: python voice_spring_agent.py /path/to/project http://localhost:8080")
        sys.exit(1)
    
    project_path = sys.argv[1]
    base_url = sys.argv[2] if len(sys.argv) > 2 else "http://localhost:8080"
    
    try:
        # Create voice agent
        voice_agent = VoiceSpringBootAgent(project_path, base_url)
        voice_agent.initialize()
        
        print("\n🎤 Voice commands available:")
        print("  • 'summary' - Get project overview")
        print("  • 'show endpoints' - List all endpoints")
        print("  • 'curl command for post users' - Get CURL commands")
        print("  • 'fields for user model' - See model fields")
        print("  • 'mandatory fields for create user request' - Required fields")
        print("  • 'internal integrations' - API dependencies")
        print("  • 'stop listening' - Exit")
        
        # Start voice interaction
        voice_agent.start_voice_interaction()
        
    except ImportError as e:
        print("❌ Missing required packages for voice functionality:")
        print("   pip install SpeechRecognition pyttsx3 pyaudio")
        print(f"   Error: {e}")
    except Exception as e:
        print(f"❌ Error starting voice agent: {e}")


if __name__ == "__main__":
    main()