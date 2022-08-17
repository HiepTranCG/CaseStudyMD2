package model.food;
import iofile.ReadAndWriteAccountFile;

import java.util.ArrayList;
import java.util.List;

public class FoodManagement {
    private List<Food> foodList = new ArrayList<>();

    public List<Food> getFoodList() {
        return foodList;
    }

    public FoodManagement() {
        this.foodList = ReadAndWriteAccountFile.readFromFileListFood();
    }

    public void showListFood() {
        this.foodList = ReadAndWriteAccountFile.readFromFileListFood();
        System.out.println("\tTÊN ĐỒ ĂN\t\t\tGIÁ\t\t\tSỐ LƯỢNG");
        for(int i = 0; i < foodList.size(); i++)
            System.out.printf("%d. %-20s %-15d %-10d\n", i + 1, foodList.get(i).getName(), foodList.get(i).getMoney(), foodList.get(i).getAmount());
    }

    public void addFood(Food food) {
        foodList.add(food);
        ReadAndWriteAccountFile.writeToFileListFoodNoAppend(foodList);
    }

    public int searchFood(String name) {
        for(int i = 0; i < foodList.size(); i++) {
            if(foodList.get(i).getName().equals(name)) {
                return i;
            }
        }
        return -1;
    }

    public void edit(String name, int money, int amount) {
        int temp = searchFood(name);
        foodList.get(temp).setMoney(money);
        foodList.get(temp).setAmount(amount);
        ReadAndWriteAccountFile.writeToFileListFoodNoAppend(foodList);
    }

    public void delete(String name) {
        int temp = searchFood(name);
        foodList.remove(temp);
        ReadAndWriteAccountFile.writeToFileListFoodNoAppend(foodList);
    }
}
