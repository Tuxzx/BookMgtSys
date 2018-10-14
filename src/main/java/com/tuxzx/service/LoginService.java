package com.tuxzx.service;

import com.tuxzx.dal.UserDao;
import com.tuxzx.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class LoginService {

    @Autowired
    private UserDao userDao;

    public Map login(String username, String password, String role) {
        if (role.equals("student")&&userDao.matchUser(username, password)) {
            return userDao.getHomeInfo(username);
        }
        if (role.equals("manager")&&userDao.matchManager(username, password)){
            return userDao.getHomeInfo(username);

        }
        return null;
    }

    public Map loginCheckAjax (String username, String password, String role) {
        Map map = new HashMap();
        if (role.equals("student")&&userDao.matchUser(username, password)) {
            String tip = StringUtils.alertHTMLString("ERROR: ", "登陆成功", "info");
            map.put("status", 200);
            map.put("info", tip);
            return map;
        }
        if (role.equals("manager")&&userDao.matchManager(username, password)){
            String tip = StringUtils.alertHTMLString("ERROR: ", "登陆成功", "info");
            map.put("status", 200);
            map.put("info", tip);
            return map;
        }
        String tip = StringUtils.alertHTMLString("ERROR: ", "登陆失败，用户名或密码错误", "danger");
        map.put("status", 404);
        map.put("info", tip);
        return map;
    }
}
