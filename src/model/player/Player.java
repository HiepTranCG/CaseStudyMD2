package model.player;

public class Player {
    private String userName;
    private String passWord;
    private int moneyTime;

    public Player() {
    }

    public Player(String userName, String passWord, int moneyTime) {
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

    public int getMoneyTime() {
        return moneyTime;
    }

    public void setMoneyTime(int moneyTime) {
        this.moneyTime = moneyTime;
    }

    @Override
    public String toString() {
        return "PlayerAccount{" +
                "userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", moneyTime=" + moneyTime +
                '}';
    }
}
