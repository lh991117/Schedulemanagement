package com.example.schedulemanagement.service;

import com.example.schedulemanagement.dto.SMRequestDto;
import com.example.schedulemanagement.dto.SMResponseDto;
import com.example.schedulemanagement.entity.ScheduelManagement;
import com.example.schedulemanagement.repository.SMRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class SMServiceImpl implements SMService{
    private final SMRepository smRepository;

    public SMServiceImpl(SMRepository smRepository) {
        this.smRepository = smRepository;
    }

    @Override
    public SMResponseDto saveSM(SMRequestDto dto) {
        ScheduelManagement sm=new ScheduelManagement(dto.getTodo(), dto.getName(), dto.getPassword(), dto.getDate());

        ScheduelManagement savedSM=smRepository.saveSM(sm);

        return new SMResponseDto(savedSM);
    }

    @Override
    public List<SMResponseDto> findAllSMs() {
        List<SMResponseDto> allSMs=smRepository.findAllMemos();

        return allSMs;
    }

    @Override
    public SMResponseDto findSMById(Long id) {
        ScheduelManagement sm=smRepository.findSMById(id);

        return new SMResponseDto(sm);
    }

    @Override
    public SMResponseDto updateSMById(Long id, String todo, String name, String password, String date) {
        ScheduelManagement sm=smRepository.findSMById(id);

        if(sm==null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = "+id);
        }

        if(todo==null || name==null || password!=null || date!=null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Todo and Name are required value.");
        }

        sm.updateSMById(todo, name);

        return new SMResponseDto(sm);
    }

    @Override
    public void deleteSM(Long id) {
        ScheduelManagement sm=smRepository.findSMById(id);

        if(sm==null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = "+id);
        }

        smRepository.deleteSM(id);
    }
}
