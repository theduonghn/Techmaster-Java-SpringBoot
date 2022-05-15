package vn.techmaster.myfirstweb.controller;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.techmaster.myfirstweb.dto.JobRequest;
import vn.techmaster.myfirstweb.model.Job;
import vn.techmaster.myfirstweb.service.JobService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/job")
public class JobController {
    private ConcurrentHashMap<String, Job> jobs;

    public JobController() {
        jobs = new ConcurrentHashMap<>();
        jobs.put("J1", new Job("J1", "Waiter", "abc", "Hanoi", 5000000, 12000000, "abc@gmail.com"));
        jobs.put("J2", new Job("J2", "Cleaner", "def", "Hai Phong", 4500000, 18000000, "def@gmail.com"));
    }

    @GetMapping()
    public List<Job> getJobs() {
        return jobs.values().stream().toList();
    }

    @GetMapping("/sortbylocation")
    public List<Job> getJobsSortByLocation() {
        List<Job> result = jobs.values().stream().collect(Collectors.toList());
        Collections.sort(result, (o1, o2) -> o1.getLocation().compareTo(o2.getLocation()));
        return result;
    }

    @GetMapping("/salary/{salary}")
    public List<Job> getJobsBySalary(@PathVariable("salary") int salary) {
        return jobs.values().stream()
                .filter(job -> job.getMin_salary() <= salary && salary <= job.getMax_salary())
                .toList();
    }

    @GetMapping("/keyword/{keyword}")
    public List<Job> getJobsByKeyWord(@PathVariable("keyword") String keyword) {
        return jobs.values().stream()
                .filter(job -> JobService.containsKeyWord(job, keyword))
                .toList();
    }

    @GetMapping("/query")
    public List<Job> getJobsByKeyWord(
            @RequestParam("location") String location,
            @RequestParam("keyword") String keyword) {
        return jobs.values().stream()
                .filter(job -> job.getLocation().equalsIgnoreCase(location)
                        && JobService.containsKeyWord(job, keyword))
                .toList();
    }

    @PostMapping()
    public Job createJob(@RequestBody JobRequest jobRequest) {
        String id = UUID.randomUUID().toString();
        Job newJob = new Job(id, jobRequest.title(), jobRequest.description(), jobRequest.location(),
                jobRequest.min_salary(), jobRequest.max_salary(), jobRequest.email_to());
        jobs.put(id, newJob);
        return newJob;
    }

    @PutMapping("/{id}")
    public Job updateJob(@PathVariable("id") String id, @RequestBody JobRequest jobRequest) {
        Job updatedJob = new Job(id, jobRequest.title(), jobRequest.description(), jobRequest.location(),
                jobRequest.min_salary(), jobRequest.max_salary(), jobRequest.email_to());
        jobs.put(id, updatedJob);
        return updatedJob;
    }

    @DeleteMapping("/{id}")
    public Job deleteJob(@PathVariable("id") String id) {
        return jobs.remove(id);
    }
}
