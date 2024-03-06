package lhweb.asia.LHTomCat.servlet;

import com.google.gson.Gson;
import lhweb.asia.LHTomCat.common.Result;
import lhweb.asia.LHTomCat.http.LhHttpServletV3;
import lhweb.asia.LHTomCat.http.LhRequestV3;
import lhweb.asia.LHTomCat.http.LhResponseV3;
import lhweb.asia.LHTomCat.model.TrainUser;
import lhweb.asia.LHTomCat.model.User;
import lhweb.asia.LHTomCat.service.UserService;

/**
 * 用户servlet
 * 这里处理和用户相关的信息
 *
 * @author 罗汉
 * @date 2024/02/26
 */
public class UserServletV3 extends LhHttpServletV3 {
    private UserService userService;//用户服务类
    private Gson gson;//谷歌的解析json的工具类

    public UserServletV3() {
        userService = new UserService();
    }

    @Override
    public void doGet(LhRequestV3 req, LhResponseV3 resp) {
        if ("login".equals(req.getParameter("action"))) {
            login(req, resp);
        } else if ("register".equals(req.getParameter("action"))) {
            register(req, resp);
        }
    }

    /**
     * 注册用户
     *
     * @param req  请求对象，包含注册用户的用户名和密码
     * @param resp 响应对象，用于返回注册结果
     */
    private void register(LhRequestV3 req, LhResponseV3 resp) {
        // 获取请求参数中的用户名和密码
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        // 调用用户服务注册新用户
        boolean registerRes = userService.register(new User(username, password));
        gson = new Gson();

        // 将注册结果转换为 JSON 字符串
        String presJson;
        if (registerRes) {
            // 注册成功，返回成功信息
            presJson = gson.toJson(Result.success("注册成功"));
        } else {
            // 注册失败，返回错误信息
            presJson = gson.toJson(Result.error("注册失败"));
        }
        // 将 JSON 字符串写入响应对象中
        resp.writeToJson(presJson);
    }


    /**
     * 登录
     * 用户登录的方法登录
     *
     * @param req  要求事情
     * @param resp 分别地
     */
    private void login(LhRequestV3 req, LhResponseV3 resp) {
        //获取用户和密码
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        //新建用户
        TrainUser trainUser = new TrainUser();
        trainUser.setUsername(username);
        trainUser.setPassword(password);
        //调用service的登录方法
        TrainUser login = userService.login(trainUser);
        gson = new Gson();
        String presJson;
        if (login != null) {
            presJson = gson.toJson(Result.success(login, "登录成功"));
        } else {
            presJson = gson.toJson(Result.error("登录失败"));
        }
        resp.writeToJson(presJson);
    }

    @Override
    public void doPost(LhRequestV3 req, LhResponseV3 res) {
        doGet(req, res);
    }

    @Override
    public void init() throws Exception {

    }

    @Override
    public void destroy() {

    }
}
