package com.example.demo.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;

@Data
@Builder
public class ResourceDto {
    private Resource resource;
    private MediaType mediaType;
}
