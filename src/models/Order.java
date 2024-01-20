package  models;

import java.util.LinkedHashMap;
import java.util.Map;

public class Order{
    
    protected String customer;
    LinkedHashMap<Fruit,Integer> orderTable;
    Fruit fruit = new Fruit();

    public Order(String customer, LinkedHashMap<Fruit,Integer> orderTable) {
        this.customer = customer;
        this.orderTable = orderTable;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public LinkedHashMap<Fruit, Integer> getOrderTable() {
        return orderTable;
    }

    public void setOrderTable(LinkedHashMap<Fruit, Integer> orderTable) {
        this.orderTable = orderTable;
    }
    
    public Double calculateAmount(int quantity, double price){
        return (price*quantity);
    }

    @Override
    public String toString() {
        Fruit f = new Fruit();
        int i = 0;
        double sum = 0;
        for(Map.Entry<Fruit, Integer> entry : orderTable.entrySet()){
            f = entry.getKey();
            i = entry.getValue();
        }
        return "Customer: " + customer + "\n" + 
                "| Product | Quantity | Price | Amount |\n"+ 
                f.getFruitName()+ "\t" + i + "\t" + f.getPrice() +"\t"+ calculateAmount(f.getQuantity(), f.getPrice())+"\t\n"+
                "Total: " + (sum+= calculateAmount(f.getQuantity(), f.getPrice()));
    }
}