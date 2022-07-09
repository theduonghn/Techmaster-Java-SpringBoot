package vn.techmaster.jobhunt.request;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicantRequest {
    private String id;

    @NotBlank(message = "Job cannot be null")
    private String jobId;

    @NotBlank(message = "Name cannot be null")
    private String name;

    @NotBlank(message = "Email cannot be null")
    @Email(message = "Invalid email")
    private String email;

    private String phone;

    private List<String> skillIds;

    @NotBlank(message = "Apply content cannot be null")
    private String applyContent;
}