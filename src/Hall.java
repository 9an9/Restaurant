import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Hall {

    public static void main(String[] args) throws IOException, InterruptedException {

        Open open = new Open();

        open.open();

        List<String> menu = open.menuList();

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        Queue<String> orderList = new LinkedList<>(); //왜 LinkedList로 했는지

        List<String> order = new ArrayList<>();

        while (bf.readLine().equals("주문할게요!")) {
            System.out.println("주문하시겠습니까?");
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
                if(menu.indexOf(s) < 0) {
                    System.out.println(s + "은 없는 메뉴입니다.");
                    iter.remove();
                }
            }

            orderList.addAll(order);

            System.out.println("잠시만 기다려주세요...");

            while(orderList.size() > 0) {
                Manager.getManager().takeOrder(orderList.poll());
            }
        }
    }
}
