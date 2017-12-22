package mx.infotec.dads.kukulkan.qa.datapoolgenerator.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.github.javafaker.Faker;

import mx.infotec.dads.kukulkan.qa.datapoolgenerator.domain.DataType;
import mx.infotec.dads.kukulkan.qa.datapoolgenerator.service.JavaFakerGeneratorService;

/**
 * The implementation of JavaFakerGeneratorService interface
 * @author roberto.villarejo
 *
 */
@Service
public class JavaFakerGeneratorServiceImpl implements JavaFakerGeneratorService {

	private final Logger log = LoggerFactory.getLogger(JavaFakerGeneratorServiceImpl.class);

	Faker faker;

	public JavaFakerGeneratorServiceImpl() {
		faker = new Faker(new Locale("es-MX"));
	}

	@Override
	public String generate(DataType dataType) {

		String data = null;
		switch (dataType) {
		case NAME:
			data = faker.name().firstName();
			break;

		case LAST_NAME:
			data = faker.name().lastName();
			break;

		case EMAIL:
			data = faker.internet().emailAddress();
			break;

		case LOREM:
			data = faker.lorem().characters();
			break;

		case COMPANY:
			data = faker.company().name();
			break;
			
		case CITY:
			data = faker.address().city();
			break;
			
		case COUNTRY:
			data = faker.address().country();
			break;
			
		case STATE:
			data = faker.address().state();
			break;
			
		case ZIP_CODE:
			data = faker.address().zipCode();
			break;
			
		case COLOR:
			data = faker.color().name();
			break;
			
		case DEPARTMENT:
			data = faker.commerce().department();
			break;
			
		case PRICE:
			data = faker.commerce().price();
			break;
			
		case PROFESSION:
			data = faker.company().profession();
			break;
			
		case PASSWORD:
			data = faker.internet().password();
			break;
			
		case FULL_NAME:
			data = faker.name().fullName();
			break;
			
		case PHONE_NUMBER:
			data = faker.phoneNumber().phoneNumber();
			break;
			
		case UNIVERSITY:
			data = faker.university().name();
			break;

		default:
			break;
		}
		return data;
	
	}

	@Override
	public List<String> generate(DataType dataType, int n) {

		List<String> data = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			data.add(generate(dataType));
		}
		return data;
	}

	@Override
	public List<List<String>> generate(List<DataType> dataTypes, int n) {
		List<List<String>> data = new ArrayList<>();
		for (DataType dataType : dataTypes) {
			data.add(generate(dataType, n));
		}
		return data;
	}
}
