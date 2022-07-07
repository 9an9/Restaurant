package kitchen.dish;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Dish {
    private String name;
    private List<String> ingrd;
    private int cookingTime;

    private int priority;

    public Dish(String name, List<String> ingrd, int cookingTime, int priority) {
        this.name = name;
        this.ingrd = ingrd;
        this.cookingTime = cookingTime;
        this.priority = priority;
    }

    public void cookDish() throws InterruptedException {
        TimeUnit.SECONDS.sleep(this.cookingTime);
    }

    public int getPriority() {
        return priority;
    }

    public String getName() {
        return name;
    }
}
