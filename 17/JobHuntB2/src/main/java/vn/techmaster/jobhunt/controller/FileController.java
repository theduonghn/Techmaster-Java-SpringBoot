package vn.techmaster.jobhunt.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import vn.techmaster.jobhunt.service.FileService;

@RestController
@RequestMapping("/")
@AllArgsConstructor
public class FileController {
    private FileService fileService;

    @GetMapping(value = "upload/employer_logo/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] readEmployerLogo(@PathVariable String id) {
        return fileService.readEmployerLogo(id);
    }
}
