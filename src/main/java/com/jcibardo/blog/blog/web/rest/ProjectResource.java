package com.jcibardo.blog.blog.web.rest;

import com.jcibardo.blog.blog.domain.Project;
import com.jcibardo.blog.blog.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/jcibardo/api")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost"})
@Transactional
public class ProjectResource {

    private static final String ENTITY_NAME = "PROJECT";

    @Autowired
    private ProjectRepository projectRepository;

    @Value("${spring.application.name}")
    private String applicationName;

    @PostMapping(path="/project", produces = {"application/json"})
    public ResponseEntity<Project> createProject(@RequestBody Project project) throws URISyntaxException {
        Project result = projectRepository.save(project);
        return ResponseEntity
                .created(new URI("/blog/project/" + result.getProject_id()))
                .body(result);
    }

    @PostMapping(path="/projects", produces = {"application/json"})
    public ResponseEntity<List<Project>> createProject(@RequestBody List<Project> projects) throws URISyntaxException {
        projectRepository.saveAll(projects);
        return ResponseEntity
                .ok()
                .body(projects);
    }

    @PutMapping(path="/project/{id}", produces = {"application/json"})
    public ResponseEntity<Project> updateProject(@PathVariable(value="id", required=false) Long id, @RequestBody Project project) throws URISyntaxException {
        Project pr = projectRepository.findById(id).get();
        pr.setTitle(project.getTitle());
        pr.setJourneys(project.getJourneys());
        Project result = projectRepository.save(pr);
        return ResponseEntity
                .ok()
                .body(result);
    }

    @GetMapping(path="/project", produces = {"application/json"})
    public ResponseEntity<List<Project>> getAllProjects(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        Page<Project> page = projectRepository.findAll(pageable);
        return ResponseEntity.ok().body(page.getContent());
    }

    @GetMapping(path="/project/{id}", produces = {"application/json"})
    public ResponseEntity<Project> getProject(@PathVariable("id") Long id) {
        Optional<Project> project = projectRepository.findById(id);
        return project.map(value -> ResponseEntity.ok().body(value)).orElseGet(() -> ResponseEntity.badRequest().body(null));
    }

    @DeleteMapping(path="/project/{id}", produces = {"application/json"})
    public ResponseEntity<Project> deleteProject(@PathVariable("id") Long id) {
        projectRepository.deleteById(id);
        return ResponseEntity
                .noContent()
                .build();
    }
}
