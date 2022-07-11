package kitchen;

import kitchen.dish.Dish;
import kitchen.dish.DishFactory;

import java.util.concurrent.atomic.AtomicBoolean;

public class Chef {
    private String name;
    private AtomicBoolean status;

    public Chef(String name, boolean status) {
        this.name = name;
        this.status = new AtomicBoolean(status);
    }

    public String cook(Dish dish) throws InterruptedException {

        DishFactory factory = new DishFactory();

        System.out.println(this.name + "가 " + dish.getName() + "를 만들고 있습니다.");

        factory.dish(dish).cookDish();

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
