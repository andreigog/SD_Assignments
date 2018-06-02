package ro.utcluj.student.twiss.model.Car;

/**
 * @author andrei_gog on 27-May-18
 */
public class TwissLarge extends CarBuilder {
    @Override
    public void buildManufacturer() {
        car.setManufacturer("Mercedes-Benz");
    }

    @Override
    public void buildModel() {
        car.setModel("Vito");
    }

    @Override
    public void buildYear() {
        car.setYear(2012);
    }

    @Override
    public void buildCapacity() {
        car.setCapacity(8);
    }

    @Override
    public void buildHorsePower() {
        car.setHorsePower(70);
    }
}
