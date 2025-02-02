package com.example.schedulemanagement.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@AllArgsConstructor
public class ScheduleManagement {
    private Long id;
    private String todo;
    private String name;
    private String password;
    private String date;

    public ScheduleManagement(
            String todo,
            String name,
            String password
    ) {
        this.todo=todo;
        this.name=name;
        this.password=password;
        this.date=LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
