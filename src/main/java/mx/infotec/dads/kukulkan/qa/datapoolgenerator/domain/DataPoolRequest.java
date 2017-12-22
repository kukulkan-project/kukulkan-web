package mx.infotec.dads.kukulkan.qa.datapoolgenerator.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * A DataPoolRequest useful for generate a DataPool
 * 
 * @author Roberto Villarejo Martínez
 *
 */
public class DataPoolRequest {

    private int rowsNumber;

    private List<DataColumn> addDataTypes = new ArrayList<>();

    private List<DataColumn> repeatDataTypes = new ArrayList<>();

    private int repeatTimes;

    public int getRowsNumber() {
        return rowsNumber;
    }

    public void setRowsNumber(int rowsNumber) {
        this.rowsNumber = rowsNumber;
    }

    public List<DataColumn> getAddDataTypes() {
        return addDataTypes;
    }

    public void setAddDataTypes(List<DataColumn> addDataTypes) {
        this.addDataTypes = addDataTypes;
    }

    public List<DataColumn> getRepeatDataTypes() {
        return repeatDataTypes;
    }

    public void setRepeatDataTypes(List<DataColumn> repeatDataTypes) {
        this.repeatDataTypes = repeatDataTypes;
    }

    public int getRepeatTimes() {
        return repeatTimes;
    }

    public void setRepeatTimes(int repeatTimes) {
        this.repeatTimes = repeatTimes;
    }

}
