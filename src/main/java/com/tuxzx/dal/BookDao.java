package com.tuxzx.dal;

import com.tuxzx.domain.Book;
import com.tuxzx.domain.BookType;
import com.tuxzx.domain.Pub;

import java.util.List;
import java.util.Map;

public interface BookDao {

    // BookInfo ***************************************************

    /**
     * 获得所有图书信息
     * @return
     */
    List<Book> getAllBooks();

    /**
     * 根据isbn号获取 不支持模糊查询
     * @param isbn
     * @return
     */
    Book getBookByISBN(String isbn);

    /**
     * 根据书名获取
     * @param name
     * @return
     */
    List<Book> getBookByName(String name);

    /**
     * 获区对应书籍的阅读者列表
     * @param isbn
     * @return
     */
    List<String> getReadUserList(String isbn);

    /**
     * 根据作者获取
     * @param author
     * @return
     */
    List<Book> getBookByAuthor(String author);

    /**
     * 根据出版社获取
     * @param pubId
     * @return
     */
    List<Book> getBookByPub(String pubId);

    /**
     * 根据书籍类型id获取 不支持模糊查询
     * @param typeId
     * @return
     */
    List<Book> getBookByType(int typeId);

    // BookType***************************************************

    /**
     * 获取所有书籍类型
     * @return
     */
    List<BookType> getAllTypes();

    /**
     * 根据书籍类型名来获取类型id
     * @param typeName
     * @return
     */
    int getTypeId(String typeName);

    /**
     * 根据书籍类型id来获取类型名
     * @param typeId
     * @return
     */
    String getTypeName(int typeId);

    // PubInfo ***************************************************

    /**
     * 获取所有出版社信息
     * @return
     */
    List<Pub> getAllPubs();

    /**
     * 根据出版社名获取出版社编号
     * @param pubName
     * @return
     */
    String getPubId(String pubName);

    /**
     * 根据出版社id获取出版社信息
     * @param pubId
     * @return
     */
    Pub getPubInfo(String pubId);

    // admin ***************************************************

    /**
     * 增加新书
     * @param book
     * @return
     */
    boolean addBook(Book book);

    /**
     * 更新书信息
     * @param book
     * @return
     */
    boolean updateBook(Book book);

    /**
     * 删除书信息
     * @param isbn
     * @return
     */
    boolean removeBook(String isbn);

    /**
     * 获取最新入馆的书信息
     * @param limit 查询数量限制
     * @return
     */
    List<Book> getNewerBook(int limit);


}
