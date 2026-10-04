package Java5.src.fill;

import Java5.src.Car.Car;
import Java5.src.collection.CustomCollectors;

import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class RandomFill implements FillStrategy {
    private final Random random = new Random();

    private static final String[] MODELS = {"Toyota", "Mercedes", "Lamborghini", "Lada", "Suzuki", "Mazda"};

    private Car randomCar() {
        String model = MODELS[random.nextInt(MODELS.length)];

        int power = random.nextInt(Java5.src.Car.MIN_POWER, 201);

        int year = random.nextInt(
                Java5.src.Car.MIN_YEAR,
                Java5.src.Car.MAX_YEAR + 1
        );

        return new Java5.src.Car.Builder()
                .setModel(model)
                .setPower(power)
                .setYear(year)
                .build();
    }

    @Override
    public List<Car> fill(int length) {
        if (length < 0) {
            throw new IllegalArgumentException(
                    "length must be >= 0, was " + length
            );
        }

        return IntStream.range(0, length)
                .mapToObj(i -> randomCar())
                .collect(CustomCollectors.toCustomList());
    }
}