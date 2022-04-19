package FreezerKeeper;

import com.mysql.jdbc.Connection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemDAO {
    java.sql.Connection con = null;
    Statement state = null;
    private static final String sqlDB_DRIVER_CLASS = "com.mysql.jdbc.Driver";
    private static final String sqlDB_URL = "jdbc:mysql://localhost:3306?useSSL=false";
    private static final String sqlDB_USERNAME = "root";
    private static final String sqlDB_PW = "root1234";
    public ItemDAO(){
        try{
            Class.forName(sqlDB_DRIVER_CLASS);
            con = DriverManager.getConnection(sqlDB_URL, sqlDB_USERNAME, sqlDB_PW);
            System.out.println("Successfully connected!");
            state = con.createStatement();
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
    public int itemInsert(Connection con, UserItem user) {
        String query = "INSERT INTO item VALUES "
                + "VALUES(item_seq.nextVal, ?, ?, ?, ?, TO_DATE(?, 'YYYY/MM/DD'),)";
        PreparedStatement pstate = null;
        int result = 0;

        try {
            pstate = con.prepareStatement(query);
            pstate.setString(1, user.getName());
            pstate.setString(2, user.getFood());
            pstate.setInt(3, user.getFoodType());
            pstate.setInt(4, user.getStorageType());
            pstate.setString(5, user.getPurchaseDate());

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

        public List<UserItem> itemSelectAll (Connection con){
            String query = "SELECT * FROM USER_TABLE";
            Statement state = null;
            ResultSet rs = null;
            List<UserItem> itemList = new ArrayList<>();
            try {
                state = con.createStatement();
                rs = state.executeQuery(query);
                while (rs.next()) {
                    int foodId = rs.getInt("foodID");
                    String food = rs.getString("food");
                    String name = rs.getString("name");
                    int storageType = rs.getInt("storageType");
                    int foodType = rs.getInt("foodType");
                    String purchaseDate = rs.getString("purchaseDate")

                    UserItem userItem = new UserItem(name, food, storageType, foodType, purchaseDate);
                    itemList.add(userItem);
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
            }
            return itemList;
        }
    }
}