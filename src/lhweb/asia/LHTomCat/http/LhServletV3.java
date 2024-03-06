package lhweb.asia.LHTomCat.http;


import java.io.IOException;

/**
 * Servlet接口
 *
 */
public interface LhServletV3 {
    void init() throws Exception;
    void service(LhRequestV3 request, LhResponseV3 response) throws IOException;
    void destroy();
}
