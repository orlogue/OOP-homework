import java.util.LinkedList;
import java.util.List;

interface Subject {
    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
}

class WeatherData implements Subject {
    private List<Observer> observers = new LinkedList<>();

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        int index = observers.indexOf(observer);
        if (index >= 0) {
            observers.remove(index);
        }
    }

    @Override
    public void notifyObservers() {
        for (Observer observer: observers) {
            observer.update(getTemperature(), getHumidity(), getPressure());
            observer.display();
        }
    }

    float getTemperature() {
        return (float) (-2 + Math.random() * 7);
    }

    float getHumidity() {
        return (float) (75 + Math.random() * 26);
    }

    float getPressure() {
        return (float) (745 + Math.random() * 26);
    }

    public void measurementsChanged() {
        notifyObservers();
    }
}

interface Observer {
    void update(float temperature, float humidity, float pressure);
    void display();
}

class CurrentConditionsDisplay implements Observer {
    private float temperature;
    private float humidity;
    private float pressure;

    public CurrentConditionsDisplay(WeatherData weatherData) {
        weatherData.registerObserver(this);
    }

    @Override
    public void update(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
    }

    @Override
    public void display() {
        System.out.printf("Temperature now: %.1fC\n", temperature);
        System.out.printf("Humidity now: %.1f", humidity); System.out.print("%\n");
        System.out.printf("Air pressure now: %.1fmm Hg\n\n", pressure);
    }
}

class PreviousConditionDisplay implements Observer {
    float temperature;
    float humidity;
    float pressure;

    public PreviousConditionDisplay(WeatherData weatherData) {
        weatherData.registerObserver(this);
    }
    public void update(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
    }

    public void display() {
        System.out.printf("Temperature yesterday: %.1fC\n", temperature);
        System.out.printf("Humidity yesterday: %.1f", humidity); System.out.print("%\n");
        System.out.printf("Air pressure yesterday: %.1fmm Hg\n\n", pressure);
    }
}

class ForecastConditionDisplay implements Observer {
    float temperature;
    float humidity;
    float pressure;

    public ForecastConditionDisplay(WeatherData weatherData) {
        weatherData.registerObserver(this);
    }
    public void update(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
    }

    public void display() {
        System.out.printf("Temperature tomorrow: %.1fC\n", temperature);
        System.out.printf("Humidity tomorrow: %.1f", humidity); System.out.print("%\n");
        System.out.printf("Air pressure tomorrow: %.1fmm Hg\n\n", pressure);
    }
}

public class Task2_Observer {
    public static void main(String[] arg) {
        WeatherData weatherData = new WeatherData();
        PreviousConditionDisplay previousConditionDisplay = new PreviousConditionDisplay(weatherData);
        CurrentConditionsDisplay currentConditionsDisplay = new CurrentConditionsDisplay(weatherData);
        ForecastConditionDisplay forecastConditionDisplay = new ForecastConditionDisplay(weatherData);
        weatherData.measurementsChanged();
    }
}