package com.codetally.controller;

import com.google.gson.Gson;

import java.io.IOException;

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
