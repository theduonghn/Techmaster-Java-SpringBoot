package vn.techmaster.jobhunt.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.techmaster.jobhunt.model.City;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobRequest {
    private String id;
    private String employerId;
    private String title;
    private String description;
    private City city;
}