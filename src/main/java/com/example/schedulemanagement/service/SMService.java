package com.example.schedulemanagement.service;

import com.example.schedulemanagement.dto.SMRequestDto;
import com.example.schedulemanagement.dto.SMResponseDto;

import java.util.List;

public interface SMService {
    SMResponseDto saveSM(SMRequestDto dto);

    List<SMResponseDto> findAllSMs();

    SMResponseDto findSMById(Long id);

    SMResponseDto updateSMById(Long id, String todo, String name, String password, String date);

    void deleteSM(Long id);
}
