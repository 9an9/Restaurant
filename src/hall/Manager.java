package hall;

import kitchen.Chef;
import kitchen.dish.Dish;
import kitchen.ingredients.Egg;
import kitchen.ingredients.Onion;
import kitchen.ingredients.Potato;
import kitchen.ingredients.Tomato;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class Manager {

    private String name;

    public Manager(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void openIngrd() {
        Egg.getEgg();
        Onion.getOnion();
        Potato.getPotato();
        Tomato.getTomato();
    }

    public void takeOrder(Hall hall, Table table) throws InterruptedException {

        List<Dish> dishes = table.getOrder();

        for(int i=0; i< dishes.size(); i++) {
            Chef chef = checkChefStatus(hall.getChefs());
            Dish dish = dishes.get(i);
            dish.setTableNum(table.getNum());
            if(chef != null) {
                if(i>0) {
                    TimeUnit.SECONDS.sleep(3);
                }
                Thread thread = new Thread(() -> {
                    try {
                        hall.getDoneDish().offer(chef.cook(dish));
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                });
                thread.start();
            }
        }
    }

    public Chef checkChefStatus(Chef[] chefs) throws InterruptedException {
        Chef chef = null;

        while (chef == null) {
            for(Chef item : chefs) {
                if(item.getStatus().get()) {
                    item.setStatus(false);
                    chef = item;
                    break;
                }
            }
        }

        return chef;
    }

    public void showMenu(Hall hall) {
        checkMenu(hall);

        System.out.println("ㅜ-----------Menu-----------ㅜ");
        for(String item : hall.getMenuList().keySet()) {
            System.out.println("| * " + item);
        }

        if(hall.getMenuList().size() == 0) {
            System.out.println("재료가 소진되었습니다.");
        }
        System.out.println("ㅗ--------------------------ㅗ");

    }

    public void checkMenu(Hall hall) {
        for(String item : hall.getMenuList().keySet()) {
            if(Potato.getPotato().getAmount().get() == 0) {
                if(item.contains("potato")) {
                    hall.getMenuList().remove(item);
                }
            }
            if(Egg.getEgg().getAmount().get() == 0) {
                if(item.contains("egg")) {
                    hall.getMenuList().remove(item);
                }
            }
            if(Onion.getOnion().getAmount().get() == 0) {
                if(item.contains("onion")) {
                    hall.getMenuList().remove(item);
                }
            }
            if(Tomato.getTomato().getAmount().get() == 0) {
                if(item.contains("tomato")) {
                    hall.getMenuList().remove(item);
                }
            }
        }
    }

    public Table assignTable(Table[] tables) {

        Table table = null;

        while (table == null) {
            for(Table item : tables) {
                if(item.getStatus()) {
                    table = item;
                    table.setStatus(false);
                    break;
                }
            }
        }
        return table;
    }
}

