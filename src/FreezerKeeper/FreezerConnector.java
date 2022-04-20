package FreezerKeeper;

import java.util.List;
import java.sql.*;

public class FreezerConnector {
    ItemDAO id = new ItemDAO();

//    public List<UserItem> itemSelectAll() throws SQLException {
//        Connection con = id.getConnection();
//        List<UserItem> itemSelectAll = id.itemSelectList();
//        con.close();
//        return itemSelectAll;
//    }
//    //Freezer menu #1 Add food item.
//    public int insertItem(UserItem itemAdd) throws SQLException {
//        Connection con = id.getConnection();
//        int result = id.insertItem(itemAdd);
//        if(result > 0) {
//            con.commit();
//        }else {
//            con.rollback();
//        }
//        con.close();
//
//        return result;
//    }
//    //Freezer menu #2 Delete food item.
//    public int deleteItem(int foodId) throws SQLException {
//        Connection con = id.getConnection();
//        int result = id.deleteItem(con,foodId);
//        if(result > 0) {
//            con.commit();
//        }else {
//            con.rollback();
//        }
//        con.close();
//        return result;
//    }
//    //Freezer menu #3 Search your food Id.
//    public UserItem searchFoodId(int foodId) throws SQLException {
//        Connection con = id.getConnection();
//        UserItem userItem = id.searchFoodId(con,foodId);
//        con.close();
//        return userItem;
//    }
   }
