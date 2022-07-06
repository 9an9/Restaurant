package ingredients;

import java.util.concurrent.atomic.AtomicInteger;

public class Potato {
    //싱글톤 객체를 static 변수로 선언
    private static Potato potato = new Potato(10);

    private AtomicInteger amount = new AtomicInteger();

    //외부에서 생성자 호출 막기
    private Potato(int amount) {
        this.amount.set(amount);
    }

    public static Potato getPotato() {
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
