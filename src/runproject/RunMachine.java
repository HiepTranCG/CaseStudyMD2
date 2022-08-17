package runproject;

import controller.HandleMenuPlayer;

public class RunMachine {
    static HandleMenuPlayer handlePlayerMenu = new HandleMenuPlayer();

    public static void main(String[] args) {
        handlePlayerMenu.run();
    }
}