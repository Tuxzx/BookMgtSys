package com.tuxzx.dal.impl;

import com.tuxzx.dal.BookDao;
import com.tuxzx.dal.rowMapper.BookMapper;
import com.tuxzx.dal.rowMapper.BookTypeMapper;
import com.tuxzx.dal.rowMapper.PubMapper;
import com.tuxzx.domain.Book;
import com.tuxzx.domain.BookType;
import com.tuxzx.domain.Pub;
import com.tuxzx.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookDaoImpl implements BookDao {

    private static final String QUERY_BOOK_ALL = "select * from book_info;";
    private static final String QUERY_BOOK_BY_NAME = "select * from book_info where book_name regexp ?;";
    private static final String QUERY_BOOK_BY_ISBN = "select * from book_info where book_isbn = ?;"; //不支持模糊查询
    private static final String QUERY_BOOK_BY_AUTHOR = "select * from book_info where book_author regexp ?;";
    private static final String QUERY_BOOK_BY_PUB = "select * from book_info where book_pub regexp ?;";
    private static final String QUERY_BOOK_BY_TYPE= "select * from book_info where book_type = ?;"; //不支持模糊查询

    private static final String QUERY_TYPEID_BY_TYPENAME= "select type_id from book_type_info where type_name = ?;";
    private static final String QUERY_TYPENAME_BY_TYPEID= "select type_name from book_type_info where type_id = ?;";
    private static final String QUERY_TYPENAME_ALL= "select * from book_type_info;";

    private static final String QUERY_PUBID_BY_PUBNAME= "select pub_id from pub_info where pub_name = ?;";
    private static final String QUERY_PUB_BY_PUBID= "select * from pub_info where pub_id = ?;";
    private static final String QUERY_PUBNAME_ALL= "select * from pub_info;";

    private static final String REMOVE_BOOK_BY_ISBN = "delete from book_info where book_isbn = ?;";
    private static final String INSERT_BOOK = "insert into book_info " +
            "(book_isbn, book_name, book_author, book_pub, book_count, book_intime, book_type, book_note) " +
            "values (?,?,?,?,?,?,?,?);";



    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Book> getAllBooks() {
        return jdbcTemplate.query(QUERY_BOOK_ALL, new BookMapper());
    }

    @Override
    public Book getBookByISBN(String isbn) {
        return jdbcTemplate.queryForObject(QUERY_BOOK_BY_ISBN, new Object[]{isbn}, new BookMapper());
    }

    @Override
    public List<Book> getBookByName(String name) {
        return jdbcTemplate.query(QUERY_BOOK_BY_NAME, new Object[]{StringUtils.toRegexp(name, name.length())}, new BookMapper());
    }

    @Override
    public List<Book> getBookByAuthor(String author) {
        return jdbcTemplate.query(QUERY_BOOK_BY_AUTHOR, new Object[]{author}, new BookMapper());
    }

    @Override
    public List<Book> getBookByPub(String pubId) {
        return jdbcTemplate.query(QUERY_BOOK_BY_PUB, new Object[]{pubId}, new BookMapper());
    }

    @Override
    public List<Book> getBookByType(int typeId) {
        return jdbcTemplate.query(QUERY_BOOK_BY_TYPE, new Object[]{typeId}, new BookMapper());
    }


    @Override
    public List<BookType> getAllTypes() {
        return jdbcTemplate.query(QUERY_TYPENAME_ALL, new BookTypeMapper());
    }

    @Override
    public int getTypeId(String typeName) {
        return jdbcTemplate.queryForObject(QUERY_TYPEID_BY_TYPENAME, new Object[]{typeName}, Integer.class);
    }

    @Override
    public String getTypeName(String typeId) {
        return jdbcTemplate.queryForObject(QUERY_TYPENAME_BY_TYPEID, new Object[]{typeId}, String.class);
    }

    @Override
    public List<Pub> getAllPubs() {
        return jdbcTemplate.query(QUERY_PUBNAME_ALL, new PubMapper());
    }

    @Override
    public String getPubId(String pubName) {
        return jdbcTemplate.queryForObject(QUERY_PUBID_BY_PUBNAME, String.class);
    }

    @Override
    public Pub getPubInfo(String pubId) {
        return jdbcTemplate.queryForObject(QUERY_PUB_BY_PUBID,new Object[]{pubId}, new PubMapper());
    }

    @Override
    public boolean addBook(Book book) {
        return jdbcTemplate.update(INSERT_BOOK, new Object[]{book.getISBN(), book.getName(), book.getAuthor(),
                book.getPub(), book.getCount(), book.getInTime(), book.getType(), book.getNote()})>0?true:false;
    }

    @Override
    public boolean removeBook(String isbn) {
        return jdbcTemplate.update(REMOVE_BOOK_BY_ISBN, new Object[]{isbn})>0?true:false;
    }


}
