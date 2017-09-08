package com.db.mathservice.communication;

import com.db.mathservice.data.ExamCoordinates;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URL;

@Service
@AllArgsConstructor
public class ExamRegistrarImpl implements ExamRegistrar {
    private DiscoveryClient discoveryClient;

    @Override
    @SneakyThrows
    public String registerExam(ExamCoordinates examCoordinates) {
        ServiceInstance serviceInstance = Services.MAPLOGIN.pickRandomInstance(discoveryClient);
        URL url = serviceInstance.getUri().toURL();
        url = new URL(url.toString()+"/exams/saveExam");
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonExam = objectMapper.writeValueAsString(examCoordinates);

        HttpEntity<String> entity = new HttpEntity<>(jsonExam, headers);
        ResponseEntity<String> loginResponse = restTemplate.exchange(url.toString(), HttpMethod.POST, entity, String.class);

        String response ="no response";

        if (loginResponse.getStatusCode() == HttpStatus.OK) {
            response = loginResponse.getBody();
            return response;
        } else if (loginResponse.getStatusCode() == HttpStatus.UNAUTHORIZED) {
            response = "bad response";
        }

        throw new RuntimeException("Can't access discovery server: " + response);
    }
}
