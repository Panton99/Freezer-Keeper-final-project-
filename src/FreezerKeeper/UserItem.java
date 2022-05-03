//Jisoo Kim 2022/04/23
package FreezerKeeper;
import java.sql.Timestamp;

public class UserItem{
    private String name;
    private String food;
    private String  storageType;
    private String foodType;
    private Timestamp mDate;
    private int day;
    private Timestamp ExpDate;
    public UserItem() {
        name = "no name";
        food = "no name";
        storageType = "no name";
        foodType = "no name";
    }
    public UserItem(String name, String food, String foodType, String storageType) {
        this.name = name;
        this.food = food;
        this.foodType = foodType;
        this.storageType = storageType;
    }
    public UserItem(String name, String food,  String foodType, String storageType, Timestamp mdate, int day,Timestamp ExpDate) {
        this.name = name;
        this.food = food;
        this.foodType = foodType;
        this.storageType = storageType;
        this.mDate = mdate;
        this.day = day;
        this.ExpDate = ExpDate;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
            this.name = name;
    }
    public String getFood() {
        return food;
    }
    public  void setFood(String food) {this.food = food;}
    public String getStorageType(){
        return storageType;
    }
    public void setStorageType(String storageType) {
            this.storageType = storageType;
    }
    public String getFoodType() {
        return foodType;
    }
    public void setFoodType(String foodType) {
            this.foodType = foodType;
    }
    public Timestamp getMDate() {return mDate;}
    public void setMDate(Timestamp mDate) {
        this.mDate = mDate;
    }
    public String toString() {
        return "\nUser Name: " + name + "\nFood: " + food + "\nFood type: "+foodType+ "\nStorage type: " + storageType
                +"\nStored date: " + mDate + "\nStorage guideline: " + day +" days"+ "\nExpiration date: " + ExpDate +"\n--------------------------------\n";
    }
}
