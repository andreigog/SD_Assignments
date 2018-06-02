package ro.utcluj.student.twiss.model.Car;

/**
 * @author andrei_gog on 27-May-18
 */
public class TwissCasual extends CarBuilder {

    @Override
    public void buildManufacturer() {
        car.setManufacturer("Toyota");
    }

    @Override
    public void buildModel() {
        car.setModel("Yaris");
    }

    @Override
    public void buildYear() {
        car.setYear(2010);
    }

    @Override
    public void buildCapacity() {
        car.setCapacity(4);
    }

    @Override
    public void buildHorsePower() {
        car.setHorsePower(100);
    }
}
