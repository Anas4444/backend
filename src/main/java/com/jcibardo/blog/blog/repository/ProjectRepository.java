package com.jcibardo.blog.blog.repository;

import com.jcibardo.blog.blog.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
