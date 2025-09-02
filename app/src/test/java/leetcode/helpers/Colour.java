package leetcode.helpers;

public enum Colour {
    RED(0),
    BLUE(1);

    public final int value;

    Colour(int value) {
        this.value = value;
    }

    public Colour opposite() {
        return this == RED ? BLUE : RED;
    }
}