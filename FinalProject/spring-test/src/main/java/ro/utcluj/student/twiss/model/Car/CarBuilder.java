package ro.utcluj.student.twiss.model.Car;

/**
 * @author andrei_gog on 27-May-18
 */
abstract class CarBuilder {
    protected Car car;
    public Car getCar(){
        return this.car;
    }
    public void createNewCar(){
        this.car=new Car();
    }
    public abstract void buildManufacturer();
    public abstract void buildModel();
    public abstract void buildYear();
    public abstract void buildCapacity();
    public abstract void buildHorsePower();
}
