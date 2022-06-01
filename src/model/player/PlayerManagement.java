package model.player;

import iofile.ReadAndWriteAccountFile;

import java.util.ArrayList;
import java.util.List;

public class PlayerManagement {
    List<Player> accountList = new ArrayList<>();

    public void registerAnAccount(Player account) {
        accountList.add(account);
        ReadAndWriteAccountFile.writeToFileAccountPlay(accountList);
    }

    public int accountSearch(String userName) {
        for (int i = 0; i < accountList.size(); i++) {
            if (accountList.get(i).getUserName().equals(userName)) {
                return i;
            }
        }
        return -1;
    }
    public void showAccounts() {
        for (Player account : accountList) {
            System.out.println(account);
        }
    }
}
