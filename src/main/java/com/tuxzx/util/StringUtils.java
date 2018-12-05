package com.tuxzx.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;
import java.util.Map;

public class StringUtils {

    private static Gson gson;
    public static String toRegexp(String text, int limit) {
        return "["+text+"]{"+limit+"}";
    }

    /**
     * 返回一个bs3的alert 登陆页面用
     * @param title title
     * @param message message
     * @param level info,danger,success,warning
     * @return
     */
    public static String alertHTMLString(String title, String message, String level) {
       return "<div class=\"alert alert-"+level+"\"" +
               " <button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-hidden=\"true\">" +
               "&times;</button> <strong>"+title+"</strong>"+message+"</div>";
    }

    /**
     * 生成一个书模块
     * @param isbn
     * @param bookName
     * @return
     */
    public static String bookHTMLString(String isbn, String bookName) {
        return "<div class=\"col-xs-6 col-sm-4 col-md-2 col-lg-2\" >"+
                " <a style=\"text-align: center;\" href=\"/getBookInfo?isbn="+isbn+"\"> <div class=\"well\" style=\"width:130px; height:180px;\"><br> " +
                "</div><p style=\"width:130px;\">"+bookName+"</p></a></div>";
    }

    /**
     * 生成学生主页类型按钮
     * @param typeId
     * @param typeName
     * @return
     */
    public static String bookTypeHTMLString(int typeId, String typeName){
        return "<button type=\"button\" class=\"btn btn-primary\" onclick=\"getBookByType('"+typeId+"')\">"+typeName+"</button>&nbsp;";
    }

    /**
     * 生成排行榜progressbar
     * @param num
     * @param pre
     * @return
     */
    public static String generateProgressBar(int num, int pre) {
        return "<div class=\"progress-bar progress-bar-info\" role=\"progressbar\" aria-valuenow=\""+pre+"\" aria-valuemin=\"0\" aria-valuemax=\"100\" style=\"width: "+pre+"%\">" +
                num +"</div>";
    }

    public static String generateButtonWithLink(String name, String link) {
        return "<a href=\""+link+"\" class=\"btn btn-primary\">"+name+"</a>";
    }
    public static String generateButtonWithonclick(String name, String function) {
        return "<button class=\"btn btn-primary\" onclick=\""+function+"\">"+name+"</button>";
    }

    public static Map returnToAjaxFormat(String status, String info) {
        Map map = new HashMap();
        map.put("status", status);
        map.put("info", info);
        return map;
    }
    public static Map returnStatusToAjax(String status) {
        Map map = new HashMap();
        map.put("status", status);
        return map;
    }

    /**
     * 使用gson把一个Map转换成json
     * @param map
     * @return
     */
    public static String mapToJson(Map map) {
        gson = new GsonBuilder()
                .setPrettyPrinting()
                .disableHtmlEscaping()
                .create();
        return gson.toJson(map);
    }

}
