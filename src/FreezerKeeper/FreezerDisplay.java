package FreezerKeeper;

import java.util.List;

public class FreezerDisplay {
    public void itemSelectList(List<UserItem> itemList) {
        System.out.println("===< Your Food Item List >===");
        for(UserItem userItem : itemList) {
            System.out.println(userItem);
        }
    }

    public void searchFoodId(UserItem id) {
        System.out.println("===< Your Food item >===");
        System.out.println(id);
    }
}
