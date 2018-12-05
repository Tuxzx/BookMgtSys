package com.tuxzx.dal.impl;

import com.tuxzx.dal.UserDao;
import com.tuxzx.dal.rowMapper.ManagerMapper;
import com.tuxzx.dal.rowMapper.StudentMapper;
import com.tuxzx.domain.Manager;
import com.tuxzx.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
public class UserDaoImpl implements UserDao {

    // 用户

    private static final String QUERY_STUID_COUNT = "select count(stu_id) from student_info where stu_id = ?;";
    private static final String QUERY_USER_LOGIN = "select count(stu_id) from student_info where stu_id = ? and stu_password = ?;";
    private static final String QUERY_USER_INFO = "select * from student_info where stu_id = ?;";
    private static final String QUERY_USER_INFO_ALL = "select * from student_info where stu_name regexp ?;";
    private static final String UPDATE_USER_TEL = "UPDATE student_info SET stu_tel=? WHERE stu_id = ?;";

    // 管理员

    private static final String QUERY_MANAGERID_COUNT = "select count(manager_id) from manager_info where manager_id = ?;";
    private static final String QUERY_MANAGER_LOGIN = "select count(manager_id) from manager_info where manager_id= ? and manager_password= ?;";
    private static final String QUERY_MANAGER_INFO = "select * from manager_info where manager_id = ?;";
    private static final String INSERT_STU_INFO = "insert into student_info (stu_id, stu_name, stu_password, stu_gender, stu_age, stu_tel, stu_pro, stu_intime) values (?,?,?,?,?,?,?,?);";
    private static final String INSERT_MANAGER_INFO = "insert into manager_info (manager_id, manager_name, manager_password, manager_office, manager_age, manager_tel) values (?,?,?,?,?,?);";

    private static final String UPDATE_USER_INFO = "update student_info set stu_name=?, stu_gender=?, stu_age=?, stu_tel=?, stu_pro=?, stu_intime=? where stu_id=?;";
    private static final String UPDATE_MANAGER_INFO = "update manager_info set manager_name = ?, manager_office = ?, manager_age = ?, manager_tel=? where manager_id = ?;";

    private static final String UPDATE_USER_PASSWORD = "update student_info set stu_password=? where stu_id = ?;";
    private static final String UPDATE_MANAGER_PASSWORD = "update manager_info set manager_password=? where manager_id = ?;";

    private static final String DELETE_USER_INFO = "delete from student_info where stu_id = ?;";
    private static final String DELETE_MANAGER_INFO = "delete from manager_info where manager_id = ?;";

    private static final String QUERY_HOMEPAGE_INFO_PART1 = "select count(borrow_info.stu_id) from borrow_info, return_info where borrow_info.borrow_id = return_info.borrow_id and borrow_info.stu_id = ? ;";
    private static final String QUERY_HOMEPAGE_INFO_PART2 = "select count(stu_id) from borrow_info where stu_id = ?";

    private static final String QUERY_NOTIFICATION_ALL = "select * from  notification order by id desc;";
    private static final String QUERY_NOTIFICATION_NEWER = "select * from  notification order by id desc limit 1;";
    private static final String INSERT_NOTIFICATION = "insert into notification (content) values (?);";


    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public boolean isUserExist(String stuId) {
        return jdbcTemplate.queryForObject(QUERY_STUID_COUNT, new Object[]{stuId}, Integer.class)>0?true:false;
    }

    @Override
    public boolean matchUser(String stuId, String stuPw) {
        return jdbcTemplate.queryForObject(QUERY_USER_LOGIN, new Object[]{stuId, stuPw}, Integer.class)>0?true:false;
    }

    @Override
    public Student getStudent(String stuId) {
        if (isUserExist(stuId)) {
            return jdbcTemplate.queryForObject(QUERY_USER_INFO, new Object[]{stuId}, new StudentMapper());
        } else {
            return null;
        }
    }

    @Override
    public List<Student> getStudents(String stuName) {
        return jdbcTemplate.query(QUERY_USER_INFO_ALL, new Object[]{stuName}, new StudentMapper());
    }

    @Override
    public boolean updateTel(String stuId, String tel) {
        return jdbcTemplate.update(UPDATE_USER_TEL, new Object[]{tel})>0?true:false;
    }


    //****************************

    @Override
    public boolean isManagerExist(String managerId) {
        return jdbcTemplate.queryForObject(QUERY_MANAGERID_COUNT,new Object[]{managerId}, Integer.class)>0?true:false;
    }

    @Override
    public boolean matchManager(String managerId, String password) {
        return jdbcTemplate.queryForObject(QUERY_MANAGER_LOGIN, new Object[]{managerId, password}, Integer.class)>0?true:false;
    }

    @Override
    public Manager getManager(String managerId) {
        return jdbcTemplate.queryForObject(QUERY_MANAGER_INFO, new Object[]{managerId}, new ManagerMapper());
    }

    @Override
    public boolean addStudent(Student student, String password) {
        if (isUserExist(student.getId())){
            return false;
        }
        return jdbcTemplate.update(INSERT_STU_INFO,
                new Object[]{student.getId(),student.getName(),password,student.getGender(),
                        student.getAge(),student.getTel(),student.getPro(),student.getInTime()})>0?true:false;
    }

    @Override
    public boolean addManager(Manager manager, String password) {
        return jdbcTemplate.update(INSERT_MANAGER_INFO,
                new Object[]{manager.getId(),manager.getName(),password,manager.getOffice(),manager.getAge(),manager.getTel()})>0?true:false;
    }

    @Override
    public boolean updateStudent(String stuId, Student student) {
        return jdbcTemplate.update(UPDATE_USER_INFO,
                new Object[]{student.getName(),student.getGender(),student.getAge(),student.getTel(),student.getPro(),student.getInTime(),stuId})>0?true:false;
    }

    @Override
    public boolean updateManager(String managerId, Manager manager) {
        return jdbcTemplate.update(UPDATE_MANAGER_INFO,
                new Object[]{manager.getName(),manager.getOffice(),manager.getAge(),manager.getTel(),managerId})>0?true:false;
    }

    @Override
    public boolean resetPassword(String id, String password, boolean isAdmin) {
        if (isAdmin)
            return jdbcTemplate.update(UPDATE_MANAGER_PASSWORD,new Object[]{password, id})>0?true:false;
        else
            return jdbcTemplate.update(UPDATE_USER_PASSWORD,new Object[]{password, id})>0?true:false;
    }

    @Override
    public boolean removeUser(String id, boolean isAdmin) {
        if (isAdmin)
            return jdbcTemplate.update(DELETE_MANAGER_INFO,new Object[]{id})>0?true:false;
        else
            return jdbcTemplate.update(DELETE_USER_INFO,new Object[]{id})>0?true:false;
    }

    @Override
    public Map<String, Object> getHomeInfo(String stuId) {
        Map<String, Object> map = new HashMap<>();
        Student student = getStudent(stuId);
        if (student == null) {return null;}
        map.put("nickname",student.getName());
        int hasBeenReturn = jdbcTemplate.queryForObject(QUERY_HOMEPAGE_INFO_PART1, new Object[]{stuId}, Integer.class);
        int borrowCount = jdbcTemplate.queryForObject(QUERY_HOMEPAGE_INFO_PART2, new Object[]{stuId}, Integer.class);
        map.put("inBorrow", borrowCount-hasBeenReturn);
        map.put("borrowCount", borrowCount);
        map.put("notification",getNewerNotification());
        return map;
    }

    @Override
    public List<String> getAllNotification() {
        final List<String> strList = new ArrayList<>();
        jdbcTemplate.query(QUERY_NOTIFICATION_ALL, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                strList.add(rs.getString("content"));
            }
        });
        if (strList.size()>0) {
            return strList;
        }else {
            return null;
        }
    }

    @Override
    public String getNewerNotification() {
        final String[] str = new String[1];
        jdbcTemplate.query(QUERY_NOTIFICATION_NEWER, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                str[0] = rs.getString("content");
            }
        });
        return str[0];
    }

    public boolean updateNotification(String str) {
        return jdbcTemplate.update(INSERT_NOTIFICATION, new Object[]{str})>0?true:false;
    }


}
