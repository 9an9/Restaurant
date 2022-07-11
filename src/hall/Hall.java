package hall;

import kitchen.Chef;
import kitchen.dish.Dish;
import kitchen.dish.Menu;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.LinkedBlockingQueue;

public class Hall {

    private Manager manager;

    private Chef[] chefs;

    private Table[] tables;

    private ConcurrentHashMap<String, Dish> menuList;

    private CopyOnWriteArrayList<String> doneDish = new CopyOnWriteArrayList<>();

    private LinkedBlockingQueue<Table> tableOrder = new LinkedBlockingQueue<>(4);

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

    public CopyOnWriteArrayList<String> getDoneDish() {
        return doneDish;
    }

    public LinkedBlockingQueue<Table> getTableOrder() {
        return tableOrder;
    }
}
