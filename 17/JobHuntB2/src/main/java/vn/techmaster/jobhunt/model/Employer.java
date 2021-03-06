package vn.techmaster.jobhunt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

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
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    private String name;
    @Column(name = "logo_path")
    private String logoPath;
    private String website;
    private String email;

    public Employer(String name, String logoPath, String website, String email) {
        this.name = name;
        this.logoPath = logoPath;
        this.website = website;
        this.email = email;
    }
}
