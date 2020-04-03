package br.inpe.dpi.terrabrasilis.businessapi;

import static br.inpe.dpi.terrabrasilis.util.Constants.API;
import static br.inpe.dpi.terrabrasilis.util.Constants.V1;
import static br.inpe.dpi.terrabrasilis.util.Constants.VISION;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.reactive.function.client.WebClient;

import br.inpe.dpi.terrabrasilis.app.BusinessApiApplication;
import br.inpe.dpi.terrabrasilis.domain.Vision;
import br.inpe.dpi.terrabrasilis.exception.VisionNotFoundException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {
	BusinessApiApplication.class
}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BusinessApiApplicationTests {

	private WebClient client;
	private final String visionPath = "/" + API + V1 + VISION; 

	public BusinessApiApplicationTests() {
		this.client = WebClient.create();
	}

	@Test
	public void contextLoads() {
	}

	@Test
	public void testExistentVision() {
		final String name = "desforestation";
		
		Vision vision = client.get()
							.uri(visionPath + "/name/" + name)
							.retrieve()
							.bodyToMono(Vision.class)							
							.blockOptional()
							.get();
		assertEquals(name, vision.getName());
	}

	@Test(expected = VisionNotFoundException.class)
	public void testNotExistentVision() {
		final String name = "any";

		Vision vision = client.get()
							.uri(visionPath + "/name/" + name)
							.retrieve()
							.bodyToMono(Vision.class)							
							.blockOptional()
							.get();
		assertEquals(name, vision.getName());
	}

}

