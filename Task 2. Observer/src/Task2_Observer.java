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
        return 5;
    }

    float getHumidity() {
        return 80;
    }

    float getPressure() {
        return 760;
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
        System.out.println("Temperature: " + temperature + "C");
        System.out.println("Humidity: " + humidity + "%");
        System.out.println("Pressure: " + pressure + "mm Hg\n");
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
        System.out.println("Temperature last week: " + (temperature - 10) + "C");
        System.out.println("Humidity last week: " + (humidity + 10) + "%");
        System.out.println("Pressure last week: " + (pressure + 15) + "mm Hg\n");
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
        System.out.println("Temperature next week: " + (temperature + 3) + "C");
        System.out.println("Humidity next week: " + (humidity + 7) + "%");
        System.out.println("Pressure next week: " + (pressure - 5) + "mm Hg\n");
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