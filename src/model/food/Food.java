package model.food;

public class Food {
    String name;
    int money;
    int amount;

    public Food() {
    }

    public Food(String name, int money, int amount) {
        this.name = name;
        this.money = money;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return name + "," + money + "," + amount;
    }
}
