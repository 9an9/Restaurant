package hall;

import kitchen.Chef;
import kitchen.dish.Dish;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

public class Hall {

    private Manager manager;

    private Chef[] chefs;

    private Table[] tables;

    private ConcurrentHashMap<String, Dish> menuList;

    private LinkedBlockingQueue<Dish> doneDish = new LinkedBlockingQueue<>();

    private CopyOnWriteArrayList<Integer> tablePriority = new CopyOnWriteArrayList<>();

    private ConcurrentHashMap<Integer, List<String>> tableOrder = new ConcurrentHashMap<>();

    public Hall(Manager manager, Chef[] chefs, Table[] tables, ConcurrentHashMap<String, Dish> menuList) {
        this.manager = manager;
        this.chefs = chefs;
        this.tables = tables;
        this.menuList = menuList;
    }

    public Manager getManager() {
        return manager;
    }

    public Chef[] getChefs() {
        return chefs;
    }

    public Table[] getTables() {
        return tables;
    }

    public ConcurrentHashMap<String, Dish> getMenuList() {
        return menuList;
    }

    public LinkedBlockingQueue<Dish> getDoneDish() {
        return doneDish;
    }

    public ConcurrentHashMap<Integer, List<String>> getTableOrder() {
        return tableOrder;
    }

    public CopyOnWriteArrayList<Integer> getTablePriority() {
        return tablePriority;
    }


}
