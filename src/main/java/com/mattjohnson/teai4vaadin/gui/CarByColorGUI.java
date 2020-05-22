package com.mattjohnson.teai4vaadin.gui;

import com.mattjohnson.teai4vaadin.model.Car;
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
public class CarByColorGUI extends VerticalLayout {

    private CarService carService;

    @Autowired
    public CarByColorGUI(CarService carService) {
        this.carService = carService;

        TextField textField = new TextField("Type color");
        Button searchButton = new Button("Search");

        textField.setRequiredIndicatorVisible(true);
        textField.setRequired(true);

        Dialog dialogCar = new Dialog();
        dialogCar.setWidth("400px");
        dialogCar.setHeight("150px");

        searchButton.addClickListener(buttonClickEvent -> {
            String color = textField.getValue();
            Optional<List<Car>> carList = carService.findByColor(color);
            dialogCar.removeAll();

            if (carList.isPresent()) {
                carList.ifPresent(CarsGUI.getGrid()::setItems);
            }
//            else {
//                dialogCar.add(new Label("Not found " + color + " cars"));
//                dialogCar.open();
//            }

        });

        add(textField);
        add(searchButton);
        add(dialogCar);
    }
}
