package model.manger;

import iofile.ReadAndWriteAccountFile;

import java.util.ArrayList;
import java.util.List;

public class ManagerManagement {
    private final List<Manager> accountList = new ArrayList<>();

    public List<Manager> getAccountList() {
        return accountList;
    }

    public void registerAnAccount(Manager account) {
        accountList.add(account);
        ReadAndWriteAccountFile.writeToFileAccount(accountList);
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
        for (Manager account : accountList) {
            System.out.println(account);
        }
    }
}
