package FreezerKeeper;

import java.util.Scanner;

public class FreezerMenu {
    Scanner scan = new Scanner(System.in);
    FreezerOperator fo = new FreezerOperator();
    public void displayFreezer() {
        while(true) {
            System.out.println("===< Freezer keeper >===");
            System.out.println("[1]Add food item");
            System.out.println("[2]Delete food item");
            System.out.println("[3]Search your food Id");
            System.out.println("[4]Search your whole food list");
            System.out.println("[5]Exit");
            System.out.println("============================");
            System.out.print("Enter the number: ");
            int num = scan.nextInt();
            scan.nextLine();

            switch(num) {
                case 1 :
                    fo.itemInsert(itemAdd());
                    break;
                case 2 :
                    fo.itemDelete(foodId());
                    break;
                case 3 :
                    fo.itemSearchId(foodId());
                    break;
                case 4 :
                    fo.itemSelectAll();
                case 5 :
                    System.out.println("Program Ended. See you next time.");
                    return;
                default :
                    System.out.println("Type the number again.");
            }
        }
    }
    //Adding the item
    public UserItem itemAdd() {
        System.out.println("===< Add food item >===");
        System.out.println("User Name : ");
        String name = scan.nextLine();
        System.out.println("Food name : ");
        String food = scan.nextLine();
        System.out.println("Food types : [1]Salad, [2]Hot dogs, [3]Luncheon meat, \n " +
                "[4]Bacon and sausage, [5]Hamburger, ham, ground meats\n"+
                "[6]Fresh beef, veal, lamb, and pork, [7]Fin fish, [8]Shell fish,\n" +
                        " [9]Eggs, [10]Soups and stews, [11]Left overs\n");
        System.out.println("Enter food type (Enter 1-11) : ");
        int foodType = scan.nextInt();
        System.out.println("[1]Refrigerator, [2]Freezer");
        System.out.println("Enter storage type (Enter 1-2) : ");
        int storageType = scan.nextInt();
        System.out.println("Date of your purchase(YYYY/MM/DD) : ");
        String purchaseDate = scan.nextLine();
        scan.nextLine();
        UserItem userItem = new UserItem(name, food, storageType, foodType, purchaseDate);
        return userItem;
    }
    public int foodId(){
        System.out.println("Enter the Food Id number");
        return Integer.parseInt(scan.nextLine());
    }
}
