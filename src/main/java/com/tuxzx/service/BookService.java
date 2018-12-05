package com.tuxzx.service;

import com.tuxzx.dal.BookDao;
import com.tuxzx.domain.Book;
import com.tuxzx.domain.BookType;
import com.tuxzx.domain.Pub;
import com.tuxzx.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookService {

    @Autowired
    private BookDao bookDao;

    /**
     * 获得排行榜
     * @return
     */
    public String getNewerBookInfo() {
        List<Book> bookList = bookDao.getNewerBook(20);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<table class=\"table table-striped table-hover\"><thead><tr><th>书名</th><th>ISBN</th><th>入馆时间</th></tr></thead><tbody>");
        for (Book book: bookList) {
            stringBuilder.append("<tr>");
            stringBuilder.append("<td>"+book.getName()+"</td>");
            stringBuilder.append("<td>"+book.getISBN()+"</td>");
            stringBuilder.append("<td>"+book.getInTime().toString()+"</td>");
            stringBuilder.append("</tr>");
        }
        stringBuilder.append("</tbody></table");
        return stringBuilder.toString();
    }

    public Map getBookInfo(String isbn) {
        Book book = bookDao.getBookByISBN(isbn);
        Map map = new HashMap();
        map.put("book_name", book.getName());
        map.put("book_author", book.getName());
        map.put("book_pub", bookDao.getPubInfo(book.getPub()).getName());
        map.put("book_count", book.getCount());
        map.put("book_intime", book.getInTime());
        map.put("book_note", book.getNote());
        System.out.println(book.getType());
        map.put("book_type", bookDao.getTypeName(book.getType()));
        map.put("borrowed_list", getAllReaderName(book.getISBN()));
        return map;
    }

    public String getAllReaderName(String isbn) {
        List<String> list = bookDao.getReadUserList(isbn);
        StringBuilder stringBuilder = new StringBuilder();
        for (String s: list) {
            stringBuilder.append(s+",");
        }
        return stringBuilder.toString();
    }

    public Map getAllBookTypeWithHtml(){
        Map map = new HashMap();
        List<BookType> typeList = null;
        typeList = bookDao.getAllTypes();
        StringBuilder htmlStr = new StringBuilder();
        htmlStr.append(StringUtils.bookTypeHTMLString(0,"全部"));
        for (BookType bookType:typeList) {
            htmlStr.append(StringUtils.bookTypeHTMLString(bookType.getId(), bookType.getName()));
        }
        map.put("status",200);
        map.put("info", htmlStr.toString());
        return map;
    }

    public Map getAllBookByTypeWithHtml(int type) {
        Map map = new HashMap();
        List<Book> bookList = null;
        StringBuilder htmlStr = new StringBuilder();
        if (type==0) {
            bookList = bookDao.getAllBooks();
        }else {
            bookList = bookDao.getBookByType(type);
        }
        for (Book book:bookList) {
            htmlStr.append(StringUtils.bookHTMLString(book.getISBN(), book.getName()));
        }
        map.put("status",200);
        map.put("info", htmlStr.toString());
        return map;
    }



    public String getBookByISBN(String isbn){
        Book book = bookDao.getBookByISBN(isbn);
        if (book == null) {return null;}
        StringBuilder strHtml = new StringBuilder();
        strHtml.append("<table class=\"table table-striped table-hover\"><thead><tr><th>ISBN</th><th>书名</th><th>作者</th><th>修改</th></tr></thead><tbody>");
        strHtml.append("<tr><td>"+book.getISBN()+"</td><td>"+book.getName()+"</td><td>"+book.getAuthor()+"</td><td>"+StringUtils.generateButtonWithonclick("修改","getUpdateBookForm("+book.getISBN()+")")+"</td></tr>");
        strHtml.append("</tbody></table");
        return strHtml.toString();
    }

    public String getBookByName(String name){
        List<Book> bookList = bookDao.getBookByName(name);
        if (bookList == null|| bookList.size()<=0) {return null;}
        StringBuilder strHtml = new StringBuilder();
        strHtml.append("<table class=\"table table-striped table-hover\"><thead><tr><th>ISBN</th><th>书名</th><th>作者</th><th>修改</th></tr></thead><tbody>");
        for (Book book: bookList) {
            strHtml.append("<tr>");
            strHtml.append("<td>"+book.getISBN()+"</td>");
            strHtml.append("<td>"+book.getName()+"</td>");
            strHtml.append("<td>"+book.getAuthor()+"</td>");
            strHtml.append("<td>"+StringUtils.generateButtonWithonclick("修改","getUpdateBookForm("+book.getISBN()+")")+"</td>");
            strHtml.append("</tr>");
        }
        strHtml.append("</tbody></table");
        return strHtml.toString();
    }

    public String getBookByNameNav(String name){
        List<Book> bookList = bookDao.getBookByName(name);
        if (bookList == null|| bookList.size()<=0) {return null;}
        StringBuilder strHtml = new StringBuilder();
        strHtml.append("<table class=\"table table-striped table-hover\"><thead><tr><th>ISBN</th><th>书名</th><th>作者</th><th>入馆时间</th><th>查看详情</th></tr></thead><tbody>");
        for (Book book: bookList) {
            strHtml.append("<tr>");
            strHtml.append("<td>"+book.getISBN()+"</td>");
            strHtml.append("<td>"+book.getName()+"</td>");
            strHtml.append("<td>"+book.getAuthor()+"</td>");
            strHtml.append("<td>"+book.getInTime()+"</td>");
            strHtml.append("<td>"+StringUtils.generateButtonWithLink("More","getBookInfo?isbn="+book.getISBN()+"\"")+"</td>");
            strHtml.append("</tr>");
        }
        strHtml.append("</tbody></table");
        return strHtml.toString();
    }



    /**
     * 动态生成更新表单
     * @param book
     * @return
     */
    public String generateUpdateBookForm(Book book) {
        StringBuilder strPub = new StringBuilder();
        StringBuilder strType = new StringBuilder();
        // 获得pub and type
        List<Pub> pubList = bookDao.getAllPubs();
        List<BookType> typeList = bookDao.getAllTypes();
        for (Pub pub: pubList){
            if (pub.getId().equals(book.getPub())) {
                strPub.append("<option value=\"" + pub.getId() + "\" selected>" + pub.getName() + "</option>");
                continue;
            }
            strPub.append("<option value=\""+pub.getId()+"\">"+pub.getName()+"</option>");
        }
        for (BookType bookType: typeList){
            if (bookType.getId() == book.getType()) {
                strType.append("<option value=\"" + bookType.getId() + "\" selected>" + bookType.getName() + "</option>");
                continue;
            }
            strType.append("<option value=\""+bookType.getId()+"\" selected>"+bookType.getName()+"</option>");
        }

        String bookformHtml = "<form id=\"bookForm\" class=\"form-horizontal\" role=\"form\" method=\"post\" onsubmit=\"return updateBook()\">" +
                "        <div class=\"form-group\">" +
                "            <label for=\"book_isbn\" class=\"col-sm-2 control-label\" style=\"text-align: center;\">ISBN</label>" +
                "            <div class=\"col-sm-10\">" +
                "                <input type=\"text\" class=\"form-control\" id=\"book_isbn\" name=\"book_isbn\" placeholder=\"ISBN\" value=\""+book.getISBN()+"\" readonly>" +
                "            </div>" +
                "        </div>" +
                "        <div class=\"form-group\">" +
                "            <label for=\"book_name\" class=\"col-sm-2 control-label\" style=\"text-align: center;\">书名</label>" +
                "            <div class=\"col-sm-10\">" +
                "                <input type=\"text\" class=\"form-control\" id=\"book_name\" name=\"book_name\" placeholder=\"书名\" value=\""+book.getName()+"\" required>" +
                "            </div>" +
                "        </div>" +
                "        <div class=\"form-group\">" +
                "            <label for=\"book_author\" class=\"col-sm-2 control-label\" style=\"text-align: center;\">作者</label>" +
                "            <div class=\"col-sm-10\">" +
                "                <input type=\"text\" class=\"form-control\" id=\"book_author\" name=\"book_author\" placeholder=\"作者\" value=\""+book.getAuthor()+"\" required>" +
                "            </div>" +
                "        </div>" +
                "        <div class=\"form-group\">" +
                "            <label for=\"book_pub\" class=\"col-sm-2 control-label\" style=\"text-align: center;\">出版社编号</label>" +
                "            <div class=\"col-sm-10\">" +
                "                <select class=\"form-control\" id=\"book_pub\" name=\"book_pub\" required>" +
                                    strPub.toString() +
                "                </select>" +
                "            </div>" +
                "        </div>" +
                "        <div class=\"form-group\">" +
                "            <label for=\"book_count\" class=\"col-sm-2 control-label\" style=\"text-align: center;\">总量</label>" +
                "            <div class=\"col-sm-10\">" +
                "                <input type=\"text\" class=\"form-control\" id=\"book_count\" name=\"book_count\" placeholder=\"总量\" value=\""+book.getCount()+"\" required>" +
                "            </div>" +
                "        </div>" +
                "        <div class=\"form-group\">" +
                "            <label for=\"book_intime\" class=\"col-sm-2 control-label\" style=\"text-align: center;\">入馆时间</label>" +
                "            <div class=\"col-sm-10\">" +
                "                <input type=\"date\" class=\"form-control\" id=\"book_intime\" name=\"book_intime\" value=\""+book.getInTime().toString()+"\" required>" +
                "            </div>" +
                "        </div>" +
                "        <div class=\"form-group\">" +
                "            <label for=\"book_type\" class=\"col-sm-2 control-label\" style=\"text-align: center;\">类型编号</label>" +
                "            <div class=\"col-sm-10\">" +
                "                <select class=\"form-control\" id=\"book_type\" name=\"book_type\" required>" +
                                    strType.toString() +
                "                </select>" +
                "            </div>" +
                "        </div>" +
                "        <div class=\"form-group\">" +
                "            <label for=\"book_note\" class=\"col-sm-2 control-label\" style=\"text-align: center;\">备注</label>" +
                "            <div class=\"col-sm-10\">" +
                "                <input type=\"textarea\" rows=\"3\" class=\"form-control\" id=\"book_note\" name=\"book_note\" placeholder=\"备注\" value=\""+book.getNote()+"\" required>" +
                "            </div>" +
                "        </div>" +
                "        <div class=\"form-group\">" +
                "            <div class=\"col-sm-offset-2 col-sm-10\">" +
                "                <button type=\"submit\" class=\"btn btn-primary btn-block\">更新</button>" +
                "                <a class=\"btn btn-danger btn-block\" onclick=\"deleteBook("+book.getISBN()+")\">删除</a>\" "+
                "            </div>" +
                "        </div>" +
                "    </form>";
        return bookformHtml;
    }

    /**
     * 动态生成更新表单
     * @return
     */
    public String generateEmptyUpdateBookForm() {
        StringBuilder strPub = new StringBuilder();
        StringBuilder strType = new StringBuilder();
        // 获得pub and type
        List<Pub> pubList = bookDao.getAllPubs();
        List<BookType> typeList = bookDao.getAllTypes();
        for (Pub pub: pubList){
            strPub.append("<option value=\""+pub.getId()+"\">"+pub.getName()+"</option>");
        }
        for (BookType bookType: typeList){
            strType.append("<option value=\""+bookType.getId()+"\">"+bookType.getName()+"</option>");
        }

        String bookformHtml = "<form id=\"bookForm\" class=\"form-horizontal\" role=\"form\" method=\"post\" onsubmit=\"return addNewBook()\">" +
                "        <div class=\"form-group\">" +
                "            <label for=\"book_isbn\" class=\"col-sm-2 control-label\" style=\"text-align: center;\">ISBN</label>" +
                "            <div class=\"col-sm-10\">" +
                "                <input type=\"text\" class=\"form-control\" id=\"book_isbn\" name=\"book_isbn\" placeholder=\"ISBN\" required>" +
                "            </div>" +
                "        </div>" +
                "        <div class=\"form-group\">" +
                "            <label for=\"book_name\" class=\"col-sm-2 control-label\" style=\"text-align: center;\">书名</label>" +
                "            <div class=\"col-sm-10\">" +
                "                <input type=\"text\" class=\"form-control\" id=\"book_name\" name=\"book_name\" placeholder=\"书名\" required>" +
                "            </div>" +
                "        </div>" +
                "        <div class=\"form-group\">" +
                "            <label for=\"book_author\" class=\"col-sm-2 control-label\" style=\"text-align: center;\">作者</label>" +
                "            <div class=\"col-sm-10\">" +
                "                <input type=\"text\" class=\"form-control\" id=\"book_author\" name=\"book_author\" placeholder=\"作者\" required>" +
                "            </div>" +
                "        </div>" +
                "        <div class=\"form-group\">" +
                "            <label for=\"book_pub\" class=\"col-sm-2 control-label\" style=\"text-align: center;\">出版社编号</label>" +
                "            <div class=\"col-sm-10\">" +
                "                <select class=\"form-control\" id=\"book_pub\" name=\"book_pub\" required>" +
                strPub.toString() +
                "                </select>" +
                "            </div>" +
                "        </div>" +
                "        <div class=\"form-group\">" +
                "            <label for=\"book_count\" class=\"col-sm-2 control-label\" style=\"text-align: center;\">总量</label>" +
                "            <div class=\"col-sm-10\">" +
                "                <input type=\"text\" class=\"form-control\" id=\"book_count\" name=\"book_count\" placeholder=\"总量\" required>" +
                "            </div>" +
                "        </div>" +
                "        <div class=\"form-group\">" +
                "            <label for=\"book_intime\" class=\"col-sm-2 control-label\" style=\"text-align: center;\">入馆时间</label>" +
                "            <div class=\"col-sm-10\">" +
                "                <input type=\"date\" class=\"form-control\" id=\"book_intime\" name=\"book_intime\" required>" +
                "            </div>" +
                "        </div>" +
                "        <div class=\"form-group\">" +
                "            <label for=\"book_type\" class=\"col-sm-2 control-label\" style=\"text-align: center;\">类型编号</label>" +
                "            <div class=\"col-sm-10\">" +
                "                <select class=\"form-control\" id=\"book_type\" name=\"book_type\" required>" +
                strType.toString() +
                "                </select>" +
                "            </div>" +
                "        </div>" +
                "        <div class=\"form-group\">" +
                "            <label for=\"book_note\" class=\"col-sm-2 control-label\" style=\"text-align: center;\">备注</label>" +
                "            <div class=\"col-sm-10\">" +
                "                <input type=\"textarea\" rows=\"3\" class=\"form-control\" id=\"book_note\" name=\"book_note\" placeholder=\"备注\" required>" +
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

    /**
     * 返回更新表单给ajax
     * @param isbn
     * @return
     */
    public String getUpdateBookForm(String isbn) {
        String strHtml = generateUpdateBookForm(bookDao.getBookByISBN(isbn));
        return strHtml;
    }

    /**
     * 返回更新表单给ajax
     * @return
     */
    public String getAddBookForm() {
        String strHtml = generateEmptyUpdateBookForm();
        return strHtml;
    }


    public boolean updateBook(Book book) {
        return bookDao.updateBook(book);
    }

    public boolean addNewBook(Book book) {
        return bookDao.addBook(book);
    }

    public boolean deleteBook(String isbn) {
        return bookDao.removeBook(isbn);
    }
}
