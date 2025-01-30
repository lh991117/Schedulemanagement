package com.example.schedulemanagement.repository;

import com.example.schedulemanagement.dto.SMResponseDto;
import com.example.schedulemanagement.entity.ScheduelManagement;

import java.util.*;

public class JdbcTemplateSMRepository implements SMRepository {
    private final Map<Long, ScheduelManagement> smList=new HashMap<>();

    @Override
    public ScheduelManagement saveSM(ScheduelManagement sm) {
        Long smid = smList.isEmpty() ? 1 : Collections.max(smList.keySet())+1;
        sm.setId(smid);

        smList.put(smid, sm);

        return sm;
    }

    @Override
    public List<SMResponseDto> findAllMemos() {
        List<SMResponseDto> allSMs=new ArrayList<>();

        for(ScheduelManagement sm:smList.values()){
            SMResponseDto dto=new SMResponseDto(sm);
            allSMs.add(dto);
        }

        return allSMs;
    }

    @Override
    public ScheduelManagement findSMById(Long id) {
        return smList.get(id);
    }

    @Override
    public void deleteSM(Long id) {
        smList.remove(id);
    }
}
