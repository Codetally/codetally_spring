package com.codetally.controller;

import com.codetally.service.ShieldServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by greg on 29/08/17.
 */

@WebServlet(value = "/shield/*")
public class ShieldController extends HttpServlet {
    private ShieldServiceImpl shieldService;

    @Override
    public void init() throws ServletException {
        shieldService = new ShieldServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String response = "";
        String[] parts = req.getRequestURI().split("/");
        String friendlyCost = "";
        if (parts.length == UrlPart.PARTSWITHOUTCOST) {
            friendlyCost = shieldService.getShieldByOwnerAndRepo(parts[UrlPart.OWNER], parts[UrlPart.REPO]);
        } else if (parts.length == UrlPart.PARTSWITHCOST) {
            friendlyCost = shieldService.getShieldByOwnerAndRepoAndCost(parts[UrlPart.OWNER], parts[UrlPart.REPO], parts[UrlPart.COST]);
        }
        response = shieldService.getShieldByValue(friendlyCost);
        resp.setHeader("Content-Type", "image/svg+xml");
        resp.getWriter().write(response);
    }
}
