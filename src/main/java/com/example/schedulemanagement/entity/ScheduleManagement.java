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

    //해당 생성자를 불러와서 객체에 값을 저장
    public ScheduleManagement(
            String todo,
            String name,
            String password
    ) {
        this.todo=todo;
        this.name=name;
        this.password=password;
        //날짜는 작성이 된 시간을 기준으로 하기 때문에 작성이 된 시간을 date 객체에 저장한다.
        this.date=LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
