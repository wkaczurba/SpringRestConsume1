package com;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
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
    public void restTest1()
    {
		Assert.assertNotNull(restTemplate);
		
		// Requires com.fasterxml.jackson.core:jackson-databind:2.8.6
		Car car = restTemplate.getForObject("http://127.0.0.1:8080/SpringRest1/cars/example", Car.class);
		System.out.println(car);
		//RestTemplate
    }

}
