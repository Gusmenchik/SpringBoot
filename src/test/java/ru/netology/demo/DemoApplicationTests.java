package ru.netology.demo;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	TestRestTemplate restTemplate;

	static GenericContainer<?> devApp = new GenericContainer<>("devapp:latest").withExposedPorts(9090);
	static GenericContainer<?> prodApp = new GenericContainer<>("prodapp:latest").withExposedPorts(9091);

	@BeforeAll
	public static void setUp() {
		devApp.start();
		prodApp.start();
	}

	@Test
	void testDevProfile() {
		String devUrl = "http://localhost:" + devApp.getMappedPort(9090) + "/profile";
		ResponseEntity<String> response = restTemplate.getForEntity(devUrl, String.class);
		assertEquals("Current profile is dev", response.getBody());
	}
////Для проверки второго образа, где profile.dev=false
//	@Test
//	void testProdProfile() {
//		String prodUrl = "http://localhost:" + prodApp.getMappedPort(9091) + "/profile";
//		ResponseEntity<String> response = restTemplate.getForEntity(prodUrl, String.class);
//		assertEquals("Current profile is production", response.getBody());
//	}
}
