package codetally.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.HttpURLConnection;

/**
 * Created by greg on 29/08/17.
 */

@WebServlet(value = "/logout")
public class LogoutController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.addHeader("set-cookie", "X-AUTH-TOKEN=deleted; path=/; expires=Thu, 01 Jan 1970 00:00:00 GMT");
        resp.addHeader("location", "/index.html");
        resp.setStatus(HttpURLConnection.HTTP_MOVED_TEMP);
    }

}
