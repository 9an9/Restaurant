package kitchen;

import ingredients.Egg;
import ingredients.Onion;
import ingredients.Potato;
import ingredients.Tomato;

import java.util.concurrent.TimeUnit;

public class Chef {
    private String name;
    private String status = "N";

    private static Chef tom = new Chef("Tom", "Y");
    private static Chef lee = new Chef("Lee", "Y");

    private Chef(String name, String status) {
        this.name = name;
        this.status = status;
    }

    public static Chef getChef(String name) {
        Chef chef = new Chef("", "");
        if(name.equals("tom")) return tom;
        if(name.equals("lee")) return lee;
        return null;
    }

    public void cook(String menu) throws InterruptedException {

        this.status = "N";

        if(menu.contains("potato")) {
            Potato.getPotato().setAmount(1);
        }
        if(menu.contains("egg")) {
            Egg.getEgg().setAmount(1);
        }
        if(menu.contains("onion")) {
            Onion.getOnion().setAmount(1);
        }
        if(menu.contains("tomato")) {
            Tomato.getTomato().setAmount(1);
        }

        TimeUnit.SECONDS.sleep(3);

        System.out.println("주문하신 " + menu + " 나왔습니다.");

        this.status = "Y";
    }

    public String getStatus() {
        return status;
    }

}
