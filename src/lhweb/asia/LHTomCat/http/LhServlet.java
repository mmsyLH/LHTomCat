package lhweb.asia.LHTomCat.http;


import java.io.IOException;

/**
 * Servlet接口
 *
 */
public interface LhServlet {
    void init() throws Exception;
    void service(LhRequest request, LhResponse response) throws IOException;
    void destroy();
}
