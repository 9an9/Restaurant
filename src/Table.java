import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class Table {

    private static Table table1 = new Table(1, true);
    private static Table table2 = new Table(2, true);
    private static Table table3 = new Table(3, true);
    private static Table table4 = new Table(4, true);

    private int num;

    private AtomicBoolean status;

    private List<String> order;

    private Table(int num, boolean status) {
        this.num = num;
        this.status.set(status);
    }

    public static Table getTable(int num) {
        switch (num) {
            case 1:
                return table1;
            case 2:
                return table2;
            case 3:
                return table3;
            case 4:
                return table4;
            default:
                return null;
        }
    }

    public boolean getStatus() {
        return this.status.get();
    }

    public void setStatus(boolean status) {
        this.status.set(status);
    }

    public void setOrder(List<String> order) {
        this.order = order;
    }

    public List<String> getOrder() {
        return order;
    }
}
