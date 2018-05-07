package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CustomersOp {

	@RequestMapping("/hi")
	 public @ResponseBody String addCustomer()
	{
		System.out.println("hi");
		return "hi to browser";
	}
	
	
}
