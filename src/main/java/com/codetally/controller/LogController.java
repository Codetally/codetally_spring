package com.codetally.controller;

import com.codetally.service.LogService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by greg on 29/08/17.
 */

@WebServlet(value = "/log/*")
public class LogController extends HttpServlet {
    private LogService logService;

    @Override
    public void init() throws ServletException {
        logService = new LogService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String response = "";
        String[] parts = req.getRequestURI().split("/");
        response = logService.getLogLinesByOwnerAndRepo(parts[UrlPart.OWNER], parts[UrlPart.REPO]);
        resp.getWriter().write(response);
    }
}
