package entity;

import interfaces.SerializedData;

import java.time.LocalDate;

public class Holiday implements SerializedData {
    private LocalDate date;

    public Holiday(LocalDate date) {
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}

