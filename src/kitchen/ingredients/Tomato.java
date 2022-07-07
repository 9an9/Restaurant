package kitchen.ingredients;

import java.util.concurrent.atomic.AtomicInteger;

public class Tomato {
    private static Tomato tomato = null;

    private AtomicInteger amount = new AtomicInteger();

    private Tomato(int amount) {
        this.amount.set(amount);
    }

    public static Tomato getTomato() {
        if(tomato == null) {
            tomato = new Tomato(10);
        }
        return tomato;
    }

    public AtomicInteger getAmount() {
        return amount;
    }

    public void setAmount(int n) {
        if(amount.get()-n < 0) {
            System.out.println("주방 : 토마토 없어요!!");
        }else {
            this.amount.set(amount.get()-n);
        }

    }
}
