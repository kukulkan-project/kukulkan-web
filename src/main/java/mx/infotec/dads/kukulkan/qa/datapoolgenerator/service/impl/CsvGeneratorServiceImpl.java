package mx.infotec.dads.kukulkan.qa.datapoolgenerator.service.impl;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.univocity.parsers.csv.CsvWriter;
import com.univocity.parsers.csv.CsvWriterSettings;

import mx.infotec.dads.kukulkan.qa.datapoolgenerator.domain.DataColumn;
import mx.infotec.dads.kukulkan.qa.datapoolgenerator.domain.DataPool;
import mx.infotec.dads.kukulkan.qa.datapoolgenerator.service.CsvGeneratorService;


@Service
public class CsvGeneratorServiceImpl implements CsvGeneratorService {

	private final Logger log = LoggerFactory.getLogger(CsvGeneratorServiceImpl.class);
	
	private CsvWriterSettings settings = new CsvWriterSettings();
	
	public CsvGeneratorServiceImpl() {
		settings.setQuoteAllFields(true);
		settings.setEmptyValue("!");
		settings.setNullValue("?");
		settings.setSkipEmptyLines(true);
	}

	@Override
	public File writeCsv(DataPool dataPool) throws IOException {
		if (dataPool.getData().isEmpty()) return null; 
		File csvFile = Files.createTempFile(dataPool.getName(), ".csv").toFile();
		CsvWriter csvWriter = new CsvWriter(csvFile, "windows-1250", settings);
		writeHeaders(dataPool, csvWriter);
		writeData(dataPool, csvWriter);
		csvWriter.close();
		return csvFile;
	}

	private void writeHeaders(DataPool dataPool, CsvWriter csvWriter) {
		List<String> headers = new ArrayList<>();
		for (DataColumn dataColumn : dataPool.getData()) {
			headers.add(dataColumn.getHeader());
		}
		csvWriter.writeHeaders(headers);
	}

	private void writeData(DataPool dataPool, CsvWriter csvWriter) {
		int rowsNumber = dataPool.getData().get(0).getData().size();
		for (int i = 0; i < rowsNumber; i++) {
			List<String> row = new ArrayList<>();
			for (DataColumn dataColumn : dataPool.getData()) {
				row.add(dataColumn.getData().get(i));
			}
			csvWriter.writeRow(row.toArray(new String[dataPool.getData().size()]));
		}		
	}

}
