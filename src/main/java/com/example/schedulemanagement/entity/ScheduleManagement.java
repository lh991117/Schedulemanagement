package com.example.schedulemanagement.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

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
            String password,
            String date) {
        this.todo=todo;
        this.name=name;
        this.password=password;
        this.date=date;
    }

    public void updateSMById(String todo, String name){
        this.todo=todo;
        this.name=name;
    }
}
