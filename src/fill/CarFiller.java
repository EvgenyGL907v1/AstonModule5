package fill;

import car.Car;

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
