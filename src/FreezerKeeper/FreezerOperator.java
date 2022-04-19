package FreezerKeeper;

import java.util.List;

public class FreezerOperator {
    //Freezer menu #1 Add food item.
    public void itemInsert(UserItem itemAdd) {
        int result = bs.itemInsert(itemAdd);
        if(result > 0) {
            System.out.println("Food is updated in your list");
        }else {
            bv.itemErr("insert");
        }
    }
    //Freezer menu #2 Delete food item.
    public void itemDelete(int foodId) {
        int result = bs.itemDelete(foodId);
        if(result > 0) {
            System.out.println("The food item is deleted.");
        }else {
            bv.itemErr("delete");
        }

    }
    //Freezer menu #3 Search your food Id.
    public void itemSearchId(int foodId) {
       UserItem userItem = bs.itemSearchtId(foodId);

        if(userItem == null) {
            bv.itemErr("foodId");
        }else {
            bv.itemSearchId(userItem);
        }

    }
    //Freezer menu #4 Search your whole food list.
    public void itemSelectAll() {
        List<UserItem> itemList = bs.itemSelectAll();

        if (itemList.isEmpty()) {
            System.out.println("There is nothing in your list.");
        } else {
            bv.itemSelectList(itemList);
        }
    }

}
