package vn.hub.clickbye.service.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Value;
import org.springframework.web.multipart.MultipartFile;
import vn.hub.clickbye.common.annotation.FileType;

/**
 * DTO cho request upload file
 * Chứa 2 file: audio (.mp3) và video (.mp4)
 */
@Value
public class FileUploadDTO {
    /**
     * File audio định dạng MP3
     * Bắt buộc phải có
     * Dung lượng tối đa 15MB
     */
    @NotNull(message = "Audio file is required")
    @FileType(allowedTypes = {"audio/mpeg"}, message = "Invalid audio format. Only MP3 files are allowed")
    MultipartFile audioFile;

    /**
     * File video định dạng MP4
     * Bắt buộc phải có
     * Dung lượng tối đa 1GB
     */
    @NotNull(message = "Video file is required")
    @FileType(allowedTypes = {"video/mp4"}, message = "Invalid video format. Only MP4 files are allowed")
    MultipartFile videoFile;
} 