package ingredients;

public class Potato {
    //싱글톤 객체를 static 변수로 선언
    private static Potato potato = new Potato(10);

    private int amount;

    //외부에서 생성자 호출 막기
    private Potato(int amount) {
        this.amount = amount;
    }

    public static Potato getPotato() {
        return potato;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int n) {
        this.amount = amount-n;
    }
}
