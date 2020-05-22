package com.mattjohnson.teai4vaadin.gui;

import com.mattjohnson.teai4vaadin.model.Car;
import com.mattjohnson.teai4vaadin.service.CarService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@SpringComponent
@UIScope
public class CarByIDGUI extends VerticalLayout {

    private CarService carService;

    @Autowired
    public CarByIDGUI(CarService carService) {
        this.carService = carService;

        NumberField numberFieldId = new NumberField("Type car ID");
        Button searchButton = new Button("Search");

        numberFieldId.setRequiredIndicatorVisible(true);
        numberFieldId.setMin(1);

        Dialog dialogCar = new Dialog();
        dialogCar.setWidth("400px");
        dialogCar.setHeight("150px");

        searchButton.addClickListener(buttonClickEvent -> {
            long id = numberFieldId.getValue().longValue();
            Optional<Car> car = carService.findById(id);
            dialogCar.removeAll();
            if (car.isPresent()) {
                car.ifPresent(CarsGUI.getGrid()::setItems);
            } else {
                dialogCar.add(new Label("Not found - car #" + id + "doesn't exist"));
                dialogCar.open();
            }

        });

        add(numberFieldId);
        add(searchButton);
        add(dialogCar);



    }
}
