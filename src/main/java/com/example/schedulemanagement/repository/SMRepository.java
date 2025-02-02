package com.example.schedulemanagement.repository;

import com.example.schedulemanagement.dto.SMResponseDto;
import com.example.schedulemanagement.entity.ScheduleManagement;

import java.util.*;

public interface SMRepository {
    SMResponseDto saveSM(ScheduleManagement sm);

    List<SMResponseDto> findAllMemos();

    List<SMResponseDto> findAllSchedules(String date, String name);

    Optional<ScheduleManagement> findSMById(Long id);

    int updateSM(Long id, String password, String todo, String name, String date);

    int deleteSM(Long id, String password);
}
