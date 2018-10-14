package com.tuxzx.dal.rowMapper;

import com.tuxzx.domain.Student;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentMapper implements RowMapper<Student> {
    @Override
    public Student mapRow(ResultSet resultSet, int i) throws SQLException {
        Student student = new Student();
        student.setId(resultSet.getString("stu_id"));
        student.setName(resultSet.getString("stu_name"));
        student.setGender(resultSet.getString("stu_gender"));
        student.setAge(resultSet.getInt("stu_age"));
        student.setTel(resultSet.getString("stu_tel"));
        student.setPro(resultSet.getString("stu_pro"));
        student.setInTime(resultSet.getDate("stu_intime"));
        return student;
    }
}
