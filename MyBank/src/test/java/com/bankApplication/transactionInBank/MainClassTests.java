package com.bankApplication.transactionInBank;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class MainClassTests {
	
	
	@LocalServerPort
	private int port;
	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void contextLoads() {
		
		System.out.println(this.restTemplate.getForObject("http://localhost:" + port + "/", String.class));
		System.out.println("test");
//		
//		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/", String.class))
//		.contains("Hello World");

	}

}
