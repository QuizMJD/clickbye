package com.clickbye.service.impl;

import com.clickbye.dto.FileUploadDTO;
import com.clickbye.service.FileUploadService;
//import com.clickbye.service.MinioService;
import vn.hub.clickbye.integration.storage.MinioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileUploadServiceImpl implements FileUploadService {
    private final MinioService minioService;

    @Override
    public Map<String, String> uploadFiles(FileUploadDTO fileUploadDTO) {
        Map<String, String> urls = new HashMap<>();
        
        // Upload audio file
        String audioUrl = uploadFile(fileUploadDTO.getAudio(), "audio");
        urls.put("audioUrl", audioUrl);
        
        // Upload video file
        String videoUrl = uploadFile(fileUploadDTO.getVideo(), "video");
        urls.put("videoUrl", videoUrl);
        
        return urls;
    }

    private String uploadFile(MultipartFile file, String type) {
        String originalFilename = file.getOriginalFilename();
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String fileName = UUID.randomUUID().toString() + extension;
        String objectName = "media/" + type + "/" + fileName;
        
        return minioService.uploadFile(file, objectName);
    }
} 