package vn.hub.clickbye.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.hub.clickbye.service.FileUploadService;
import vn.hub.clickbye.service.dto.FileUploadDTO;
import vn.hub.clickbye.service.dto.response.Response;

/**
 * Controller xử lý các request liên quan đến upload file
 * Endpoint: /api/files
 */
@RestController
@RequestMapping("/api/files")
@RequiredArgsConstructor
public class FileUploadController {

    private final FileUploadService fileUploadService;

    /**
     * API upload 2 file (audio và video)
     * 
     * @param request Chứa 2 file cần upload
     * @return Response thành công nếu upload thành công
     * @throws BusinessException nếu validation thất bại
     */
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Response<Void> uploadFiles(@Valid FileUploadDTO request) {
        fileUploadService.uploadFiles(request);
        return Response.noContent();
    }
} 