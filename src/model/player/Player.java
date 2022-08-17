package model.player;

public class Player {
    private String userName;
    private String passWord;
    private long moneyTime;

    public Player() {
    }

    public Player(String userName, String passWord, long moneyTime) {
        this.userName = userName;
        this.passWord = passWord;
        this.moneyTime = moneyTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public long getMoneyTime() {
        return moneyTime;
    }

    public void setMoneyTime(long moneyTime) {
        this.moneyTime = moneyTime;
    }

    @Override
    public String toString() {
        return userName + ',' + passWord + ',' + moneyTime;
    }
}
