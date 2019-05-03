package com.waracle.projects;

import org.junit.Test;
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.test.context.ActiveProfiles;
import spock.lang.Issue
import spock.lang.Narrative
import spock.lang.Subject
import spock.lang.Title;

@Narrative("""ApplicationSpec is responsible for Integration test """)
@Title("Tests for Application implementation")
@Subject(Application)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class ApplicationSpec {

	@LocalServerPort
	private int http_port

	@Autowired
	TestRestTemplate testRestTemplate

	def setup(){

	}

	@Issue("JIRA-100")
	def "CakeController /cakes endpoint is hit" (){
		given: "the cakecontroller endpoint"
		when: "a GET request is made to  the endpoint"
		HttpHeaders httpHeaders = new HttpHeaders()
		httpHeaders.setContentType(MediaType.APPLICATION_JSON)
		ResponseEntity<String> response = testRestTemplate.exchange(
				"http://localhost:"+ http_port +"/cakes",
				 HttpMethod.GET,
				 new HttpEntity<>(httpHeaders),
		         new ParameterizedTypeReference<String>() {
				 });
		then: "A HTTP status code of 200 is returned"
		response.statusCode == HttpStatus.OK

		and: "cakes are returned"
		String returnedCakes = response.getBody()
		returnedCakes.size() == 5
	}

}
