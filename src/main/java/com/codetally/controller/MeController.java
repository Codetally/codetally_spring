package com.codetally.controller;

import com.codetally.service.MeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.HttpURLConnection;

/**
 * Created by greg on 29/08/17.
 */

@WebServlet(value = "/me")
public class MeController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String token = "";
        for (Cookie cookie : req.getCookies()) {
            System.out.println("Cookie name: " + cookie.getName() + ", and value: " + cookie.getValue());
            if (cookie.getName().equalsIgnoreCase("X-AUTH-TOKEN")) {
                token = cookie.getValue();
                break;
            }
        }

        MeService meService = new MeService();
        String meString = meService.validateMe(token);
        if (meString == null || meString.isEmpty()) {
            resp.setStatus(HttpURLConnection.HTTP_UNAUTHORIZED);
        } else {
            resp.getWriter().write(meString);
            resp.setStatus(HttpURLConnection.HTTP_OK);
        }
    }
}
