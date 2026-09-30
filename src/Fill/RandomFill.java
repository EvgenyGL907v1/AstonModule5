package Fill;

import Car.Car;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomFill implements FillStrategy {
    private final Random random = new Random();

    private static final String[] MODELS = {"Toyota", "Mercedes", "Lamborghini", "Lada", "Suzuki", "Mazda"};

    @Override
    public List<Car> fill(int length) {
        List<Car> result = new ArrayList<>();

        for (int i = 0; i < length; i++) {
            String model = MODELS[random.nextInt(0,6)];
            int power = random.nextInt(50, 201);
            int year = random.nextInt(1886, 2037);
            result.add(new Car.Builder().setModel(model).setPower(power).setYear(year).build());
        }
        return result;
    }
}