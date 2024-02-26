package lhweb.asia.LHTomCat.servlet;

import lhweb.asia.LHTomCat.http.LhRequest;
import lhweb.asia.LHTomCat.http.LhResponse;

import java.io.IOException;

/**
 * 罗汉httpservlet
 *
 * @author 罗汉
 * @date 2024/02/26
 */
public abstract class LhHttpServlet implements LhServlet {
    @Override
    public void service(LhRequest req, LhResponse resp) throws IOException {
        // equalsIgnoreCase比较两个字符串是否相等，且忽略大小写
        if ("GET".equalsIgnoreCase(req.getMethod())) {
            // this的动态绑定非常非常重要  谁调用的就是谁  真正的运行类型
            this.doGet(req, resp);
        }
        if ("POST".equalsIgnoreCase(req.getMethod())) {
            this.doPost(req, resp);
        }
    }

    // 模板设计模式
    // 让LhHttpServlet的子类去实现
    public abstract void doGet(LhRequest req, LhResponse resp);

    public abstract void doPost(LhRequest req, LhResponse resp);
}
