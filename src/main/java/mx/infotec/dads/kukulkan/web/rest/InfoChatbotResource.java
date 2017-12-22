package mx.infotec.dads.kukulkan.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ai.api.model.Fulfillment;
import mx.infotec.dads.kukulkan.domain.dialogflow.RequestDialogFlow;
import mx.infotec.dads.kukulkan.service.InfoChatbotService;

@RestController
@RequestMapping("/api")
public class InfoChatbotResource {

    private final Logger log = LoggerFactory.getLogger(InfoChatbotResource.class);

    private InfoChatbotService chatbotService;

    @Autowired
    public InfoChatbotResource(InfoChatbotService chatbotService) {
        this.chatbotService = chatbotService;
    }

    @PostMapping(path = "/fulfillment")
    public Fulfillment doFullfillment(@RequestBody RequestDialogFlow request) {
        log.debug("REST request for action {}", request.getResult().getAction());
        return chatbotService.fullfill(request);
    }
}
