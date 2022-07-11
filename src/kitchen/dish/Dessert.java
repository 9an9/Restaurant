package kitchen.dish;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Dessert extends Dish{
    public Dessert(String name, List<String> ingrd) {
        super("DESSERT", name, ingrd,3);
    }

    @Override
    public void cookDish() throws InterruptedException {
        TimeUnit.SECONDS.sleep(4);
    }
}
