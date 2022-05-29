package main;

import controller.player.HandleMenuPlayer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class RunMachine extends Thread {
    static HandleMenuPlayer handlePlayerMenu = new HandleMenuPlayer();
    @Override
    public void run() {
        Socket socket = handlePlayerMenu.getSocket();
        BufferedReader br = null;
        while (true) {
            try {
                br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            String reply = null;
            try {
                assert br != null;
                reply = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(reply);
        }
    }

    public static void main(String[] args) {
        handlePlayerMenu.start();
        RunMachine mainPlayer =  new RunMachine();
        mainPlayer.start();
    }
}