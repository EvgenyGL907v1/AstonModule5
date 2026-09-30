package Fill;

import Car.Car;

import java.util.List;

@FunctionalInterface
public interface FillStrategy {
    List<Car> fill(int length);
}
