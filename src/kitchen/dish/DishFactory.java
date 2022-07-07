package kitchen.dish;

import java.util.Arrays;

import static kitchen.dish.Menu.STIR1;

public class DishFactory {

    public Dish dish(Menu menu){
        Dish returnType = null;
        switch (menu){
            case STIR1:
                returnType = new Stir_Fry("STIR1", Menu.STIR1.getValue());
                break;

            case STIR2:
                returnType = new Stir_Fry("STIR2", Menu.STIR2.getValue());
                break;

            case STIR3:
                returnType = new Stir_Fry("STIR3", Menu.STIR3.getValue());
                break;

            case SOUP1:
                returnType = new Soup("SOUP1", Menu.SOUP1.getValue());
                break;

            case SOUP2:
                returnType = new Soup("SOUP2", Menu.SOUP2.getValue());
                break;

            case DESSERT1:
                returnType = new Dessert("DESSERT1", Menu.DESSERT1.getValue());
                break;
        }

        return returnType;
    }
}
