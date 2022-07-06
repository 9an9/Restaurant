import kitchen.Chef;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class Manager {

    private static Manager manager = new Manager();

    public static Manager getManager() {
        return manager;
    }

    public void takeOrder(String menu) throws InterruptedException {
        Chef chef = Chef.getChef(checkChefStatus());

        new Thread(() -> {
            try {
                chef.cook(menu);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

    public String checkChefStatus() throws InterruptedException {
        Map<String, AtomicBoolean> status = new HashMap<>();
        status.put("tom", Chef.getChef("tom").getStatus());
        status.put("lee", Chef.getChef("lee").getStatus());

        for(String item : status.keySet()) {
            if(status.get(item).get()) {
                return item;
            }
        }
        System.out.println("모든 셰프가 요리중입니다...");

        TimeUnit.SECONDS.sleep(3);

        checkChefStatus();

        return null;
    }
}
