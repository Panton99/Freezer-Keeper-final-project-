package FreezerKeeper;
import java.util.Date;

public class UserItem{
/*

`food` VARCHAR(50) NOT NULL ,
`storageDate` INT NOT NULL ,
`storageType` INT NOT NULL ,
`name` VARCHAR(45) NOT NULL ;
* */
    private String name;
    private String food;
    private int storageType;
    private int foodType;
    private String purchaseDate;
    private Date mDate;
    public UserItem(String name, String food, int storageType, int foodType) {
        super();
        this.name = name;
        this.food = food;
        this.storageType = storageType;
        this.foodType = foodType;
//        this.purchaseDate = purchaseDate;
    }
    public String getName() {
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getFood() {
        return food;
    }
    public  void setFood(String food){
        this.food = food;
    }
    public int getStorageType(){
        return storageType;
    }
    public void setStorageType(int foodType){
        this.foodType = foodType;
    }
    public int getFoodType() {
        return foodType;
    }
    public void setFoodType(int foodType){
        this.foodType = foodType;
    }
    public String getPurchaseDate(){
        return purchaseDate;
    }
    public void setPurchaseDate(String purchaseDate){
        this.purchaseDate = purchaseDate;
    }
    public Date getMDate(){
        return mDate;
    }
    public void setMDate(Date mDate){
        this.mDate = mDate;
    }
    public String toString(){
        return "UserItem [User Name=" + name + ", Food=" + food + ", Storage type=" + storageType+"]";
    }
}
