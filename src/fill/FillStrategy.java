package Java5.src.fill;

import Java5.src.Car.Car;

import java.util.List;

@FunctionalInterface
public interface FillStrategy {
    List<Car> fill(int length);
}
