package com.taskmanager;

import com.taskmanager.dto.CreateTaskRequest;
import com.taskmanager.entities.StatusTask;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
class TaskManagerApiApplicationTests {
	@Autowired
	TestRestTemplate restTemplate;

	@Test
	void createTask_Success() {
		CreateTaskRequest requestBody = new CreateTaskRequest();
		requestBody.setTitle("Task Title 1");
		requestBody.setDescription("Task Description 1");
		ResponseEntity<Void> response = restTemplate.postForEntity("/api/v1/tasks", requestBody, Void.class);

	}

}
