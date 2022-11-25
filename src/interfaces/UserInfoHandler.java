package interfaces;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public interface UserInfoHandler {
    public <T extends User> HashMap<String, T> retrieve();

    public <T extends User> void save(HashMap<String, T> inputMap);
}