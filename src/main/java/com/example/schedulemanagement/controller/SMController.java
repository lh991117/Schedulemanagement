package com.example.schedulemanagement.controller;

import com.example.schedulemanagement.dto.SMRequestDto;
import com.example.schedulemanagement.dto.SMResponseDto;
import com.example.schedulemanagement.entity.ScheduelManagement;
import com.example.schedulemanagement.service.SMService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/scheduels")
public class SMController {
    private final SMService smService;

    public SMController(SMService smService) {
        this.smService = smService;
    }

    @PostMapping
    public ResponseEntity<SMResponseDto> createSM(@RequestBody SMRequestDto dto) {

        return new ResponseEntity<>(smService.saveSM(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public List<SMResponseDto> findAllSMs() {
        return smService.findAllSMs();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SMResponseDto> findSMById(@PathVariable Long id) {
        return new ResponseEntity<>(smService.findSMById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SMResponseDto> updateSMById(@PathVariable Long id, @RequestBody SMRequestDto dto) {
        return new ResponseEntity<>(smService.updateSMById(id, dto.getTodo(), dto.getName(), dto.getPassword(), dto.getDate()), HttpStatus.OK);
    }

    @DeleteMapping("{/id}")
    public ResponseEntity<Void> deleteSM(@PathVariable Long id) {
        smService.deleteSM(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
