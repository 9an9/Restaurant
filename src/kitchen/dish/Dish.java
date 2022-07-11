package kitchen.dish;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Dish {

    private String type;
    private String name;
    private List<String> ingrd;

    private int priority;

    public Dish(String type, String name, List<String> ingrd, int priority) {
        this.type = type;
        this.name = name;
        this.ingrd = ingrd;
        this.priority = priority;
    }

    public void cookDish()  throws InterruptedException {}

    public int getPriority() {
        return priority;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public List<String> getIngrd() {
        return ingrd;
    }

}
