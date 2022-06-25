package vn.techmaster.jobhunt.model;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
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
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
  private String id;
  private String emp_id;
  private String title;
  private String description;
  @Enumerated(EnumType.STRING)
  private City city;
  @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
  private LocalDateTime updated_at;
  @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
  private LocalDateTime created_at;

  public Job(String emp_id, String title, String description, City city, LocalDateTime updated_at,
      LocalDateTime created_at) {
    this.emp_id = emp_id;
    this.title = title;
    this.description = description;
    this.city = city;
    this.updated_at = updated_at;
    this.created_at = created_at;
  }
}
