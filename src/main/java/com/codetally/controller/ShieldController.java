package com.codetally.controller;

import com.codetally.service.ShieldService;
import com.codetally.service.ShieldServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class ShieldController {

    @Autowired
    private ShieldService shieldService;

    @ResponseBody
    @RequestMapping(value = "/shield", method = RequestMethod.GET , produces = MediaType.)
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
