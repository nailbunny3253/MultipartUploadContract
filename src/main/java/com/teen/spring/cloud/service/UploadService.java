package com.teen.spring.cloud.service;

import com.teen.spring.cloud.domain.UploadResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@RestController
public class UploadService {

  @RequestMapping(value = "/uploadMultiPart", method = RequestMethod.POST)
  @ResponseBody
  public ResponseEntity<UploadResponse> uploadMultiPart(@RequestParam("uploadInformation") String uploadInformation, @RequestPart("multipartFile") MultipartFile multipartFile) throws IOException {
    String requestId = String.valueOf(UUID.randomUUID());
    doSomethingWithFile(requestId, uploadInformation, multipartFile.getOriginalFilename(), multipartFile.getInputStream());
    return new ResponseEntity<>(UploadResponse.builder().requestId(requestId).statusMessage("File Upload Successful").build(), HttpStatus.OK);
  }

  private void doSomethingWithFile(String requestId, String uploadInformation, String originalFilename, InputStream inputStream) {
    // Do something with the information ...
  }

}
