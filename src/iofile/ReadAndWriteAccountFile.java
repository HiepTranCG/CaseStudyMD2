package iofile;

import model.manger.Manager;
import model.player.Player;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReadAndWriteAccountFile {
    public static final String PATH_ACCOUNT_MANAGER = "/main/file/data/ManagerAccount.csv";
    public static final String PATH_ACCOUNT_PLAYER = "/main/file/data/PlayerAccount.csv";

    public static void writeToFileAccount(List<Manager> list) {
        FileWriter fw = null;
        BufferedWriter bw = null;
        try {
            String str = "";
            fw = new FileWriter(PATH_ACCOUNT_MANAGER, true);
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
        List<Manager> accountList = new ArrayList<>();
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

    public static void writeToFileAccountPlay(List<Player> list) {
        FileWriter fw = null;
        BufferedWriter bw = null;
        try {
            String str = "";
            fw = new FileWriter(PATH_ACCOUNT_PLAYER, true);
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
        List<Player> accountList = new ArrayList<>();
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
}
