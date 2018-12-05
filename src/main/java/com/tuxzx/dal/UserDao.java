package com.tuxzx.dal;

import com.tuxzx.domain.Manager;
import com.tuxzx.domain.Student;

import java.util.List;
import java.util.Map;

public interface UserDao {

    /**
     * 用户是否存在
     * @param stuId 学生id
     * @return true为用户存在
     */
    boolean isUserExist(String stuId);

    /**
     * 登陆
     * @param stuId 学生id
     * @param stuPw 密码
     * @return true为匹配成功
     */
    boolean matchUser(String stuId, String stuPw);

    /**
     * 获取学生信息
     * @param stuId
     * @return
     */
    Student getStudent(String stuId);

    /**
     * 获取学生信息 模糊查询
     * @param stuName
     * @return
     */
    List<Student> getStudents(String stuName);

    /**
     * 更新电话信息
     * @param stuId
     * @param tel
     * @return
     */
    boolean updateTel(String stuId, String tel);


    //管理员权限接口****************************************************************************************************

    /**
     * 管理员是否存在
     * @param managerId
     * @return
     */
    boolean isManagerExist(String managerId);

    /**
     * 登陆
     * @param managerId
     * @param password
     * @return
     */
    boolean matchManager(String managerId, String password);

    /**
     * 获取管理员信息
     * @param managerId
     * @return
     */
    Manager getManager(String managerId);

    /**
     * 添加学生
     * @param student
     * @return
     */
    boolean addStudent(Student student, String password);

    /**
     * 添加管理员
     * @param manager
     * @return
     */
    boolean addManager(Manager manager, String password);

    /**
     * 管理员更新用户信息
     * @param stuId
     * @param student
     * @return
     */
    boolean updateStudent(String stuId, Student student);

    /**
     * 更新管理员信息
     * @param managerId
     * @param manager
     * @return
     */
    boolean updateManager(String managerId, Manager manager);

    /**
     * 重设密码
     * @param id 学生id或管理员id
     * @param password 学生密码或管理员密码
     * @param isAdmin true为管理员 false为学生
     * @return
     */
    boolean resetPassword(String id, String password, boolean isAdmin);

    /**
     * 移除用户
     * @param id 学生id或管理员id
     * @param isAdmin true为管理员 false为学生
     * @return
     */
    boolean removeUser(String id, boolean isAdmin);

    // 用户信息多表查询*************************************************************************************************

    /**
     * 获取主页用户相关信息
     * @param stuId
     * @return
     */
    Map<String, Object> getHomeInfo(String stuId);

    List<String> getAllNotification();

    String getNewerNotification();

    boolean updateNotification(String str);

}
