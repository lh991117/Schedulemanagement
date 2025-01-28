package com.example.schedulemanagement.dto;

import com.example.schedulemanagement.entity.ScheduelManagement;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SMResponseDto {
    private Long id;
    private String todo;
    private String name;
    private String password;
    private String date;

    public SMResponseDto(ScheduelManagement scheduelManagement) {
        this.id = scheduelManagement.getId();
        this.todo=scheduelManagement.getTodo();
        this.name=scheduelManagement.getName();
        this.password=scheduelManagement.getPassword();
        this.date=scheduelManagement.getDate();
    }
}
