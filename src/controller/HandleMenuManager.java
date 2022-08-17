package controller;

import input.Input;
import input.Validate;
import iofile.ReadAndWriteAccountFile;
import model.food.Food;
import model.food.FoodManagement;
import model.manger.Manager;
import model.manger.ManagerManagement;
import model.menu.MenuManager;
import model.player.Player;
import model.player.PlayerManagement;

import java.util.List;

public class HandleMenuManager {
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_RESET = "\u001B[0m";
    public ManagerManagement accountManagement = new ManagerManagement();
    public PlayerManagement playerManagement = new PlayerManagement();
    public FoodManagement foodManagement = new FoodManagement();
    public final int OUTSIDE_REGISTER = 1;
    public final int OUTSIDE_LOGIN = 2;
    public final int INSIDE_MANAGE_PLAYERACCOUNT = 1;
    public final int INSIDE_MANAGE_FOOD = 2;
    public final int VIEW_FOOD = 1;
    public final int FOOD_ADD = 2;
    public final int FOOD_EDIT = 3;
    public final int FOOD_DELETE = 4;
    public final int PLAYER_SHOWLIST = 1;
    public final int PLAYER_REGISTER = 2;
    public final int PLAYER_ADDMONEY = 3;
    public final int PLAYER_DELETE = 4;
    public final int LOGOUT = 0;
    public final int DEFAULT = -1;

    public void menuOutSide() {
        int choose = DEFAULT;
        MenuManager.showStartMenu();
        do {
            choose = Input.inputNumber();
            switch (choose) {
                case OUTSIDE_REGISTER:
                    register();
                    MenuManager.showStartMenu();
                    break;
                case OUTSIDE_LOGIN:
                    if (login() == true) {
                        menuInSide();
                    }
                    break;
                default:
                    System.err.println("Vui lòng nhập lại!");
            }
        } while (choose != LOGOUT);
    }

    private boolean login() {
        List<Manager> managerList = ReadAndWriteAccountFile.readFromFileAccount();
        String username = "";
        String password = "";
        System.out.println("---------- ĐĂNG NHẬP MÁY ----------");
        System.out.println("NHẬP TÀI KHOẢN:");
        username = Input.inputText();
        System.out.println("NHẬP MẬT KHẨU:");
        password = Input.inputText();
        for (int i = 0; i < managerList.size(); i++) {
            if (username.equals(managerList.get(i).getUserName()) && password.equals(managerList.get(i).getPassWord())) {
                System.out.println(ANSI_YELLOW + "Đăng nhập thành công!" + ANSI_RESET);
                return true;
            }
        }
        System.err.println("Sai tài khoản hoặc mật khẩu!!");
        MenuManager.showStartMenu();
        return false;
    }

    private void register() {
        List<Manager> managerList = ReadAndWriteAccountFile.readFromFileAccount();
        String username = "";
        String password = "";
        System.out.println("-----ĐĂNG KÍ TÀI KHOẢN NGƯỜI CHƠI-----");
        do {
            System.out.println("NHẬP TÀI KHOẢN (hiep123@gmail.com):");
            username = Input.inputText();
            if (Validate.getValiDateUsrSv(username) == false ) {
                System.err.println("Tài khoản không hợp lệ!");
                continue;
            }
            if (accountManagement.accountSearch(username) != -1) {
                System.err.println("Tài đã tồn tại!");
                continue;
            }
        } while (Validate.getValiDateUsrSv(username) == false || accountManagement.accountSearch(username) != -1);
        do {
            System.out.println("NHẬP MẬT KHẨU:");
            password = Input.inputText();
            if (Validate.getValiDatePswSv(password) == false) {
                System.err.println("Mật khẩu không hợp lệ!");
            }
        } while (Validate.getValiDatePswSv(password) == false);
        accountManagement.registerAnAccount(new Manager(username, password));
    }

    //---------------------------Menu inside-------------------------------
    public void menuInSide() {
        int choose = DEFAULT;
        do {
            MenuManager.showMainMenu();
            choose = Input.inputNumber();
            switch (choose) {
                case INSIDE_MANAGE_PLAYERACCOUNT:
                    menuPlayer();
                    break;
                case INSIDE_MANAGE_FOOD:
                    menuFood();
                    break;
                default:
                    System.err.println("Vui lòng nhập lại!");
            }
        } while (choose != LOGOUT);
    }

    //------------------- MENU PLAYER ACCOUNT -------------
    public void menuPlayer() {
        int choose = DEFAULT;
        do {
            MenuManager.showMenuPlayerAccount();
            choose = Input.inputNumber();
            switch (choose) {
                case PLAYER_SHOWLIST:
                    showListPlayerAccount();
                    break;
                case PLAYER_REGISTER:
                    registerPlayerAccount();
                    break;
                case PLAYER_ADDMONEY:
                    addMoneyPlayerAccount();
                    break;
                case PLAYER_DELETE:
                    deletePlayerAccount();
                    break;
            }
        } while(choose != LOGOUT);
    }

    private void showListPlayerAccount() {
        playerManagement = new PlayerManagement();
        System.out.println("\n-----DANH SÁCH TÀI KHOẢN NGƯỜI CHƠI-----");
        playerManagement.showAccounts();
    }
    private void registerPlayerAccount() {
        String username = "";
        String password = "";
        int money = 0;
        System.out.println("------ĐĂNG KÝ TÀI KHOẢN NGƯỜI CHƠI------");
        do {
            System.out.println("NHẬP TÀI KHOẢN ĐĂNG KÝ:");
            username = Input.inputText();
            if (playerManagement.accountSearch(username) != -1) {
                System.err.println("Tên đăng nhập đã tồn tại!");
                continue;
            }
            if (Validate.getValiDateUsrPlay(username) == false) {
                System.err.println("Tên tài đăng kí không hợp lệ");
                continue;
            }
        } while (playerManagement.accountSearch(username) != -1 || Validate.getValiDateUsrPlay(username) == false);
        do {
            System.out.println("NHẬP MẬT KHẨU ĐĂNG KÝ:");
            password = Input.inputText();
            if (Validate.getValiDatePswPlay(password) == false) {
                System.err.println("Mật khẩu không hợp lệ!");
            }
        } while (Validate.getValiDatePswPlay(password) == false);
        do {
            System.out.println("NHẬP SỐ TIỀN NẠP:");
            money = Input.inputNumber();
            if (money < 0) {
                System.err.println("Số tiền nhập không hợp lệ!");
            }
        } while (money < 0);
        playerManagement.registerAnAccount(new Player(username, password, money));
        System.out.println(ANSI_YELLOW + "Đăng kí tài khoản thành công!" + ANSI_RESET);
    }

    private void addMoneyPlayerAccount() {
        System.out.println("------------NẠP TIỀN------------");
        String username = "";
        int money = 0;
        System.out.println("NHẬP TÊN TÀI KHOẢN MUỐN NẠP:");
        do {
            username = Input.inputText();
            if (playerManagement.accountSearch(username) == -1) {
                System.err.println("Tài khoản không tồn tại!");
            }
        } while (playerManagement.accountSearch(username) == -1);

        System.out.println("NHẬP SỐ TIỀN MUỐN NẠP:");
        do {
            money = Input.inputNumber();
            if (money < 0) {
                System.err.println("Số tiền muốn nạp không hợp lệ!");
            }
        } while (money < 0);
        playerManagement.addMoney(username, money);
        System.out.println(ANSI_YELLOW + "Nạp tiền thành công!" + ANSI_RESET);
    }

    private void deletePlayerAccount() {
        String username = "";
        System.out.println("------------XÓA TÀI KHOẢN------------");
        System.out.println("TÊN TÀI KHOẢN:");
        username = Input.inputText();
        if(playerManagement.accountSearch(username) != -1) {
            String confirm = "";
            System.out.println("XÁC NHẬN XÓA TÀI KHOẢN " + username + " (y/n)?");
            confirm = Input.inputText();
            if("y".equals(confirm) || "Y".equals(confirm)) {
                playerManagement.delete(username);
                System.out.println(ANSI_YELLOW + "Xóa tài khoản thành công!" + ANSI_RESET);
            } else {
                System.err.println(ANSI_YELLOW + "Đã hủy!" + ANSI_RESET);
            }
        } else {
            System.err.println("Tài khoản " + username + " không tồn tại");
        }

    }

    // ------------------ MENU FOOD ------------------
    private void menuFood() {
        int choose = DEFAULT;
        do {
            MenuManager.showMenuFood();
            choose = Input.inputNumber();
            switch (choose){
                case VIEW_FOOD:
                    viewFood();
                    break;
                case FOOD_ADD:
                    addFood();
                    break;
                case FOOD_EDIT:
                    editFood();
                    break;
                case FOOD_DELETE:
                    deleteFood();
                    break;
            }
        } while (choose != LOGOUT);
    }

    private void viewFood() {
        System.out.println("-----------DANH SÁCH ĐỒ ĂN-----------");
        foodManagement.showListFood();
    }

    private void addFood() {
        String name = "";
        int price = 0;
        int amount = 0;
        System.out.println("-----------THÊM ĐỒ ĂN-----------");
        do {
            System.out.println("NHẬP TÊN ĐỒ ĂN:");
            name = Input.inputText();
            System.out.println("NHẬP GIÁ:");
            price = Input.inputNumber();
            System.out.println("NHẬP SỐ LƯỢNG:");
            amount = Input.inputNumber();
            if(price < 0 || amount < 0) {
                System.err.println("Không hợp lệ!");
            }
        } while(price < 0 || amount < 0);
        foodManagement.addFood(new Food(name, price, amount));

    }

    private void editFood() {
        String name = "";
        System.out.println("---------SỬA THÔNG TIN ĐỒ ĂN---------");
        System.out.println("NHẬP TÊN ĐỒ ĂN:");
        name = Input.inputText();
        if(foodManagement.searchFood(name) != -1) {
            int money = 0;
            int amount = 0;
            System.out.println("NHẬP GIÁ TIỀN:");
            money = Input.inputNumber();
            System.out.println("NHẬP SỐ LƯỢNG:");
            amount = Input.inputNumber();
            if(money < 0 || amount < 0) {
                System.err.println("Không hợp lệ!");
            } else {
                foodManagement.edit(name, money, amount);
                System.out.println(ANSI_YELLOW + "Cập nhật thành công!" + ANSI_RESET);
            }
        } else {
            System.err.println("Đồ ăn " + name + " không tồn tại!");
        }
    }

    private void deleteFood() {
        String name = "";
        System.out.println("\n-----------XÓA ĐỒ ĂN-----------");
        System.out.println("NHẬP TÊN ĐỒ ĂN:");
        name = Input.inputText();
        if(foodManagement.searchFood(name) != -1) {
            String confirm = "";
            System.out.println("Xác nhận xóa đồ ăn " + name + " (y/n)?");
            confirm = Input.inputText();
            if("y".equals(confirm) || "Y".equals(confirm)) {
                foodManagement.delete(name);
                System.out.println(ANSI_YELLOW + "Đã xóa thành công!" + ANSI_RESET);
            } else {
                System.err.println("Đã hủy!");
            }
        } else {
            System.err.println("Đồ ăn " + name + " không tồn tại!");
        }
    }
}
