package com.example.schedulemanagement.service;

import com.example.schedulemanagement.dto.SMRequestDto;
import com.example.schedulemanagement.dto.SMResponseDto;

import java.util.List;

public interface SMService {
    //요청받은 값을 저장하는 메서드
    SMResponseDto saveSM(SMRequestDto dto);

    List<SMResponseDto> findAllSMs(String date, String name);

    SMResponseDto findSMById(Long id);

    SMResponseDto updateSMByPassword(Long id, String todo, String name, String password, String date);

    void deleteSM(Long id, String password);
}
