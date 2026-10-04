package Java5.src.fill;

import Java5.src.Car.Car;

import java.util.List;

public class CarFiller {
    private FillStrategy strategy;

    public CarFiller(FillStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(FillStrategy strategy) {
        this.strategy = strategy;
    }

    public List<Car> fill(int length) {
        return strategy.fill(length);
    }
}
