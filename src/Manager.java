import kitchen.Chef;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public class Manager {

    private static Manager manager = new Manager();

    public static Manager getManager() {
        return manager;
    }

    public void takeOrder(String menu) throws InterruptedException {
        String name = checkChefStatus();
        if(name != null) {
            Chef chef = Chef.getChef(name);

            new Thread(() -> {
                try {
                    chef.cook(menu);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }).start();
        }
    }

    public String checkChefStatus() throws InterruptedException {
        String name = "";
        Map<String, AtomicBoolean> status = new HashMap<>();
        status.put("tom", Chef.getChef("tom").getStatus());
        status.put("lee", Chef.getChef("lee").getStatus());

        while (name.equals("")) {
            for(String item : status.keySet()) {
                if(status.get(item).get()) {
                    Chef.getChef(item).setStatus(false);
                    name = item;
                    break;
                }
            }
        }

        return name;
    }
}
