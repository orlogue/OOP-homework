import java.util.ArrayList;

class Dough {
    String dough;
}
class ThinCrustDough extends Dough {
    public ThinCrustDough() {
        this.dough = "Thin crust";
    }
}
class ThickCrustDough extends Dough {
    public ThickCrustDough() {
        this.dough = "Thick crust";
    }
}

class Sauce {
    String sauce;
}
class TomatoSauce extends Sauce {
    public TomatoSauce() {
        this.sauce = "Tomato sauce";
    }
}
class CreamSauce extends Sauce {
    public CreamSauce() {
        this.sauce = "Cream sauce";
    }
}

class Vegetables {
    String vegetables;
}
class DeliciousVegetables extends Vegetables {
    public DeliciousVegetables() {
        this.vegetables = "Delicious vegetables";
    }
}
class AverageVegetables extends Vegetables {
    public AverageVegetables() {
        this.vegetables = "Average vegetables";
    }
}

class Sausages {
    String sausages;
}
class SalamiSausages extends Sausages {
    public SalamiSausages() {
        this.sausages = "Salami";
    }
}
class ChorizoSausages extends Sausages {
    public ChorizoSausages() {
        this.sausages = "Chorizo";
    }
}

class Cheese {
    String cheese;
}
class MozzarellaCheese extends Cheese {
    public MozzarellaCheese() {
        this.cheese = "Mozzarella";
    }
}
class CheddarCheese extends Cheese {
    public CheddarCheese() {
        this.cheese = "Cheddar";
    }
}

interface PizzaIngredientFactory {
    Dough creatDough();
    Sauce createSauce();
    Vegetables createVegetables();
    Sausages createSausages();
    Cheese createCheese();
    ArrayList<String> checkIngredients();
}

class NYCPizzaIngredientFactory implements PizzaIngredientFactory {
    private ArrayList<String> ingredients = new ArrayList<>();

    @Override
    public Dough creatDough() {
        Dough dough = new ThickCrustDough();
        ingredients.add(dough.dough);
        return dough;
    }

    @Override
    public Sauce createSauce() {
        Sauce sauce = new TomatoSauce();
        ingredients.add(sauce.sauce);
        return sauce;
    }

    @Override
    public Vegetables createVegetables() {
        Vegetables vegetables = new AverageVegetables();
        ingredients.add(vegetables.vegetables);
        return vegetables;
    }

    @Override
    public Sausages createSausages() {
        Sausages sausages = new ChorizoSausages();
        ingredients.add(sausages.sausages);
        return sausages;
    }

    @Override
    public Cheese createCheese() {
        Cheese cheese = new CheddarCheese();
        ingredients.add(cheese.cheese);
        return cheese;
    }

    @Override
    public ArrayList<String> checkIngredients() {
        return ingredients;
    }
}

class ChicagoPizzaIngredientFactory implements PizzaIngredientFactory {
    private ArrayList<String> ingredients = new ArrayList<>();

    @Override
    public Dough creatDough() {
        Dough dough = new ThinCrustDough();
        ingredients.add(dough.dough);
        return dough;
    }

    @Override
    public Sauce createSauce() {
        Sauce sauce = new CreamSauce();
        ingredients.add(sauce.sauce);
        return sauce;
    }

    @Override
    public Vegetables createVegetables() {
        Vegetables vegetables = new DeliciousVegetables();
        ingredients.add(vegetables.vegetables);
        return vegetables;
    }

    @Override
    public Sausages createSausages() {
        Sausages sausages = new SalamiSausages();
        ingredients.add(sausages.sausages);
        return sausages;
    }

    @Override
    public Cheese createCheese() {
        Cheese cheese = new MozzarellaCheese();
        ingredients.add(cheese.cheese);
        return cheese;
    }

    @Override
    public ArrayList<String> checkIngredients() {
        return ingredients;
    }
}

abstract class Pizza {
    protected String name;
    protected Dough dough;
    protected Sauce sauce;
    protected Vegetables vegetables;
    protected Sausages sausages;
    protected Cheese cheese;
    protected ArrayList<String> ingredients = new ArrayList<>();

    abstract void prepare();

    public void bake() {
       name += " baked";
    }

    public void cut() {
        name += ", cut";
    }
    public void box() {
        name += ", and boxed.";
    }

    public void check() {
        System.out.println(name + "\nEnjoy your beloved pizza!\nIngredients: ");
        int i = 1;
        for (String ing : ingredients) {
            System.out.println(i++ + ") " + ing);
        }
        System.out.println();
    }

}

class CheesePizza extends Pizza {
    private final PizzaIngredientFactory ingredientFactory;

    CheesePizza(PizzaIngredientFactory ingredientFactory) {
        this.ingredientFactory = ingredientFactory;
    }

    @Override
    public void prepare() {
        this.name = "Cheese pizza";
        this.dough = ingredientFactory.creatDough();
        this.sauce = ingredientFactory.createSauce();
        this.cheese = ingredientFactory.createCheese();
        this.ingredients = ingredientFactory.checkIngredients();
    }
}

class PepperoniPizza extends Pizza {
    private final PizzaIngredientFactory ingredientFactory;

    PepperoniPizza(PizzaIngredientFactory ingredientFactory) {
        this.ingredientFactory = ingredientFactory;
    }

    @Override
    public void prepare() {
        this.name = "Pepperoni pizza";
        this.dough = ingredientFactory.creatDough();
        this.sauce = ingredientFactory.createSauce();
        this.sausages = ingredientFactory.createSausages();
        this.cheese = ingredientFactory.createCheese();
        this.ingredients = ingredientFactory.checkIngredients();
    }
}

class GreekPizza extends Pizza {
    private final PizzaIngredientFactory ingredientFactory;

    GreekPizza(PizzaIngredientFactory ingredientFactory) {
        this.ingredientFactory = ingredientFactory;
    }

    @Override
    public void prepare() {
        this.name = "Greek pizza";
        this.dough = ingredientFactory.creatDough();
        this.sauce = ingredientFactory.createSauce();
        this.vegetables = ingredientFactory.createVegetables();
        this.cheese = ingredientFactory.createCheese();
        this.ingredients = ingredientFactory.checkIngredients();
    }
}

abstract class PizzaStore {
    public Pizza orderPizza(String type) {
        Pizza pizza = createPizza(type);
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();
        return pizza;
    }

    private Pizza createPizza(String type) {
        Pizza pizza = null;
        PizzaIngredientFactory ingredientFactory = this.getPizzaIngredientFactory();

        if (type == "Cheese") {
            pizza = new CheesePizza(ingredientFactory);
        } else if (type == "Pepperoni") {
            pizza = new PepperoniPizza(ingredientFactory);
        } else if (type == "Greek") {
            pizza = new GreekPizza(ingredientFactory);
        }
        return pizza;
    }

    protected abstract PizzaIngredientFactory getPizzaIngredientFactory();
}

class NYCPizzaStore extends PizzaStore {
    @Override
    protected PizzaIngredientFactory getPizzaIngredientFactory() {
        return new NYCPizzaIngredientFactory();
    }
}
class ChicagoPizzaStore extends PizzaStore {
    @Override
    protected PizzaIngredientFactory getPizzaIngredientFactory() {
        return new ChicagoPizzaIngredientFactory();
    }
}

public class Main {
    public static void main(String[] args) {
        PizzaStore NYC = new NYCPizzaStore();
        PizzaStore Chicago = new ChicagoPizzaStore();
        Pizza pizza1 = Chicago.orderPizza("Cheese");
        pizza1.check();
        Pizza pizza2 = NYC.orderPizza("Greek");
        pizza2.check();
        Pizza pizza3 = NYC.orderPizza("Pepperoni");
        pizza3.check();
    }
}
