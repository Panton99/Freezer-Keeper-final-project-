//Jisoo Kim 2022/04/21
package FreezerKeeper;
import java.util.Date;

public class UserItem{
    private String name;
    private String food;
    private int storageType;
    private String storageTypeIs;
    private int foodType;
    private String foodTypeIs;
    private String purchaseDate;
    private Date mDate;
    public UserItem() {
        name = "no name";
        food = "no name";
        storageType = 0;
        foodType = 0;
    }
    public UserItem(String name, String food, int storageType, int foodType) {
        this.name = name;
        this.food = food;
        this.storageType = storageType;
        this.foodType = foodType;
//        this.purchaseDate = purchaseDate;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        if (name.matches("[^a-zA-Z]*$+")) {
            System.err.println("Error: only alphabets.");
            System.exit(0);
        } else {
            this.name = name;
        }
    }
    public String getFood() {
        return food;
    }
    public  void setFood(String food) {
        if (food.matches("[^a-zA-Z]*$+")) {
            System.err.println("Error: only alphabets.");
            System.exit(0);
        } else {
            this.food = food;
        }
    }
    public int getStorageType(){
        return storageType;
    }
    public void setStorageType(int storageType) {
        if (storageType < 0) {
            System.err.println("Error: bad parameter in.");
            System.exit(0);
        } else {
            this.foodType = foodType;
        }
    }
    public int getFoodType() {
        return foodType;
    }
    public void setFoodType(int foodType){
        if (foodType < 0) {
            System.err.println("Error: bad parameter in.");
            System.exit(0);
        } else {
            this.foodType = foodType;
        }
    }
//    public String getPurchaseDate(){
//        return purchaseDate;
//    }
//    public void setPurchaseDate(String purchaseDate){
//        this.purchaseDate = purchaseDate;
//    }
//    public Date getMDate(){
//        return mDate;
//    }
//    public void setMDate(Date mDate){
//        this.mDate = mDate;
//    }
    public String toString() {
        if(storageType == 1) {
            storageTypeIs = "Refrigerator";
        }
        else{
            storageTypeIs = "Freezer";
        }
        switch(foodType){
            case 1:
                foodTypeIs = "Salad";
                break;
            case 2:
                foodTypeIs = "Hot dogs";
                break;
            case 3:
                foodTypeIs = "Luncheon meat";
                break;
            case 4:
                foodTypeIs = "Bacon and sausage";
                break;
            case 5:
                foodTypeIs = "Hamburger, ham, ground meats";
                break;
            case 6:
                foodTypeIs = "Fresh beef, veal, lamb, pork";
                break;
            case 7:
                foodTypeIs = "Fin fish";
                break;
            case 8:
                foodTypeIs = "Shell fish";
                break;
            case 9:
                foodTypeIs = "Eggs";
                break;
            case 10:
                foodTypeIs = "Soups and stews";
                break;
            case 11:
                foodTypeIs = "Left overs";
                break;
            case 12:
                foodTypeIs = "etc";
                break;
            default:
               System.err.println("Something went wrong! Bye.");
        }
        return "<<Food list>>" +"\nUser Name: " + name + "\nFood: " + food + "\nFood type: "+foodTypeIs+ "\nStorage type: " + storageTypeIs +"\n---------------------\n";
    }
}
