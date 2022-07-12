package kitchen.dish;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Dish implements Comparable<Dish>{

    private String type;
    private String name;
    private List<String> ingrd;

    private int priority;

    private int tableNum;

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

    @Override
    public int compareTo(Dish o) {
        return this.getPriority() - o.getPriority();
    }

    public void setTableNum(int tableNum) {
        this.tableNum = tableNum;
    }
}
