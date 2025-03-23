package vn.hub.clickbye.service;

import vn.hub.clickbye.service.dto.FileUploadDTO;

/**
 * Service xử lý upload file
 * Hỗ trợ upload 2 loại file:
 * - Audio file (.mp3)
 * - Video file (.mp4)
 */
public interface FileUploadService {
    /**
     * Upload 2 file lên MinIO server
     * @param request Chứa 2 file cần upload
     * @throws BusinessException nếu validation thất bại
     */
    void uploadFiles(FileUploadDTO request);
} 