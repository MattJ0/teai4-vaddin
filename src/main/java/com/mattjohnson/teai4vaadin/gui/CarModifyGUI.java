package com.mattjohnson.teai4vaadin.gui;

import com.mattjohnson.teai4vaadin.service.CarService;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;

@SpringComponent
@UIScope
public class CarModifyGUI extends VerticalLayout {

    private CarService carService;

    @Autowired
    public CarModifyGUI(CarService carService) {
        this.carService = carService;

    }
}
