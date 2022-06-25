package vn.techmaster.jobhunt.request;

import vn.techmaster.jobhunt.model.City;
import vn.techmaster.jobhunt.model.Employer;

public record JobRequest(
                String id,
                Employer employer,
                String title,
                String description,
                City city) {
}