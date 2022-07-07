package kitchen.ingredients;

import java.util.concurrent.atomic.AtomicInteger;

public class Onion {
    private static Onion onion = null;

    private AtomicInteger amount = new AtomicInteger();

    private Onion(int amount) {
        this.amount.set(amount);
    }

    public static Onion getOnion() {
        if(onion == null) {
            onion = new Onion(10);
        }
        return onion;
    }

    public AtomicInteger getAmount() {
        return amount;
    }

    public void setAmount(int n) {
        if(amount.get()-n <0) {
            System.out.println("주방 : 양파 없어요!!");
        }else {
            this.amount.set(amount.get()-n);
        }
    }
}
