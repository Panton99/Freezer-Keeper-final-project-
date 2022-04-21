package FreezerKeeper;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemDAO {
    Connection con;
    Statement state;
    ResultSet rs;
    String sqlDB_DRIVER_CLASS = "com.mysql.jdbc.Driver";
    String sqlDB_URL = "jdbc:mysql://localhost:3306/freezerDB_schema?useSSL=false";
    String sqlDB_USERNAME = "root";
    String sqlDB_PW = "root1234";
    //Connecting the DB.
    public ItemDAO(){
        try{
            Class.forName(sqlDB_DRIVER_CLASS);
            con = DriverManager.getConnection(sqlDB_URL, sqlDB_USERNAME, sqlDB_PW);
            System.out.println("Database successfully connected!");
            state = con.createStatement(rs.TYPE_SCROLL_INSENSITIVE, rs.CONCUR_UPDATABLE);
            String sql;
            sql = "select * from FreezerDB";
            rs = state.executeQuery(sql);
        }
        catch (ClassNotFoundException e){
            System.err.println("\nDriver loading failed..");
        }
        catch (SQLException e){
            System.err.println("\nDatabase connection failed..");
        }
    }
    //Creating the table in DB
    public void createTable() {
        try {
            String createTable = "CREATE TABLE freezerDB (name varchar(50) not null, foodName varchar(45) not null, foodType int not null,"
                    + " storageType int not null, PRIMARY KEY (foodName))";
            state.execute(createTable);
            System.out.println("Freezer DB table has been made.");
        } catch(Exception e) {
            System.out.println("Something is wrong : " + e.toString());
        }
    }
    //Insert the food item (user name, food, food type, storage type)
    public int insertItem(UserItem userItem) {
        String query = "INSERT INTO freezerDB(name, foodName, foodType, storageType)"
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
//            System.out.println("Food item added: "+pstate.executeUpdate());
            if (result > 0) {
                System.out.println("\nItem is successfully updated.");
            } else {
                System.out.println("\nItem update failed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                rs.close();
//                state.close();
//                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
    //Deleting the food information by its name.
    public String deleteItem(String foodName) {
        String query2 = "DELETE FROM FreezerDB "
                + "WHERE foodName = ?";
        PreparedStatement pstate = null;
        try {
            pstate = con.prepareStatement(query2);
            pstate.setString(1, foodName);
            pstate.executeUpdate();
            System.out.println("The item is deleted from the list.");

        } catch (SQLException e) {
            e.printStackTrace();
            } finally {
                try {
                    rs.close();
//                    state.close();
//                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
        return foodName;
    }

//    public UserItem searchFood(String foodName) {
//        String query4 = "SELECT * FROM FreezerDB " + "WHERE foodName= ?";
//        PreparedStatement pstate = null;
//        ResultSet rs4 = null;
//        UserItem userItem = new UserItem;
//        try {
//            pstate = con.prepareStatement(query4);
//            pstate.setInt(1, foodName);
//            rs4 = pstate.executeQuery();
//            while(rs4.next()) {
//                        userItem.setName(rs4.getName());,
//                        rs4.getString(userItem.getFood()),
//                        rs4.getInt(userItem.getStorageType()),
//                        rs4.getInt(userItem.getFoodType()));
////                        rs4.getString(userItem.getPurchaseDate());
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//            rs.close();
//            state.close();
//            pstate.close();
//            }
//            catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        return userItem;
//    }
    //Showing the whole list of foods in the DB.
    public List<UserItem> itemShowList() {
            String query3 = "SELECT * FROM FreezerDB";
            List<UserItem> itemList = new ArrayList<>();
            try {
                ResultSet rs1 = state.executeQuery(query3);
                while (rs1.next()) {
                    String name = rs1.getString("name");
                    String foodName = rs1.getString("foodName");
                    int foodType = rs1.getInt("foodType");
                    int storageType = rs1.getInt("storageType");
                    UserItem userItem = new UserItem(name, foodName, storageType, foodType);
                    itemList.add(userItem);
                    System.out.println(userItem.toString());
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    rs.close();
//                    state.close();
//                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            return itemList;
    }
}
