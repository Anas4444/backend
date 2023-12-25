package com.jcibardo.blog.blog.web.rest;

import com.jcibardo.blog.blog.domain.Daterange;
import com.jcibardo.blog.blog.repository.DaterangeRepository;
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
public class DaterangeResource {

    private static final String ENTITY_NAME = "DATERANGE";

    @Autowired
    private DaterangeRepository daterangeRepository;

    @Value("${spring.application.name}")
    private String applicationName;

    @PostMapping(path="/daterange", produces = {"application/json"})
    public ResponseEntity<Daterange> createDaterange(@RequestBody Daterange daterange) throws URISyntaxException {
        Daterange result = daterangeRepository.save(daterange);
        return ResponseEntity
                .created(new URI("/blog/daterange/" + result.getDaterange_id()))
                .body(result);
    }

    @PostMapping(path="/dateranges", produces = {"application/json"})
    public ResponseEntity<List<Daterange>> createDaterange(@RequestBody List<Daterange> dateranges) throws URISyntaxException {
        daterangeRepository.saveAll(dateranges);
        return ResponseEntity
                .ok()
                .body(dateranges);
    }

    @PutMapping(path="/daterange/{id}", produces = {"application/json"})
    public ResponseEntity<Daterange> updateDaterange(@PathVariable(value="id", required=false) Long id, @RequestBody Daterange daterange) throws URISyntaxException {
        Daterange d_r = daterangeRepository.findById(id).get();
        d_r.setDate_r(daterange.getDate_r());
        d_r.setJourney(daterange.getJourney());
        Daterange result = daterangeRepository.save(d_r);
        return ResponseEntity
                .ok()
                .body(result);
    }

    @GetMapping(path="/daterange", produces = {"application/json"})
    public ResponseEntity<List<Daterange>> getAllDateranges(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        Page<Daterange> page = daterangeRepository.findAll(pageable);
        return ResponseEntity.ok().body(page.getContent());
    }

    @GetMapping(path="/daterange/{id}", produces = {"application/json"})
    public ResponseEntity<Daterange> getDaterange(@PathVariable("id") Long id) {
        Optional<Daterange> daterange = daterangeRepository.findById(id);
        return daterange.map(value -> ResponseEntity.ok().body(value)).orElseGet(() -> ResponseEntity.badRequest().body(null));
    }

    @DeleteMapping(path="/daterange/{id}", produces = {"application/json"})
    public ResponseEntity<Daterange> deleteDaterange(@PathVariable("id") Long id) {
        daterangeRepository.deleteById(id);
        return ResponseEntity
                .noContent()
                .build();
    }
}
