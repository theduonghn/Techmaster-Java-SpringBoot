package vn.techmaster.jobhunt.request;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.techmaster.jobhunt.model.City;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobRequest {
    private String id;

    @NotBlank(message = "Employer cannot be null")
    private String employerId;

    @NotBlank(message = "Title cannot be null")
    private String title;

    @NotBlank(message = "Description cannot be null")
    private String description;

    private City city;
}