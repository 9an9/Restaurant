package kitchen.ingredients;

import java.util.concurrent.atomic.AtomicInteger;

public class Egg {
    private static Egg egg = null;

    private AtomicInteger amount = new AtomicInteger();

    private Egg(int amount) {
        this.amount.set(amount);
    }

    public static Egg getEgg() {
        if(egg == null) {
            egg = new Egg(10);
        }
        return egg;
    }

    public AtomicInteger getAmount() {
        return amount;
    }

    public void setAmount(int n) {
        if(amount.get()-n <0) {
            System.out.println("주방 : 달걀 없어요!!");
        }else {
            this.amount.set(amount.get()-n);
        }
    }
}
