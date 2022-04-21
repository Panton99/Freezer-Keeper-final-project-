//Jisoo Kim 2022/04/21
package FreezerKeeper;

import java.sql.SQLException;
import java.util.Scanner;

public class FreezerMenu {
    Scanner scan = new Scanner(System.in);
    ItemDAO id = new ItemDAO();
    UserItem ui = new UserItem();
    public void displayFreezer() {
        try {
            while (true) {
                System.out.println("\n====< Freezer keeper >====");
                System.out.println("[1]Create the table");
                System.out.println("[2]Add food item");
                System.out.println("[3]Delete your food item");
                System.out.println("[4]Search food");
                System.out.println("[5]Show whole food list");
                System.out.println("[6]Exit");
                System.out.println("==========================");
                System.out.print("Enter the number: ");
                int num = scan.nextInt();
                scan.nextLine();

                switch (num) {
                    case 1:
                        id.createTable();
                        break;
                    case 2:
                        id.insertItem(itemAdd());
                        break;
                    case 3:
                        id.deleteItem(foodName());
                        break;
//                case 4 :
//                    id.searchFood();
//                    break;
                    case 5:
                        id.itemShowList();
                        break;
                    case 6:
                        System.out.println("Program Ended. See you next time.");
                        return;
                    default:
                        System.out.println("Type the number again.");
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                id.state.close();
                id.rs.close();
                id.con.close();
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }

    }
    //Adding the item
    public UserItem itemAdd() {
        System.out.println("====< Add food item >====");
        System.out.println("User Name : ");
        ui.setName(scan.nextLine());
        System.out.println("Food name : ");
        ui.setFood(scan.nextLine());
        System.out.println("Food types : [1]Salad, [2]Hot dogs, [3]Luncheon meat, \n " +
                "[4]Bacon and sausage, [5]Hamburger, ham, ground meats\n"+
                "[6]Fresh beef, veal, lamb, pork, [7]Fin fish, [8]Shell fish,\n" +
                        " [9]Eggs, [10]Soups and stews, [11]Left overs, [12]etc\n");
        System.out.println("Enter food type (Enter 1-11) : ");
        ui.setFoodType(scan.nextInt());
        System.out.println("[1]Refrigerator, [2]Freezer");
        System.out.println("Enter storage type (Enter 1-2) : ");
        ui.setStorageType(scan.nextInt());
//        System.out.println("Date of your purchase(YYYY/MM/DD) : ");
//        String purchaseDate = scan.nextLine();
        scan.nextLine();
        UserItem userItem = new UserItem(ui.getName(), ui.getFood(), ui.getStorageType(), ui.getFoodType());
        return userItem;
    }
    //Ask for the food that you want to delete.
    public String foodName() {
        System.out.println("Enter the food name you want to delete: ");
        return scan.nextLine();
    }

}
