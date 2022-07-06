package kitchen;

import ingredients.Egg;
import ingredients.Onion;
import ingredients.Potato;
import ingredients.Tomato;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class Chef {
    private String name;
    private AtomicBoolean status;

    private static Chef tom = new Chef("Tom", true);
    private static Chef lee = new Chef("Lee", true);

    private Chef(String name, boolean status) {
        this.name = name;
        this.status = new AtomicBoolean(status);
    }

    public static Chef getChef(String name) {
        if(name.equals("tom")) return tom;
        if(name.equals("lee")) return lee;
        return null;
    }

    public void cook(String menu) throws InterruptedException {

        this.status.set(false);

        TimeUnit.SECONDS.sleep(5);

        System.out.println(this.name + " : 주문하신 " + menu + " 나왔습니다.");

        this.status.set(true);
    }

    public AtomicBoolean getStatus() {
        return status;
    }

}
