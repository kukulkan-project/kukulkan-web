package mx.infotec.dads.kukulkan.service;

import ai.api.model.Fulfillment;
import mx.infotec.dads.kukulkan.domain.dialogflow.RequestDialogFlow;

public interface InfoChatbotService {

    public Fulfillment fullfill(RequestDialogFlow request);

}
