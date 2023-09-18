package com.poly.services;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileStorageService {

    private static final String UPLOAD_DIR = "uploads";

    public void storeFile(MultipartFile file) throws IOException {
        // Tạo đường dẫn đến thư mục lưu trữ nếu nó chưa tồn tại
        File uploadDir = new File(UPLOAD_DIR);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        // Lấy tên tệp gốc
        String originalFileName = file.getOriginalFilename();
        String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
        String newFileName = System.currentTimeMillis() + fileExtension;

        // Lưu tệp vào thư mục lưu trữ
        Path filePath = Paths.get(UPLOAD_DIR, newFileName);
        file.transferTo(filePath.toFile());
    }
}
