package kitchen.dish;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Stir_Fry extends Dish{
    public Stir_Fry(String name, List<String> ingrd) {
         super("STIR", name, ingrd, 2);
    }

    @Override
    public void cookDish() throws InterruptedException {
        TimeUnit.SECONDS.sleep(4);
    }
}
