package controller;

import input.Input;
import iofile.ReadAndWriteAccountFile;
import model.food.Food;
import model.food.FoodManagement;
import model.menu.MenuPlayer;
import model.player.Player;
import model.player.PlayerManagement;

import java.net.Socket;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class HandleMenuPlayer {
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_RESET = "\u001B[0m";
    private int idAccount = -1;
    private Player myAccount = new Player();
    private PlayerManagement playerManagement = new PlayerManagement();
    private FoodManagement foodManagement = new FoodManagement();
    public static final int SHOW_MONEY_OF_ACCOUNT = 1;
    public static final int CHANGE_PASSWORD = 2;
    public static final int MENU_FOOD = 3;
    public static final int ORDER_FOOD = 4;
    public static final int LOGOUT = 0;
    public static final int DEFAULT = -1;
    Socket socket;
    public void run() {
        connect();
    }

    public void connect() {
        countDownTime(handlePlayerLogin());
        handlePlayerMenu();
    }

    public int handlePlayerLogin() {
        String userName = "";
        String passWord = "";
        boolean checkLogin = true;
        do {
            System.out.println("---------- ĐĂNG NHẬP MÁY ----------");
            System.out.println("NHẬP TÊN TÀI KHOẢN:");
            userName = Input.inputText();
            System.out.println("NHẬP TÊN MẬT KHẨU:");
            passWord = Input.inputText();
            List<Player> accountList = ReadAndWriteAccountFile.readFromFileAccountPlay();
            for (int i = 0; i < accountList.size(); i++) {
                if (accountList.get(i).getUserName().equals(userName) && accountList.get(i).getPassWord().equals(passWord)) {
                    idAccount = i;
                    System.out.println(ANSI_YELLOW + "Đăng nhập thành công!" + ANSI_RESET);
                    checkLogin = false;
                    return idAccount;
                }
            }
            if (checkLogin) {
                System.err.println("Sai tài khoản hoặc mật khẩu !");
            }
        } while (checkLogin);
        return -1;
    }

    public void handlePlayerMenu() {
        int choose = DEFAULT;
        do {
            MenuPlayer.showMenuPlayer();
            choose = Input.inputNumber();
            switch (choose) {
                case SHOW_MONEY_OF_ACCOUNT:
                    showAccount();
                    break;
                case CHANGE_PASSWORD:
                    changePassword();
                    break;
                case MENU_FOOD:
                    showFood();
                    break;
                case ORDER_FOOD:
                    orderFood();
                    break;
                case LOGOUT:
                    connect();
                    break;
                default:
                    System.err.println("Không có lựa chọn này vui lòng nhập lại!");
            }
        } while (choose != LOGOUT);
    }

    private void orderFood() {
        String name = "";
        int amount = 0;
        int money = 0;
        List<Food> foodList = ReadAndWriteAccountFile.readFromFileListFood();
        List<Player> accountList = ReadAndWriteAccountFile.readFromFileAccountPlay();
        Player player = getMyAccount();
        showFood();
        System.out.println("NHẬP TÊN MÓN MUA: ");
        name = Input.inputText();
        System.out.println("NHẬP SỐ LƯỢNG MUA: ");
        amount = Input.inputNumber();
        for(int i = 0; i < foodList.size(); i++) {
            if(foodList.get(i).getName().equals(name) &&
                    amount >= 1 && amount <= foodList.get(i).getAmount()) {
                money = amount * foodList.get(i).getMoney();
                String confirm = "";
                System.out.println("Xác nhận order " + name + " số lượng " + amount + " (y/n)?");
                confirm = Input.inputText();
                if("y".equals(confirm) || "Y".equals(confirm) &&
                        player.getMoneyTime() - money >= 0) {
                    foodList.get(i).setAmount(foodList.get(i).getAmount() - amount);
                    accountList.get(idAccount).setMoneyTime(player.getMoneyTime() - money);
                    ReadAndWriteAccountFile.writeToFileListFoodNoAppend(foodList);
                    ReadAndWriteAccountFile.writeToFileAccountPlayNoAppend(accountList);
                    System.out.println(ANSI_YELLOW + "Order thành công!" + ANSI_RESET);
                    return;
                }
            }
        }
        System.err.println("Order không thành công!");
    }

    private void changePassword() {
        List<Player> accountList = ReadAndWriteAccountFile.readFromFileAccountPlay();
        String oldPassword = "";
        String newPassword = "";
        String cnewPassword = "";
        myAccount = getMyAccount();
        System.out.println("------ ĐỔI MẬT KHẨU ------");
        System.out.println("NHẬP MẬT KHẨU hiện tại: ");
        do {
            oldPassword = Input.inputText();
            if (myAccount.getPassWord().equals(oldPassword) == false) {
                System.err.println("Mật khẩu không đúng!\nHãy nhập lại!");
            }
        } while (myAccount.getPassWord().equals(oldPassword) == false);

        do {
            System.out.println("NHẬP MẬT KHẨU MỚI: ");
            newPassword = Input.inputText();
            System.out.println("XÁC NHẬN MẬT KHẨU MỚI: ");
            cnewPassword = Input.inputText();
            if(newPassword.equals(cnewPassword) == false || oldPassword.equals(cnewPassword)) {
                System.err.println("Mật khẩu mới không hợp lệ!");
            }
        } while(newPassword.equals(cnewPassword) == false);
        for (int i = 0; i < accountList.size(); i++) {
            if (accountList.get(i).getUserName().equals(myAccount.getUserName())) {
                accountList.get(i).setPassWord(cnewPassword);
            }
        }
        ReadAndWriteAccountFile.writeToFileAccountPlayNoAppend(accountList);
        System.out.println(ANSI_YELLOW + "Đổi mật khẩu thành công!" + ANSI_RESET);
    }

    private void showAccount() {
        myAccount = getMyAccount();
        System.out.println("---THÔNG TIN TÀI KHOẢN CỦA BẠN:---\n" +
                "TÀI KHOẢN: " + myAccount.getUserName() + '\n' +
                "MẬT KHẨU: " + myAccount.getPassWord() + '\n' +
                "SỐ TIỀN CÒN LẠI: " + myAccount.getMoneyTime());
    }

    public void countDownTime(int index) {
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                List<Player> accountList = ReadAndWriteAccountFile.readFromFileAccountPlay();
                long money = 0;
                for(int i = 0; i < accountList.size(); i++) {
                    if(i == index) {
                        money = accountList.get(i).getMoneyTime();
                        break;
                    }
                }
                if(money > 0) {
                    money = money - 5000;
                    for(int i = 0; i < accountList.size(); i++) {
                        if(i == index) {
                            accountList.get(i).setMoneyTime(money);
                            ReadAndWriteAccountFile.writeToFileAccountPlayNoAppend(accountList);
                            break;
                        }
                    }
                } else {
                    System.err.println("Tài khoản đã hết tiền. Vui lòng nạp thêm!");
                    connect();
                }
            }
        };
        long period = 10000;
        timer.schedule(timerTask, 0, period);
    }
    public void showFood() {
        System.out.println("--------DANH SÁCH ĐỒ ĂN--------");
        foodManagement.showListFood();
    }

    public Player getMyAccount() {
        List<Player> players = ReadAndWriteAccountFile.readFromFileAccountPlay();
        for(int i = 0; i < players.size(); i++) {
            if(i == idAccount)  {
                return players.get(i);
            }
        }
        return null;
    }
}
