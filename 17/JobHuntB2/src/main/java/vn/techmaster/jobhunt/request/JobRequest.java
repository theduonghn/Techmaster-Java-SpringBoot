package vn.techmaster.jobhunt.request;

import vn.techmaster.jobhunt.model.City;

public record JobRequest(
        String id,
        String emp_id,
        String title,
        String description,
        City city) {
}