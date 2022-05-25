package model;

public class Account {
    String accountName;
    String passWord;
    String phoneNumber;
    String email;
    String customerName;
    int age;
    String userName;

    public Account() {
    }

    public Account(String accountName, String passWord, String phoneNumber,
                   String email, String customerName, int age, String userName) {
        this.accountName = accountName;
        this.passWord = passWord;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.customerName = customerName;
        this.age = age;
        this.userName = userName;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountName='" + accountName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", customerName='" + customerName + '\'' +
                ", age=" + age +
                ", userName='" + userName + '\'' +
                '}';
    }
}
