package com.jcibardo.blog.blog.web.rest;

import com.jcibardo.blog.blog.domain.Item;
import com.jcibardo.blog.blog.repository.ItemRepository;
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
public class ItemResource {
    private static final String ENTITY_NAME = "ITEM";

    @Autowired
    private ItemRepository itemRepository;

    @Value("${spring.application.name}")
    private String applicationName;

    @PostMapping(path="/item", produces = {"application/json"})
    public ResponseEntity<Item> createItem(@RequestBody Item item) throws URISyntaxException {
        Item result = itemRepository.save(item);
        return ResponseEntity
                .created(new URI("/blog/item/" + result.getItem_id()))
                .body(result);
    }

    @PostMapping(path="/items", produces = {"application/json"})
    public ResponseEntity<List<Item>> createItems(@RequestBody List<Item> items) throws URISyntaxException {
        itemRepository.saveAll(items);
        return ResponseEntity
                .ok()
                .body(items);
    }

    @PutMapping(path="/item/{id}", produces = {"application/json"})
    public ResponseEntity<Item> updateItem(@PathVariable(value="id", required=false) Long id, @RequestBody Item item) throws URISyntaxException {
        Item it = itemRepository.findById(id).get();
        it.setImg(item.getImg());
        it.setTitle(item.getTitle());
        Item result = itemRepository.save(it);
        return ResponseEntity
                .ok()
                .body(result);
    }

    @GetMapping(path="/item", produces = {"application/json"})
    public ResponseEntity<List<Item>> getAllItems(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        Page<Item> page = itemRepository.findAll(pageable);
        return ResponseEntity.ok().body(page.getContent());
    }

    @GetMapping(path="/item/{id}", produces = {"application/json"})
    public ResponseEntity<Item> getItem(@PathVariable("id") Long id) {
        Optional<Item> item = itemRepository.findById(id);
        return item.map(value -> ResponseEntity.ok().body(value)).orElseGet(() -> ResponseEntity.badRequest().body(null));
    }

    @DeleteMapping(path="/item/{id}", produces = {"application/json"})
    public ResponseEntity<Item> deleteItem(@PathVariable("id") Long id) {
        itemRepository.deleteById(id);
        return ResponseEntity
                .noContent()
                .build();
    }
}