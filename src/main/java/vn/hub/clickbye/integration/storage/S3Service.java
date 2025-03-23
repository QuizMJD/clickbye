package vn.hub.clickbye.integration.storage;

import vn.hub.clickbye.integration.storage.model.UploadFileAgrs;

public sealed interface S3Service permits MinioService {
    String upload(UploadFileAgrs request);

    byte[] download(String bucket, String fileName);
}

