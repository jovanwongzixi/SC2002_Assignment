package movies;

public enum MovieType {
    THREE_D("3D"),
    BLOCKBUSTER("Blockbuster"),
    REGULAR("Regular");

    private String text;

    MovieType(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }
}
