package hall;

import kitchen.dish.Dish;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class Table {

    private int num;

    private AtomicBoolean status;

    private List<Dish> order = new ArrayList<>();

    public Table(int num, boolean status) {
        this.num = num;
        this.status = new AtomicBoolean(status);
    }

    public boolean getStatus() {
        return this.status.get();
    }

    public void setStatus(boolean status) {
        this.status.set(status);
    }

    public List<Dish> getOrder() {
        return order;
    }

    public int getNum() {
        return num;
    }
}
