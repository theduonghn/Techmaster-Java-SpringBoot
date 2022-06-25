package vn.techmaster.jobhunt.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.techmaster.jobhunt.exception.BadRequestException;
import vn.techmaster.jobhunt.model.Employer;
import vn.techmaster.jobhunt.repository.EmployerRepository;

@Service
public class EmployerService {
    @Autowired
    private EmployerRepository employerRepository;

    public List<Employer> findAll() {
        return employerRepository.findAll();
    }

    public Employer findById(String id) {
        Optional<Employer> oEmployer = employerRepository.findById(id);
        if (oEmployer.isEmpty()) {
            throw new BadRequestException("Employer with id = " + id + " is not exist");
        }

        return oEmployer.get();
    }
}
