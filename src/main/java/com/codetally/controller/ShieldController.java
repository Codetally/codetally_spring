package com.codetally.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class ShieldController {

    @Autowired
    private ShieldService shieldService;

    @ResponseBody
    @RequestMapping(value = "/shield/{owner}/{project}/{cost}", method = RequestMethod.GET , produces = "image/svg+xml")
    protected String getShield(@PathVariable String owner,
                               @PathVariable String project,
                               @PathVariable String cost)  {

        return shieldService.getShieldByOwnerAndRepoAndCost(owner, project, cost);
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
