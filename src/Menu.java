import ingredients.Egg;
import ingredients.Onion;
import ingredients.Potato;
import ingredients.Tomato;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Menu {
    private static Menu menu = new Menu();

    private List<String> menuList = Arrays.asList("[stir-fry]potato-onion",
            "[stir-fry]egg-onion","[stir-fry]egg-tomato","[dessert]tomato","[soup]potato","[soup]onion");

    public static Menu getMenu() {
        return menu;
    }

    public List<String> getMenuList() {
        return menuList;
    }

    public List<String> showMenu() {
        //List<String> menu = new ArrayList<>();
//        menuList.add("[stir-fry]potato-onion");
//        menuList.add("[stir-fry]egg-onion");
//        menuList.add("[stir-fry]egg-tomato");
//        menuList.add("[dessert]tomato");
//        menuList.add("[soup]potato");
//        menuList.add("[soup]onion");


        if(Potato.getPotato().getAmount() == 0) {
            menuList.stream().filter((a) -> !a.contains("potato"));
        }
        if(Egg.getEgg().getAmount() == 0) {
            menuList.stream().filter((a) -> !a.contains("egg"));
        }
        if(Onion.getOnion().getAmount() == 0) {
            menuList.stream().filter((a) -> !a.contains("onion"));
        }
        if(Tomato.getTomato().getAmount() == 0) {
            menuList.stream().filter((a) -> !a.contains("tomato"));
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
