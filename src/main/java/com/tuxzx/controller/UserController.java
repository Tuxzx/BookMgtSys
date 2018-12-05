package com.tuxzx.controller;

import com.sun.istack.internal.Nullable;
import com.tuxzx.domain.Book;
import com.tuxzx.domain.Student;
import com.tuxzx.service.UserService;
import com.tuxzx.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.Map;

@Controller
public class UserController {
    //todo 完成BookController的service层逻辑

    @Autowired
    private UserService userService;



    @GetMapping (value = "/toBookMgt")
    public String toBookMgt() {
        return "management_bookMgt";
    }

    @GetMapping (value = "/toStudentMgt")
    public String toStudent() {
        return "management_studentMgt";
    }

    /**
     * 获取用户信息by学号返回给ajax
     * @param stu_id
     * @return
     */
    @PostMapping (value = "/getStudentById", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getStudentById(String stu_id) {
        String strHtml = userService.getStudentById(stu_id);
        Map map = null;
        if (strHtml == null) {
            map = StringUtils.returnToAjaxFormat("failed", "该id不存在，请检查输入");
        }else {
            map = StringUtils.returnToAjaxFormat("success", strHtml);
        }
        return StringUtils.mapToJson(map);
    }

    @PostMapping (value = "/getStudentsByName", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getStudentsByName(String stu_name) {
        String strHtml = userService.getStudentsByName(stu_name);
        Map map = null;
        if (strHtml == null) {
            map = StringUtils.returnToAjaxFormat("failed", "该用户名不存在，请检查输入");
        }else {
            map = StringUtils.returnToAjaxFormat("success", strHtml);
        }
        return StringUtils.mapToJson(map);
    }

    @PostMapping (value = "/getStudentUpdateForm", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getStudentUpdateForm(String stu_id) {
        String strHtml = userService.getUpdateStudnetForm(stu_id);
        Map map = null;
        if (strHtml == null) {
            map = StringUtils.returnToAjaxFormat("failed", "该书不存在，请检查输入");
        }else {
            map = StringUtils.returnToAjaxFormat("success", strHtml);
        }
        return StringUtils.mapToJson(map);
    }

    @GetMapping (value = "/getStudentAddForm", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getStudentAddForm() {
        String strHtml = userService.getAddStudnetForm();
        Map map = null;
        if (strHtml == null) {
            map = StringUtils.returnToAjaxFormat("failed", "该书不存在，请检查输入");
        }else {
            map = StringUtils.returnToAjaxFormat("success", strHtml);
        }
        return StringUtils.mapToJson(map);
    }

    @PostMapping (value = "/updateStudent", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String updateStudent (String stu_id, String stu_name, String stu_password, String stu_gender, int stu_age, String stu_tel, String stu_pro, Date stu_intime) {
        Student student = new Student(stu_id, stu_name, stu_gender, stu_age, stu_tel, stu_pro, stu_intime);
        if (userService.updateStudent(student)) {
            if (!stu_password.equals("")) {
                if (userService.updateStudentPassword(stu_id, stu_password)) {
                    return StringUtils.mapToJson(StringUtils.returnToAjaxFormat("success", "更新成功!"));
                }else {
                    return StringUtils.mapToJson(StringUtils.returnToAjaxFormat("success", "账户信息更新成功!\n密码更新失败!"));
                }
            }else {
                return StringUtils.mapToJson(StringUtils.returnToAjaxFormat("success", "更新成功!\n未修改密码)"));
            }
        } else {
            return StringUtils.mapToJson(StringUtils.returnToAjaxFormat("failed","更新失败!"));
        }
    }

    @PostMapping (value = "/addNewStudent", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String addNewStudent (String stu_id, String stu_name, String stu_password, String stu_gender, int stu_age, String stu_tel, String stu_pro, Date stu_intime) {
        Student student = new Student(stu_id, stu_name, stu_gender, stu_age, stu_tel, stu_pro, stu_intime);
        if (userService.addNewStudent(student, stu_password)) {
            return StringUtils.mapToJson(StringUtils.returnToAjaxFormat("success", "更新成功!"));
        } else {
            return StringUtils.mapToJson(StringUtils.returnToAjaxFormat("failed","更新失败!"));
        }
    }

    @GetMapping (value = "exitSystem")
    public String exitSystem(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @PostMapping (value = "getNewerNotification", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getNewerNotification() {
        String str = userService.getNewerNotification();
        return StringUtils.mapToJson(StringUtils.returnToAjaxFormat("success",str));

    }

    @PostMapping (value = "updateNotification", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String updateNotification(String notification) {
        if (userService.updateNotification(notification)){
            return StringUtils.mapToJson(StringUtils.returnStatusToAjax("success"));
        }else {
            return StringUtils.mapToJson(StringUtils.returnStatusToAjax("failed"));
        }
    }

}
