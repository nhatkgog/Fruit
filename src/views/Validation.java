package views;

import java.util.ArrayList;
import java.util.Scanner;
import models.Fruit;
import models.Order;

public class Validation {

    Scanner sc = new Scanner(System.in);

    public boolean askToCheck() {
        while (true) {
            System.out.println("Do you want to continue (Y/N): ");
            String c = sc.nextLine().toUpperCase();
            switch (c) {
                case "Y":
                    return true;
                case "N":
                    return false;
                default:
                    System.out.println("Retry! Please enter only Y or N..");
            }
        }
    }

    public String getValue(String msg) {
        System.out.print(msg);
        return uppercaseFirst(sc.nextLine().toLowerCase());
    }

    public String getAndValidValue(String msg, String regex, String cause) {
        String value;
        while (true) {
            try {
                value = getValue(msg);
                if (value.isEmpty()) {
                    throw new Exception("Input can not empty");
                }
                if (!value.matches(regex)) {
                    throw new Exception(cause);
                }
                break;
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        return value;
    }

    public double getAndValidMoney(String msg) {
        String money;
        while (true) {
            try {
                money = getAndValidValue(msg, "[\\d.]+", "Invalid amount of money");
                if (Double.parseDouble(money) <= 0) {
                    throw new Exception("Amount of money must be a positive number");
                }
                return Double.parseDouble(money);
            } catch (NumberFormatException e) {
                System.out.println("Invalid amount of money");
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public int getAndValidQuantity(String msg) {
        String quantity;
        while (true) {
            try {
                quantity = getAndValidValue(msg, "[\\d]+", "Invalid amount of quantity");
                if (Integer.parseInt(quantity) <= 0) {
                    throw new Exception("Amount of quantity must be a positive number");
                }
                return Integer.parseInt(quantity);
            } catch (NumberFormatException e) {
                System.out.println("Invalid amount of quantity");
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public String showMoney(double money) {
        return String.format("$%.2f", money);
    }

    public String trimName(String name) {
        return name.trim();
    }

    public String uppercaseFirst(String string) {
        if (string.isEmpty()) {
            return string;
        } else {
            return Character.toUpperCase(string.charAt(0)) + string.substring(1);
        }
    }

    public String getName(String msg, String regex) {
        while (true) {
            String name = getAndValidValue(msg, regex, "Invalid name");
            return uppercaseFirst(trimName(name));
        }
    }

    public int getInteger(String msg) {
        int value;

        while (true) {
            try {
                System.out.print(msg);
                value = Integer.parseInt(sc.next());

                if (value > 0) {
                    break;
                } else {
                    System.out.println("Please enter a value greater than or equal to 0.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        }

        return value;
    }

    public boolean isEnoughQuantity(Fruit fruit, int orderQuantity) {
        return fruit.getQuantity() >= orderQuantity;
    }
    
    public <T> boolean validateEquality(T obj1, T obj2, int numberOfVariables) {
        if (obj1 == null || obj2 == null) {
            return false;
        }

        int matchedVariables = 0;

        for (java.lang.reflect.Field field : obj1.getClass().getDeclaredFields()) {
            field.setAccessible(true);

            if (field.getName().equals("quantity")) {
                continue;
            }

            try {
                Object value1 = field.get(obj1);
                Object value2 = field.get(obj2);

                if (value1 != null && value1.equals(value2)) {
                    matchedVariables++;
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

            if (matchedVariables == numberOfVariables) {
                return true;
            }
        }

        return false;
    }
}
