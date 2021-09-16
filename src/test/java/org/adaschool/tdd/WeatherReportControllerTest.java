package org.adaschool.tdd;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.junit.jupiter.api.Assertions;


import java.util.Date;

import org.adaschool.tdd.controller.weather.dto.WeatherReportDto;
import org.adaschool.tdd.repository.document.GeoLocation;

@SpringBootTest( webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT )
public class WeatherReportControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private WeatherReportDto obj = new WeatherReportDto(new GeoLocation(20, 0), 35f, 40f, "test", new Date());

    @Test
    void testPostMapping(){
        ResponseEntity<String> result=this.restTemplate.postForEntity("http://localhost:"+port +"/v1/weather", new HttpEntity<>(obj),String.class);
        Assertions.assertEquals(500, result.getStatusCodeValue());
    }

    @Test
    void testGetMapping(){
        ResponseEntity<String> result=this.restTemplate.getForEntity("http://localhost:"+port +"/v1/weather/asd", String.class);
        Assertions.assertEquals(500, result.getStatusCodeValue());
    }


    
}
