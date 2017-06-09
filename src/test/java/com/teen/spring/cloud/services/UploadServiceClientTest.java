package com.teen.spring.cloud.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
@AutoConfigureStubRunner(ids = {"com.teen.spring.cloud:multipartcontract:+:stubs:8080"}, workOffline = true)
@DirtiesContext
public class UploadServiceClientTest {

  private static final String URL = "http://localhost:8080";

  @Test
  public void testMultiPart() throws IOException {
    String contentPackDetails = "{\"name\": \"anyName\", \"version\": \"1.0\", \"category\": \"aCategory\"}";
    ClassLoader classloader = Thread.currentThread().getContextClassLoader();
    InputStream inputStream = classloader.getResourceAsStream("test-1.0.jar");
    MultipartFile multipartFile = new MockMultipartFile("spark-jobs", inputStream);

    LinkedMultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
    map.add("uploadInformation", contentPackDetails);
    map.add("multipartFile", new ByteArrayResource(multipartFile.getBytes()));
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.MULTIPART_FORM_DATA);

    HttpEntity<LinkedMultiValueMap<String, Object>> requestEntity = new HttpEntity<>(map, headers);
    ResponseEntity<String> result = new RestTemplate().exchange(URL + "/uploadMultiPart", HttpMethod.POST, requestEntity, String.class);
    assertEquals(200, result.getStatusCodeValue());
    String body = result.getBody();
    assertNotNull(body);
  }
}
