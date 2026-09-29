package Car;

public class Car {
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
            if (power <= 0) {
                throw new IllegalArgumentException("Мощность должна быть больше 0.");
            }

            if (model == null || model.trim().isEmpty()) {
                throw new IllegalArgumentException("Модель не может быть пустой.");
            }

            if (year < 1886 || year > 2036) {
                throw new IllegalArgumentException("Некорректный год производства.");
            }
        }
    }
}
