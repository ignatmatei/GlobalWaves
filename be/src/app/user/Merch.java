package app.user;

public final class Merch {
    private String name;
    private int price;
    private String description;
    /**
     * Constructor with parameters
     */
    public Merch(final String name, final int price, final String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }
    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }
    public String getDescription() {
        return description;
    }
}
