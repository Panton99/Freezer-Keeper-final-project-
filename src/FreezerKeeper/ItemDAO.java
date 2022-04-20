package FreezerKeeper;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemDAO {
    Connection con;
    Statement state;
    ResultSet rs;
    String sqlDB_DRIVER_CLASS = "com.mysql.jdbc.Driver";
    String sqlDB_URL = "jdbc:mysql://localhost:3306?useSSL=false";
    String sqlDB_USERNAME = "root";
    String sqlDB_PW = "root1234";

    public ItemDAO(){
        try{
            Class.forName(sqlDB_DRIVER_CLASS);
            con = DriverManager.getConnection(sqlDB_URL, sqlDB_USERNAME, sqlDB_PW);
            System.out.println("Successfully connected!");
            state = con.createStatement(rs.TYPE_SCROLL_INSENSITIVE, rs.CONCUR_UPDATABLE);
        }
        catch (ClassNotFoundException e){
            System.err.println("Driver loading failed..");
        }
        catch (SQLException e){
            System.err.println("Database connection failed..");
        }
    }
    public java.sql.Connection getConnection()
    {
        return con;
    }
    public Statement getStatement()
    {
        return state;
    }

    public int insertItem(UserItem userItem) {

        String query = "INSERT INTO user_table"
                + "VALUES(?, ?, ?, ?)";
        PreparedStatement pstate = null;
        int result = 0;

        try {
            pstate = con.prepareStatement(query);
            pstate.setString(1, userItem.getName());
            pstate.setString(2, userItem.getFood());
            pstate.setInt(3, userItem.getFoodType());
            pstate.setInt(4, userItem.getStorageType());
//            pstate.setString(5, sqlDate);

            result = pstate.executeUpdate();

            if (result > 0) {
                System.out.println("Item is successfully updated.");
            } else {
                System.out.println("Item update failed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                pstate.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return result;
        }
    }
//        public int deleteItem(Connection con, int foodId) {
//            String query2 = "DELETE FROM item "
//                    + "WHERE FOOD_ID = ?";
//            PreparedStatement pstate = null;
//            int result2 = 0;
//
//            try {
//                pstate = con.prepareStatement(query2);
//                pstate.setInt(1, foodId);
//                result2 = pstate.executeUpdate();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            } finally {
//                try {
//                    pstate.close();
//                }
//                catch (SQLException e) {
//                e.printStackTrace();
//                }
//            }
//            return result2;
//        }
//    public UserItem searchFoodId(Connection con, int foodId) {
//        String query4 = "SELECT * FROM foodId " + "WHERE FOOD_ID= ?";
//        PreparedStatement pstate = null;
//        ResultSet rs = null;
//        UserItem userItem = null;
//
//        try {
//            pstate = con.prepareStatement(query4);
//            pstate.setInt(1, foodId);
//
//            rs = pstate.executeQuery();
//
//            while(rs.next()) {
//                userItem = new UserItem(rs.getInt("FOOD_ID"),
//                        rs.getString(userItem.getName()),
//                        rs.getString(userItem.getFood()),
//                        rs.getInt(userItem.getStorageType()),
//                        rs.getInt(userItem.getFoodType()),
//                        rs.getString(userItem.getPurchaseDate());
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//            rs.close();
//            pstate.close();
//            }
//            catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        return userItem;
//    }

    public List<UserItem> itemSelectList() {
            String query3 = "SELECT * FROM USER_TABLE";
            Statement state = null;
            ResultSet rs = null;
            List<UserItem> itemList = new ArrayList<>();
            try {
                state = con.createStatement();
                rs = state.executeQuery(query3);
                while (rs.next()) {
                    int foodId = rs.getInt("foodID");
                    String food = rs.getString("food");
                    String name = rs.getString("name");
                    int storageType = rs.getInt("storageType");
                    int foodType = rs.getInt("foodType");
                    String purchaseDate = rs.getString("purchaseDate");

                    UserItem userItem = new UserItem(name, food, storageType, foodType);
                    itemList.add(userItem);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            return itemList;
    }
}
