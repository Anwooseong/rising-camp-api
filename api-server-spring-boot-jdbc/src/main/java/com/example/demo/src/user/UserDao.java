package com.example.demo.src.user;

import com.example.demo.src.user.model.GetUserRes;
import com.example.demo.src.user.model.PatchUserReq;
import com.example.demo.src.user.model.PostUserReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class UserDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public int checkPhone(String phone) {
        String checkPhoneQuery = "select exists(select phone from User where phone = ?)";
        String checkPhoneParams = phone;
        return this.jdbcTemplate.queryForObject(checkPhoneQuery, int.class, checkPhoneParams);
    }

    public int createUser(PostUserReq postUserReq) {
        String createUserQuery = "insert into User(name, uid, password, phone, address) VALUES (?,?,?,?,?)";
        Object[] createUserParams = new Object[]{postUserReq.getName(), postUserReq.getUid(), postUserReq.getPassword(), postUserReq.getPhone(), postUserReq.getAddress()};
        this.jdbcTemplate.update(createUserQuery, createUserParams);

        String lastInsertIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInsertIdQuery, int.class);
    }

    public List<GetUserRes> getUsers() {
        String getUsersQuery = "select * from User";
        return this.jdbcTemplate.query(getUsersQuery,
                (rs, rowNum) -> new GetUserRes(
                        rs.getInt("userId"),
                        rs.getString("name"),
                        rs.getString("uid"),
                        rs.getString("password"),
                        rs.getString("phone"),
                        rs.getString("address")
                ));
    }

    public GetUserRes getUser(int userId) {
        String getUserQuery = "select * from User where userId = ?";
        int getUserParams = userId;
        return this.jdbcTemplate.queryForObject(getUserQuery,
                (rs, rowColumn) -> new GetUserRes(
                        rs.getInt("userId"),
                        rs.getString("name"),
                        rs.getString("uid"),
                        rs.getString("password"),
                        rs.getString("phone"),
                        rs.getString("address")),
                getUserParams);
    }

    public int modifyUserPhoneAndAddress(PatchUserReq patchUserReq) {
        String modifyUserQuery = "update User set phone = ?, address = ? where userId = ?";
        Object[] modifyUserParams = new Object[]{patchUserReq.getPhone(), patchUserReq.getAddress(), patchUserReq.getUserId()};

        return this.jdbcTemplate.update(modifyUserQuery, modifyUserParams);
    }

    public int deleteUser(int userId) {
        String deleteUserQuery = "delete from User where userId = ?";
        return this.jdbcTemplate.update(deleteUserQuery, userId);
    }
}
