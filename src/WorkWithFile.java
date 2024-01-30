import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.SortedMap;

public class WorkWithFile {

    protected void createFile(String fileName) {
        try {
            File file = new File(fileName);
            if (file.createNewFile()) {
                System.out.println("Файл " + fileName + " создан");
            }
        } catch (IOException e) {
            System.out.println("Ошибка при создании файла");
        }
    }

    protected void addDataToFile(String data, String fileName) {
        try (FileWriter writer = new FileWriter(fileName, true);) {
            System.out.println("Произвожу добавление новых данных");
            writer.write(data);
            writer.write('\n');

        } catch (IOException e) {
            System.out.println("Ошибка при записи в файл");
        }
    }


    protected void deleteFromFile(int deletedToy, String fileName) {
        ArrayList<String> data = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line = reader.readLine();
            while (line != null) {
                data.add(line);
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        data.remove(deletedToy - 1);
        if (fileName.equals("Склад.csv")) {
            try (FileWriter writer = new FileWriter(fileName);) {
                for (int i = 0; i < data.size(); i++) {
                    String line = data.get(i).replace("id: " + Integer.toString(2 + i), "id: "
                            + Integer.toString(i + 1));
                    writer.write(line);
                    writer.write('\n');
                }
            } catch (IOException e) {
                System.out.println("Ошибка при записи в файл");
            }
        } else {
            try (FileWriter writer = new FileWriter(fileName);) {
                for (int i = 0; i < data.size(); i++) {
                    String line = data.get(i);
                    writer.write(line);
                    writer.write('\n');
                }
            } catch (IOException e) {
                System.out.println("Ошибка при записи в файл");
            }
        }


    }

    protected ArrayList<String> readFromFile(String fileName) {
        ArrayList<String> toys = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line = reader.readLine();
            while (line != null) {
                toys.add(line);
                System.out.println(line);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return toys;
    }

    protected ArrayList<Toy> parseFromStorageFile() {
        ArrayList<Toy> toys = new ArrayList<>();
        ArrayList<String[]> strings = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("Склад.csv"));
            String line = reader.readLine();
            while (line != null) {
                strings.add(line.split(";"));
                line = reader.readLine();
            }
            for (int i = 0; i < strings.size(); i++) {
                String temp = Arrays.toString(strings.get(0)).replace("[", "")
                        .replace("]", "").replace(",", ":");
                String[] temp2 = temp.split(":");
                int id = Integer.parseInt(temp2[1].replace(" ", ""));
                int quantity = Integer.parseInt(temp2[5].replace(" ", ""));
                int weight = Integer.parseInt(temp2[7].replace(" ", ""));
                Toy toy = new Toy(id, temp2[3] , quantity, weight);
                toys.add(toy);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return toys;
    }

    protected ArrayList<ToyForContest> parseFromContestFile() {
        ArrayList<ToyForContest> contestArrayList = new ArrayList<>();
        ArrayList<String[]> strings = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("Лист для розыгрыша.csv"));
            String line = reader.readLine();
            while (line != null) {
                strings.add(line.split(";"));
                line = reader.readLine();
            }
            for (int i = 0; i < strings.size(); i++) {
                String temp = Arrays.toString(strings.get(i)).replace("[", "")
                        .replace("]", "").replace(",", ":");
                String[] temp2 = temp.split(":");
                int id = Integer.parseInt(temp2[1].replace(" ", ""));
                int quantity = Integer.parseInt(temp2[5].replace(" ", ""));
                int weight = Integer.parseInt(temp2[7].replace(" ", ""));
                int quantityForContest = Integer.parseInt(temp2[9].replace(" ", ""));
                ToyForContest toyForContest = new ToyForContest(id, temp2[3] , quantity, weight, quantityForContest);
                contestArrayList.add(toyForContest);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contestArrayList;
    }


    protected void rewriteFile(int changedId, String name, int quantity, int weight) {
        ArrayList<String> data = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("Склад.csv"));
            String line = reader.readLine();
            while (line != null) {
                data.add(line);
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        data.set(changedId - 1, "id: " + changedId + "; Название игрушки: " + name +
                "; Колличество на складе: " + quantity + "; Шанс выйгрыша: " + weight);
        try (FileWriter writer = new FileWriter("Склад.csv");) {
            for (int i = 0; i < data.size(); i++) {
                writer.write(data.get(i) + " ");
                writer.write('\n');
            }
        } catch (IOException e) {
            System.out.println("Ошибка при записи в файл");
        }
    }
}
