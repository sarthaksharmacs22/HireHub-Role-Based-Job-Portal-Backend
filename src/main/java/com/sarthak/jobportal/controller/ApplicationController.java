package com.sarthak.jobportal.controller;

import com.sarthak.jobportal.model.Application;
import com.sarthak.jobportal.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @PostMapping("/apply")
    public Application apply(
            @RequestParam Long userId,
            @RequestParam Long jobId) {
        return applicationService.applyToJob(userId, jobId);
    }

    @GetMapping("/user/{userId}")
    public List<Application> getUserApplications(@PathVariable Long userId) {
        return applicationService.getApplicationsByUser(userId);
    }

    @GetMapping("/job/{jobId}")
    public List<Application> getJobApplications(@PathVariable Long jobId) {
        return applicationService.getApplicationsByJob(jobId);
    }

}
