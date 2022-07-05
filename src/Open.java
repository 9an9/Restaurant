import ingredients.Egg;
import ingredients.Onion;
import ingredients.Potato;
import ingredients.Tomato;

import java.util.ArrayList;
import java.util.List;

public class Open {
    private List<String> menu;

    public void open() {

        System.out.println("재료를 준비중입니다...");

        System.out.println("** 오늘의 재료 **");
        System.out.println("- Potato : " + Potato.getPotato().getAmount() + " 개");
        System.out.println("- Egg : " + Egg.getEgg().getAmount() + " 개");
        System.out.println("- Onion : " + Onion.getOnion().getAmount() + " 개");
        System.out.println("- Tomato : " + Tomato.getTomato().getAmount() + " 개");

        System.out.println("OPEN!");
    }

    public List<String> menuList() {
        List<String> menu = new ArrayList<>();
        menu.add("[stir-fry]potato-onion");
        menu.add("[stir-fry]egg-onion");
        menu.add("[stir-fry]egg-tomato");
        menu.add("[dessert]tomato");
        menu.add("[soup]potato");
        menu.add("[soup]onion");


        if(Potato.getPotato().getAmount() == 0) {
            menu.stream().filter((a) -> !a.contains("potato"));
        }
        if(Egg.getEgg().getAmount() == 0) {
            menu.stream().filter((a) -> !a.contains("egg"));
        }
        if(Onion.getOnion().getAmount() == 0) {
            menu.stream().filter((a) -> !a.contains("onion"));
        }
        if(Tomato.getTomato().getAmount() == 0) {
            menu.stream().filter((a) -> !a.contains("tomato"));
        }

        System.out.println("ㅜ-----------Menu-----------ㅜ");
        for(String item : menu) {
            System.out.println("| * " + item);
        }

        if(menu.size() == 0) {
            System.out.println("재료가 소진되었습니다.");
        }
        System.out.println("ㅗ--------------------------ㅗ");

        return menu;
    }
}
