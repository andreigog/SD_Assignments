package ro.utcluj.student.twiss.model.Car;

public class Car {
    private Long id;
    private String manufacturer;
    private String model;
    private int capacity;
    private int year;
    private int horsePower;

    public Car() {
    }

    public Car(String manufacturer, String model, int capacity, int year, int horsePower) {
        this.manufacturer = manufacturer;
        this.model = model;
        this.capacity = capacity;
        this.year = year;
        this.horsePower = horsePower;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getHorsePower() {
        return horsePower;
    }

    public void setHorsePower(int horsePower) {
        this.horsePower = horsePower;
    }
}
