package kitchen;

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

        System.out.println(this.name + "가 " + menu + "를 만들고 있습니다.");

        TimeUnit.SECONDS.sleep(3);

        System.out.println(this.name + " : 주문하신 " + menu + " 나왔습니다.");

        this.status.set(true);
    }

    public AtomicBoolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status.set(status);
    }
}
