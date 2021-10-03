import javax.management.InvalidAttributeValueException;
import java.util.*;

import static java.util.Set.of;

public class SimpleGarage implements Garage {
    private TreeSet<Car> carVelocitySet;
    private TreeSet<Car> carPowerSet;
    private HashMap<Owner, HashSet<Car>> ownerCarMap;
    private HashMap<Car, Owner> carOwnerMap;
    private HashMap<String, HashSet<Car>> brandCarMap;
    private HashMap<Long, Car> idCarMap;


    public SimpleGarage() {
        Comparator<Car> velocityComparator = new Comparator<Car>() {
            @Override
            public int compare(Car x, Car y) {
                if (x.getMaxVelocity() == y.getMaxVelocity() && x.getCarId() == y.getCarId()) {
                    return 0;
                } else if (x.getMaxVelocity() > y.getMaxVelocity() || (x.getCarId() > y.getCarId() && x.getMaxVelocity() == y.getMaxVelocity())) {
                    return 1;
                } else {
                    return -1;
                }
            }
        };

        Comparator<Car> powerComparator = new Comparator<Car>() {
            @Override
            public int compare(Car x, Car y) {
                if (x.getPower() == y.getPower() && x.getCarId() == y.getCarId()) {
                    return 0;
                } else if (x.getPower() > y.getPower() || (x.getCarId() > y.getCarId() && x.getPower() == y.getPower())) {
                    return 1;
                } else {
                    return -1;
                }
            }
        };

        carVelocitySet = new TreeSet<>(velocityComparator);
        carPowerSet = new TreeSet<>(powerComparator);
        ownerCarMap = new HashMap<>();
        brandCarMap = new HashMap<>();
        idCarMap = new HashMap<>();
        carOwnerMap = new HashMap<>();
    }

    @Override
    public Collection<Owner> allCarsUniqueOwners() {
        return ownerCarMap.keySet();
    }

    @Override
    public Collection<Car> topThreeCarsByMaxVelocity() {
        return carVelocitySet.descendingSet().stream().limit(3).toList();
    }

    @Override
    public Collection<Car> allCarsOfBrand(String brand) {
        Collection<Car> cars = brandCarMap.get(brand);
        return cars != null ? cars : new ArrayList<>();
    }

    @Override
    public Collection<Car> carsWithPowerMoreThan(int power) {
        return carPowerSet.stream().filter(car -> car.getPower() > power).toList();
    }

    @Override
    public Collection<Car> allCarsOfOwner(Owner owner) {
        Collection<Car> cars = ownerCarMap.get(owner);
        return cars != null ? cars : new ArrayList<>();
    }

    @Override
    public int meanOwnersAgeOfCarBrand(String brand) {
        var ownerIds = new HashSet<Long>();
        var cars = allCarsOfBrand(brand);
        if (cars.isEmpty()) {
            return -1;
        }
        int sumAge = 0;
        for (Car car : cars) {
            if (!ownerIds.contains(car.getOwnerId())) {
                ownerIds.add(car.getOwnerId());
                sumAge += getAge(car.getOwnerId());
            }
        }

        return sumAge / ownerIds.size();
    }

    private int getAge(long ownerId) {
        for (Owner owner : ownerCarMap.keySet()) {
            if (ownerId == owner.getOwnerId()) {
                return owner.getAge();
            }
        }
        throw new InputMismatchException("owner not in List");
    }

    @Override
    public int meanCarNumberForEachOwner() {
        if (carVelocitySet.isEmpty()) {
            return -1;
        }
        int sumCarNumber = 0;
        for (Owner owner : ownerCarMap.keySet()) {
            int sumOwnerCarNumber = 0;
            for (Car car : allCarsOfOwner(owner)) {
                sumOwnerCarNumber += car.getCarId();
            }
            sumCarNumber += sumOwnerCarNumber / allCarsOfOwner(owner).size();
        }
        return sumCarNumber / ownerCarMap.keySet().size();
    }

    @Override
    public Car removeCar(long carId) {
        Car carToRemove = idCarMap.get(carId);
        if (carToRemove == null){
            return null;
        }
        carVelocitySet.remove(carToRemove);
        carPowerSet.remove(carToRemove);
        idCarMap.remove(carId);
        ownerCarMap.get(carOwnerMap.get(carToRemove)).remove(carToRemove);
        if (ownerCarMap.get(carOwnerMap.get(carToRemove)).isEmpty()){
            ownerCarMap.remove(carOwnerMap.get(carToRemove));
        }
        brandCarMap.get(carToRemove.getBrand()).remove(carToRemove);
        if (brandCarMap.get(carToRemove.getBrand()).isEmpty()){
            brandCarMap.remove(carToRemove.getBrand());
        }
        carOwnerMap.remove(carToRemove);
        return carToRemove;
    }

    @Override
    public void addCar(Car car, Owner owner) {
        if(car.getOwnerId() != owner.getOwnerId()){
            System.out.println("ownerId != owner id of car");
            return;
        }
        carVelocitySet.add(car);
        carPowerSet.add(car);
        idCarMap.put(car.getCarId(), car);
        carOwnerMap.put(car, owner);

        if (brandCarMap.containsKey(car.getBrand())) {
            brandCarMap.get(car.getBrand()).add(car);
        } else {
            brandCarMap.put(car.getBrand(), new HashSet<>(of(car)));
        }

        if (ownerCarMap.containsKey(owner)) {
            ownerCarMap.get(owner).add(car);
        } else {
            ownerCarMap.put(owner, new HashSet<>(of(car)));
        }
    }
}
