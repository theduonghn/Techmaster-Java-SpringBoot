package vn.techmaster.jobhunt.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
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
  // BEGIN Attributes
  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
  private String id;

  @ManyToOne(fetch = FetchType.LAZY)
  private Employer employer;

  private String title;

  private String description;

  @Enumerated(EnumType.STRING)
  private City city;

  @LastModifiedDate
  @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
  private LocalDateTime updated_at;

  @CreatedDate
  @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
  private LocalDateTime created_at;
  // END Attributes

  public Job(Employer employer, String title, String description, City city, LocalDateTime updated_at,
      LocalDateTime created_at) {
    this.employer = employer;
    this.title = title;
    this.description = description;
    this.city = city;
    this.updated_at = updated_at;
    this.created_at = created_at;
  }
}
