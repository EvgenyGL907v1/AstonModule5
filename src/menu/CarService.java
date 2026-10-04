package menu;

import car.Car;
import carSorter.CarSorter;
import collection.CustomArrayList;
import collection.CustomCollectors;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class CarService {

    public enum SortField {
        MODEL(1),
        POWER(2),
        YEAR(3);

        private static final Map<Integer, SortField> BY_CODE =
                Arrays.stream(values())
                        .collect(Collectors.toMap(f -> f.code, f -> f));

        private final int code;
        SortField(int code) {
            this.code = code;
        }

        public static SortField fromCode(int code) {
            SortField field = BY_CODE.get(code);
            if (field == null) {
                throw new IllegalArgumentException("Неизвестное поле сортировки: " + code);
            }
            return field;
        }
    }

    private final CarSorter sorter;
    private final List<Car> cars = new CustomArrayList<>();
    private boolean created = false;

    public CarService() {
        this(new CarSorter());
    }

    public CarService(CarSorter sorter) {
        this.sorter = Objects.requireNonNull(sorter, "sorter");
    }

    public void setCars(List<Car> cars) {
        Objects.requireNonNull(cars, "cars");

        this.cars.clear();

        cars.stream()
                .map(car -> Objects.requireNonNull(car, "car"))
                .forEach(this.cars::add);

        this.created = true;
    }

    public boolean isCreated() { return created; }
    public boolean isEmpty()   { return cars.isEmpty(); }
    public int size()          { return cars.size(); }

    public List<Car> getCars() {
        return Collections.unmodifiableList(cars);
    }

    public void clear() {
        cars.clear();
        created = false;
    }

    public void sort(int fieldCode) {
        if (isEmpty()) return;
        SortField field = SortField.fromCode(fieldCode);
        switch (field) {
            case MODEL -> sorter.sortByModel(cars);
            case POWER -> sorter.sortByPower(cars);
            case YEAR  -> sorter.sortByYear(cars);
        }
    }

    public List<Car> find(String model) {
        if (isEmpty() || model == null || model.isBlank()) {
            return new CustomArrayList<>();
        }
        return cars.stream()
                .filter(car -> model.equalsIgnoreCase(car.getModel()))
                .collect(CustomCollectors.toCustomList());
    }
}