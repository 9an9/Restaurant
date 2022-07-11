package kitchen.dish;

import java.util.Arrays;

import static kitchen.dish.Menu.STIR1;

public class DishFactory {

    public Dish dish(Dish dish){
        Dish returnType = null;
        switch (dish.getType()){
            case "SOUP":
                returnType = new Soup(dish.getName(), dish.getIngrd());
                break;

            case "STIR":
                returnType = new Stir_Fry(dish.getName(), dish.getIngrd());
                break;

            case "DESSERT":
                returnType = new Dessert(dish.getName(), dish.getIngrd());
                break;
        }

        return returnType;
    }
}
