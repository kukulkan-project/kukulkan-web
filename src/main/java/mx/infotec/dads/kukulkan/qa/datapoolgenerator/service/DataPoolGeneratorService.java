package mx.infotec.dads.kukulkan.qa.datapoolgenerator.service;

import mx.infotec.dads.kukulkan.qa.datapoolgenerator.domain.DataPool;

/**
 * The interface for DataPoolGeneratorService
 * @author Roberto Villarejo Martínez
 *
 */
public interface DataPoolGeneratorService {

	public default DataPool generate(DataPool dataPool) {
		copyDataSource(dataPool);
		addColumns(dataPool);
		repeatData(dataPool);
		return dataPool;
	}

	public abstract void repeatData(DataPool dataPool);

	public abstract void addColumns(DataPool dataPool);

	public abstract void copyDataSource(DataPool dataPool);
	
}
