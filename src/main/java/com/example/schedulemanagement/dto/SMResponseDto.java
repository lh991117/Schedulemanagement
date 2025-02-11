package com.example.schedulemanagement.dto;

import com.example.schedulemanagement.entity.ScheduleManagement;
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

    //ScheduleManagement에 있는 값을 가져와 응답한다.
    public SMResponseDto(ScheduleManagement scheduleManagement) {
        this.id = scheduleManagement.getId();
        this.todo = scheduleManagement.getTodo();
        this.name = scheduleManagement.getName();
        this.password = scheduleManagement.getPassword();
        this.date = scheduleManagement.getDate();
    }
}
