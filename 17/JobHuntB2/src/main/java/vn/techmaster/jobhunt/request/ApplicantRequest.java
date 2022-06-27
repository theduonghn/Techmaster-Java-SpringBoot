package vn.techmaster.jobhunt.request;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicantRequest {
    private String id;
    private String jobId;
    private String name;
    private String email;
    private String phone;
    private List<String> skillIds;
    private String applyContent;
}