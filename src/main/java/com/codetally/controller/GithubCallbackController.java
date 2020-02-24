package com.codetally.controller;

import com.codetally.service.MeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by greg on 29/08/17.
 */

@WebServlet(value = "/ghcb")
public class GithubCallbackController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //try to detect if the app has been installed. If not, redirect back to https://github.com/apps/codetally/installations/new
        Map<String, String> mapQuery = queryToMap(req.getQueryString());

        MeServiceImpl meService = new MeServiceImpl();
        resp.addHeader("set-cookie", meService.createCookie(meService.handleCallback(mapQuery.get("code"))));
        resp.addHeader("location", "/index.html");
        resp.setStatus(HttpURLConnection.HTTP_MOVED_TEMP);
    }

    public Map<String, String> queryToMap(String query) {
        Map<String, String> result = new HashMap<String, String>();
        for (String param : query.split("&")) {
            String[] pair = param.split("=");
            if (pair.length > 1) {
                result.put(pair[0], pair[1]);
            } else {
                result.put(pair[0], "");
            }
        }
        return result;
    }

}
