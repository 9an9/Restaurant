package ingredients;

public class Tomato {
    //싱글톤 객체를 static 변수로 선언
    private static Tomato tomato = new Tomato(10);

    private int amount;

    //외부에서 생성자 호출 막기
    private Tomato(int amount) {
        this.amount = amount;
    }

    public static Tomato getTomato() {
        return tomato;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int n) {
        this.amount = amount-n;
    }
}
