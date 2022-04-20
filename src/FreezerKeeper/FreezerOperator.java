package FreezerKeeper;
import java.sql.SQLException;
import java.util.List;


public class FreezerOperator {
    FreezerConnector fc = new FreezerConnector();
    FreezerDisplay fd = new FreezerDisplay();
    ItemDAO id = new ItemDAO();
    //Freezer menu #1 Add food item.
    public void insertItem(UserItem itemAdd) {
//        try {
            int result = id.insertItem(itemAdd);
            if (result > 0) {
                System.out.println("Food is updated in your list");
            } else {
                System.err.println("Somethings wrong.");
            }
//        }
//        catch (SQLException e) {
//            e.printStackTrace();
//        }
    }

//    //Freezer menu #2 Delete food item.
//    public void deleteItem(int foodId) {
//        try {
//            int result = fc.deleteItem(con, foodId);
//            if (result > 0) {
//                System.out.println("The food item is deleted.");
//            } else {
//                System.err.println("Somethings wrong.");
//            }
//        }
//        catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//    //Freezer menu #3 Search your food Id.
//    public void searchFoodId(Connection con, int foodId) {
//        try {
//            UserItem userItem = fc.searchFoodId(con, foodId);
//            if (userItem == null) {
//                System.err.println("Somethings wrong.");
//            } else {
//                fd.searchFoodId(userItem);
//            }
//        }
//        catch (SQLException e){
//            e.printStackTrace();
//        }
//    }
    //Freezer menu #4 show your whole food list.
//    public void itemSelectAll() {
//        try{
//            List<UserItem> itemList = fc.itemSelectAll();
//            if (itemList.isEmpty()) {
//                System.out.println("There is nothing in your list.");
//            } else {
//                fd.itemSelectList(itemList);
//            }
//        }
//        catch (SQLException e){
//            e.printStackTrace();
//        }
//    }
//
}
