package iofile;

import model.food.Food;
import model.manger.Manager;
import model.player.Player;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReadAndWriteAccountFile {
    public static final String PATH_ACCOUNT_MANAGER = "src//iofile//data//ManagerAccount.csv";
    public static final String PATH_ACCOUNT_PLAYER = "src//iofile//data//PlayerAccount.csv";
    public static final String PATH_FOOD = "src//iofile//data//ListFood.csv";

    public static void writeToFileAccountNoAppend(List<Manager> list) {
        FileWriter fw = null;
        BufferedWriter bw = null;
        try {
            String str = "";
            fw = new FileWriter(PATH_ACCOUNT_MANAGER);
            bw = new BufferedWriter(fw);
            for (Manager account : list) {
                str += account + "\n";
            }
            bw.write(str);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Manager> readFromFileAccount() {
        List<Manager> accountList = new ArrayList<Manager>();
        FileReader fr = null;
        BufferedReader br = null;
        String inf = "";
        try {
            fr = new FileReader(PATH_ACCOUNT_MANAGER);
            br = new BufferedReader(fr);
            while ((inf = br.readLine()) != null) {
                String[] arr = inf.split(",");
                accountList.add(new Manager(arr[0], arr[1]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return accountList;
    }

    public static void writeToFileAccountPlayNoAppend(List<Player> list) {
        FileWriter fw = null;
        BufferedWriter bw = null;
        try {
            String str = "";
            fw = new FileWriter(PATH_ACCOUNT_PLAYER);
            bw = new BufferedWriter(fw);
            for (Player account : list) {
                str += account + "\n";
            }
            bw.write(str);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Player> readFromFileAccountPlay() {
        List<Player> accountList = new ArrayList<Player>();
        FileReader fr = null;
        BufferedReader br = null;
        String inf = "";
        try {
            fr = new FileReader(PATH_ACCOUNT_PLAYER);
            br = new BufferedReader(fr);
            while ((inf = br.readLine()) != null) {
                String[] arr = inf.split(",");
                accountList.add(new Player(arr[0], arr[1], Integer.parseInt(arr[2])));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return accountList;
    }

    public static List<Food> readFromFileListFood() {
        List<Food> foodList = new ArrayList<Food>();
        FileReader fr = null;
        BufferedReader br = null;
        String inf = "";
        try {
            fr = new FileReader(PATH_FOOD);
            br = new BufferedReader(fr);
            while (((inf = br.readLine()) != null)) {
                String[] arr = inf.split(",");
                foodList.add(new Food(arr[0], Integer.parseInt(arr[1]), Integer.parseInt(arr[2])));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return foodList;
    }

    public static void writeToFileListFoodNoAppend(List<Food> listFood) {
        FileWriter fw = null;
        BufferedWriter bw = null;
        try {
            String str = "";
            fw = new FileWriter(PATH_FOOD);
            bw = new BufferedWriter(fw);
            for (Food food : listFood) {
                str += food + "\n";
            }
            bw.write(str);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
