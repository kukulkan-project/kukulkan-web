package mx.infotec.dads.kukulkan.engine.domain.core;

/**
 * DataContext, it represent the context of data used during the model
 * generation
 * 
 * @author Daniel Cortes Pichardo
 *
 */
public class DataContextContainer<T> {

    private DataContextType dataContextType;

    private T dataContext;

    public DataContextContainer(DataContextType dataContextType, T data) {
        this.setDataContextType(dataContextType);
        this.setDataContext(data);
    }

    public T getDataContext() {
        return dataContext;
    }

    public void setDataContext(T dataContext) {
        this.dataContext = dataContext;
    }

    public DataContextType getDataContextType() {
        return dataContextType;
    }

    public void setDataContextType(DataContextType dataContextType) {
        this.dataContextType = dataContextType;
    }

}
