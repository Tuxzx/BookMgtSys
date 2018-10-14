package com.tuxzx.dal.rowMapper;

import com.tuxzx.domain.Manager;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ManagerMapper implements RowMapper<Manager> {
    @Override
    public Manager mapRow(ResultSet resultSet, int i) throws SQLException {
        Manager manager = new Manager();
        manager.setId(resultSet.getString("manager_id"));
        manager.setName(resultSet.getString("manager_name"));
        manager.setOffice(resultSet.getString("manager_office"));
        manager.setAge(resultSet.getInt("manager_age"));
        manager.setTel(resultSet.getString("manager_tel"));
        return manager;
    }
}
