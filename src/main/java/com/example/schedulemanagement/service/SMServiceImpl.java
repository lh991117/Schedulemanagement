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

    //요청 받은 값을 ScheduleManagement에 저장하여 smRepository에 있는 saveSM메서드로 옮긴다.
    @Override
    public SMResponseDto saveSM(SMRequestDto dto) {
        ScheduleManagement sm=new ScheduleManagement(dto.getTodo(), dto.getName(), dto.getPassword());

        return smRepository.saveSM(sm);
    }

    //입력 받은 date, name 값을 smRepository의 findAllSchedules메서드로 넘긴다.
    @Override
    public List<SMResponseDto> findAllSMs(String date, String name) {
        return smRepository.findAllSchedules(date, name);
    }

    //입력 받은 id 값을 smRepository의 findSMById메서드로 넘긴다.
    @Override
    public SMResponseDto findSMById(Long id) {
        Optional<ScheduleManagement> optionalSM=smRepository.findSMById(id);

        //해당되는 id에 데이터가 없다면 아래의 오류를 출력한다.
        if(optionalSM.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = "+id);
        }

        return new SMResponseDto(optionalSM.get());
    }

    //id, 수정된 todo, 수정된 name, password, date값을 가져와서 smRepository의 update메서드에 값을 넘기고 값을 보여주기 위해서 findSMById를 통해 값을 반환한다.
    @Override
    public SMResponseDto updateSMByPassword(Long id, String todo, String name, String password, String date) {
        if(todo==null || name==null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Todo and Name are required value.");
        }

        //날짜는 수정된 날짜를 넣어야하기 때문에 수정한 시간을 date에 저장한다.
        int updateRow=smRepository.updateSM(id, password, todo, name, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        if(updateRow==0){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No data has been modified.");
        }

        return new SMResponseDto(smRepository.findSMById(id).get());
    }

    //입력된 id, password를 smRepository의 deleteSM메서드에 넘겨서 실행시킨다.
    @Override
    public void deleteSM(Long id, String password) {
        int deleteRow=smRepository.deleteSM(id, password);

        if(deleteRow==0){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist password = "+password);
        }
    }
}
