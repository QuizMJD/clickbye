package com.clickbye.controller;

import vn.hub.clickbye.service.dto.response.Response;
import com.clickbye.dto.FileUploadDTO;
import com.clickbye.service.FileUploadService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/upload")
@RequiredArgsConstructor
public class FileUploadController {
    private final FileUploadService fileUploadService;

    @PostMapping
    public Response<?> uploadFiles(@Valid FileUploadDTO fileUploadDTO) {
        fileUploadService.uploadFiles(fileUploadDTO);
        return Response.noContent();
    }
} 