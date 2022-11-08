package entity;

import interfaces.SerializedData;

public class Flag implements SerializedData {
    private boolean flag;

    public Flag(boolean flag) {
        this.flag = flag;
    }
    public void setFlag(boolean flag) {
        this.flag = flag;
    }
    public boolean getFlag(){
        return flag;
    }
    public void inverseFlag(){
        flag = !flag;
    }
}
