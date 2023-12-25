package com.jcibardo.blog.blog.web.rest;

import com.jcibardo.blog.blog.domain.Journey;
import com.jcibardo.blog.blog.repository.JourneyRepository;
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
public class JourneyResource {

    private static final String ENTITY_NAME = "JOURNEY";

    @Autowired
    private JourneyRepository journeyRepository;

    @Value("${spring.application.name}")
    private String applicationName;

    @PostMapping(path="/journey", produces = {"application/json"})
    public ResponseEntity<Journey> createDaterange(@RequestBody Journey journey) throws URISyntaxException {
        Journey result = journeyRepository.save(journey);
        return ResponseEntity
                .created(new URI("/blog/journey/" + result.getJourney_id()))
                .body(result);
    }

    @PostMapping(path="/journeys", produces = {"application/json"})
    public ResponseEntity<List<Journey>> createDaterange(@RequestBody List<Journey> journeys) throws URISyntaxException {
        journeyRepository.saveAll(journeys);
        return ResponseEntity
                .ok()
                .body(journeys);
    }

    @PutMapping(path="/journey/{id}", produces = {"application/json"})
    public ResponseEntity<Journey> updateJourney(@PathVariable(value="id", required=false) Long id, @RequestBody Journey journey) throws URISyntaxException {
        /*Journey j = journeyRepository.findById(id).get();
        j.setDates(journey.getDates());
        j.setImage(journey.getImage());
        j.setPlace(journey.getPlace());*/
        Journey result = journeyRepository.save(journey);
        return ResponseEntity
                .ok()
                .body(result);
    }

    @GetMapping(path="/journey", produces = {"application/json"})
    public ResponseEntity<List<Journey>> getAllJourneys(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        Page<Journey> page = journeyRepository.findAll(pageable);
        return ResponseEntity.ok().body(page.getContent());
    }

    @GetMapping(path="/journey/{id}", produces = {"application/json"})
    public ResponseEntity<Journey> getJourney(@PathVariable("id") Long id) {
        Optional<Journey> journey = journeyRepository.findById(id);
        return journey.map(value -> ResponseEntity.ok().body(value)).orElseGet(() -> ResponseEntity.badRequest().body(null));
    }

    @DeleteMapping(path="/journey/{id}", produces = {"application/json"})
    public ResponseEntity<Journey> deleteJourney(@PathVariable("id") Long id) {
        journeyRepository.deleteById(id);
        return ResponseEntity
                .noContent()
                .build();
    }
}
