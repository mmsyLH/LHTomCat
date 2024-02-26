package lhweb.asia.LHTomCat.servlet;

import com.google.gson.Gson;
import lhweb.asia.LHTomCat.common.Result;
import lhweb.asia.LHTomCat.http.LhRequest;
import lhweb.asia.LHTomCat.http.LhResponse;

/**
 * 用户servlet
 * 这里处理和用户相关的信息
 *
 * @author 罗汉
 * @date 2024/02/26
 */
public class UserServlet extends LhHttpServlet {
    public UserServlet() {

    }

    @Override
    public void doGet(LhRequest req, LhResponse res) {
        if ("login".equals(req.getParameter("action"))) {
            login(req, res);
        }
    }

    /**
     * 登录
     * 用户登录的方法登录
     *
     * @param req  要求事情
     * @param resp 分别地
     */
    private void login(LhRequest req, LhResponse resp) {
        System.out.println("这里是登录方法");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        System.out.println("username:" + username);
        System.out.println("password:" + password);
        if ("admin".equals(username)&&"123456".equals(password)) {//登录成功
            Gson gson = new Gson();
            String presJson = gson.toJson(Result.success("登录成功"));
            System.out.println("presJson："+presJson);
            resp.writeToJson(presJson);
        }else {//登录失败
            Gson gson = new Gson();
            String presJson = gson.toJson(Result.error("登录失败"));
            System.out.println("presJson："+presJson);
            resp.writeToJson(presJson);
        }
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
