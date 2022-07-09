package vn.techmaster.jobhunt.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployerRequest {
    private String id;

    @NotBlank(message = "Name cannot be null")
    private String name;

    private String website;

    @NotBlank(message = "Email cannot be null")
    @Email(message = "Invalid email")
    private String email;

    private MultipartFile logo;
}