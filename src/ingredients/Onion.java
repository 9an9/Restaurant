package ingredients;

public class Onion {
    //싱글톤 객체를 static 변수로 선언
    private static Onion onion = new Onion(10);

    private int amount;

    //외부에서 생성자 호출 막기
    private Onion(int amount) {
        this.amount = amount;
    }

    public static Onion getOnion() {
        return onion;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int n) {
        if(amount-n <0) {
            System.out.println("주방 : 양파 없어요!!");
        }else {
            this.amount = amount-n;
        }
    }
}
