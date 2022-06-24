package vn.techmaster.jobhunt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.techmaster.jobhunt.model.Employer;
import vn.techmaster.jobhunt.repository.EmployerRepository;

@Service
public class EmployerService {
    @Autowired
    private EmployerRepository employerRepository;

    public List<Employer> findAll() {
        return employerRepository.findAll();
    }
}
