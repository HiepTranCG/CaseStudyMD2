package model.manger;

import iofile.ReadAndWriteAccountFile;

import java.util.ArrayList;
import java.util.List;

public class ManagerManagement {
    private List<Manager> accountList = new ArrayList<>();

    public List<Manager> getAccountList() {
        return accountList;
    }

    public ManagerManagement() {
        this.accountList = ReadAndWriteAccountFile.readFromFileAccount();
    }

    public void registerAnAccount(Manager account) {
        List<Manager> managerList = ReadAndWriteAccountFile.readFromFileAccount();
        managerList.add(account);
        ReadAndWriteAccountFile.writeToFileAccountNoAppend(managerList);
    }

    public int accountSearch(String userName) {
        List<Manager> managerList = ReadAndWriteAccountFile.readFromFileAccount();
        for (int i = 0; i < managerList.size(); i++) {
            if (accountList.get(i).getUserName().equals(userName)) {
                return i;
            }
        }
        return -1;
    }
    public void showAccounts() {
        List<Manager> managerList = ReadAndWriteAccountFile.readFromFileAccount();
        for (Manager account : managerList) {
            System.out.println(account);
        }
    }
}
