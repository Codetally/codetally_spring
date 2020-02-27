package com.codetally.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by greg on 29/08/17.
 */

@WebServlet(value = "/config/*")
public class ConfigController extends HttpServlet {
    private ChargeService chargeService;

    @Override
    public void init() throws ServletException {
        chargeService = new ChargeService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String response = "";
        String[] parts = req.getRequestURI().split("/");
        response = chargeService.getChargeConfig(parts[UrlPart.OWNER], parts[UrlPart.REPO]);
        resp.getWriter().write(response);
    }
}
