package com.example.schedulemanagement.controller;

import com.example.schedulemanagement.dto.SMRequestDto;
import com.example.schedulemanagement.dto.SMResponseDto;
import com.example.schedulemanagement.service.SMService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/schedules")
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

    @GetMapping("/search")
    public ResponseEntity<List<SMResponseDto>> findAllSMs(@RequestParam(required = false) String date, @RequestParam(required = false) String name) {
        return new ResponseEntity<>(smService.findAllSMs(date, name), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SMResponseDto> findSMById(@PathVariable Long id) {
        return new ResponseEntity<>(smService.findSMById(id), HttpStatus.OK);
    }

    @PatchMapping("/{id}/{password}")
    public ResponseEntity<SMResponseDto> updateSMById(@PathVariable Long id,@PathVariable String password, @RequestBody SMRequestDto dto) {
        return new ResponseEntity<>(smService.updateSMByPassword(id, dto.getTodo(), dto.getName(), password, dto.getDate()), HttpStatus.OK);
    }

    @DeleteMapping("/{id}/{password}")
    public ResponseEntity<Void> deleteSM(@PathVariable Long id,@PathVariable String password) {
        smService.deleteSM(id, password);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
