package org.l8;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class CarLogicImpl implements CarLogic {
List <Car> cars;
    public CarLogicImpl() {
        this.cars = new ArrayList<>();
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public void addCar(int id, String model, LocalDate createYear, double price, String winCode) {
        Car car = new Car(id, model, createYear, price, winCode);
        cars.add(car);
    }

    public boolean remove(int id) {
        return cars.removeIf(c -> c.getId() == id);
    }

    public boolean removeAll() {
        cars.clear();
        return true;
    }
    public void showAllCars() {
       cars.forEach(System.out::println);
    }
    @Override
    public List<Car> getThisModelCars(String model) {
        return cars.stream().filter(c -> model.equals(c.getModel())).sorted(Comparator.comparing(c->c.getCreateYear().getYear())).toList();
    }

    @Override
    public List<Car> getCarsExploitedMoreThenThisYear(int year) {
        return cars.stream().filter(c -> c.getCreateYear().getYear() > year).toList();
    }

    @Override
    public List<Car> getCarsThisYearWhichPriceMoreThen(int year, double price) {
        return cars.stream().filter(c -> year==c.getCreateYear().getYear() && c.getPrice() > price ).toList();
    }

    @Override
    public List<Car> sortCarsByPrice() {
        return cars.stream().sorted(Comparator.comparingDouble(Car::getPrice).reversed().thenComparing(c -> c.getCreateYear().getYear())).toList();
    }

    @Override
    public Set<String> modelsCarsInProgrammes() {
        return cars.stream().map(Car::getModel).collect(Collectors.toSet());
    }

    @Override
    public Map<String, List<Car>> mapModels() {
        return cars.stream().collect(Collectors.groupingBy(Car::getModel));
    }
}
