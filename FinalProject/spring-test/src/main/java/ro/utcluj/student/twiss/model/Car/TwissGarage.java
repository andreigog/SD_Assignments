package ro.utcluj.student.twiss.model.Car;

import ro.utcluj.student.twiss.model.Car.Car;
import ro.utcluj.student.twiss.model.Car.Garage;
import ro.utcluj.student.twiss.model.Car.Iterator;

import java.util.ArrayList;
import java.util.List;

/**
 * @author andrei_gog on 27-May-18
 */
public class TwissGarage implements Garage {
    private List<Car> carList;

    public TwissGarage() {
        carList = new ArrayList<>();
    }

    public void addCar(Car car) {
        this.carList.add(car);
    }

    public void removeChannel(Car car) {
        this.carList.remove(car);
    }

    @Override
    public Iterator getIterator() {
        return new IteratorImpl(this.carList);
    }

    private class IteratorImpl implements Iterator {

        private List<Car> cars;
        private int position;

        public IteratorImpl(List<Car> cars) {
            this.position=0;
            this.cars = cars;
        }

        @Override
        public boolean hasNext() {
            if (position<cars.size())
                return true;
            return false;
        }

        @Override
        public Car next() {
            if (this.hasNext())
                return cars.get(position++);
            else
                return null;
        }

    }
}
