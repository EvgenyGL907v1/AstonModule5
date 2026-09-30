package car;

public class Car {
    public static final int MIN_YEAR = 1886;
    public static final int MAX_YEAR = 2036;
    public static final int MIN_POWER = 1;

    private final int power;
    private final String model;
    private final int year;

    private Car(Builder builder) {
        this.power = builder.power;
        this.model = builder.model;
        this.year = builder.year;
    }

    public int getPower() {
        return power;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    @Override
    public String toString() {
        return "Автомобиль {" + "мощность=" + power +  ", модель='" + model + '\'' + ", год=" + year +'}';
    }

    public static class Builder {
        private int power;
        private String model;
        private int year;

        public Builder setPower(int power) {
            this.power = power;
            return this;
        }

        public Builder setModel(String model) {
            this.model = model;
            return this;
        }

        public Builder setYear(int year) {
            this.year = year;
            return this;
        }

        public Car build() {
            validate();

            return new Car(this);
        }

        private void validate() {
            if (power < Car.MIN_POWER) {
                throw new IllegalArgumentException("Мощность должна быть больше 0.");
            }

            if (model == null || model.trim().isEmpty()) {
                throw new IllegalArgumentException("Модель не может быть пустой.");
            }
            if (year < Car.MIN_YEAR || year > Car.MAX_YEAR) {
                throw new IllegalArgumentException("Некорректный год производства.");
            }
        }
    }
}