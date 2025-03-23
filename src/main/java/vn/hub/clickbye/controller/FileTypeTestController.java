package vn.hub.clickbye.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.hub.clickbye.common.annotation.FileType;
import vn.hub.clickbye.service.dto.response.Response;
import org.springframework.web.multipart.MultipartFile;

/**
 * Controller test cho annotation @FileType
 * 
 * Các endpoint test:
 * 1. /api/test/audio: Upload file audio (MP3)
 * 2. /api/test/video: Upload file video (MP4)
 * 3. /api/test/image: Upload file ảnh (JPG/PNG)
 */
@RestController
@RequestMapping("/api/test")
@RequiredArgsConstructor
public class FileTypeTestController {

    /**
     * Test upload file audio MP3
     * 
     * @param file File audio cần upload
     * @return Response thành công nếu file đúng định dạng
     */
    @PostMapping(value = "/audio", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Response<String> testAudioUpload(
            @Valid @FileType(
                allowedTypes = {"audio/mpeg"},
                message = "Invalid audio format. Only MP3 files are allowed"
            ) MultipartFile file) {
        return Response.ok("Audio file is valid");
    }

    /**
     * Test upload file video MP4
     * 
     * @param file File video cần upload
     * @return Response thành công nếu file đúng định dạng
     */
    @PostMapping(value = "/video", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Response<String> testVideoUpload(
            @Valid @FileType(
                allowedTypes = {"video/mp4"},
                message = "Invalid video format. Only MP4 files are allowed"
            ) MultipartFile file) {
        return Response.ok("Video file is valid");
    }

    /**
     * Test upload file ảnh (JPG/PNG)
     * 
     * @param file File ảnh cần upload
     * @return Response thành công nếu file đúng định dạng
     */
    @PostMapping(value = "/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Response<String> testImageUpload(
            @Valid @FileType(
                allowedTypes = {"image/jpeg", "image/png"},
                message = "Invalid image format. Only JPG/PNG files are allowed"
            ) MultipartFile file) {
        return Response.ok("Image file is valid");
    }
}