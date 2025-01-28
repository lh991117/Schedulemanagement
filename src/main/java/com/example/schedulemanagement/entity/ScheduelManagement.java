package com.example.schedulemanagement.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ScheduelManagement {
    private Long id;
    private String todo;
    private String name;
    private String password;
    private String date;

    public ScheduelManagement(
            String todo,
            String name,
            String password,
            String date) {
        this.todo=todo;
        this.name=name;
        this.password=password;
        this.date=date;
    }
}
