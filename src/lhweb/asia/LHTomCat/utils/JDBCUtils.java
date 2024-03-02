package lhweb.asia.LHTomCat.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * jdbcutils
 *
 * @author 罗汉
 * @version 1.0 这是一个工具类，完成 mysql的连接和关闭资源
 * @date 2024/02/21
 */
public class JDBCUtils {
    private static String USERNAME;
    private static String PWD;
    private static String URL;
    private static String DRIVER;
    private static Properties properties;
    private static JDBCUtils jdbcUtils = null;


    public static void main(String[] args) {
        try {
            JDBCUtils.getConnection();
            System.out.println("数据库连接ok");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // 在静态代码块完成 ds初始化
    static {
        properties = new Properties();
        try {
            // 因为可能是web项目，工作目录在out, 文件的加载，需要使用类加载器
            // properties.load(new FileInputStream("F:\\cykjWorkSpacrce\\fileTest\\src\\database.properties"));
            properties.load(JDBCUtils.class.getClassLoader().getResourceAsStream("database.properties"));
            DRIVER = properties.getProperty("driverClassName");
            URL = properties.getProperty("url");
            USERNAME = properties.getProperty("username");
            PWD = properties.getProperty("password");
            Class.forName(DRIVER);
        } catch (Exception e) {
            System.err.println("注册驱动失败");
            e.printStackTrace();
        }
    }

    /**
     * 得到数据库的连接
     *
     * @return : 返回数据库的连接
     * @throws SQLException : SQL的异常
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PWD);
    }

    /**
     * 设置数据库
     */
    public static void setDatabase(String username, String password, String url) {
        setDatabase(username, password, url, DRIVER);
    }

    /**
     * 设置数据库
     */
    public static void setDatabase(String username, String password, String url, String driver) {
        properties.setProperty("username", username);
        properties.setProperty("password", password);
        if (url != null) {
            properties.setProperty("url", url);
        }
        properties.setProperty("driverClassName", driver);
        try {
            properties.store(new FileOutputStream("src/database.properties"), "");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获得实例
     *
     * @return {@link JDBCUtils}
     */
    public static JDBCUtils getInstance() {
        if (jdbcUtils != null) {
            return new JDBCUtils();
        } else {
            return jdbcUtils;
        }
    }

    /**
     * 关闭所有打开的资源
     *
     * @param connection : 连接的对象
     * @param statement  : 执行SQL语句的对象
     */
    public static void close(Connection connection, Statement statement) {
        close(connection, statement, null);
    }

    /**
     * 关闭资源 从小关闭到大
     *
     * @param connection 连接
     * @param statement  执行SQL语句的对象
     * @param resultSet  结果集
     */
    public static void close(Connection connection, Statement statement, ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
