package com.example.schedulemanagement.repository;

import com.example.schedulemanagement.dto.SMResponseDto;
import com.example.schedulemanagement.entity.ScheduleManagement;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Repository
public class JdbcTemplateSMRepository implements SMRepository {
    //jdbc를 사용하기 위해서 JdbcTemplate 객체를 받는다.
    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateSMRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    //값을 저장하기 위한 메서드
    @Override
    public SMResponseDto saveSM(ScheduleManagement sm) {
        SimpleJdbcInsert jdbcInsert=new SimpleJdbcInsert(jdbcTemplate);
        //scheduel테이블에 id를 열 값으로 저장(id는 자동으로 증가한다.)
        jdbcInsert.withTableName("scheduel").usingGeneratedKeyColumns("id");

        //saveSM을 통해 응답 받은 데이터들을 테이블에 저장한다.
        Map<String, Object> params = new HashMap<>();
        params.put("todo", sm.getTodo());
        params.put("name", sm.getName());
        params.put("password", sm.getPassword());
        params.put("date", sm.getDate());

        Number key=jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(params));

        return new SMResponseDto(key.longValue(), sm.getTodo(), sm.getName(), sm.getPassword(), sm.getDate());
    }

    //date와 name의 값을 입력 받아 해당 값을 만족하는 데이터를 조회
    @Override
    public List<SMResponseDto> findAllSchedules(String date, String name) {
        String sql="select * from scheduel where 1=1";
        List<Object> params = new ArrayList<>();

        //date나 name의 값이 둘 중 하나만 있거나 둘 다 있는 경우 해당 sql문을 추가한다.
        if(date!=null){
            sql+=" and date like ?";
            params.add(date+"%");
        }
        if(name!=null && !name.isEmpty()){
            sql+=" and name = ?";
            params.add(name);
        }
        //일정을 기준으로 내림차순으로 정렬한다.
        sql+=" order by date desc";

        return jdbcTemplate.query(sql, params.toArray(),smRowMapper());
    }

    //입력받은 id 값을 통해서 id와 일치하는 데이터들을 조회한다.
    @Override
    public Optional<ScheduleManagement> findSMById(Long id) {
        List<ScheduleManagement> result = jdbcTemplate.query("select * from scheduel where id = ?", smRowMapperV2(), id);

        return result.stream().findAny();
    }

    //id와 password 값이 일치하는 데이터를 수정한다.
    @Override
    public int updateSM(Long id, String password, String todo, String name, String date) {
        return jdbcTemplate.update("update scheduel set todo = ?, name = ?, date = ? where id = ? and password = ?", todo, name, date, id, password);
    }

    //id와 password가 일치하는 데이터를 삭제한다.
    @Override
    public int deleteSM(Long id, String password) {
        return jdbcTemplate.update("delete from scheduel where id = ? and password = ?", id, password);
    }

    private RowMapper<SMResponseDto> smRowMapper() {
        return new RowMapper<SMResponseDto>() {
            @Override
            public SMResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new SMResponseDto(
                        rs.getLong("id"),
                        rs.getString("todo"),
                        rs.getString("name"),
                        rs.getString("password"),
                        rs.getString("date")
                );
            }
        };
    }

    private RowMapper<ScheduleManagement> smRowMapperV2() {
        return new RowMapper<ScheduleManagement>() {
            @Override
            public ScheduleManagement mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new ScheduleManagement(
                        rs.getLong("id"),
                        rs.getString("todo"),
                        rs.getString("name"),
                        rs.getString("password"),
                        rs.getString("date")
                );
            }
        };
    }
}
