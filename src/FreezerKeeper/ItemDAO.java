//Jisoo Kim 2022/04/21
package FreezerKeeper;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import oracle.jdbc.proxy.annotation.Pre;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class ItemDAO implements FreezerKeeper {
    //Data Access Object (DB connection, insert, delete).
    Connection con;
    Statement state;
    ResultSet rs;
    private String sqlDB_DRIVER_CLASS = "com.mysql.jdbc.Driver"; //loading MySQL driver
    private String sqlDB_URL = "jdbc:mysql://localhost:3306/freezerDB_schema?useSSL=false"; //Connection with DB
    private String sqlDB_USERNAME = "root";
    private String sqlDB_PW = "root1234";

    //Connecting the DB.
    public ItemDAO() {
        try {
            Class.forName(sqlDB_DRIVER_CLASS);
            con = DriverManager.getConnection(sqlDB_URL, sqlDB_USERNAME, sqlDB_PW); //Connection with DB
            state = con.createStatement(rs.TYPE_SCROLL_INSENSITIVE, rs.CONCUR_UPDATABLE); //Allow issuing SQL queries to DB
        } catch (ClassNotFoundException e) {
            System.err.println("\nDriver loading failed..");
        } catch (SQLException e) {
            System.err.println("\nDatabase connection failed..");
        }
    }

    //Creating the table for user input in DB
    public void createTable() {
        try {
            String createTable = "CREATE TABLE if not exists freezerDB (name varchar(50) not null, foodName varchar(45) not null, foodType varchar(50) not null,"
                    + " storageType varchar(50) not null, mDate timestamp default current_timestamp, ExpDate timestamp, PRIMARY KEY (foodName, name))";
            state.execute(createTable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //Create the Cold_Storage_Chart table with txt file.
    /**Query for importing Txt file into Database.
     *  LOAD DATA LOCAL INFILE 'Users/jisookim/Desktop/Cold_Storage_Chart.txt'
     *             INTO TABLE Cold_Storage_Chart
     *             FIELDS terminated by '\t'
     *             lines terminated by '\n'
     *             ignore 1 rows
     *                     (foodType, storageType, Days)
     */
    //Create the table for Cold storage chart(reference table)
    public void createColdStorageChartTable() {
        try {
            state = con.createStatement();
            state.executeUpdate("CREATE TABLE if not exists Cold_Storage_Chart (foodType varchar(45) NOT NULL, storageType varchar(45) NOT NULL, Days int NOT NULL)");
            state.executeUpdate("INSERT IGNORE INTO Cold_Storage_Chart (`foodType`,`storageType`,`Days`) VALUES ('Eggs','Refrigerator',35)");
            state.executeUpdate("INSERT IGNORE INTO Cold_Storage_Chart (`foodType`,`storageType`,`Days`) VALUES ('Eggs ','Freezer',360)");
            state.executeUpdate("INSERT IGNORE INTO Cold_Storage_Chart (`foodType`,`storageType`,`Days`) VALUES ('etc','Freezer',21)");
            state.executeUpdate("INSERT IGNORE INTO Cold_Storage_Chart (`foodType`,`storageType`,`Days`) VALUES ('etc','Refrigerator',1)");
            state.executeUpdate("INSERT IGNORE INTO Cold_Storage_Chart (`foodType`,`storageType`,`Days`) VALUES ('Fin fish','Freezer',90)");
            state.executeUpdate("INSERT IGNORE INTO Cold_Storage_Chart (`foodType`,`storageType`,`Days`) VALUES ('Fin fish','Refrigerator',3)");
            state.executeUpdate("INSERT IGNORE INTO Cold_Storage_Chart (`foodType`,`storageType`,`Days`) VALUES ('Fresh beef, veal, lamb, pork','Freezer',360)");
            state.executeUpdate("INSERT IGNORE INTO Cold_Storage_Chart (`foodType`,`storageType`,`Days`) VALUES ('Fresh beef, veal, lamb, pork','Refrigerator',5)");
            state.executeUpdate("INSERT IGNORE INTO Cold_Storage_Chart (`foodType`,`storageType`,`Days`) VALUES ('Hamburger, ham, ground meats','Freezer',120)");
            state.executeUpdate("INSERT IGNORE INTO Cold_Storage_Chart (`foodType`,`storageType`,`Days`) VALUES ('Hamburger, ham, ground meats','Refrigerator',2)");
            state.executeUpdate("INSERT IGNORE INTO Cold_Storage_Chart (`foodType`,`storageType`,`Days`) VALUES ('Hot dogs','Freezer',60)");
            state.executeUpdate("INSERT IGNORE INTO Cold_Storage_Chart (`foodType`,`storageType`,`Days`) VALUES ('Hot dogs','Refrigerator',7)");
            state.executeUpdate("INSERT IGNORE INTO Cold_Storage_Chart (`foodType`,`storageType`,`Days`) VALUES ('Left overs','Freezer',60)");
            state.executeUpdate("INSERT IGNORE INTO Cold_Storage_Chart (`foodType`,`storageType`,`Days`) VALUES ('Left overs','Refrigerator',4)");
            state.executeUpdate("INSERT IGNORE INTO Cold_Storage_Chart (`foodType`,`storageType`,`Days`) VALUES ('Luncheon meat, Bacon and sausage','Freezer',30)");
            state.executeUpdate("INSERT IGNORE INTO Cold_Storage_Chart (`foodType`,`storageType`,`Days`) VALUES ('Luncheon meat, Bacon and sausage','Refrigerator',5)");
            state.executeUpdate("INSERT IGNORE INTO Cold_Storage_Chart (`foodType`,`storageType`,`Days`) VALUES ('Salad','Freezer',0)");
            state.executeUpdate("INSERT IGNORE INTO Cold_Storage_Chart (`foodType`,`storageType`,`Days`) VALUES ('Salad','Refrigerator',4)");
            state.executeUpdate("INSERT IGNORE INTO Cold_Storage_Chart (`foodType`,`storageType`,`Days`) VALUES ('Shell fish','Freezer',120);");
            state.executeUpdate("INSERT IGNORE INTO Cold_Storage_Chart (`foodType`,`storageType`,`Days`) VALUES ('Shell fish','Refrigerator',3)");
            state.executeUpdate("INSERT IGNORE INTO Cold_Storage_Chart (`foodType`,`storageType`,`Days`) VALUES ('Soups and stews','Freezer',90)");
            state.executeUpdate("INSERT IGNORE INTO Cold_Storage_Chart (`foodType`,`storageType`,`Days`) VALUES ('Soups and stews','Refrigerator',4)");
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    //Insert the food item (user's name, food, food type, storage type)
    public int insertItem(UserItem userItem) {
        String query = "INSERT INTO freezerDB (name, foodName, foodType, storageType)"
                + "VALUES(?, ?, ?, ?)";
        PreparedStatement pstate;
        int result = 0;
        try {
            pstate = con.prepareStatement(query);
            pstate.setString(1, userItem.getName());
            pstate.setString(2, userItem.getFood());
            pstate.setString(3, userItem.getFoodType());
            pstate.setString(4, userItem.getStorageType());
            result = pstate.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    //Deleting the food information by its name.
    public String deleteItem(String foodName) {
        String query2 = "DELETE FROM freezerDB "
                + "WHERE foodName = ?";
        PreparedStatement pstate;
        try {
            pstate = con.prepareStatement(query2);
            pstate.setString(1, foodName);
            pstate.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return foodName;
    }
    //Search the food item.
    public List<UserItem> searchFood(String foodName) {
        List<UserItem> itemList = new ArrayList<>();
        String query4 = "SELECT name, freezerDB.foodName, freezerDB.foodType, freezerDB.storageType, freezerDB.mDate, Cold_Storage_Chart.Days," +
                " DATE_ADD( freezerDB.mDate, INTERVAL Cold_Storage_Chart.Days DAY) AS ExpDate " +
                "FROM freezerDB INNER JOIN Cold_Storage_Chart "
                + "ON  freezerDB.foodType = Cold_Storage_Chart.foodType AND freezerDB.storageType = Cold_Storage_Chart.StorageType " + "WHERE foodName= ?";
        PreparedStatement pstate;
        ResultSet rs4;
        try {
            pstate = con.prepareStatement(query4);
            pstate.setString(1, foodName);
            rs4 = pstate.executeQuery();
            while(rs4.next()) {
                itemList.add(new UserItem(rs4.getString(1), rs4.getString(2),
                        rs4.getString(3), rs4.getString(4), rs4.getTimestamp(5), rs4.getInt(6), rs4.getTimestamp(7)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return itemList;
    }

    //Showing the whole list of foods in the DB.
    public List<UserItem> getExpDate() {
        List<UserItem> itemList = new ArrayList<>();
        String query5 = "SELECT name, freezerDB.foodName, freezerDB.foodType, freezerDB.storageType, freezerDB.mDate, Cold_Storage_Chart.Days," +
                " DATE_ADD( freezerDB.mDate, INTERVAL Cold_Storage_Chart.Days DAY) AS ExpDate " +
                "FROM freezerDB INNER JOIN Cold_Storage_Chart "
                + "ON freezerDB.foodType = Cold_Storage_Chart.foodType AND freezerDB.storageType = Cold_Storage_Chart.StorageType ";
        PreparedStatement pstate;
        ResultSet rs5;
        try {
            pstate = con.prepareStatement(query5);
            rs5 = pstate.executeQuery();
            while(rs5.next()) {
                String name = rs5.getString("name");
                String foodName = rs5.getString("foodName");
                String foodType = rs5.getString("foodType");
                String storageType = rs5.getString("storageType");
                Timestamp mDate = rs5.getTimestamp("mDate");
                int Days = rs5.getInt("Days");
                Timestamp ExpDate =rs5.getTimestamp("ExpDate");
                UserItem userItem = new UserItem(name, foodName, foodType, storageType, mDate, Days, ExpDate);
                itemList.add(userItem);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return itemList;
    }

    // Alert the upcoming expiration date within 3 days.
    public List<UserItem> getExpAlert() {
        List<UserItem> itemList = new ArrayList<>();
        String query5 = "SELECT name, freezerDB.foodName, freezerDB.foodType, freezerDB.storageType, freezerDB.mDate, Cold_Storage_Chart.Days, DATE_ADD( freezerDB.mDate, INTERVAL Cold_Storage_Chart.Days DAY) AS ExpDate " +
                "FROM freezerDB INNER JOIN Cold_Storage_Chart "
                + "ON freezerDB.foodType = Cold_Storage_Chart.foodType AND freezerDB.storageType = Cold_Storage_Chart.StorageType " + "WHERE( (DATE_ADD( freezerDB.mDate, INTERVAL Cold_Storage_Chart.Days DAY)) <= (NOW() + INTERVAL 3 DAY))";
        PreparedStatement pstate;
        ResultSet rs6;
        try {
            pstate = con.prepareStatement(query5);
            rs6 = pstate.executeQuery();
            while(rs6.next()) {
                itemList.add(new UserItem(rs6.getString(1), rs6.getString(2),
                        rs6.getString(3), rs6.getString(4), rs6.getTimestamp(5), rs6.getInt(6), rs6.getTimestamp(7)));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return itemList;
    }
    //Close the result sets.
    public void closeConnection() throws Exception {
        con.close();
        state.close();
    }
}

