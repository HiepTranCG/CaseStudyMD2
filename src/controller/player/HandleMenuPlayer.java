package controller.player;

import iofile.ReadAndWriteAccountFile;
import model.input.Input;
import model.menu.MenuPlayer;
import model.player.Player;

import java.awt.*;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Calendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class HandleMenuPlayer extends Thread {
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_RESET = "\u001B[0m";
    Socket socket;

    {
        try {
            socket = new Socket("localhost", 2006);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Socket getSocket() {
        return socket;
    }

    public void connect() {
        deductFromAccount(handlePlayerLogin());
        handlePlayerMenu();
    }

    public int handlePlayerLogin() {
        List<Player> accountList = ReadAndWriteAccountFile.readFromFileAccountPlay();
        String userName = "";
        String passWord = "";
        int index = -1;
        boolean checkLogin = true;
        do {
            System.out.println("---------- ĐĂNG NHẬP MÁY ----------");
            System.out.println("NHẬP TÊN TÀI KHOẢN");
            userName = Input.inputText(passWord);
            System.out.println("NHẬP TÊN MẬT KHẨU");
            passWord = Input.inputText(passWord);
            for (Player account : accountList) {
                if (account.getUserName().equals(userName) && account.getPassWord().equals(passWord)) {
                    System.out.println(ANSI_GREEN + "Đăng nhập thành công !" + ANSI_RESET);
                    checkLogin = false;
                    break;
                }
            }
            for (int i = 0; i < accountList.size(); i++) {
                if (accountList.get(i).getUserName().equals(userName) && accountList.get(i).getPassWord().equals(passWord)){
                    index = i;
                    break;
                }
            }
            if (checkLogin) {
                System.err.println("Sai tài khoản hoặc mật khẩu !");
            }

        } while (checkLogin);
        return index;
    }

    public void handlePlayerMenu() {
        int choose = -1;
        do {
            MenuPlayer.showMenuPlayer();
            choose = Input.inputNumber(choose);
            switch (choose) {
                case 1:
                    disPlayers();
                    break;
                case 2:
                    try {
                        handleMenuOder();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case 3:
                    handleChat();
                    break;
                case 4 :
                    showFood();
                    break;
                case 0:
                    handlePlayerLogin();
                    break;
                default:
                    System.out.println(ANSI_YELLOW + "Không có lựa chọn này vui lòng nhập lại!" + ANSI_RESET);
            }
        } while (choose != 0);
    }

    public void handleMenuOder() throws IOException {

    }

    public void handleChat() {
        PrintStream ps = null;
        try {
            ps = new PrintStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        String inbox = "";
        System.out.println("NHẬP TIN NHẮN : ");
        inbox = Input.inputText(inbox);
        ps.println(ANSI_PURPLE + "Khách : " + inbox + ANSI_RESET);
    }

    @Override
    public void run() {
        connect();
    }

    public void deductFromAccount(int index) {
        Timer timer = new Timer();
        TimerTask task = new TimerTask()  {
            @Override
            public void run() {
                List<Player> Players = ReadAndWriteAccountFile.readFromFileAccountPlay();
                int money = 0;
                for (int i = 0; i < Players.size(); i++) {
                    if (i == index) {
                        money = Players.get(index).getMoneyTime();
                        break;
                    }
                }
                if (money > 0) {
                    money = money - 5000;
                    for (int i = 0; i < Players.size(); i++) {
                        if (i == index) {
                            Players.get(i).setMoneyTime(money);
                            Players.set(i , Players.get(i));
                            ReadAndWriteAccountFile.writeToFileAccountPlayNoAppend(Players);
                        }
                    }
                } else {
                    System.out.println(ANSI_YELLOW + "Đã hết tiền vui lòng nạp thêm !" + ANSI_RESET);
                    connect();
                }
            }
        };
        Calendar data = Calendar.getInstance();
        timer.schedule(task, data.getTime(), 20000);
    }

    public void showFood() {

    }

    public void disPlayers() {
        List<Player> Players = ReadAndWriteAccountFile.readFromFileAccountPlay();
        String playAcc = "";
        System.out.println("NHẬP TÊN TÀI KHOẢN CỦA BẠN");
        playAcc = Input.inputText(playAcc);
        boolean checkPlayAcc = true;
        for (Player p : Players) {
            if (playAcc.equals(p.getUserName())) {
                System.out.println("TÀI KHOẢN : " + p.getUserName() + " , MẬT KHẨU : " + p.getPassWord() + " , SỐ TIỀN : " + p.getMoneyTime());
                checkPlayAcc = false;
            }
        }
        if (checkPlayAcc) System.out.println(ANSI_YELLOW + " Không tìm thấy tài khoản này" + ANSI_RESET);
    }
}
