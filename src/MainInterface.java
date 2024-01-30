import java.util.NoSuchElementException;
import java.util.Scanner;

public class MainInterface {

    private Storage storage = new Storage();
    private Contest contest = new Contest();
    private WorkWithFile workWithFile = new WorkWithFile();

    protected void mainInterface() {
        workWithFile.createFile("Склад.csv");
        workWithFile.createFile("Лист для розыгрыша.csv");
        workWithFile.createFile("Результаты розыгрыша.csv");
        try (Scanner scanner = new Scanner(System.in)) {
            int choice = 0;
            while (choice != 3) {
                System.out.println("Добро пожаловать в наш магазин. Что желате сделать?:" + '\n' +
                        "1) Поработать с складом" + '\n' +
                        "2) Произвести розыгрыш игрушек" + '\n' +
                        "3) Выйти из программы");
                choice = scanner.nextInt();
                switch (choice) {
                    case 1 -> {
                        while (choice != 5) {
                            System.out.println('\n' + "Добро пожаловать на склад. Что желате сделать?:" + '\n' +
                                    "1) Показать все товары" + '\n' +
                                    "2) Добавить товар" + '\n' +
                                    "3) Удалить товар" + '\n' +
                                    "4) Изменить товар" + '\n' +
                                    "5) Вернуться в основное меню");
                            choice = scanner.nextInt();
                            switch (choice) {
                                case 1 -> storage.showStorage();
                                case 2 -> storage.addToyToStorage();
                                case 3 -> storage.deleteFromStorage();
                                case 4 -> storage.changeToyInStorage();
                                case 5 -> System.out.println("Возвращаемся в основное меню");
                                default -> System.out.println("Ошибка");
                            }
                        }
                    }
                    case 2 -> {
                        while (choice != 6) {
                            System.out.println('\n' + "Добро пожаловать в систему проведения розыгрпыша. " +
                                    "Что желате сделать?:" + '\n' +
                                    "1) Показать список игрушек для розыгрыша" + '\n' +
                                    "2) Добавить игрушку в розыгрыш" + '\n' +
                                    "3) Удалить игрушку из розыгрыша" + '\n' +
                                    "4) Провести розыгрыш" + '\n' +
                                    "5) Показать лист с результатами розыгрышей" + '\n' +
                                    "6) Вернуться в основное меню");
                            choice = scanner.nextInt();
                            switch (choice) {
                                case 1 -> contest.showContestList();
                                case 2 -> contest.addToyToContest();
                                case 3 -> contest.deleteFromContestList();
                                case 4 -> contest.startContest();
                                case 5 -> contest.showResultOfContests();
                                case 6 -> System.out.println("Возвращаемся в основное меню");
                                default -> System.out.println("Ошибка");
                            }
                        }
                    }
                    case 3 -> System.out.println("До свиданья.");
                    default -> System.out.println("Неверная команда");
                }
            }
        } catch (
                NoSuchElementException e) {
            System.out.println("Вы ошиблись при вводе команды, попробуйте снова.");
        }
    }
}
