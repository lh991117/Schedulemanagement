package com.example.schedulemanagement.entity;

import com.example.schedulemanagement.dto.SMRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class ScheduelManagement {
    @Setter
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

    public void updateSMById(String todo, String name){
        this.todo=todo;
        this.name=name;
    }
}
