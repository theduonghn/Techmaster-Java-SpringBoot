package vn.techmaster.myfirstweb.service;

import vn.techmaster.myfirstweb.model.Job;

public class JobService {
    public static boolean containsKeyWord(Job job, String keyword) {
        return job.getTitle().toLowerCase().contains(keyword.toLowerCase())
                || job.getDescription().toLowerCase().contains(keyword.toLowerCase());
    }
}
