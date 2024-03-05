package lhweb.asia.LHTomCat.dao;


import lhweb.asia.LHTomCat.model.TrainUser;
import lhweb.asia.LHTomCat.model.User;
import lhweb.asia.LHTomCat.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * 用户DAO
 *
 * @author 罗汉
 * @date 2024/02/26
 */
public class UserDao {

    private Connection conn = null;
    private PreparedStatement pstat = null;
    private ResultSet res = null;

    public static void main(String[] args) {
        User user = new User("admin", "123456");
        UserDao userDao = new UserDao();

        // int add = userDao.add(user);
        // System.out.println(add);


        List<User> allUser = userDao.findAllUser();
        System.out.println(allUser);
    }

    /**
     * 添加
     *
     * @param user 用户
     * @return int
     */
    public int add(User user) {
        int index = -1;
        try {
            conn = JDBCUtils.getConnection();
            String insertQuery = "INSERT INTO user (username, password) VALUES (?, ?)";
            pstat = conn.prepareStatement(insertQuery, PreparedStatement.RETURN_GENERATED_KEYS);
            pstat.setString(1, user.getUsername());
            pstat.setString(2, user.getPassword());

            int rowsAffected = pstat.executeUpdate();

            // 获取新插入行的ID
            if (rowsAffected > 0) {
                ResultSet generatedKeys = pstat.getGeneratedKeys();
                if (generatedKeys.next()) {
                    index = generatedKeys.getInt(1);
                }
            }

        } catch (SQLException e) {
            // TODO: 处理异常
            e.printStackTrace();
        } finally {
            // 关闭资源
            JDBCUtils.close(conn, pstat, res);
        }

        return index;
    }

    /**
     * 查找所有用户
     *
     * @return {@link List}<{@link User}>
     */
    public List<User> findAllUser() {
        User play = null;
        List<User> players = new ArrayList<>();
        try {
            conn = JDBCUtils.getConnection();

            String selectQuery = "SELECT * FROM user";
            pstat = conn.prepareStatement(selectQuery);
            res = pstat.executeQuery();
            // 处理查询结果
            while (res.next()) {
                play = new User();
                play.setUsername(res.getString("username"));
                play.setPassword(res.getString("password"));
                players.add(play);
            }

        } catch (SQLException e) {
            // TODO: 处理异常
            e.printStackTrace();
        } finally {
            // 关闭资源
            JDBCUtils.close(conn, pstat, res);
        }

        return players;
    }

    /**
     * 按用户名获取
     *
     * @param username 用户名
     * @return {@link User}
     */
    public TrainUser getByUsername(String username) {
        TrainUser user = null;
        try {
            conn = JDBCUtils.getConnection();
            String selectQuery = "SELECT * FROM train_user WHERE username = ?";
            pstat = conn.prepareStatement(selectQuery);
            pstat.setString(1, username);
            res = pstat.executeQuery();

            // 处理查询结果
            if (res.next()) {
                user = new TrainUser();
                user.setUsername(res.getString("username"));
                user.setPassword(res.getString("password"));
                user.setUserid(res.getString("userid"));
                user.setSex(res.getString("sex"));
                user.setMoney(res.getInt("money"));
                user.setId(res.getString("id"));
                user.setPhone(res.getString("phone"));
                user.setPhoto(res.getString("photo"));
                user.setCreatetime(res.getDate("createtime"));
            }

        } catch (SQLException e) {
            // TODO: 处理异常
            e.printStackTrace();
        } finally {
            // 关闭资源
            JDBCUtils.close(conn, pstat, res);
        }
        return user;
    }


}
