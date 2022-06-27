package vn.techmaster.jobhunt.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.techmaster.jobhunt.exception.BadRequestException;
import vn.techmaster.jobhunt.model.Skill;
import vn.techmaster.jobhunt.repository.SkillRepository;

@Service
public class SkillService {
    @Autowired
    private SkillRepository skillRepository;

    public List<Skill> findAll() {
        return skillRepository.findAll();
    }

    public Skill findById(String id) {
        Optional<Skill> oSkill = skillRepository.findById(id);
        if (oSkill.isEmpty()) {
            throw new BadRequestException("Skill with id = " + id + " does not exist");
        }

        return oSkill.get();
    }
}
