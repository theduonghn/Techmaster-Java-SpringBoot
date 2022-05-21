package vn.techmaster.jobhunt.request;

public record EmployerRequest(
        String name,
        String website,
        String email,
        String logo_path) {
    // MultipartFile logo) {
}