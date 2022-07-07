package kitchen;

import kitchen.dish.Dish;
import kitchen.dish.DishFactory;
import kitchen.dish.Menu;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class Chef {
    private String name;
    private AtomicBoolean status;

    private static Chef tom = new Chef("Tom", true);
    private static Chef lee = new Chef("Lee", true);

    private Chef(String name, boolean status) {
        this.name = name;
        this.status = new AtomicBoolean(status);
    }

    public static Chef getChef(String name) {
        if(name.equals("tom")) return tom;
        if(name.equals("lee")) return lee;
        return null;
    }

    public String cook(Dish dish) throws InterruptedException {

        System.out.println(this.name + "가 " + dish.getName() + "를 만들고 있습니다.");

        dish.cookDish();

        System.out.println(this.name + " : 주문하신 " + dish.getName() + " 나왔습니다.");

        this.status.set(true);

        return dish.getName();
    }

    public AtomicBoolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status.set(status);
    }
}
