package menu;

import car.Car;
import carSorter.CarSorter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class CarService {

    public enum SortField {
        POWER(1, "по мощности"),
        MODEL(2, "по модели"),
        YEAR(3, "по году");

        private final int code;
        private final String description;

        SortField(int code, String description) {
            this.code = code;
            this.description = description;
        }

        public static SortField fromCode(int code) {
            for (SortField field : values()) {
                if (field.code == code) return field;
            }
            throw new IllegalArgumentException("Неизвестное поле сортировки: " + code);
        }
    }

    private final CarSorter sorter;
    private List<Car> cars = new ArrayList<>();
    private boolean created = false;

    public CarService() {
        this(new CarSorter());
    }

    public CarService(CarSorter sorter) {
        this.sorter = Objects.requireNonNull(sorter, "sorter");
    }

    public void setCars(List<Car> cars) {
        Objects.requireNonNull(cars, "cars");
        this.cars = new ArrayList<>(cars);
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
            case POWER -> sorter.sortByPower(cars);
            case MODEL -> sorter.sortByModel(cars);
            case YEAR  -> sorter.sortByYear(cars);
        }
    }

    public List<Car> find(String model) {
        if (isEmpty() || model == null || model.isBlank()) {
            return List.of();
        }
        return cars.stream()
                .filter(car -> car.getModel() != null)
                .filter(car -> car.getModel().equalsIgnoreCase(model))
                .toList();
    }
}