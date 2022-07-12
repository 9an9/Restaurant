import hall.Hall;
import hall.Manager;
import hall.Table;
import kitchen.Chef;
import kitchen.dish.Dish;
import kitchen.dish.Menu;
import kitchen.ingredients.Egg;
import kitchen.ingredients.Onion;
import kitchen.ingredients.Potato;
import kitchen.ingredients.Tomato;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in)); // 주문입력

        Queue<Table> orderList = new LinkedList<>(); // 받은 주문들(주방에 요청하는 용도)

        List<Dish> order = new ArrayList<>(); // 테이블별 주문서

        Table table = null;


        // 1. Hall 만들기
        // 1-2. Manager
        Manager manager = new Manager("Ann");
        // 1-3. Chef
        Chef[] chefs = new Chef[2];
        chefs[0] = new Chef("Tom", true);
        chefs[1] = new Chef("Lee", true);
        // 1-4. Table
        Table[] tables = new Table[4];
        for(int i=1; i<5; i++) {
            tables[i-1] = new Table(i, true);
        }
        // 1-5. Menu
        ConcurrentHashMap<String, Dish> menuList = new ConcurrentHashMap<>();
        menuList.put("[STIR]potato-onion", new Dish("STIR", "[STIR]potato-onion", Arrays.asList("potato", "onion"), 2 ));
        menuList.put("[STIR]egg-onion", new Dish("STIR", "[STIR]egg-onion", Arrays.asList("potato", "onion"), 2 ));
        menuList.put("[STIR]egg-tomato", new Dish("STIR", "[STIR]egg-tomato", Arrays.asList("potato", "onion"), 2 ));
        menuList.put("[SOUP]egg", new Dish("SOUP", "[SOUP]egg", Arrays.asList("egg"), 1 ));
        menuList.put("[SOUP]onion", new Dish("SOUP", "[SOUP]onion", Arrays.asList("onion"), 1 ));
        menuList.put("[SOUP]tomato", new Dish("SOUP", "[SOUP]tomato", Arrays.asList("tomato"), 1 ));
        menuList.put("[SOUP]potato", new Dish("SOUP", "[SOUP]potato", Arrays.asList("tomato"), 1 ));
        menuList.put("[DESSERT]tomato", new Dish("DESSERT", "[DESSERT]tomato", Arrays.asList("tomato"), 3 ));

        Hall hall = new Hall(manager,chefs, tables,menuList);

        //테이블별 주문 초기화
        for(int i=1; i<5; i++) {
            hall.getTableOrder().put(i, new ArrayList<>());
        }

        // 2. 영업 시작
        System.out.println(hall.getManager().getName() + " : 출근하기 싫다....!");
        System.out.println("open!");
        System.out.println(
                "** 오늘의 재료 : 감자(" + Potato.getPotato().getAmount()
                        + ") / 달걀(" + Egg.getEgg().getAmount()
                        + ") / 양파(" + Onion.getOnion().getAmount()
                        + ") / 토마토(" + Tomato.getTomato().getAmount() + ") **");

        // 2-1. 주방에 테이블 별 주문 넣기 Thread 작동
        Thread kitchenOrder = new Thread(() -> {
            try {
                while(menuList.size() > 0) {
                    synchronized (orderList) {
                        while(orderList.size() > 0) {
                            hall.getManager().takeOrder(hall, orderList.poll());
                        }
                    }
                }
                System.out.println("셰프님 홀 마감했습니다!");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        kitchenOrder.start();


        // 2-2. 주방에서 음식 내오기 Thread 작동
        Thread kitchenDish = new Thread(() -> {
            try {
                while(true) {
                    if(hall.getDoneDish().size()>0) {
                        Dish dish = hall.getDoneDish().take();
                        for(Integer item : hall.getTablePriority()) {
                            if(hall.getTableOrder().get(item).contains(dish.getName())) {
                                System.out.println(item + "번 테이블 음식 " + dish.getName() + "나왔습니다.");
                                hall.getTableOrder().get(item).remove(dish.getName());
                            }
                            if(hall.getTableOrder().get(item).size()<=0) {
                                System.out.println(item + "번 테이블 음식 다 나왔습니다. 맛있게 드세요!");
                                hall.getTablePriority().remove(item);
                                hall.getTables()[item].setStatus(true);
                                hall.getTables()[item].getOrder().clear();
                            }
                        }
                    }
                    if(hall.getTableOrder().size()<=0) {
                        System.out.println("영업 끝! 문닫습니다!!!");
                        break;
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        kitchenDish.start();

        // 2-3. 주문 받기
        while(hall.getMenuList().size() > 0) {
            if (bf.readLine().equals("주문할게요!")) {
                table = hall.getManager().assignTable(hall.getTables());
                hall.getManager().showMenu(hall);
                System.out.println("Ann : 주문하시겠습니까?");
                while(true) {
                    String str = bf.readLine();
                    if(str.equals(".")) {
                        break;
                    }
                    if(!str.isEmpty() && hall.getMenuList().get(str) != null) {
                        order.add(hall.getMenuList().get(str));
                    }
                }

                Iterator<Dish> iter = order.iterator();
                while (iter.hasNext()) {
                    String s = iter.next().getName();
                    if(!hall.getMenuList().containsKey(s)) {
                        System.out.println("Ann : " + s + "은 없는 메뉴입니다.");
                        iter.remove();
                    }else {
                        if(hall.getMenuList().get(s).getName().contains("potato")) {
                            if(Potato.getPotato().getAmount().get() < 1) {
                                System.out.println("Ann : 감자 없어요!");
                                iter.remove();
                                continue;
                            }else {
                                Potato.getPotato().setAmount(1);
                            }
                        }
                        if(hall.getMenuList().get(s).getName().contains("egg")) {
                            if(Egg.getEgg().getAmount().get() < 1) {
                                System.out.println("Ann : 달걀 없어요!");
                                iter.remove();
                                continue;
                            }else {
                                Egg.getEgg().setAmount(1);
                            }
                        }
                        if(hall.getMenuList().get(s).getName().contains("onion")) {
                            if(Onion.getOnion().getAmount().get() < 1) {
                                System.out.println("Ann : 양파 없어요!");
                                iter.remove();
                                continue;
                            }else {
                                Onion.getOnion().setAmount(1);
                            }
                        }
                        if(hall.getMenuList().get(s).getName().contains("tomato")) {
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
                order.sort((a,b) -> {
                    return a.getPriority() - b.getPriority();
                });
                table.getOrder().addAll(order);
//                hall.getTableOrder().put(table);
                hall.getTablePriority().add(table.getNum());
                hall.getTableOrder().get(table.getNum()).addAll(order.stream().map((a) -> a.getName()).collect(Collectors.toList()));
                synchronized (order) {
                    orderList.add(table);
                }

                if(order.size()<=0) {
                    table.setStatus(true);
                    System.out.println("메뉴 다시 결정하시고 불러주세요!");
                }else {
                    System.out.println("Ann : 잠시만 기다려주세요...");
                    System.out.println("ㅜ=================주문서================ㅜ");
                    for(int item : hall.getTableOrder().keySet()) {
                        System.out.print(item + "번 테이블 : ");
                        for(String str : hall.getTableOrder().get(item)) {
                            System.out.print(str + "/ ");
                        }
                        System.out.println();
                        System.out.println("---------------------------------");
                    }
                    System.out.println(
                            "감자(" + Potato.getPotato().getAmount()
                                    + ") / 달걀(" + Egg.getEgg().getAmount()
                                    + ") / 양파(" + Onion.getOnion().getAmount()
                                    + ") / 토마토(" + Tomato.getTomato().getAmount() + ")");
                    manager.checkMenu(hall);
                    order.clear();
                }
            }
        }
        System.out.println("재료가 모두 소진되었습니다^^");

    }
}
