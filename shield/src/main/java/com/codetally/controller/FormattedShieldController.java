package com.codetally.controller;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

/**
 * Created by greg on 29/08/17.
 */

@Controller
public class FormattedShieldController  {

    @Autowired
    private ShieldService shieldService;

    @ResponseBody
    @RequestMapping(value = "/formattedshield/{projectid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
    public String getShieldData(@PathVariable Long projectid) throws IOException {
        ShieldCost shieldCost = shieldService.getShieldCostByProjectId(projectid);
        return new Gson().toJson(shieldCost);
    }
}
