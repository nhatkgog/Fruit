package models;

import java.util.ArrayList;
import java.util.Scanner;
import views.Validation;

public class Product {

    ArrayList<Fruit> listOfFruit = new ArrayList<>();
    Validation val = new Validation();

    public Product() {
    }

    public void createFruit() {
        while (true) {
            Scanner input = new Scanner(System.in);;
            int fruitID = val.getInteger("Enter fruit ID:");
            val.getValue("");
            String fruitName = val.getAndValidValue("Enter fruit name: ", "[\\pL]+", "Invalid fruit name".toUpperCase());
            System.out.println("Enter price of each fruit:");
            double price = input.nextDouble();
            System.out.println("Enter fruit quantity:");
            int quantity = input.nextInt();
            String origin = val.getValue("Enter fruit origin: ");
            Fruit f = new Fruit(fruitID, fruitName,origin , price, quantity);
            listOfFruit.add(f);
            System.out.println("Create Successfully!\n");
            if (!val.askToCheck()) {
                showFruit();
                return;
            }
        }
    }

    public void showFruit() {
        System.out.print("|    ID    |    Fruit   |    Origin   |    Price    |    Quantity    |\n");
        display();
    }
    
    public void display(){
        for (Fruit f : listOfFruit) {
            System.out.print(f);
        }
        if (listOfFruit.isEmpty()) {
            System.out.println("Not found data");
        }
    }
    
    public ArrayList<Fruit> getList(){
        return listOfFruit;
    }
}
