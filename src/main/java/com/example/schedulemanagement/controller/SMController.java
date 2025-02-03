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

    //요청 받은 값을 저장
    @PostMapping
    public ResponseEntity<SMResponseDto> createSM(@RequestBody SMRequestDto dto) {
        return new ResponseEntity<>(smService.saveSM(dto), HttpStatus.CREATED);
    }

    //사용하는 유저에게 date나 name 값을 입력 받고 조회하여 출력
    //date와 name값이 null 값으로 들어올 수 있다.(이런 경우에는 전체 조회)
    @GetMapping("/search")
    public ResponseEntity<List<SMResponseDto>> findAllSMs(@RequestParam(required = false) String date, @RequestParam(required = false) String name) {
        return new ResponseEntity<>(smService.findAllSMs(date, name), HttpStatus.OK);
    }

    //사용하는 유저에게 id값을 입력 받고 조회하여 출력
    @GetMapping("/{id}")
    public ResponseEntity<SMResponseDto> findSMById(@PathVariable Long id) {
        return new ResponseEntity<>(smService.findSMById(id), HttpStatus.OK);
    }

    //사용하는 유저에게 id, password 값을 입력 받고 해당 위치의 값을 수정
    @PatchMapping("/{id}/{password}")
    public ResponseEntity<SMResponseDto> updateSMById(@PathVariable Long id,@PathVariable String password, @RequestBody SMRequestDto dto) {
        return new ResponseEntity<>(smService.updateSMByPassword(id, dto.getTodo(), dto.getName(), password, dto.getDate()), HttpStatus.OK);
    }

    //사용하는 유저에게 id, password 값을 입력 받고 해당 위치의 값을 삭제
    @DeleteMapping("/{id}/{password}")
    public ResponseEntity<Void> deleteSM(@PathVariable Long id,@PathVariable String password) {
        smService.deleteSM(id, password);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
