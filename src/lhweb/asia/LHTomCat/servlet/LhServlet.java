package lhweb.asia.LHTomCat.servlet;


import lhweb.asia.LHTomCat.http.LhRequest;
import lhweb.asia.LHTomCat.http.LhResponse;

import java.io.IOException;

/**
 * Servlet接口
 *
 */
public interface LhServlet {
    void init() throws Exception;
    void service(LhRequest req, LhResponse resp) throws IOException;
    void destroy();
}
