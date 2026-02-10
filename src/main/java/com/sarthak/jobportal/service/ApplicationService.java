package com.sarthak.jobportal.service;

import com.sarthak.jobportal.model.Application;
import com.sarthak.jobportal.model.Job;
import com.sarthak.jobportal.model.User;
import com.sarthak.jobportal.repository.ApplicationRepository;
import com.sarthak.jobportal.repository.JobRepository;
import com.sarthak.jobportal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JobRepository jobRepository;

    public Application applyToJob(Long userId, Long jobId) {
        Optional<User> userOptional = userRepository.findById(userId);
        Optional<Job> jobOptional = jobRepository.findById(jobId);

        if (userOptional.isEmpty() || jobOptional.isEmpty()) {
            throw new RuntimeException("User or Job not found");
        }

        User user = userOptional.get();
        Job job = jobOptional.get();

        // Check duplicate application
        Optional<Application> existing =
                applicationRepository.findByUserAndJob(user, job);

        if (existing.isPresent()) {
            throw new RuntimeException("You have already applied to this job");
        }

        Application application = new Application();
        application.setUser(user);
        application.setJob(job);
        application.setStatus("APPLIED");
        application.setAppliedAt(LocalDateTime.now());

        return applicationRepository.save(application);
    }


    public List<Application> getApplicationsByUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return applicationRepository.findByUser(user);
    }

    public List<Application> getApplicationsByJob(Long jobId) {
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found"));

        return applicationRepository.findByJob(job);
    }

}
