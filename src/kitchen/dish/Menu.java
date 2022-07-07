package kitchen.dish;

import kitchen.ingredients.Egg;
import kitchen.ingredients.Onion;
import kitchen.ingredients.Potato;
import kitchen.ingredients.Tomato;

import java.util.Arrays;
import java.util.List;

public enum Menu {
    STIR1(Arrays.asList("potato", "onion")),
    STIR2(Arrays.asList("egg", "onion")),
    STIR3(Arrays.asList("egg", "tomato")),
    SOUP1(Arrays.asList("potato")),
    SOUP2(Arrays.asList("onion")),
    DESSERT1(Arrays.asList("tomato"));


    private final List<String> ingrd; //왜 final인지

    Menu(List<String> ingrd) {
        this.ingrd = ingrd;
    }

    public List<String> getValue(){
        return ingrd;
    }
}
