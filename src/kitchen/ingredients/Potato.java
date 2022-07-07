package kitchen.ingredients;

import java.util.concurrent.atomic.AtomicInteger;

public class Potato {
    private static Potato potato = null;

    private AtomicInteger amount = new AtomicInteger();

    private Potato(int amount) {
        this.amount.set(amount);
    }

    public static Potato getPotato() {
        if(potato == null) {
            potato = new Potato(10);
        }
        return potato;
    }

    public AtomicInteger getAmount() {
        return amount;
    }

    public void setAmount(int n) {
        if(amount.get()-n <0) {
            System.out.println("주방 : 감자 없어요!!");
        }else {
            this.amount.set(amount.get()-n);
        }
    }
}
