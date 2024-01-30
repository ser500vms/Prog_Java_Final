public class ToyForContest extends Toy{
    private int quantityForContest;
    protected ToyForContest(int id, String name, int quantity, int weight, int quantityForContest) {
        super(id, name, quantity, weight);
        this.quantityForContest = quantityForContest;
    }

    protected int getQuantityForContest() {
        return quantityForContest;
    }

    protected void setQuantityForContest(int quantityForContest) {
        this.quantityForContest = quantityForContest;
    }

    @Override
    public String toString() {
        return  "id: " + super.getId() +
                "; Название игрушки: "  + super.getName() +
                "; Колличество на складе: " + super.getQuantity() +
                "; Шанс выйгрыша: " + super.getWeight() +
                "; Участвуют в розыгрыше: " + quantityForContest;
    }
}
