public class Toy {
    private int id;
    private String name;
    private int quantity;
    private int weight;

    protected Toy(int id, String name, int quantity, int weight) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.weight = weight;
    }

    protected int getId() {
        return id;
    }

    protected String getName() {
        return name;
    }

    protected int getQuantity() {
        return quantity;
    }

    protected int getWeight() {
        return weight;
    }

    protected void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return  "id: " + id +
                "; Название игрушки: "  + name +
                "; Колличество на складе: " + quantity +
                "; Шанс выйгрыша: " + weight;
    }
}
