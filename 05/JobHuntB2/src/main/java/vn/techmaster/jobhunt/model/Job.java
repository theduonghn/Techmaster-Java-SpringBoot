package vn.techmaster.jobhunt.model;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Job {
  private String id;
  private String emp_id;
  private String title;
  private String description;
  private City city;

  @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
  private LocalDateTime updated_at;

  @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
  private LocalDateTime created_at;
}
