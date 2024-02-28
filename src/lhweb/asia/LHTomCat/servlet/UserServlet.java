package lhweb.asia.LHTomCat.servlet;

import com.google.gson.Gson;
import lhweb.asia.LHTomCat.common.Result;
import lhweb.asia.LHTomCat.http.LhHttpServlet;
import lhweb.asia.LHTomCat.http.LhRequest;
import lhweb.asia.LHTomCat.http.LhResponse;
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
public class UserServlet extends LhHttpServlet {
    private UserService userService;//用户服务类
    private Gson gson;//谷歌的解析json的工具类

    public UserServlet() {
        userService = new UserService();
    }

    @Override
    public void doGet(LhRequest req, LhResponse resp) {
        if ("login".equals(req.getParameter("action"))) {
            login(req, resp);
        } else if ("register".equals(req.getParameter("action"))) {
            register(req, resp);
        }
    }

    /**
     * 注册
     *
     * @param req  请求
     * @param resp 响应
     */

    private void register(LhRequest req, LhResponse resp) {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        boolean registerRes = userService.register(new User(username, password));
        gson = new Gson();
        String presJson;
        if (registerRes) {
            presJson = gson.toJson(Result.success("注册成功"));
        } else {
            presJson = gson.toJson(Result.error("注册失败"));
        }
        resp.writeToJson(presJson);
    }

    /**
     * 登录
     * 用户登录的方法登录
     *
     * @param req  要求事情
     * @param resp 分别地
     */
    private void login(LhRequest req, LhResponse resp) {
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
        // System.out.println("presJson："+presJson);
        resp.writeToJson(presJson);
    }

    @Override
    public void doPost(LhRequest req, LhResponse res) {
        doGet(req, res);
    }

    @Override
    public void init() throws Exception {

    }

    @Override
    public void destroy() {

    }
}
