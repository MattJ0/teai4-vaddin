package com.mattjohnson.teai4vaadin.gui;

import com.mattjohnson.teai4vaadin.model.Car;
import com.mattjohnson.teai4vaadin.model.Color;
import com.mattjohnson.teai4vaadin.service.CarService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@SpringComponent
@UIScope
public class CarAddGUI extends VerticalLayout {

    private CarService carService;

    @Autowired
    public CarAddGUI(CarService carService) {
        this.carService = carService;

        TextField markField = new TextField("Type mark");
        TextField modelField = new TextField("Type model");
        TextField colorField = new TextField("Type color");
        Button addButton = new Button("Add car");

        Dialog dialogCar = new Dialog();
        dialogCar.setWidth("400px");
        dialogCar.setHeight("150px");

        addButton.addClickListener(buttonClickEvent -> {
           String mark = markField.getValue();
           String model = modelField.getValue();
           Color color = null;


           try{
               color = Color.valueOf(colorField.getValue().toLowerCase());
               carService.addCar(new Car(mark, model, color));
           }
           catch(Exception e){
               dialogCar.removeAll();
               dialogCar.add("color out of range");
               dialogCar.open();
           }


           Optional<List<Car>> carList = carService.findAll();
           carList.ifPresent(CarsGUI.getGrid()::setItems);

        });


        add(markField);
        add(modelField);
        add(colorField);
        add(addButton);
    }
}
