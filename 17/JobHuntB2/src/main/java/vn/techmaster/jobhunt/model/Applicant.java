package vn.techmaster.jobhunt.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
@Table(name = "applicant")
public class Applicant {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    @ManyToOne(fetch = FetchType.LAZY)
    private Job job;
    private String name;
    private String email;
    private String phone;
    @ManyToMany
    @JoinTable(name = "applicant_skill", joinColumns = @JoinColumn(name = "applicant_id"), inverseJoinColumns = @JoinColumn(name = "skill_id"))
    private List<Skill> skills;
    private String applyContent;

    public Applicant(Job job, String name, String email, String phone, List<Skill> skills,
            String applyContent) {
        this.job = job;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.skills = skills;
        this.applyContent = applyContent;
    }
}
