package com;

import java.net.URI;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import com.config.AppConfig;
import com.domain.Car;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=AppConfig.class)
public class RestTemplateTest {
	@Autowired
	RestTemplate restTemplate;
	
	@Test
    public void carTest()
    {
		Assert.assertNotNull(restTemplate);
		
		// Requires com.fasterxml.jackson.core:jackson-databind:2.8.6
		Car car = restTemplate.getForObject("http://127.0.0.1:8080/SpringRest1/cars/example", Car.class);
		System.out.println(car);
		
    }
	
	@Test
	public void postTest() {
		Car newCar = new Car(33L, "Chrysler", "305C", 5001, 2006);
		
		ResponseEntity<Car> response = restTemplate.postForEntity(
				URI.create("http://127.0.0.1:8080/SpringRest1/cars/add"), 
				newCar, 
				Car.class);
		
		
		Assert.assertEquals(HttpStatus.CREATED, response.getStatusCode());
		URI newResourceLocation = response.getHeaders().getLocation();
		Car returnedCar = response.getBody();
		
		Assert.assertEquals(returnedCar, newCar);
		
		System.out.println("Response headers:" + response.getHeaders() );
		System.out.println("Response location: " + newResourceLocation);
		System.out.println("Response ("+ response.getStatusCode() +") body:" + response.getBody());
		//RestTemplate
		
		Car newResource = restTemplate.getForObject(newResourceLocation, Car.class);
		Assert.assertEquals(newResource, newCar);		
	}

}
