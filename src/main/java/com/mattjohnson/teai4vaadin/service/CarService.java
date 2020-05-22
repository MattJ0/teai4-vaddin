package com.mattjohnson.teai4vaadin.service;

import com.mattjohnson.teai4vaadin.model.Car;

import java.util.List;
import java.util.Optional;

public interface CarService {

    Optional<List<Car>> findAll();

    Optional<Car> findById(long id);

    Optional<List<Car>> findByColor(String color);

    boolean addCar(Car car);

    boolean updateCar(long id, Car car);

    boolean modifyCarAttribute(long id, Car car);

    Optional<Car> removeCar(long id);


}
