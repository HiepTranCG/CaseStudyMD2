package controller.manager;

import model.input.Input;
import model.manger.ManagerManagement;
import model.menu.MenuManager;
import model.player.PlayerManagement;

import java.util.List;

public class HandleMenu extends Thread {
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_GREEN = "\u001B[32m";
    private final ManagerManagement accountManagement = new ManagerManagement();
    private final PlayerManagement playAccountManagement = new PlayerManagement();

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

    }

    public void handleLogin() {

    }

    public void handleMainMenu() {

    }

    public void handleForgotPrw() {

    }
}
