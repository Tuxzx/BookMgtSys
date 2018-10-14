package com.tuxzx.dal.rowMapper;

import com.tuxzx.domain.Pub;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PubMapper implements RowMapper<Pub> {
    @Override
    public Pub mapRow(ResultSet resultSet, int i) throws SQLException {
        Pub pub = new Pub();
        pub.setId(resultSet.getString("pub_id"));
        pub.setName(resultSet.getString("pub_name"));
        pub.setPosition(resultSet.getString("pub_position"));
        return pub;
    }
}
