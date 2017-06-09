package com.teen.spring.cloud.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UploadResponse {
  private String requestId;
  private String statusMessage;
}
