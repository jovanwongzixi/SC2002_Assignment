package interfaces;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public interface UserInfoHandler {
    public <T extends SerializedData> HashMap<String, T> retrieve();

    public <T extends SerializedData> void save(HashMap<String, T> inputMap);
}