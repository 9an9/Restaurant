package ingredients;

import java.util.concurrent.atomic.AtomicInteger;

public class Egg {
    //싱글톤 객체를 static 변수로 선언
    private static Egg egg = new Egg(10);

    private AtomicInteger amount = new AtomicInteger();

    //외부에서 생성자 호출 막기
    private Egg(int amount) {
        this.amount.set(amount);
    }

    public static Egg getEgg() {
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
