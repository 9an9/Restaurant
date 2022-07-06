package ingredients;

public class Egg {
    //싱글톤 객체를 static 변수로 선언
    private static Egg egg = new Egg(10);

    private int amount;

    //외부에서 생성자 호출 막기
    private Egg(int amount) {
        this.amount = amount;
    }

    public static Egg getEgg() {
        return egg;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int n) {
        if(amount-n <0) {
            System.out.println("주방 : 달걀 없어요!!");
        }else {
            this.amount = amount-n;
        }
    }
}
