package ingredients;

import java.util.concurrent.atomic.AtomicInteger;

public class Tomato {
    //싱글톤 객체를 static 변수로 선언
    private static Tomato tomato = new Tomato(10);

    private AtomicInteger amount = new AtomicInteger();

    //외부에서 생성자 호출 막기
    private Tomato(int amount) {
        this.amount.set(amount);
    }

    public static Tomato getTomato() {
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
