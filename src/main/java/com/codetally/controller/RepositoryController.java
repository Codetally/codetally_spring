package com.codetally.controller;

import com.codetally.service.RepositoryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by greg on 29/08/17.
 */

@WebServlet(value = "/repos/*")
public class RepositoryController extends HttpServlet {
    private RepositoryService repositoryService;

    @Override
    public void init() throws ServletException {
        repositoryService = new RepositoryService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String response = "";
        String[] parts = req.getRequestURI().split("/");
        response = repositoryService.getAllByOwnername(parts[UrlPart.OWNER]);
        resp.getWriter().write(response);
    }
}
