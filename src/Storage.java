import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Storage {
    private WorkWithFile workWithFile = new WorkWithFile();
    private ArrayList<Toy> storage = new ArrayList<>();

    private void setStorage() {
        this.storage = workWithFile.parseFromStorageFile();
    }

    protected ArrayList<Toy> getStorage() {
        setStorage();
        return storage;
    }

    @Override
    public String toString() {
        return "На складе: " + '\n' + storage;
    }

    protected void addToyToStorage() {
        String result;
        ArrayList<Toy> toys = getStorage();
        int id = toys.size() + 1;
        String name;
        int quantity;
        int weight;

        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите название игрушки");
            name = scanner.nextLine();
            System.out.println("Введите количество товара");
            quantity = scanner.nextInt();
            System.out.println("Введите шанс выйгрыша");
            weight = scanner.nextInt();
            result = "id: " + id + "; Название игрушки: " + name + "; Колличество на складе: " + quantity +
                    "; Шанс выйгрыша: " + weight;
            workWithFile.addDataToFile(result, "Склад.csv");

        } catch (NoSuchElementException e) {
            System.out.println("Вы ошиблись, запустите команду заново");
        }
    }

    protected void showStorage() {
        workWithFile.readFromFile("Склад.csv");
    }

    protected void deleteFromStorage() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите id товара, который хотите удалить, вот список товаров на складе:");
            workWithFile.readFromFile("Склад.csv");
            workWithFile.deleteFromFile(scanner.nextInt(), "Склад.csv");

        } catch (
                NoSuchElementException e) {
            System.out.println("Вы ошиблись при вводе команды, попробуйте снова.");
        }
    }

    protected void changeToyInStorage() {
        int changedToyId;
        String name;
        int quantity;
        int weight;
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите id товара, который хотите изменить, вот список товаров на складе:");
            workWithFile.readFromFile("Склад.csv");
            changedToyId = scanner.nextInt();
            System.out.println("Введите название игрушки");
            scanner.nextLine();
            name = scanner.nextLine();
            System.out.println("Введите количество товара");
            quantity = scanner.nextInt();
            System.out.println("Введите шанс выйгрыша");
            weight = scanner.nextInt();
            workWithFile.rewriteFile(changedToyId, name, quantity, weight);
        } catch (
                NoSuchElementException e) {
            System.out.println("Вы ошиблись при вводе команды, попробуйте снова.");
        }
    }
}

