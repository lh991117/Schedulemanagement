package com.example.schedulemanagement.service;

import com.example.schedulemanagement.dto.SMRequestDto;
import com.example.schedulemanagement.dto.SMResponseDto;
import com.example.schedulemanagement.entity.ScheduleManagement;
import com.example.schedulemanagement.repository.SMRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class SMServiceImpl implements SMService{
    private final SMRepository smRepository;

    public SMServiceImpl(SMRepository smRepository) {
        this.smRepository = smRepository;
    }

    @Override
    public SMResponseDto saveSM(SMRequestDto dto) {
        ScheduleManagement sm=new ScheduleManagement(dto.getTodo(), dto.getName(), dto.getPassword());

        return smRepository.saveSM(sm);
    }

    @Override
    public List<SMResponseDto> findAllSMs() {
        List<SMResponseDto> allSMs=smRepository.findAllMemos();

        return allSMs;
    }

    @Override
    public List<SMResponseDto> findAllSMs(String date, String name) {
        return smRepository.findAllSchedules(date, name);
    }

    @Override
    public SMResponseDto findSMById(Long id) {
        Optional<ScheduleManagement> optionalSM=smRepository.findSMById(id);

        if(optionalSM.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = "+id);
        }

        return new SMResponseDto(optionalSM.get());
    }

    @Override
    public SMResponseDto updateSMByPassword(Long id, String todo, String name, String password, String date) {
        if(todo==null || name==null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Todo and Name are required value.");
        }

        int updateRow=smRepository.updateSM(id, password, todo, name, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        if(updateRow==0){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No data has been modified.");
        }

        return new SMResponseDto(smRepository.findSMById(id).get());
    }

    @Override
    public void deleteSM(Long id, String password) {
        int deleteRow=smRepository.deleteSM(id, password);

        if(deleteRow==0){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist password = "+password);
        }
    }
}
