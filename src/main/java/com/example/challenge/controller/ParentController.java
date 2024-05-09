package com.example.challenge.controller;

import com.example.challenge.dto.ParentDTO;
import com.example.challenge.service.impl.ParentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/parents")
@RequiredArgsConstructor
public class ParentController {

    private final ParentServiceImpl parentServiceImpl;

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<ParentDTO> createParent(@RequestBody ParentDTO parent) {
        ParentDTO createdParent = parentServiceImpl.createParent(parent);
        return ResponseEntity.ok(createdParent);
    }

    @PutMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<ParentDTO> updateParent(@RequestBody ParentDTO parent) {
        ParentDTO updatedParent = parentServiceImpl.updateParent(parent);
        return ResponseEntity.ok(updatedParent);
    }

    @GetMapping(value = "/{parentId}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ParentDTO> getParentById(@PathVariable Long parentId) {
        ParentDTO existingParent = parentServiceImpl.getParentById(parentId);
        return ResponseEntity.ok(existingParent);
    }
}