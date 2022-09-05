public class Item {
    protected int itemId;
    protected String name;
    protected double price;
    protected String type;
    protected int availability;



    public Item() {
    }

    public Item(int itemId, String name, double price, String type, int availability) {
        this.itemId = itemId;
        this.name = name;
        this.price = price;
        this.type = type;
        this.availability = availability;
    }

    public int getItemId() {
        return this.itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAvailability() {
        return this.availability;
    }

    public void setAvailability(int availability) {
        this.availability = availability;
    }

    

    @Override
    public String toString() {
        return "{" +
            " itemId='" + getItemId() + "'" +
            ", name='" + getName() + "'" +
            ", price='" + getPrice() + "'" +
            ", type='" + getType() + "'" +
            ", availability='" + getAvailability() + "'" +
            "}";
    }

}
