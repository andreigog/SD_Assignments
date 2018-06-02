package ro.utcluj.student.twiss.model.Car;

/**
 * @author andrei_gog on 27-May-18
 */
public class TwissDeluxe extends CarBuilder {
    @Override
    public void buildManufacturer() {
        car.setManufacturer("Porsche");
    }

    @Override
    public void buildModel() {
        car.setModel("Panamera");
    }

    @Override
    public void buildYear() {
        car.setYear(2017);
    }

    @Override
    public void buildCapacity() {
        car.setCapacity(2);
    }

    @Override
    public void buildHorsePower() {
        car.setHorsePower(296);
    }
}
