package com.sarthak.jobportal.repository;

import com.sarthak.jobportal.model.Application;
import com.sarthak.jobportal.model.Job;
import com.sarthak.jobportal.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

    List<Application> findByUser(User user);

    Optional<Application> findByUserAndJob(User user, Job job);

    List<Application> findByJob(Job job);

}
