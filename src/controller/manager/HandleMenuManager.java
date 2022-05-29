package controller.manager;
import iofile.ReadAndWriteAccountFile;
import model.input.Input;
import model.input.Validate;
import model.manger.Manager;
import model.manger.ManagerManagement;
import model.menu.MenuManager;
import model.player.Player;
import model.player.PlayerManagement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.List;

public class HandleMenuManager extends Thread {
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_GREEN = "\u001B[32m";
    private final ManagerManagement accountManagement = new ManagerManagement();
    private final PlayerManagement PlayerManagement = new PlayerManagement();

    public void handleAccountMenu() {
        int choose = -1;
        MenuManager.showStartMenu();
        do {
            choose = Input.inputNumber(choose);
            switch (choose) {
                case 1:
                    registrationProcessing();
                    MenuManager.showStartMenu();
                    break;
                case 2:
                    handleLogin();
                    handleMainMenu();
                    break;
                case 3:
                    handleForgotPrw();
                    MenuManager.showStartMenu();
                    break;
                default:
                    System.out.println(ANSI_YELLOW + "Vui lòng nhập lại!" + ANSI_RESET);
            }
        } while (choose != 0);
    }

    public void registrationProcessing() {
        String userName = "";
        String passWord = "";
        do {
            System.out.println("---------- ĐĂNG KÍ ----------");
            System.out.println("NHẬP TÊN TÀI KHOẢN (linh1704@gmail.com)");
            userName = Input.inputText(userName);
            if (!Validate.getValiDateUsrSv(userName)) {
                System.err.println("Sai định dạng nhập lại !");
            }
            if (checkAccountExists(userName)) {
                System.err.println("Tài khoản đã tồn tại !");
            }
        } while (!Validate.getValiDateUsrSv(userName) || checkAccountExists(userName));

        do {
            System.out.println("NHẬP MẬT KHẨU (ít nhất 1 chữ 1 số)");
            passWord = Input.inputText(passWord);
            if (!Validate.getValiDatePswSv(passWord)) {
                System.err.println("Sai định dạng nhập lại !");
            }
        } while (!Validate.getValiDatePswSv(passWord));
        System.out.println(ANSI_GREEN + "Đăng kí thành công !" + ANSI_RESET);
        Manager masterAccount = new Manager(userName, passWord);
        accountManagement.registerAnAccount(masterAccount);
    }

    public boolean checkAccountExists(String userName) {
        List<Manager> accountList = ReadAndWriteAccountFile.readFromFileAccount();
        for (Manager account : accountList) {
            if (account.getUserName().equals(userName)) {
                return true;
            }
        }
        return false;
    }

    public void handleLogin() {
        List<Manager> accountList = ReadAndWriteAccountFile.readFromFileAccount();
        String userName = "";
        String passWord = "";
        boolean checkLogin = true;
        do {
            System.out.println("---------- ĐĂNG NHẬP ----------");
            System.out.println("NHẬP TÊN TÀI KHOẢN");
            userName = Input.inputText(passWord);
            System.out.println("NHẬP MẬT KHẨU");
            passWord = Input.inputText(passWord);
            for (Manager account : accountList) {
                if (account.getUserName().equals(userName) && account.getPassWord().equals(passWord)) {
                    System.out.println(ANSI_GREEN + "Đăng nhập thành công !" + ANSI_RESET);
                    checkLogin = false;
                    break;
                }
            }
            if (checkLogin) {
                System.err.println("Sai tài khoản hoặc mật khẩu !");
                handleAccountMenu();
            }
        } while (checkLogin);
    }



    public void handleForgotPrw() {
        int choose = -1;
        do {
            MenuManager.showForgotPwdMenu();
            choose = Input.inputNumber(choose);
            switch (choose) {
                case 1:
                    showAccount();
                    break;
                case 2:
                    handlingPasswordChange();
                    break;
                case 0:
                    break;
                default:
                    System.out.println(ANSI_YELLOW + "Không có lựa chọn trong menu!" + ANSI_RESET);
            }

        } while (choose != 0);
    }

    public void handlingPasswordChange() {
        try {
            Socket socket = new Socket("localhost", 1704);
            PrintStream ps = new PrintStream(socket.getOutputStream());
            String userName = "";
            System.out.println("NHẬP TÊN TÀI KHOẢN CẦN ĐỔI LÊN SEVER : ");
            userName = Input.inputText(userName);
            ps.println(userName);

            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String reply = br.readLine();
            System.out.println(reply);
            changePwd(reply, userName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void changePwd(String reply, String userName) {
        String newPwd = "";
        List<Manager> accountList = ReadAndWriteAccountFile.readFromFileAccount();
        if ("Máy chủ : Hãy nhập mật khẩu mới (ít nhất 1 chữ 1 số)!".equals(reply)) {
            do {
                newPwd = Input.inputText(newPwd);
                if (!Validate.getValiDatePswSv(newPwd)) {
                    System.err.println("Sai định dạng nhập lại !");
                }
            } while (!Validate.getValiDatePswSv(newPwd));
            for (Manager account : accountList) {
                if (account.getUserName().equals(userName)) account.setPassWord(newPwd);
            }
            System.out.println(ANSI_GREEN + "Cập nhật mật khẩu thành công" + ANSI_RESET);
            ReadAndWriteAccountFile.writeToFileAccountNoAppend(accountList);
        }
    }

    public void showAccount() {
        List<Manager> accountList = ReadAndWriteAccountFile.readFromFileAccount();
        String userName = "";
        System.out.println("NHẬP TÊN TÀI KHOẢN : ");
        userName = Input.inputText(userName);
        boolean checkAccount = true;
        for (Manager account : accountList) {
            if (userName.equals(account.getUserName())) {
                System.out.println("[ Tên Tài Khoản : " + account.getUserName()
                        + " ; Mật Khẩu : " + account.getPassWord() + " ]");
                checkAccount = false;
            }
        }
        if (checkAccount) {
            System.out.println(ANSI_YELLOW + "Không tìm thấy tài khoản này!" + ANSI_RESET);
        }
    }

    public void handleMainMenu() {
        
    }

    private void handleFoodManagement() {
    }

    public void registrationAccPlay() {
        String userName = "";
        String passWord = "";
        int moneyAccount = 0;
        do {
            System.out.println("---------- ĐĂNG KÍ TÀI KHOẢN NGƯỜI CHƠI ----------");
            System.out.println("NHẬP TÊN TÀI KHOẢN (linh1704)");
            userName = Input.inputText(userName);
            if (!Validate.getValiDateUsrPlay(userName)) {
                System.err.println("Sai định dạng nhập lại !");
            }
            if (checkAccountPlayExists(userName)) {
                System.err.println("Tài khoản đã tồn tại !");
            }
        } while (!Validate.getValiDateUsrPlay(userName) || checkAccountPlayExists(userName));
        do {
            System.out.println("NHẬP MẬT KHẨU (1)");
            passWord = Input.inputText(passWord);
            if (!Validate.getValiDatePswPlay(passWord)) {
                System.err.println("Sai định dạng nhập lại !");
            }
        } while (!Validate.getValiDatePswPlay(passWord));
        do {
            System.out.println("NẠP TIỀN VÀO TÀI KHOẢN");
            moneyAccount = Input.inputNumber(moneyAccount);
            if (moneyAccount <= 20000) System.err.println("Vui lòng nhập số tiền tối thiểu 20.000");
        } while (moneyAccount <= 20000);
        System.out.println(ANSI_GREEN + "Cấp tài khoản thành công !" + ANSI_RESET);
        Player playAccount = new Player(userName, passWord, moneyAccount);
        PlayerManagement.registerAnAccount(playAccount);
    }

    public boolean checkAccountPlayExists(String userName) {
        List<Player> accountList = ReadAndWriteAccountFile.readFromFileAccountPlay();
        for (Player account : accountList) {
            if (account.getUserName().equals(userName)) {

                return true;
            }
        }
        return false;
    }

    public void topUpAccount() {
        List<Player> playAccounts = ReadAndWriteAccountFile.readFromFileAccountPlay();
        String playAcc = "";
        int money = 0;
        System.out.println("NHẬP TÊN TÀI KHOẢN KHÁCH");
        playAcc = Input.inputText(playAcc);
        int moneyLeftOver = 0;
        boolean checkPlayAcc = true;
        boolean checkUp = false;
        for (int i = 0; i < playAccounts.size(); i++) {
            if (playAcc.equals(playAccounts.get(i).getUserName())) {
                System.out.println("NHẬP SỐ TIỀN CẦN NẠP : ");
                money = Input.inputNumber(money);
                moneyLeftOver = playAccounts.get(i).getMoneyTime();
                playAccounts.get(i).setMoneyTime(money + moneyLeftOver);
                playAccounts.set(i, playAccounts.get(i));
                checkPlayAcc = false;
                checkUp = true;
            }
        }
        if (checkUp) System.out.println(ANSI_GREEN + "Nạp tiền thành công !" + ANSI_RESET);
        if (checkPlayAcc) System.out.println(ANSI_YELLOW + " Không tìm thấy tài khoản này !" + ANSI_RESET);
        ReadAndWriteAccountFile.writeToFileAccountPlayNoAppend(playAccounts);
    }

    public void disPlayAccounts() {
        List<Player> playAccounts = ReadAndWriteAccountFile.readFromFileAccountPlay();
        String playAcc = "";
        System.out.println("NHẬP TÊN TÀI KHOẢN KHÁCH");
        playAcc = Input.inputText(playAcc);
        boolean checkPlayAcc = true;
        for (Player p : playAccounts) {
            if (playAcc.equals(p.getUserName())) {
                System.out.println("TÀI KHOẢN : " + p.getUserName() + " , MẬT KHẨU : " + p.getPassWord() + " , SỐ TIỀN : " + p.getMoneyTime());
                checkPlayAcc = false;
            }
        }
        if (checkPlayAcc) System.out.println(ANSI_YELLOW + " Không tìm thấy tài khoản này" + ANSI_RESET);
    }

    @Override
    public void run() {
        handleAccountMenu();
    }
}
