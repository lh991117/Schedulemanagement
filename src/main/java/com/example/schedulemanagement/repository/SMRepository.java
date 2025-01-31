package com.example.schedulemanagement.repository;

import com.example.schedulemanagement.dto.SMResponseDto;
import com.example.schedulemanagement.entity.ScheduleManagement;

import java.util.*;

public interface SMRepository {
    SMResponseDto saveSM(ScheduleManagement sm);

    List<SMResponseDto> findAllMemos();

    Optional<ScheduleManagement> findSMById(Long id);

    int updateSM(Long id, String todo, String name);

    int deleteSM(Long id);
}
