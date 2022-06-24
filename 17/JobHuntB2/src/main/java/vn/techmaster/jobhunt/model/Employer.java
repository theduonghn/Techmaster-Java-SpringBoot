package vn.techmaster.jobhunt.model;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "employer")
public class Employer {
  @Id
  private String id;
  private String name;
  private String logo_path;
  private String website;
  private String email;

  public Employer(String name, String logo_path, String website, String email) {
    this.id = UUID.randomUUID().toString();
    this.name = name;
    this.logo_path = logo_path;
    this.website = website;
    this.email = email;
  }
}
