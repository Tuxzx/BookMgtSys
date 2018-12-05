package com.tuxzx.service;


import com.tuxzx.dal.BookDao;
import com.tuxzx.dal.UserDao;
import com.tuxzx.domain.Book;
import com.tuxzx.domain.BookType;
import com.tuxzx.domain.Pub;
import com.tuxzx.domain.Student;
import com.tuxzx.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;



    public String getStudentById(String stuId) {
        Student student = userDao.getStudent(stuId);
        if (student == null) {return null;}
        StringBuilder strHtml = new StringBuilder();
        strHtml.append("<table class=\"table table-striped table-hover\"><thead><tr><th>Id</th><th>姓名</th><th>性别</th><th>年龄</th><th>电话</th><th>专业</th><th>入学时间</th><th>修改</th></tr></thead><tbody>");
        strHtml.append("<tr>");
        strHtml.append("<td>"+student.getId()+"</td>");
        strHtml.append("<td>"+student.getName()+"</td>");
        strHtml.append("<td>"+student.getGender()+"</td>");
        strHtml.append("<td>"+student.getAge()+"</td>");
        strHtml.append("<td>"+student.getTel()+"</td>");
        strHtml.append("<td>"+student.getPro()+"</td>");
        strHtml.append("<td>"+student.getInTime()+"</td>");
        strHtml.append("<td>"+StringUtils.generateButtonWithonclick("修改", "getUpdateStudentForm("+student.getId()+")")+"</td>");
        strHtml.append("</tr></tbody></table>");
        return strHtml.toString();
    }

    public String getStudentsByName(String stuName) {
        List<Student> studentList = userDao.getStudents(stuName);
        if (studentList==null||studentList.size()<=0) {return null;}
        StringBuilder strHtml = new StringBuilder();
        strHtml.append("<table class=\"table table-striped table-hover\"><thead><tr><th>Id</th><th>姓名</th><th>性别</th><th>年龄</th><th>电话</th><th>专业</th><th>入学时间</th><th>修改</th></tr></thead><tbody>");
        for (Student student: studentList) {
            strHtml.append("<tr>");
            strHtml.append("<td>"+student.getId()+"</td>");
            strHtml.append("<td>"+student.getName()+"</td>");
            strHtml.append("<td>"+student.getGender()+"</td>");
            strHtml.append("<td>"+student.getAge()+"</td>");
            strHtml.append("<td>"+student.getTel()+"</td>");
            strHtml.append("<td>"+student.getPro()+"</td>");
            strHtml.append("<td>"+student.getInTime()+"</td>");
            strHtml.append("<td>"+StringUtils.generateButtonWithonclick("修改", "getUpdateStudentForm("+student.getId()+")")+"</td>");
            strHtml.append("</tr>");
        }
        strHtml.append("</tbody></table>");
        return strHtml.toString();
    }

    public String generateUpdateStudentForm(Student student) {
        String strGender = null;
        if (student.getGender().equals("男")) {
            strGender = "<option value=\"男\" selected>男</option>"+ "<option value=\"女\">女</option>";
        }else {
            strGender = "<option value=\"男\">男</option>"+ "<option value=\"女\" selected>女</option>";

        }
        String bookformHtml = "<form id=\"studentForm\" class=\"form-horizontal\" role=\"form\" method=\"post\" onsubmit=\"return updateStudent()\">" +
                "        <div class=\"form-group\">" +
                "            <label for=\"stu_id\" class=\"col-sm-2 control-label\" style=\"text-align: center;\">Id</label>" +
                "            <div class=\"col-sm-10\">" +
                "                <input type=\"text\" class=\"form-control\" id=\"stu_id\" name=\"stu_id\" placeholder=\"学生ID\" value=\""+student.getId()+"\" readonly>" +
                "            </div>" +
                "        </div>" +
                "        <div class=\"form-group\">" +
                "            <label for=\"stu_name\" class=\"col-sm-2 control-label\" style=\"text-align: center;\">姓名</label>" +
                "            <div class=\"col-sm-10\">" +
                "                <input type=\"text\" class=\"form-control\" id=\"stu_name\" name=\"stu_name\" placeholder=\"姓名\" value=\""+student.getName()+"\" required>" +
                "            </div>" +
                "        </div>" +
                "        <div class=\"form-group\">" +
                "            <label for=\"stu_password\" class=\"col-sm-2 control-label\" style=\"text-align: center;\">密码</label>" +
                "            <div class=\"col-sm-10\">" +
                "                <input type=\"text\" class=\"form-control\" id=\"stu_password\" name=\"stu_password\" placeholder=\"原密码不可见，强制修改，不修改则留空\">" +
                "            </div>" +
                "        </div>" +
                "        <div class=\"form-group\">" +
                "            <label for=\"stu_gender\" class=\"col-sm-2 control-label\" style=\"text-align: center;\">性别</label>" +
                "            <div class=\"col-sm-10\">" +
                "                <select class=\"form-control\" id=\"stu_gender\" name=\"stu_gender\" required>" +
                                    strGender +
                "                </select>" +
                "            </div>" +
                "        </div>" +
                "        <div class=\"form-group\">" +
                "            <label for=\"stu_age\" class=\"col-sm-2 control-label\" style=\"text-align: center;\">年龄</label>" +
                "            <div class=\"col-sm-10\">" +
                "                <input type=\"number\" class=\"form-control\" id=\"stu_age\" name=\"stu_age\" placeholder=\"年龄\" value=\""+student.getAge()+"\" required>" +
                "            </div>" +
                "        </div>" +
                "        <div class=\"form-group\">" +
                "            <label for=\"stu_tel\" class=\"col-sm-2 control-label\" style=\"text-align: center;\">电话号码</label>" +
                "            <div class=\"col-sm-10\">" +
                "                <input type=\"text\" class=\"form-control\" id=\"stu_tel\" name=\"stu_tel\" placeholder=\"电话号码\" value=\""+student.getTel()+"\" required>" +
                "            </div>" +
                "        </div>" +
                "        <div class=\"form-group\">" +
                "            <label for=\"stu_pro\" class=\"col-sm-2 control-label\" style=\"text-align: center;\">所在专业</label>" +
                "            <div class=\"col-sm-10\">" +
                "                <input type=\"text\" class=\"form-control\" id=\"stu_pro\" name=\"stu_pro\" placeholder=\"专业名\" value=\""+student.getPro()+"\" required>" +
                "            </div>" +
                "        </div>" +
                "        <div class=\"form-group\">" +
                "            <label for=\"stu_intime\" class=\"col-sm-2 control-label\" style=\"text-align: center;\">入学时间</label>" +
                "            <div class=\"col-sm-10\">" +
                "                <input type=\"date\" class=\"form-control\" id=\"stu_intime\" name=\"stu_intime\" placeholder=\"入学时间\" value=\""+student.getInTime().toString()+"\" required>" +
                "            </div>" +
                "        </div>" +
                "        <div class=\"form-group\">" +
                "            <div class=\"col-sm-offset-2 col-sm-10\">" +
                "                <button type=\"submit\" class=\"btn btn-primary btn-block\">更新</button>" +
                "                <button class=\"btn btn-danger btn-block\" onclick=\"deleteStudent("+student.getId()+")\">删除</button>\" "+
                "            </div>" +
                "        </div>" +
                "    </form>";
        return bookformHtml;
    }

    public String generateEmptyUpdateStudentForm() {
        String bookformHtml = "<form id=\"studentForm\" class=\"form-horizontal\" role=\"form\" method=\"post\" onsubmit=\"return addNewStudent()\">" +
                "        <div class=\"form-group\">" +
                "            <label for=\"stu_id\" class=\"col-sm-2 control-label\" style=\"text-align: center;\">Id</label>" +
                "            <div class=\"col-sm-10\">" +
                "                <input type=\"text\" class=\"form-control\" id=\"stu_id\" name=\"stu_id\" placeholder=\"学生ID\" required>" +
                "            </div>" +
                "        </div>" +
                "        <div class=\"form-group\">" +
                "            <label for=\"stu_name\" class=\"col-sm-2 control-label\" style=\"text-align: center;\">姓名</label>" +
                "            <div class=\"col-sm-10\">" +
                "                <input type=\"text\" class=\"form-control\" id=\"stu_name\" name=\"stu_name\" placeholder=\"姓名\" required>" +
                "            </div>" +
                "        </div>" +
                "        <div class=\"form-group\">" +
                "            <label for=\"stu_password\" class=\"col-sm-2 control-label\" style=\"text-align: center;\">密码</label>" +
                "            <div class=\"col-sm-10\">" +
                "                <input type=\"text\" class=\"form-control\" id=\"stu_password\" name=\"stu_password\" placeholder=\"密码\">" +
                "            </div>" +
                "        </div>" +
                "        <div class=\"form-group\">" +
                "            <label for=\"stu_gender\" class=\"col-sm-2 control-label\" style=\"text-align: center;\">性别</label>" +
                "            <div class=\"col-sm-10\">" +
                "                <select class=\"form-control\" id=\"stu_gender\" name=\"stu_gender\" required>" +
                "                   <option value=\"男\" selected>男</option>"+
                "                   <option value=\"女\">女</option>"+
                "                </select>" +
                "            </div>" +
                "        </div>" +
                "        <div class=\"form-group\">" +
                "            <label for=\"stu_age\" class=\"col-sm-2 control-label\" style=\"text-align: center;\">年龄</label>" +
                "            <div class=\"col-sm-10\">" +
                "                <input type=\"number\" class=\"form-control\" id=\"stu_age\" name=\"stu_age\" placeholder=\"年龄\" required>" +
                "            </div>" +
                "        </div>" +
                "        <div class=\"form-group\">" +
                "            <label for=\"stu_tel\" class=\"col-sm-2 control-label\" style=\"text-align: center;\">电话号码</label>" +
                "            <div class=\"col-sm-10\">" +
                "                <input type=\"text\" class=\"form-control\" id=\"stu_tel\" name=\"stu_tel\" placeholder=\"电话号码\" required>" +
                "            </div>" +
                "        </div>" +
                "        <div class=\"form-group\">" +
                "            <label for=\"stu_pro\" class=\"col-sm-2 control-label\" style=\"text-align: center;\">所在专业</label>" +
                "            <div class=\"col-sm-10\">" +
                "                <input type=\"text\" class=\"form-control\" id=\"stu_pro\" name=\"stu_pro\" placeholder=\"专业名\" required>" +
                "            </div>" +
                "        </div>" +
                "        <div class=\"form-group\">" +
                "            <label for=\"stu_intime\" class=\"col-sm-2 control-label\" style=\"text-align: center;\">入学时间</label>" +
                "            <div class=\"col-sm-10\">" +
                "                <input type=\"date\" class=\"form-control\" id=\"stu_intime\" name=\"stu_intime\" placeholder=\"入学时间\" required>" +
                "            </div>" +
                "        </div>" +
                "        <div class=\"form-group\">" +
                "            <div class=\"col-sm-offset-2 col-sm-10\">" +
                "                <button type=\"submit\" class=\"btn btn-primary btn-block\">添加</button>" +
                "            </div>" +
                "        </div>" +
                "    </form>";
        return bookformHtml;
    }

    public String getUpdateStudnetForm(String stuId) {
        String strHtml = generateUpdateStudentForm(userDao.getStudent(stuId));
        return strHtml;
    }

    public String getAddStudnetForm() {
        String strHtml = generateEmptyUpdateStudentForm();
        return strHtml;
    }

    public boolean updateStudent(Student student) {
        return userDao.updateStudent(student.getId(), student);
    }

    public boolean updateStudentPassword(String stuId, String password) {
        return userDao.resetPassword(stuId, password, false);
    }

    public boolean addNewStudent(Student student, String password) {
        return userDao.addStudent(student, password);
    }

    public String getNewerNotification(){
        String notification = userDao.getNewerNotification();
        if (notification== null) {
            return "暂无通知!";
        }
        return notification;
    }

    public boolean updateNotification(String str){
        return userDao.updateNotification(str);
    }

    public String getAllNotification() {
        return null;
    }
}
