package com.example.schedulemanagement.repository;

import com.example.schedulemanagement.dto.SMResponseDto;
import com.example.schedulemanagement.entity.ScheduelManagement;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface SMRepository {
    ScheduelManagement saveSM(ScheduelManagement sm);

    List<SMResponseDto> findAllMemos();

    ScheduelManagement findSMById(Long id);

    void deleteSM(Long id);
}
