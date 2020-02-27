package com.codetally.controller;

import com.codetally.model.github.GithubEvent;
import com.codetally.service.GithubWebhookService;
import com.codetally.service.GithubWebhookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.security.Principal;


@Controller
public class GithubWebhookController {

    @Autowired
    GithubWebhookService githubWebhookService;

    @RequestMapping(value = "/webhook", method = RequestMethod.POST)
    public String add(@RequestBody GithubEvent githubEvent) {
        //shouldn't this be adding a generic "event"?
        githubWebhookService.add(githubEvent);
        return "";
    }
}
