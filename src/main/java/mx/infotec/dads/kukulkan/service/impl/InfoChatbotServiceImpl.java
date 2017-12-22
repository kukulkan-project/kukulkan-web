package mx.infotec.dads.kukulkan.service.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import ai.api.model.AIContext;
import ai.api.model.Fulfillment;
import mx.infotec.dads.kukulkan.domain.Category;
import mx.infotec.dads.kukulkan.domain.Contact;
import mx.infotec.dads.kukulkan.domain.Service;
import mx.infotec.dads.kukulkan.domain.dialogflow.Message;
import mx.infotec.dads.kukulkan.domain.dialogflow.RequestDialogFlow;
import mx.infotec.dads.kukulkan.repository.CategoryRepository;
import mx.infotec.dads.kukulkan.repository.ServiceRepository;
import mx.infotec.dads.kukulkan.service.InfoChatbotService;

@org.springframework.stereotype.Service
public class InfoChatbotServiceImpl implements InfoChatbotService {

	private final Logger log = LoggerFactory.getLogger(InfoChatbotServiceImpl.class);

	@Autowired
	private ServiceRepository serviceRepository;

	@Autowired
	private CategoryRepository categoryRepository;
	
	private final Random randomGenerator = new Random();

	@Override
	public Fulfillment fullfill(RequestDialogFlow request) {

		String action = request.getResult().getAction();
		String categoryName = "";
		String serviceName = "";
		Service service = null;
		Category category = null;
		List<Category> categories = Collections.emptyList();
		List<Service> services = Collections.emptyList();
		Message message;
		String speech = "";

		log.debug("Request for action {}", action);

		Fulfillment fulfillment = new Fulfillment();
		fulfillment.setSource("Infochatbot service");

		switch (action) {
		case "input.welcome":
			log.debug("Request to get the welcome message");
			
			message = getRandomMessage(request);
			if (message == null) {
				speech += "Hola! ";
			} else {
				speech += message.getSpeech();
			}
			categories = categoryRepository.findAll();
			Iterator<Category> iterator = categories.iterator();
			while (iterator.hasNext()) {
				speech = speech.concat(iterator.next().getName() + "\n");
			}
			break;

		case "support.category.services":
			categoryName = request.getParameter("category");
			log.debug("Request to get the services of category {}", categoryName);
			category = categoryRepository.findOneByName(categoryName);
			if (category != null) {
				services = serviceRepository.findAllByCategory(category.getId());
				Iterator<Service> it = services.iterator();
				while (it.hasNext()) {
					speech = speech.concat(it.next().getName() + "\n");
				}
			}		

			break;

		case "support.category.description":
			categoryName = request.getParameter("category");
			log.debug("Request to get the description of category {}", categoryName);
			category = categoryRepository.findOneByName(categoryName);
			message = getRandomMessage(request);
			if (message != null) {
				speech += message.getSpeech();
			}
			if (category != null) {
				speech += category.getDescription();
			} else {
				speech += "Lo siento, no ofrecemos ese servicio...";
			}
//			AIEvent event = new AIEvent("service-description");
//			fulfillment.setFollowupEvent(event);
			break;

		case "support.category.contact":
			categoryName = request.getParameter("category");
			log.debug("Request to get the contact of category {}", categoryName);
			category = categoryRepository.findOneByName(categoryName);
			log.debug(category.toString());
			if (category != null) {
				Contact contact = category.getContact();
				speech = "Más información con " + contact.getName() + ". Escribe un correo a "
						+ contact.getEmail() + " o llama al teléfono " + contact.getPhone();
			}

			break;
			
		case "support.service.description":
			serviceName = request.getParameter("service");
			log.debug("Request to get the description of service {}", serviceName);
			service = serviceRepository.findOneByName(serviceName);
			if (service != null) {
				speech = service.getDescription() + "\n"
						+ "Sigue este enlace: " + service.getUrl();
				AIContext context = new AIContext("category");
				context.setLifespan(5);
				context.setParameters(new HashMap<String, String>());
				context.getParameters().put("category", service.getCategory().getName());
				fulfillment.setContextOut();
			}
			break;

		default:
			speech = "Lo siento, no entendí eso :(";
			break;
		}
		
		fulfillment.setSpeech(speech);

		return fulfillment;
	}
	
	/**
	 * Get a random message from request (responses defined in DialogFlow intent)
	 * @param request
	 * @return a random Message or null if there are no Messages
	 */
	private Message getRandomMessage(RequestDialogFlow request) {
		List<Message> messages = request.getResult().getFullfillment().getMessages();
		int randomIndex = randomGenerator.nextInt(messages.size());
		log.debug("Getting the {} message of messages {}", randomIndex, messages);
		return request.getMessage(randomIndex);
	}

}
