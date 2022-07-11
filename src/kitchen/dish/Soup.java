package kitchen.dish;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Soup extends Dish{
    public Soup(String name, List<String> ingrd) {
        super("SOUP", name, ingrd,1);
    }

    @Override
    public void cookDish() throws InterruptedException {
        TimeUnit.SECONDS.sleep(4);
    }
}
