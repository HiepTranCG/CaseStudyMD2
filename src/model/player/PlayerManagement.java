package model.player;

import iofile.ReadAndWriteAccountFile;

import java.util.ArrayList;
import java.util.List;

public class PlayerManagement {
    private List<Player> accountList = new ArrayList<>();
    public List<Player> getAccountList() {
        return accountList;
    }

    public PlayerManagement() {
        this.accountList = ReadAndWriteAccountFile.readFromFileAccountPlay();
    }

    public void registerAnAccount(Player account) {
        accountList.add(account);
        ReadAndWriteAccountFile.writeToFileAccountPlayNoAppend(accountList);
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
        System.out.println("TÊN TÀI KHOẢN \t\t MẬT KHẨU \t\t SỐ TIỀN");
        for (Player account : accountList) {
            System.out.printf("%10s%15s%20d\n", account.getUserName(), account.getPassWord(), account.getMoneyTime());
        }
    }
    public void addMoney(String username, long money) {
        accountList.get(accountSearch(username)).setMoneyTime(accountList.get(accountSearch(username)).getMoneyTime() + money);
        ReadAndWriteAccountFile.writeToFileAccountPlayNoAppend(accountList);
    }

    public void changePassword(String username, String password) {
        for (int i = 0; i < accountList.size(); i++) {
            if (accountList.get(i).getUserName().equals(username)) {
                accountList.get(i).setPassWord(password);
            }
        }
        ReadAndWriteAccountFile.writeToFileAccountPlayNoAppend(accountList);
    }

    public void delete(String username) {
        accountList.remove(accountSearch(username));
        ReadAndWriteAccountFile.writeToFileAccountPlayNoAppend(accountList);
    }
}
