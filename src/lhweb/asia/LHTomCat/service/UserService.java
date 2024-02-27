package lhweb.asia.LHTomCat.service;


import lhweb.asia.LHTomCat.dao.UserDao;
import lhweb.asia.LHTomCat.model.User;
import lhweb.asia.LHTomCat.utils.DataUtils;


/**
 * 用户服务
 *
 * @author 罗汉
 * @date 2024/02/26
 */
public class UserService {
    private static UserDao userDao = new UserDao();

    public UserService() {

    }

    /**
     * 判断用户名是否存在
     *
     * @param username 用户名
     * @return boolean
     */
    public boolean isExists(String username) {
        User user = userDao.getByUsername(username);
        return user != null;
    }

    /**
     * 注册
     *
     * @param user 用户
     * @return boolean
     */
    public boolean register(User user) {
        // 1 用户名效验
        String username = user.getUsername();
        if (username == null || username.trim().isEmpty()) {

            return false;
        }

        // 2 密码效验
        String password = user.getPassword();
        if (password == null || password.length() < 6) {

            return false;
        }

        // 3 用户名是否存在效验
        boolean exists = isExists(user.getUsername());
        if (exists) {

        }
        // MD5加密
        user.setPassword(DataUtils.encryptPassword(user.getPassword()));

        return userDao.add(user) != -1;
    }

    /**
     * 登录
     *
     * @param loginUser 登录用户
     * @return {@link User}
     */
    public User login(User loginUser) {
        String username = loginUser.getUsername();
        String password = loginUser.getPassword();
        User user = userDao.getByUsername(username);
        if (user == null) {

            return null;
        }
        System.out.println(DataUtils.encryptPassword(password));
        System.out.println(user.getPassword());
        if (!DataUtils.verifyPassword(password, user.getPassword())) {
            return null;
        }
        return user;
    }
}
