package com.clickbye.dto;

import com.clickbye.common.annotation.FileType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class FileUploadDTO {
    @NotNull(message = "Audio file is required")
    @FileType(allowedTypes = {"audio/mpeg"}, message = "Invalid audio format. Only MP3 files are allowed")
    private MultipartFile audio;

    @NotNull(message = "Video file is required")
    @FileType(allowedTypes = {"video/mp4"}, message = "Invalid video format. Only MP4 files are allowed")
    private MultipartFile video;
} 