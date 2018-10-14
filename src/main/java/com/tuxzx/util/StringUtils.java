package com.tuxzx.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Map;

public class StringUtils {

    private static Gson gson;
    public static String toRegexp(String text, int limit) {
        return "["+text+"]{"+limit+"}";
    }

    /**
     * 返回一个bs3的alert
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

    public static String bookHTMLString(String isbn, String bookName) {
        return "<div class=\" col-sm-4 col-md-2 col-lg-2\">" +
                " <a href=\"/getBookInfo?isbn="+isbn+"\"> <div class=\"well\"> " +
                "<br><br><br><br><br><br><br><br> " +
                "</div><p>"+bookName+"</p></a></div>";
    }
    
    public static String bookTypeHTMLString(int typeId, String typeName){
        return "<button type=\"button\" class=\"btn btn-primary\" onclick=\"getBookByType('"+typeId+"')\">"+typeName+"</button>&nbsp;";
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