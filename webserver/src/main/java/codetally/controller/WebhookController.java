package codetally.controller;


import codetally.service.WebhookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class WebhookController {

    @Autowired
    WebhookService webhookService;

    @RequestMapping(value = "/webhook", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE)
    public void add(@RequestBody String body) {
        webhookService.processWebhook(body);
    }
}
