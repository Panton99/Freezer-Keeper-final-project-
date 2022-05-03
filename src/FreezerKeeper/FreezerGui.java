//Jisoo Kim 2022/04/23
package FreezerKeeper;
import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
public class FreezerGui extends JFrame {
    JFrame jframe = new JFrame();
    JPanel jpanel = new JPanel();
    //One line input component
    JTextField tName = new JTextField(); //for name input.
    JTextField tFood = new JTextField(); //for food item input.
    //multiple line input component
    JTextArea ta = new JTextArea();
    JButton create, add, delete, view, search, alert;
    JLabel nameLabel = new JLabel("User name : ");
    JLabel foodLabel = new JLabel("Food name : ");
    JLabel foodTypeLabel = new JLabel("Food type : ");
    JLabel storageTypeLabel = new JLabel("Storage type : ");
    String [] optChoose = {"Salad", "Hot dogs", "Luncheon meat, Bacon and sausage", "Hamburger, ham, ground meats",
            "Fresh beef, veal, lamb, pork", "Fin fish", "Shell fish",
             "Eggs", "Soups and stews", "Left overs", "etc"};
    JComboBox<String> jComboBoxFoodType = new JComboBox<>(optChoose);
    String [] optSChoose = {"Refrigerator", "Freezer"};
    JComboBox<String> jComboBoxStorageType= new JComboBox<>(optSChoose);

    public FreezerGui() {
        view();
    }
    public void view() {
        try{//Interface settings
            jframe.setTitle("Freezer Keeper");
            jframe.setBounds(100, 100, 1000 , 1000);
            jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            jframe.setVisible(true);
            jpanel.setLayout(null);
            jframe.add(jpanel);
            UIManager.put(ta, new FontUIResource(new Font("Dialog", Font.BOLD, 16)));
            //Scroll bar
            JScrollPane jsp = new JScrollPane(ta);
            jsp.setBounds(60, 160, 850, 540);
            jpanel.add(jsp);
            //set the labels in the interface.
            tName.setBounds(110, 25, 120, 25);//name input component
            jpanel.add(tName);
            nameLabel.setBounds(30, 21, 100, 30);
            jpanel.add(nameLabel);
            tFood.setBounds(330, 25, 120, 25);//food item input component
            jpanel.add(tFood);
            foodLabel.setBounds(250, 21, 100, 30);
            jpanel.add(foodLabel);
            jComboBoxFoodType.setBounds(530, 25, 200, 25);
            jpanel.add(jComboBoxFoodType);
            foodTypeLabel.setBounds(460, 21, 100, 30);
            jpanel.add(foodTypeLabel);
            jComboBoxStorageType.setBounds(820, 25, 150, 25);
            jpanel.add(jComboBoxStorageType);
            storageTypeLabel.setBounds(730, 21, 100, 30);
            jpanel.add(storageTypeLabel);
            //set the buttons in the interface.
            jpanel.add(create = new JButton("Create new table"));
            create.setBounds(60, 70, 150, 30);
            jpanel.add(add = new JButton("Add food item"));
            add.setBounds(220, 70, 150, 30);
            jpanel.add(delete = new JButton("Delete food item"));
            delete.setBounds(380, 70, 150, 30);
            jpanel.add(view = new JButton("View whole food list"));
            view.setBounds(540, 70, 170, 30);
            jpanel.add(search = new JButton("Search food item"));
            search.setBounds(720, 70, 170, 30);
            jpanel.add(alert = new JButton("Upcoming expiration list"));
            alert.setBounds(380, 110, 220, 40);

            ItemDAO dao = new ItemDAO();
            ta.append("Database successfully connected!");
            dao.createColdStorageChartTable();

            //Close database on exit.
            jframe.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e)
                {
                    try{
                        super.windowClosing(e);
                        dao.closeConnection();
                    }
                    catch (Exception ep) {
                        ep.printStackTrace();
                    }
                }
                });

            //Making new user's table in the Database.
            create.addActionListener((event) -> {
                    ta.setText("");
                    dao.createTable();
                    ta.append("Freezer DB table has been made. \n");
                    tName.setText("");
                    tFood.setText("");
                    jComboBoxFoodType.setAction(null);
                    jComboBoxStorageType.setAction(null);
            });

            //Adding food item in user's list.
            add.addActionListener((event) -> {
                    ta.setText("");
                    String name = tName.getText();
                    String foodName = tFood.getText();
                    // To find whether there is a wrong parameter in user's name or food item.
                    if (!name.matches("^[a-zA-Z]+$") || foodName.isEmpty() ) {
                        ta.append("Error: Wrong parameters.");
                    }
                    else{
                    String foodType = jComboBoxFoodType.getItemAt(jComboBoxFoodType.getSelectedIndex());
                    String storageType = jComboBoxStorageType.getItemAt(jComboBoxStorageType.getSelectedIndex());
                    dao.insertItem(new UserItem(name, foodName, foodType, storageType));
                    ta.append("Item is successfully updated.\n");
                    tName.setText("");
                    tFood.setText("");
                    jComboBoxFoodType.setAction(null);
                    jComboBoxStorageType.setAction(null);
                    }
            });

            //Deleting the item in user's list.
            delete.addActionListener((event) -> {
                    ta.setText("");
                    String foodName = tFood.getText();
                    dao.deleteItem(foodName);
                    ta.append("The item is deleted from the list.\n");
                    tName.setText("");
                    tFood.setText("");
                    jComboBoxFoodType.setAction(null);
                    jComboBoxStorageType.setAction(null);
            });

            //Showing whole food list.
            view.addActionListener((event) -> {
                    ta.setText("");
                    dao.getExpDate();
                    if(dao.getExpDate().isEmpty()) {
                        ta.append("There is no item in the list.");
                    }
                    else {
                        ta.append("This is your whole list of food item.\n");
                    }
                    ta.append(dao.getExpDate().toString().replace("[", "").replace("]", "").replace(",",""));
                    tName.setText("");
                    tFood.setText("");
                    jComboBoxFoodType.setAction(null);
                    jComboBoxStorageType.setAction(null);
            });

            //Showing search result.
            search.addActionListener((event) ->{
                    ta.setText("");
                    String foodName = tFood.getText();
                    dao.searchFood(foodName);
                    if(dao.searchFood(foodName).isEmpty()) {
                        ta.append("There is no such food item in the list.");
                    }
                    else {
                        ta.append("This is your search result.\n");
                    ta.append(dao.searchFood(foodName).toString().replace("[", "").replace("]", "").replace(",",""));
                    }
                    tName.setText("");
                    tFood.setText("");
                    jComboBoxFoodType.setAction(null);
                    jComboBoxStorageType.setAction(null);
            });
            //Showing upcoming expiration food list.
            alert.addActionListener((event) -> {
                    ta.setText("");
                    dao.getExpAlert();
                    if(dao.getExpAlert().isEmpty()) {
                        ta.append("There is no upcoming expiration dates in the list.");
                    }
                    else {
                        ta.append("This is your upcoming expiration/expired date. You should use within 3 days.\n");
                        ta.append(dao.getExpAlert().toString().replace("[", "").replace("]", "").replace(",",""));

                    }
                    tName.setText("");
                    tFood.setText("");
                    jComboBoxFoodType.setAction(null);
                    jComboBoxStorageType.setAction(null);
            });
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
