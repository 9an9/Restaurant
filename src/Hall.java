import kitchen.dish.Menu;
import kitchen.ingredients.Egg;
import kitchen.ingredients.Onion;
import kitchen.ingredients.Potato;
import kitchen.ingredients.Tomato;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class Hall {

    public static CopyOnWriteArrayList<String> menuList = new CopyOnWriteArrayList<>(Arrays.stream(Menu.values()).map((a) -> a.name()).collect(Collectors.toList()));

    public static void main(String[] args) throws IOException, InterruptedException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        Queue<Table> orderList = new LinkedList<>();

        List<String> order = new ArrayList<>();

        Table table = null;

        Manager.getManager();
        System.out.println("Ann : 출근하기 싫다....!");
        System.out.println("open!");
        System.out.println(
                "** 오늘의 재료 : 감자(" + Potato.getPotato().getAmount()
                        + ") / 달걀(" + Egg.getEgg().getAmount()
                        + ") / 양파(" + Onion.getOnion().getAmount()
                        + ") / 토마토(" + Tomato.getTomato().getAmount() + ") **");

        Thread thread = new Thread(() -> {
            try {
                while(menuList.size() > 0) {
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

        while(menuList.size() > 0) {
            while (bf.readLine().equals("주문할게요!")) {
                table = Manager.getManager().assignTable();
                table.setStatus(false);
                Manager.getManager().showMenu();
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
                    if(menuList.indexOf(s) < 0) {
                        System.out.println("Ann : " + s + "은 없는 메뉴입니다.");
                        iter.remove();
                    }else {
                        if(Menu.valueOf(s).getValue().contains("potato")) {
                            if(Potato.getPotato().getAmount().get() < 1) {
                                System.out.println("Ann : 감자 없어요!");
                                iter.remove();
                                continue;
                            }else {
                                Potato.getPotato().setAmount(1);
                            }
                        }
                        if(Menu.valueOf(s).getValue().contains("egg")) {
                            if(Egg.getEgg().getAmount().get() < 1) {
                                System.out.println("Ann : 달걀 없어요!");
                                iter.remove();
                                continue;
                            }else {
                                Egg.getEgg().setAmount(1);
                            }
                        }
                        if(Menu.valueOf(s).getValue().contains("onion")) {
                            if(Onion.getOnion().getAmount().get() < 1) {
                                System.out.println("Ann : 양파 없어요!");
                                iter.remove();
                                continue;
                            }else {
                                Onion.getOnion().setAmount(1);
                            }
                        }
                        if(Menu.valueOf(s).getValue().contains("tomato")) {
                            if(Tomato.getTomato().getAmount().get() < 1) {
                                System.out.println("Ann : 토마토 없어요!");
                                iter.remove();
                                continue;
                            }else {
                                Tomato.getTomato().setAmount(1);
                            }
                        }
                    }
                }

                table.setOrder(order);
                synchronized (order) {
                    orderList.add(table);
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
        System.out.println("영업종료! 재료가 모두 소진되었습니다^^");

    }
}
