package com.clickbye.service;

import com.clickbye.dto.FileUploadDTO;
import java.util.Map;

public interface FileUploadService {
    Map<String, String> uploadFiles(FileUploadDTO fileUploadDTO);
} 