package vn.techmaster.jobhunt.model;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "job")
public class Job {
  @Id
  private String id;
  private String emp_id;
  private String title;
  private String description;
  private City city;

  @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
  private LocalDateTime updated_at;

  @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
  private LocalDateTime created_at;

  public Job(String emp_id, String title, String description, City city) {
    this.id = UUID.randomUUID().toString();
    this.emp_id = emp_id;
    this.title = title;
    this.description = description;
    this.city = city;
  }
}
