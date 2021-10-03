import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class GarageTest {
    @Test
    public void testAllCarsUniqueOwnersEmpty(){
        Garage garage = new SimpleGarage();
        assertTrue(garage.allCarsUniqueOwners().isEmpty());
    }

    @Test
    public void testAllCarsUniqueOwnersOne(){
        Garage garage = new SimpleGarage();
        Owner owner1 = new Owner(1,"Ilon", "Mask", 40);
        Car car1 = new Car(1, "Tesla", "x", 250, 220, 1);
        Car car2 = new Car(2, "Tesla", "s", 230, 210, 1);
        garage.addCar(car1, owner1);
        garage.addCar(car2, owner1);
        assertTrue(garage.allCarsUniqueOwners().contains(owner1));
        assertEquals(1, garage.allCarsUniqueOwners().size());
    }

    @Test
    public void testAllCarsUniqueOwnersTwo(){
        Garage garage = new SimpleGarage();
        Owner owner1 = new Owner(1,"Ilon", "Mask", 40);
        Owner owner2 = new Owner(2,"Mark", "Mask", 30);
        Car car1 = new Car(1, "Tesla", "x", 250, 220, 1);
        Car car2 = new Car(2, "Mers", "s", 230, 250, 1);
        Car car3 = new Car(3, "Tesla", "m", 210, 230, 2);
        Car car4 = new Car(4, "Toyota", "l", 180, 110, 2);
        garage.addCar(car1, owner1);
        garage.addCar(car2, owner1);
        garage.addCar(car3, owner2);
        garage.addCar(car4, owner2);
        assertTrue(garage.allCarsUniqueOwners().contains(owner1));
        assertTrue(garage.allCarsUniqueOwners().contains(owner2));
        assertEquals(2, garage.allCarsUniqueOwners().size());
    }

    @Test
    public void testTopThreeCarsByMaxVelocityEmpty(){
        Garage garage = new SimpleGarage();
        assertTrue(garage.topThreeCarsByMaxVelocity().isEmpty());
    }

    @Test
    public void testTopThreeCarsByMaxVelocityTwoCars(){
        Garage garage = new SimpleGarage();
        Owner owner1 = new Owner(1,"Ilon", "Mask", 40);
        Car car1 = new Car(1, "Tesla", "x", 250, 220, 1);
        Car car2 = new Car(2, "Tesla", "s", 230, 210, 1);
        garage.addCar(car1, owner1);
        garage.addCar(car2, owner1);
        assertTrue(garage.topThreeCarsByMaxVelocity().contains(car1));
        assertTrue(garage.topThreeCarsByMaxVelocity().contains(car2));
        assertEquals(2, garage.topThreeCarsByMaxVelocity().size());
    }

    @Test
    public void testTopThreeCarsByMaxVelocityMoreThan3(){
        Garage garage = new SimpleGarage();
        Owner owner1 = new Owner(1,"Ilon", "Mask", 40);
        Owner owner2 = new Owner(2,"Mark", "Mask", 30);
        Car car1 = new Car(1, "Tesla", "x", 250, 220, 1);
        Car car2 = new Car(2, "Mers", "s", 230, 250, 1);
        Car car3 = new Car(3, "Tesla", "m", 210, 230, 2);
        Car car4 = new Car(4, "Toyota", "l", 180, 110, 2);
        garage.addCar(car1, owner1);
        garage.addCar(car2, owner1);
        garage.addCar(car3, owner2);
        garage.addCar(car4, owner2);
        assertTrue(garage.topThreeCarsByMaxVelocity().contains(car1));
        assertTrue(garage.topThreeCarsByMaxVelocity().contains(car2));
        assertTrue(garage.topThreeCarsByMaxVelocity().contains(car3));
        assertEquals(3, garage.topThreeCarsByMaxVelocity().size());
    }

    @Test
    public void testTopThreeCarsByMaxVelocityMoreThan3WithSameVelocity(){
        Garage garage = new SimpleGarage();
        Owner owner1 = new Owner(1,"Ilon", "Mask", 40);
        Owner owner2 = new Owner(2,"Mark", "Mask", 30);
        Car car1 = new Car(1, "Tesla", "x", 250, 220, 1);
        Car car2 = new Car(2, "Mers", "s", 230, 250, 1);
        Car car3 = new Car(3, "Tesla", "m", 210, 230, 2);
        Car car4 = new Car(4, "Toyota", "l", 210, 110, 2);
        garage.addCar(car1, owner1);
        garage.addCar(car2, owner1);
        garage.addCar(car3, owner2);
        garage.addCar(car4, owner2);
        assertTrue(garage.topThreeCarsByMaxVelocity().contains(car1));
        assertTrue(garage.topThreeCarsByMaxVelocity().contains(car2));
        assertTrue(garage.topThreeCarsByMaxVelocity().contains(car4));
        assertEquals(3, garage.topThreeCarsByMaxVelocity().size());
    }

    @Test
    public void testAllCarsOfBrandEmpty(){
        Garage garage = new SimpleGarage();
        assertTrue(garage.allCarsOfBrand("tesla").isEmpty());
    }

    @Test
    public void testAllCarsOfBrandTwoCars(){
        Garage garage = new SimpleGarage();
        Owner owner1 = new Owner(1,"Ilon", "Mask", 40);
        Car car1 = new Car(1, "Tesla", "x", 250, 220, 1);
        Car car2 = new Car(2, "Tesla", "s", 230, 210, 1);
        garage.addCar(car1, owner1);
        garage.addCar(car2, owner1);
        assertTrue(garage.allCarsOfBrand("Tesla").contains(car1));
        assertTrue(garage.allCarsOfBrand("Tesla").contains(car2));
        assertEquals(2, garage.allCarsOfBrand("Tesla").size());
    }

    @Test
    public void testAllCarsOfBrandMoreThan3(){
        Garage garage = new SimpleGarage();
        Owner owner1 = new Owner(1,"Ilon", "Mask", 40);
        Owner owner2 = new Owner(2,"Mark", "Mask", 30);
        Car car1 = new Car(1, "Tesla", "x", 250, 220, 1);
        Car car2 = new Car(2, "Mers", "s", 230, 250, 1);
        Car car3 = new Car(3, "Tesla", "m", 210, 230, 2);
        Car car4 = new Car(4, "Toyota", "l", 180, 110, 2);
        garage.addCar(car1, owner1);
        garage.addCar(car2, owner1);
        garage.addCar(car3, owner2);
        garage.addCar(car4, owner2);
        assertTrue(garage.allCarsOfBrand("Tesla").contains(car1));
        assertTrue(garage.allCarsOfBrand("Tesla").contains(car3));
        assertTrue(garage.allCarsOfBrand("Mers").contains(car2));
        assertTrue(garage.allCarsOfBrand("Toyota").contains(car4));
        assertEquals(1, garage.allCarsOfBrand("Toyota").size());
        assertEquals(2, garage.allCarsOfBrand("Tesla").size());
        assertEquals(1, garage.allCarsOfBrand("Mers").size());
    }

    @Test
    public void testCarsWithPowerMoreThanEmpty(){
        Garage garage = new SimpleGarage();
        assertTrue(garage.carsWithPowerMoreThan(0).isEmpty());
    }

    @Test
    public void testCarsWithPowerMoreThanTwoCars(){
        Garage garage = new SimpleGarage();
        Owner owner1 = new Owner(1,"Ilon", "Mask", 40);
        Car car1 = new Car(1, "Tesla", "x", 250, 220, 1);
        Car car2 = new Car(2, "Tesla", "s", 230, 210, 1);
        garage.addCar(car1, owner1);
        garage.addCar(car2, owner1);
        assertTrue(garage.carsWithPowerMoreThan(210).contains(car1));
        assertFalse(garage.carsWithPowerMoreThan(210).contains(car2));
        assertEquals(1, garage.carsWithPowerMoreThan(210).size());
    }

    @Test
    public void testCarsWithPowerMoreThanMoreThan3(){
        Garage garage = new SimpleGarage();
        Owner owner1 = new Owner(1,"Ilon", "Mask", 40);
        Owner owner2 = new Owner(2,"Mark", "Mask", 30);
        Car car1 = new Car(1, "Tesla", "x", 250, 220, 1);
        Car car2 = new Car(2, "Mers", "s", 230, 250, 1);
        Car car3 = new Car(3, "Tesla", "m", 210, 230, 2);
        Car car4 = new Car(4, "Toyota", "l", 180, 110, 2);
        garage.addCar(car1, owner1);
        garage.addCar(car2, owner1);
        garage.addCar(car3, owner2);
        garage.addCar(car4, owner2);
        assertTrue(garage.carsWithPowerMoreThan(200).contains(car1));
        assertTrue(garage.carsWithPowerMoreThan(200).contains(car2));
        assertTrue(garage.carsWithPowerMoreThan(200).contains(car3));
        assertEquals(3, garage.carsWithPowerMoreThan(200).size());
    }

    @Test
    public void testCarsWithPowerMoreThanWithSamePower(){
        Garage garage = new SimpleGarage();
        Owner owner1 = new Owner(1,"Ilon", "Mask", 40);
        Owner owner2 = new Owner(2,"Mark", "Mask", 30);
        Car car1 = new Car(1, "Tesla", "x", 250, 220, 1);
        Car car2 = new Car(2, "Mers", "s", 230, 250, 1);
        Car car3 = new Car(3, "Tesla", "m", 210, 220, 2);
        Car car4 = new Car(4, "Toyota", "l", 210, 110, 2);
        garage.addCar(car1, owner1);
        garage.addCar(car2, owner1);
        garage.addCar(car3, owner2);
        garage.addCar(car4, owner2);
        assertTrue(garage.carsWithPowerMoreThan(220).contains(car2));
        assertTrue(garage.carsWithPowerMoreThan(200).contains(car2));
        assertTrue(garage.carsWithPowerMoreThan(200).contains(car3));
        assertEquals(1, garage.carsWithPowerMoreThan(220).size());
        assertEquals(3, garage.carsWithPowerMoreThan(200).size());
    }

    @Test
    public void testAllCarsOfOwnerEmpty(){
        Garage garage = new SimpleGarage();
        Owner owner1 = new Owner(1,"Ilon", "Mask", 40);
        assertTrue(garage.allCarsOfOwner(owner1).isEmpty());
    }

    @Test
    public void testAllCarsOfOwnerTwoCars(){
        Garage garage = new SimpleGarage();
        Owner owner1 = new Owner(1,"Ilon", "Mask", 40);
        Car car1 = new Car(1, "Tesla", "x", 250, 220, 1);
        Car car2 = new Car(2, "Tesla", "s", 230, 210, 1);
        garage.addCar(car1, owner1);
        garage.addCar(car2, owner1);
        assertTrue(garage.allCarsOfOwner(owner1).contains(car1));
        assertTrue(garage.allCarsOfOwner(owner1).contains(car2));
        assertEquals(2, garage.allCarsOfOwner(owner1).size());
    }

    @Test
    public void testAllCarsOfOwnerMoreThan3(){
        Garage garage = new SimpleGarage();
        Owner owner1 = new Owner(1,"Ilon", "Mask", 40);
        Owner owner2 = new Owner(2,"Mark", "Mask", 30);
        Owner owner3 = new Owner(3,"John", "Mask", 20);
        Car car1 = new Car(1, "Tesla", "x", 250, 220, 1);
        Car car2 = new Car(2, "Mers", "s", 230, 250, 1);
        Car car3 = new Car(3, "Tesla", "m", 210, 230, 2);
        Car car4 = new Car(4, "Toyota", "l", 180, 110, 3);
        garage.addCar(car1, owner1);
        garage.addCar(car2, owner1);
        garage.addCar(car3, owner2);
        garage.addCar(car4, owner3);
        assertTrue(garage.allCarsOfOwner(owner1).contains(car1));
        assertTrue(garage.allCarsOfOwner(owner1).contains(car2));
        assertTrue(garage.allCarsOfOwner(owner2).contains(car3));
        assertTrue(garage.allCarsOfOwner(owner3).contains(car4));
        assertEquals(2, garage.allCarsOfOwner(owner1).size());
        assertEquals(1, garage.allCarsOfOwner(owner2).size());
        assertEquals(1, garage.allCarsOfOwner(owner3).size());
    }

    @Test
    public void testMeanOwnersAgeOfCarBrandEmpty(){
        Garage garage = new SimpleGarage();
        assertEquals(-1, garage.meanOwnersAgeOfCarBrand("Tesla"));
    }

    @Test
    public void testMeanOwnersAgeOfCarBrandOneOwner(){
        Garage garage = new SimpleGarage();
        Owner owner1 = new Owner(1,"Ilon", "Mask", 40);
        Car car1 = new Car(1, "Tesla", "x", 250, 220, 1);
        Car car2 = new Car(2, "Tesla", "s", 230, 210, 1);
        garage.addCar(car1, owner1);
        garage.addCar(car2, owner1);
        assertEquals(40, garage.meanOwnersAgeOfCarBrand("Tesla"));
    }

    @Test
    public void testMeanOwnersAgeOfCarBrandMoreThan3(){
        Garage garage = new SimpleGarage();
        Owner owner1 = new Owner(1,"Ilon", "Mask", 40);
        Owner owner2 = new Owner(2,"Mark", "Mask", 30);
        Owner owner3 = new Owner(3,"John", "Mask", 20);
        Car car1 = new Car(1, "Tesla", "x", 250, 220, 1);
        Car car2 = new Car(2, "Mers", "s", 230, 250, 1);
        Car car5 = new Car(5, "Tesla", "p", 230, 250, 3);
        Car car3 = new Car(3, "Tesla", "m", 210, 230, 2);
        Car car6 = new Car(6, "Mers", "200", 210, 230, 2);
        Car car4 = new Car(4, "Toyota", "l", 180, 110, 3);
        garage.addCar(car1, owner1);
        garage.addCar(car2, owner1);
        garage.addCar(car3, owner2);
        garage.addCar(car6, owner2);
        garage.addCar(car4, owner3);
        garage.addCar(car5, owner3);
        assertEquals(30, garage.meanOwnersAgeOfCarBrand("Tesla"));
        assertEquals(35, garage.meanOwnersAgeOfCarBrand("Mers"));
        assertEquals(20, garage.meanOwnersAgeOfCarBrand("Toyota"));
    }

    @Test
    public void testMeanCarNumberForEachOwnerEmpty(){
        Garage garage = new SimpleGarage();
        assertEquals(-1, garage.meanCarNumberForEachOwner());
    }

    @Test
    public void testMeanCarNumberForEachOwnerOneOwner(){
        Garage garage = new SimpleGarage();
        Owner owner1 = new Owner(1,"Ilon", "Mask", 40);
        Car car1 = new Car(1, "Tesla", "x", 250, 220, 1);
        Car car2 = new Car(3, "Tesla", "s", 230, 210, 1);
        garage.addCar(car1, owner1);
        garage.addCar(car2, owner1);
        assertEquals(2, garage.meanCarNumberForEachOwner());
    }

    @Test
    public void testMeanCarNumberForEachOwnerThan3(){
        Garage garage = new SimpleGarage();
        Owner owner1 = new Owner(1,"Ilon", "Mask", 40);
        Owner owner2 = new Owner(2,"Mark", "Mask", 30);
        Owner owner3 = new Owner(3,"John", "Mask", 20);
        Car car1 = new Car(1, "Tesla", "x", 250, 220, 1);
        Car car2 = new Car(11, "Mers", "s", 230, 250, 1);
        Car car5 = new Car(5, "Tesla", "p", 230, 250, 3);
        Car car3 = new Car(14, "Tesla", "m", 210, 230, 2);
        Car car6 = new Car(6, "Mers", "200", 210, 230, 2);
        Car car4 = new Car(13, "Toyota", "l", 180, 110, 3);
        garage.addCar(car1, owner1);
        garage.addCar(car2, owner1);
        garage.addCar(car3, owner2);
        garage.addCar(car6, owner2);
        garage.addCar(car4, owner3);
        garage.addCar(car5, owner3);
        assertEquals(8, garage.meanCarNumberForEachOwner());
    }

    @Test
    public void testRemoveCarEmpty(){
        Garage garage = new SimpleGarage();
        assertNull(garage.removeCar(1));
    }

    @Test
    public void testRemoveCarOneCar(){
        Garage garage = new SimpleGarage();
        Owner owner1 = new Owner(1,"Ilon", "Mask", 40);
        Car car1 = new Car(1, "Tesla", "x", 250, 220, 1);
        garage.addCar(car1, owner1);
        assertTrue( garage.topThreeCarsByMaxVelocity().contains(car1));
        assertEquals(car1, garage.removeCar(1));
        assertFalse(garage.topThreeCarsByMaxVelocity().contains(car1));
    }

    @Test
    public void testAddRemoveAddRemoveMultipleCar(){
        Garage garage = new SimpleGarage();
        Owner owner1 = new Owner(1,"Ilon", "Mask", 40);
        Owner owner2 = new Owner(2,"Mark", "Mask", 30);
        Owner owner3 = new Owner(3,"John", "Mask", 20);
        Car car1 = new Car(1, "Tesla", "x", 250, 220, 1);
        Car car2 = new Car(11, "Mers", "s", 230, 250, 1);
        Car car5 = new Car(5, "Tesla", "p", 230, 250, 3);
        Car car3 = new Car(14, "Tesla", "m", 210, 230, 2);
        Car car6 = new Car(6, "Mers", "200", 210, 230, 2);
        Car car4 = new Car(13, "Toyota", "l", 180, 110, 3);
        garage.addCar(car1, owner1);
        garage.addCar(car2, owner1);
        garage.addCar(car3, owner2);
        garage.addCar(car6, owner2);
        garage.addCar(car4, owner3);
        garage.addCar(car5, owner3);

        assertTrue(garage.allCarsOfOwner(owner1).contains(car1));
        assertTrue(garage.topThreeCarsByMaxVelocity().contains(car1));
        assertTrue(garage.carsWithPowerMoreThan(200).contains(car1));
        assertEquals(2, garage.allCarsOfOwner(owner1).size());
        assertEquals(2, garage.allCarsOfOwner(owner2).size());
        assertEquals(2, garage.allCarsOfOwner(owner3).size());

        garage.removeCar(1);
        assertFalse(garage.allCarsOfOwner(owner1).contains(car1));
        assertEquals(1, garage.allCarsOfOwner(owner1).size());
        assertEquals(2, garage.allCarsOfOwner(owner2).size());
        assertEquals(2, garage.allCarsOfOwner(owner3).size());
        assertFalse(garage.topThreeCarsByMaxVelocity().contains(car1));
        assertFalse(garage.carsWithPowerMoreThan(200).contains(car1));

        garage.addCar(car1, owner1);
        assertTrue(garage.allCarsOfOwner(owner1).contains(car1));
        assertTrue(garage.topThreeCarsByMaxVelocity().contains(car1));
        assertTrue(garage.carsWithPowerMoreThan(200).contains(car1));
        assertEquals(2, garage.allCarsOfOwner(owner1).size());
        assertEquals(2, garage.allCarsOfOwner(owner2).size());
        assertEquals(2, garage.allCarsOfOwner(owner3).size());

        garage.removeCar(1);
        assertFalse(garage.allCarsOfOwner(owner1).contains(car1));
        assertEquals(1, garage.allCarsOfOwner(owner1).size());
        assertEquals(2, garage.allCarsOfOwner(owner2).size());
        assertEquals(2, garage.allCarsOfOwner(owner3).size());
        assertFalse(garage.topThreeCarsByMaxVelocity().contains(car1));
        assertFalse(garage.carsWithPowerMoreThan(200).contains(car1));
    }
}
