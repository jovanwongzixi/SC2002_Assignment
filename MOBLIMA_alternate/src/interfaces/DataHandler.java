package interfaces;

import java.util.List;

public interface DataHandler {
    public <T extends SerializedData> List<T> retrieve();
    public <T extends SerializedData> void save(List<T> inputList);
}
