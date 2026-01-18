package com.gustavoallves.cvanalyzer.repository;

import com.gustavoallves.cvanalyzer.enums.JobLevel;
import com.gustavoallves.cvanalyzer.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {

    List<Job> findByLevel(JobLevel level);

    List<Job> findByTitleContainingIgnoreCase(String title);

}
