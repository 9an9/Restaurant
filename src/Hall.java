import ingredients.Egg;
import ingredients.Onion;
import ingredients.Potato;
import ingredients.Tomato;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Hall {

    public static void main(String[] args) throws IOException, InterruptedException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        Queue<String> orderList = new LinkedList<>(); //왜 LinkedList로 했는지

        List<String> order = new ArrayList<>();

        Thread thread = new Thread(() -> {
            try {
                while(Menu.getMenu().getMenuList().size() > 0) {
                    synchronized (orderList) {
                        while(orderList.size() > 0) {
                            Manager.getManager().takeOrder(orderList.poll());
                        }
                    }
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        thread.start();

        while(Menu.getMenu().getMenuList().size() > 0) {
            while (bf.readLine().equals("주문할게요!")) {
                Menu.getMenu().showMenu();
                System.out.println("Ann : 주문하시겠습니까?");
                while(true) {
                    String str = bf.readLine();
                    if(str.equals(".")) {
                        break;
                    }
                    order.add(str);
                }

                Iterator<String> iter = order.iterator();
                while (iter.hasNext()) {
                    String s = iter.next();
                    if(Menu.getMenu().getMenuList().indexOf(s) < 0) {
                        System.out.println("Ann : " + s + "은 없는 메뉴입니다.");
                        iter.remove();
                    }else {
                        if(s.contains("potato")) {
                            Potato.getPotato().setAmount(1);
                        }
                        if(s.contains("egg")) {
                            Egg.getEgg().setAmount(1);
                        }
                        if(s.contains("onion")) {
                            Onion.getOnion().setAmount(1);
                        }
                        if(s.contains("tomato")) {
                            Tomato.getTomato().setAmount(1);
                        }
                    }
                }
                synchronized (orderList) {
                    orderList.addAll(order);
                }
                order.clear();

                System.out.println("Ann : 잠시만 기다려주세요...");
                System.out.println(
                        "감자(" + Potato.getPotato().getAmount()
                                + ") / 달걀(" + Egg.getEgg().getAmount()
                                + ") / 양파(" + Onion.getOnion().getAmount()
                                + ") / 토마토(" + Tomato.getTomato().getAmount() + ")");
            }
        }

    }
}
