package com.teen.spring.cloud.services;

import com.jayway.restassured.module.mockmvc.RestAssuredMockMvc;
import com.teen.spring.cloud.service.UploadService;
import org.junit.Before;
import org.mockito.InjectMocks;

import javax.annotation.Resource;

public abstract class UploadServiceBase {

  private UploadService uploadService = new UploadService();

  @Before
  public void setup() throws Exception {
    RestAssuredMockMvc.standaloneSetup(uploadService);
  }
}
