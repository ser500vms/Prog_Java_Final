import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;

public class Contest {
    private WorkWithFile workWithFile = new WorkWithFile();
    private ArrayList<ToyForContest> contestList = new ArrayList<>();


    private void setToyForContest() {
        this.contestList = workWithFile.parseFromContestFile();
    }

    protected ArrayList<ToyForContest> getToyForContest() {
        setToyForContest();
        return contestList;
    }

    @Override
    public String toString() {
        return "Разыгрываются следующие позиции: " + '\n' + contestList;
    }

    protected void addToyToContest() {
        System.out.println("Введите id игрушки для добавления в розыгрыш. Вот Список всех игрушек на складе:");
        ArrayList<String> toys = workWithFile.readFromFile("Склад.csv");
        int toyId;
        int quantityToyForContest;
        String result;
        try {
            Scanner scanner = new Scanner(System.in);
            toyId = scanner.nextInt();
            System.out.println("Введите количество игрушек для розыгрыша");
            quantityToyForContest = scanner.nextInt();
            result = toys.get(toyId - 1) + "; Участвуют в розыгрыше: " + quantityToyForContest;
            workWithFile.addDataToFile(result, "Лист для розыгрыша.csv");

        } catch (NoSuchElementException e) {
            System.out.println("Вы ошиблись, запустите команду заново");
        }
    }

    protected void showContestList() {
        workWithFile.readFromFile("Лист для розыгрыша.csv");
    }


    protected void deleteFromContestList() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите id игрушки, который хотите удалить, вот список игрушек участвующих в розыгрыше:");
            workWithFile.readFromFile("Лист для розыгрыша.csv");
            workWithFile.deleteFromFile(scanner.nextInt(), "Лист для розыгрыша.csv");

        } catch (
                NoSuchElementException e) {
            System.out.println("Вы ошиблись при вводе команды, попробуйте снова.");
        }
    }

    protected void startContest() {
        java.time.LocalDate currentDate = java.time.LocalDate.now();
        workWithFile.addDataToFile("Результаты розыгрыша от ->" + currentDate + '\n',
                "Результаты розыгрыша.csv");
        ArrayList<ToyForContest> toyForContests = getToyForContest();
        int count = 0;
        Random random = new Random();
        for (ToyForContest toyForContest : toyForContests) {
            while (toyForContest.getQuantityForContest() > 0) {
                int winn = random.nextInt(100) + 1;
                if (winn < toyForContest.getWeight()) {
                    System.out.println("Поздравляем Вы выйграли " + toyForContest.getName());
                    System.out.println("Выпало на кубике -> " + winn);
                    System.out.println("Нужно чтобы выпало до -> " + toyForContest.getWeight());
                    toyForContest.setQuantity(toyForContest.getQuantity() - 1);
                    workWithFile.rewriteFile(toyForContest.getId(), toyForContest.getName(),
                            toyForContest.getQuantity(), toyForContest.getWeight());
                    java.time.LocalDateTime currentDateTime = java.time.LocalDateTime.now();
                    String log = "Игрушка " + toyForContest.getName() + " была выйграна ->" + currentDateTime + '\n';
                    workWithFile.addDataToFile(log, "Результаты розыгрыша.csv");
                    toyForContest.setQuantityForContest(toyForContest.getQuantityForContest() - 1);
                    count++;
                } else {
                    System.out.println("Сожалем Вы проигралм, повезет в следующий раз");
                    System.out.println("Выпало на кубике -> " + winn);
                    System.out.println("Нужно чтобы выпало до -> " + toyForContest.getWeight());
                    java.time.LocalDateTime currentDateTime = java.time.LocalDateTime.now();
                    String log = "Игрушка " + toyForContest.getName() + " не была выйграна ->" + currentDateTime + '\n';
                    workWithFile.addDataToFile(log, "Результаты розыгрыша.csv");
                    toyForContest.setQuantityForContest(toyForContest.getQuantityForContest() - 1);
                }
            }
            String resultOfContest = "Игрушка " + toyForContest.getName() + " была выйграна " + count + " раз" + '\n';
            workWithFile.addDataToFile(resultOfContest, "Результаты розыгрыша.csv");
            count = 0;
        }

    }

    protected void showResultOfContests() {
        workWithFile.readFromFile("Результаты розыгрыша.csv");
    }

}
