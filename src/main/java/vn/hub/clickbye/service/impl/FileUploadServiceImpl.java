package vn.hub.clickbye.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import vn.hub.clickbye.integration.storage.MinioService;
import vn.hub.clickbye.service.FileUploadService;
import vn.hub.clickbye.service.dto.FileUploadDTO;

import java.util.UUID;

/**
 * Implementation của FileUploadService
 * Xử lý upload và validate 2 loại file:
 * - Audio file (.mp3): tối đa 15MB
 * - Video file (.mp4): tối đa 1GB
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class FileUploadServiceImpl implements FileUploadService {

    /** Dung lượng tối đa cho file audio (15MB) */
    private static final long MAX_AUDIO_SIZE = 15 * 1024 * 1024;
    
    /** Dung lượng tối đa cho file video (1GB) */
    private static final long MAX_VIDEO_SIZE = 1024 * 1024 * 1024;
    
    /** Định dạng file audio */
    private static final String AUDIO_EXTENSION = ".mp3";
    
    /** Định dạng file video */
    private static final String VIDEO_EXTENSION = ".mp4";

    private final MinioService minioService;

    @Override
    public void uploadFiles(FileUploadDTO request) {
        // Upload file audio lên MinIO
        uploadFile(request.getAudioFile(), "audio");

        // Upload file video lên MinIO
        uploadFile(request.getVideoFile(), "video");
    }

    /**
     * Upload file lên MinIO server
     * @param file File cần upload
     * @param type Loại file (audio/video)
     * @return URL của file sau khi upload
     */
    private String uploadFile(MultipartFile file, String type) {
        String originalFilename = file.getOriginalFilename();
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String fileName = UUID.randomUUID().toString() + extension;
        String objectName = "media/" + type + "/" + fileName;

        return minioService.uploadFile(file, objectName);
    }
} 