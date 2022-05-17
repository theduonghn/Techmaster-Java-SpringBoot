package vn.techmaster.myfirstweb.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Job {
    private String id;
    private String title;
    private String description;
    private Location location;
    private int min_salary;
    private int max_salary;
    private String email_to;
}
