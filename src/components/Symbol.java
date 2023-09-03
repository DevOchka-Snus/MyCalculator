package components;

public enum Symbol {
    ONE ,
    TWO ,
    THREE,
    FOUR,
    FIVE,
    SIX,
    SEVEN,
    EIGHT,
    NINE,
    PLUS,
    ZER0,
    MINUS;
    //STAR,
    //SLASH;

    private String title;
    Symbol getNext() {
        return values()[this.ordinal() + 1];
    }

    @Override
    public String toString() {
        return "Symbol{}";
    }
}
