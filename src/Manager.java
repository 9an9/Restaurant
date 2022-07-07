import kitchen.Chef;
import kitchen.dish.Dish;
import kitchen.dish.DishFactory;
import kitchen.dish.Menu;
import kitchen.ingredients.Egg;
import kitchen.ingredients.Onion;
import kitchen.ingredients.Potato;
import kitchen.ingredients.Tomato;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class Manager {

    private static Manager manager = null;

    public static Manager getManager() {
        if(manager == null) {
            return new Manager();
        }
        return manager;
    }

    public void openIngrd() {
        Egg.getEgg();
        Onion.getOnion();
        Potato.getPotato();
        Tomato.getTomato();
    }

    public void takeOrder(Table table) throws InterruptedException {

        List<String> order = table.getOrder();
        List<Dish> dishes = null;
        List<String> done = null;
        DishFactory dishFactory = new DishFactory();
        for(String item : order) {
            dishes.add(dishFactory.dish(Menu.valueOf(item)));
        }
        dishes.sort((a,b) -> {
            return a.getPriority() - b.getPriority();
        });

        Thread[] as = new Thread[dishes.size()];
        for(int i=0; i< dishes.size(); i++) {
            String name = checkChefStatus();
            Dish dish = dishes.get(i);
            if(name != null) {
                Chef chef = Chef.getChef(name);

                as[i] = new Thread(() -> {
                    try {
                        chef.cook(dish);

                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                });
                as[i].start();
            }
        }

        while(true) {
            int cnt = 0;
            for(int i=0; i<as.length; i++) {
                if(as[i].getState() == Thread.State.TERMINATED){
                    cnt++;
                }
            }
            if(cnt == as.length){
                break;
            }
        }
    }

    public String checkChefStatus() throws InterruptedException {
        String name = "";
        Map<String, AtomicBoolean> status = new HashMap<>();
        status.put("tom", Chef.getChef("tom").getStatus());
        status.put("lee", Chef.getChef("lee").getStatus());

        while (name.equals("")) {
            for(String item : status.keySet()) {
                if(status.get(item).get()) {
                    Chef.getChef(item).setStatus(false);
                    name = item;
                    break;
                }
            }
        }

        return name;
    }

    public void showMenu() {
        checkMenu();

        System.out.println("ㅜ-----------Menu-----------ㅜ");
        for(String item : Hall.menuList) {
            System.out.println("| * " + item);
        }

        if(Hall.menuList.size() == 0) {
            System.out.println("재료가 소진되었습니다.");
        }
        System.out.println("ㅗ--------------------------ㅗ");

    }

    public void checkMenu() {
        if(Potato.getPotato().getAmount().get() == 0) {
            Hall.menuList.removeIf(a->a.contains("potato"));
        }
        if(Egg.getEgg().getAmount().get() == 0) {
            Hall.menuList.removeIf(a->a.contains("egg"));
        }
        if(Onion.getOnion().getAmount().get() == 0) {
            Hall.menuList.removeIf(a->a.contains("onion"));
        }
        if(Tomato.getTomato().getAmount().get() == 0) {
            Hall.menuList.removeIf(a->a.contains("tomato"));
        }
    }

    public Table assignTable() {

        Table table = null;

        while (table == null) {
            for(int i=1; i<5; i++) {
                if(Table.getTable(i).getStatus()) {
                    table = Table.getTable(i);
                    break;
                }
            }
        }
        return table;
    }
}

