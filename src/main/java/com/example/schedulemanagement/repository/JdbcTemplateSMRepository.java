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
    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateSMRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public SMResponseDto saveSM(ScheduleManagement sm) {
        SimpleJdbcInsert jdbcInsert=new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("scheduel").usingGeneratedKeyColumns("id");

        Map<String, Object> params = new HashMap<>();
        params.put("todo", sm.getTodo());
        params.put("name", sm.getName());
        params.put("password", sm.getPassword());
        params.put("date", sm.getDate());

        Number key=jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(params));

        return new SMResponseDto(key.longValue(), sm.getTodo(), sm.getName(), sm.getPassword(), sm.getDate());
    }

    @Override
    public List<SMResponseDto> findAllMemos() {
        return jdbcTemplate.query("select * from scheduel", smRowMapper());
    }

    @Override
    public Optional<ScheduleManagement> findSMById(Long id) {
        List<ScheduleManagement> result = jdbcTemplate.query("select * from scheduel where id = ?", smRowMapperV2(), id);

        return result.stream().findAny();
    }

    @Override
    public int updateSM(Long id, String todo, String name) {
        return jdbcTemplate.update("update scheduel set todo = ?, name = ? where id = ?", todo, name, id);
    }

    @Override
    public int deleteSM(Long id) {
        return jdbcTemplate.update("delete from scheduel where id = ?", id);
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
