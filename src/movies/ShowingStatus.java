package movies;

public enum ShowingStatus {
    COMING_SOON("Coming Soon"),
    PREVIEW("Preview"),
    NOW_SHOWING("Now Showing"),
    END_OF_SHOWING("End of Showing");

    private String text;

    ShowingStatus(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }
}
