package vn.techmaster.jobhunt.request;

import java.util.List;

import vn.techmaster.jobhunt.model.Job;
import vn.techmaster.jobhunt.model.Skill;

public record ApplicantRequest(
        Job job,
        String name,
        String email,
        String phone,
        List<Skill> skills,
        String applyContent) {
}