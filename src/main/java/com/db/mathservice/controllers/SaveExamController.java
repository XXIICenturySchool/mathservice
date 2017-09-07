package com.db.mathservice.controllers;

import com.db.mathservice.dao.ExamConfigurationRepository;
import com.db.mathservice.dao.LocalGlobalExamIdRelationRepository;
import com.db.mathservice.data.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Valentin on 06.09.2017.
 */
@RestController
@AllArgsConstructor
public class SaveExamController {
    ExamConfigurationRepository repository;

    private DiscoveryClient discoveryClient;
    LocalGlobalExamIdRelationRepository localGlobalExamIdRelationRepository;

    @SneakyThrows
    @PostMapping("/save_exam")
    public ExamCoordinates addExam(@RequestBody ExamConfiguration inputConfiguration) {
        String localId = repository.save(inputConfiguration).getId();

        ExamCoordinates examCoordinates = ExamCoordinates.builder().teacherId(inputConfiguration.getTeacherId())
                .url("/exams?localExamId=" + localId).build();

        String globalId = registerExam(examCoordinates);

        localGlobalExamIdRelationRepository.save(new LocalGlobalExamIdRelation(localId, globalId));

        return examCoordinates;
    }

    private String registerExam(ExamCoordinates examCoordinates) throws MalformedURLException, JsonProcessingException {
        ServiceInstance serviceInstance = Services.MAPLOGIN.pickRandomInstance(discoveryClient);
        System.out.println("URI "+serviceInstance.getUri());
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
