interface Beverage {
    String getDescreption();
    float getCost();
}

class Cappuccino implements Beverage {
    @Override
    public String getDescreption() {
        return "Cappucino";
    }

    @Override
    public float getCost() {
        return 300;
    }
}

class Espresso implements Beverage {
    @Override
    public String getDescreption() {
        return "Espresso";
    }

    @Override
    public float getCost() {
        return 200;
    }
}

class Latte implements Beverage {
    @Override
    public String getDescreption() {
        return "Latte";
    }

    @Override
    public float getCost() {
        return 250;
    }
}

class Mocha implements Beverage {
    @Override
    public String getDescreption() {
        return "Mocha";
    }

    @Override
    public float getCost() {
        return 275;
    }
}

interface Condiment extends Beverage {}

class CaramelSyrup implements Condiment {
    Beverage BasicBeverage;

    public CaramelSyrup(Beverage basicBeverage) {
        BasicBeverage = basicBeverage;
    }

    @Override
    public String getDescreption() {
        return BasicBeverage.getDescreption() + " with caramel syrup";
    }

    @Override
    public float getCost() {
        return BasicBeverage.getCost() + 30;
    }
}

class SoyMilk implements Condiment {
    Beverage BasicBeverage;

    public SoyMilk(Beverage basicBeverage) {
        BasicBeverage = basicBeverage;
    }

    @Override
    public String getDescreption() {
        return BasicBeverage.getDescreption() + " with soy milk";
    }

    @Override
    public float getCost() {
        return BasicBeverage.getCost() + 80;
    }
}

class Cinnamon implements Condiment {
    Beverage BasicBeverage;

    public Cinnamon(Beverage basicBeverage) {
        BasicBeverage = basicBeverage;
    }

    @Override
    public String getDescreption() {
        return BasicBeverage.getDescreption() + " with cinnamon";
    }

    @Override
    public float getCost() {
        return BasicBeverage.getCost() + 15;
    }
}

class WhippedCream implements Condiment {
    Beverage BasicBeverage;

    public WhippedCream(Beverage basicBeverage) {
        BasicBeverage = basicBeverage;
    }

    @Override
    public String getDescreption() {
        return BasicBeverage.getDescreption() + " with whipped cream";
    }

    @Override
    public float getCost() {
        return BasicBeverage.getCost() + 15;
    }
}

public class Decorator {
    public static void main(String[] args) {
        Beverage beverage1 = new Cinnamon(new WhippedCream(new CaramelSyrup(new Latte())));
        Beverage beverage2 = new WhippedCream(new CaramelSyrup(new SoyMilk(new Mocha())));

        System.out.printf("%s.\nCost: %.0fRUB\n\n", beverage1.getDescreption(), beverage1.getCost());
        System.out.printf("%s.\nCost: %.0fRUB\n\n", beverage2.getDescreption(), beverage2.getCost());
    }
}