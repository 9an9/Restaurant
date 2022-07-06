import ingredients.Egg;
import ingredients.Onion;
import ingredients.Potato;
import ingredients.Tomato;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Menu {
    private static Menu menu = new Menu();

    private CopyOnWriteArrayList<String> menuList = new CopyOnWriteArrayList<>(Arrays.asList("[stir-fry]potato-onion",
            "[stir-fry]egg-onion","[stir-fry]egg-tomato","[dessert]tomato","[soup]potato","[soup]onion"));

    public static Menu getMenu() {
        return menu;
    }

    public List<String> getMenuList() {
        return menuList;
    }

    public List<String> showMenu() {

        if(Potato.getPotato().getAmount().get() == 0) {
            menuList.removeIf(a->a.contains("potato"));
        }
        if(Egg.getEgg().getAmount().get() == 0) {
            menuList.removeIf(a->a.contains("egg"));
        }
        if(Onion.getOnion().getAmount().get() == 0) {
            menuList.removeIf(a->a.contains("onion"));
        }
        if(Tomato.getTomato().getAmount().get() == 0) {
            menuList.removeIf(a->a.contains("tomato"));
        }

        System.out.println("ㅜ-----------Menu-----------ㅜ");
        for(String item : menuList) {
            System.out.println("| * " + item);
        }

        if(menuList.size() == 0) {
            System.out.println("재료가 소진되었습니다.");
        }
        System.out.println("ㅗ--------------------------ㅗ");

        return menuList;
    }
}
