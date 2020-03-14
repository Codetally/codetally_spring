package com.codetally.controller;


import com.codetally.model.ShieldCost;
import com.codetally.service.ShieldService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

/**
 * Created by greg on 29/08/17.
 */

@Controller
public class FormattedShieldController  {

    @Autowired
    private ShieldService shieldService;

    @ResponseBody
    @RequestMapping(value = "/formattedshield/{projectid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getShieldData(@PathVariable Long projectid) throws IOException {
        ShieldCost shieldCost = shieldService.getShieldCostByProjectId(projectid);
        return new Gson().toJson(shieldCost);
    }
}
