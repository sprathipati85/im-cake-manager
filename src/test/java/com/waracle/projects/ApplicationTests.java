package com.waracle.projects;

import com.waracle.projects.domain.Cake;
import com.waracle.projects.repository.ICakeRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {Application.class })
public class ApplicationTests {

	@LocalServerPort
	private int http_port;

	@Autowired
	private ICakeRepository cakeRepository;

	TestRestTemplate restTemplate = new TestRestTemplate();
	HttpHeaders headers = new HttpHeaders();
	String responseJson = "[{\"id\":null,\"title\":\"Lemon cheesecake\",\"desc\":\"A cheesecake made of lemon\"," +
			"\"image\":\"https://s3-eu-west-1.amazonaws.com/s3.mediafileserver.co.uk/carnation/WebFiles/RecipeImages/lemoncheesecake_lg.jpg\"}," +
			"{\"id\":null,\"title\":\"victoria sponge\",\"desc\":\"sponge with jam\"," +
			"\"image\":\"http://www.bbcgoodfood.com/sites/bbcgoodfood.com/files/recipe_images/recipe-image-legacy-id--1001468_10.jpg\"}," +
			"{\"id\":null,\"title\":\"Carrot cake\"," +
			"\"desc\":\"Bugs bunnys favourite\"," +
			"\"image\":\"http://www.villageinn.com/i/pies/profile/carrotcake_main1.jpg\"}," +
			"{\"id\":null,\"title\":\"Birthday cake\",\"desc\":\"a yearly treat\"," +
			"\"image\":\"http://cornandco.com/wp-content/uploads/2014/05/birthday-cake-popcorn.jpg\"}," +
			"{\"id\":null,\"title\":\"Banana cake\",\"desc\":\"Donkey kongs favourite\"," +
			"\"image\":\"http://ukcdn.ar-cdn.com/recipes/xlarge/ff22df7f-dbcd-4a09-81f7-9c1d8395d936.jpg\"}]";

	@Before
	public void setup() {
		cakeRepository.deleteAll();
	}

	@Test
	public void invokeControllerGetEndpoint() {
		HttpEntity<String> requestEntity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(
				"http://localhost:"+ http_port +"/cakes",
				HttpMethod.GET, requestEntity, String.class);
		String responseBody = response.getBody();
		Assert.assertNotNull(responseBody);
		Assert.assertEquals(responseBody, responseJson);
	}

	@Test
	public void invokeControllerPostEndpoint() {
		Cake cake = new Cake();
		cake.setDesc("Premium velvet cake ");
		cake.setImage("http://cakes.com/velvet.jpg");
		cake.setTitle("Velvet Cake");
		cake.setId(100L);

		HttpEntity<Cake> requestEntity = new HttpEntity<>(cake, headers);

		ResponseEntity<String> response = restTemplate.exchange(
				"http://localhost:"+ http_port +"/cakes",
				HttpMethod.POST, requestEntity, String.class);
		String responseBody = response.getBody();
		Assert.assertEquals(response.getStatusCode(), HttpStatus.CREATED);
		Assert.assertNull(responseBody);
	}
}
