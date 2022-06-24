package vn.techmaster.jobhunt.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;

import vn.techmaster.jobhunt.exception.BadRequestException;
import vn.techmaster.jobhunt.util.Utils;

@Service
public class FileService {
    // Folder path to upload file
    private final Path rootPath = Paths.get("upload");

    public FileService() {
        createFolder(rootPath.toString());
    }

    // Create folder if not exists
    public void createFolder(String path) {
        File folder = new File(path);
        if (!folder.exists()) {
            folder.mkdirs();
        }
    }

    // Upload file
    public String uploadEmployerLogo(String id, MultipartFile file) {
        // Create folder for employer id
        Path employerDir = Paths.get("upload")
                .resolve("employer_logo")
                .resolve(id);
        createFolder(employerDir.toString());

        // Validate file
        validate(file);

        // Create path of file
        File fileServer = new File(employerDir + "/" + file.getOriginalFilename());
        try {
            // Sử dụng Buffer để lưu dữ liệu
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(fileServer));
            stream.write(file.getBytes());
            stream.close();

            return file.getOriginalFilename();
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi upload file");
        }
    }

    public void validate(MultipartFile file) {
        // Validate file name
        String fileName = file.getOriginalFilename();
        if (fileName == null || fileName.equals("")) {
            throw new BadRequestException("Tên file không hợp lệ");
        }

        // Validate file extension
        String fileExtension = Utils.getExtensionFile(fileName);
        if (!Utils.checkFileExtenstion(fileExtension)) {
            throw new BadRequestException("File không hợp lệ");
        }

        // Kiểm tra size (upload dưới 2MB)
        if ((double) file.getSize() > (2 * 1024 * 1024)) {
            throw new BadRequestException("File không được vượt quá 2MB");
        }
    }

    // Read file
    public byte[] readEmployerLogo(String id, String fileId) {
        // Lấy đường dẫn file tương ứng với user_id
        Path employerPath = rootPath.resolve("employer_logo").resolve(id);

        // Kiểm tra userPath có tồn tại hay không
        if (!Files.exists(employerPath)) {
            throw new RuntimeException("Lỗi khi đọc file " + fileId);
        }

        try {
            Path file = employerPath.resolve(fileId);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                InputStream inputStream = resource.getInputStream();
                byte[] byteArray = StreamUtils.copyToByteArray(inputStream);
                inputStream.close(); // Remember to close InputStream
                return byteArray;
            } else {
                throw new RuntimeException("Lỗi khi đọc file " + fileId);
            }
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi đọc file " + fileId);
        }
    }
}
