package mx.infotec.dads.kukulkan.qa.datapoolgenerator.service;

import java.io.File;
import java.io.IOException;

import mx.infotec.dads.kukulkan.qa.datapoolgenerator.domain.DataPool;

public interface CsvGeneratorService {
	
	public File writeCsv(DataPool dataPool) throws IOException;

}
