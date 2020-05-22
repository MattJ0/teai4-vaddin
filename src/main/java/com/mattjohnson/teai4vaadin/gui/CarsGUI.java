package com.mattjohnson.teai4vaadin.gui;

import com.mattjohnson.teai4vaadin.model.Car;
import com.mattjohnson.teai4vaadin.service.CarService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Route("cars")
@UIScope
public class CarsGUI extends VerticalLayout {

    private static Grid<Car> grid;
    private CarService carService;
    private CarByIDGUI carByIDGUI;
    private CarByColorGUI carByColorGUI;
    private CarAddGUI carAddGUI;
    private CarUpdateGUI carUpdateGUI;
    private CarModifyGUI carModifyGUI;
    private CarRemoveGUI carRemoveGUI;

    @Autowired
    public CarsGUI(CarService carService, CarByIDGUI carByIDGUI, CarByColorGUI carByColorGUI,
                   CarAddGUI carAddGUI, CarUpdateGUI carUpdateGUI, CarModifyGUI carModifyGUI,
                   CarRemoveGUI carRemoveGUI) {
        this.carService = carService;
        this.carByIDGUI = carByIDGUI;
        this.carByColorGUI = carByColorGUI;
        this.carAddGUI = carAddGUI;
        this.carUpdateGUI = carUpdateGUI;
        this.carModifyGUI = carModifyGUI;
        this.carRemoveGUI = carRemoveGUI;
        grid = new Grid<>(Car.class);

        Optional<List<Car>> carList = carService.findAll();
        carList.ifPresent(grid::setItems);


        Tab tab1 = new Tab("Find car by ID");
        Div page1 = new Div();
        page1.add(carByIDGUI);

        Tab tab2 = new Tab("Find cars by color");
        Div page2 = new Div(carByColorGUI);

        page2.setVisible(false);

        Tab tab3 = new Tab("Add new car");
        Div page3 = new Div(carAddGUI);

        page3.setVisible(false);

        Tab tab4 = new Tab("Update car");
        Div page4 = new Div(carUpdateGUI);

        page4.setVisible(false);

        Tab tab5 = new Tab("Modify car");
        Div page5 = new Div();

        page5.setVisible(false);

        Tab tab6 = new Tab("Remove car");
        Div page6 = new Div();
        page6.add(carRemoveGUI);
        page6.setVisible(false);

        Map<Tab, Component> tabsToPages = new HashMap<>();
        tabsToPages.put(tab1, page1);
        tabsToPages.put(tab2, page2);
        tabsToPages.put(tab3, page3);
        tabsToPages.put(tab4, page4);
        tabsToPages.put(tab5, page5);
        tabsToPages.put(tab6, page6);
        Tabs tabs = new Tabs(tab1, tab2, tab3, tab4, tab5, tab6);
        Div pages = new Div(page1, page2, page3, page4, page5, page6);
        Set<Component> pagesShown = Stream.of(page1)
                .collect(Collectors.toSet());

        tabs.addSelectedChangeListener(event -> {
            pagesShown.forEach(page -> page.setVisible(false));
            pagesShown.clear();
            Component selectedPage = tabsToPages.get(tabs.getSelectedTab());
            selectedPage.setVisible(true);
            pagesShown.add(selectedPage);
        });

        Button refreshGrid = new Button("Refresh list");
        refreshGrid.addClickListener(buttonClickEvent -> {
            Optional<List<Car>> carListRefresh = carService.findAll();
            carListRefresh.ifPresent(grid::setItems);
        });

        add(tabs);
        add(pages);
        add(refreshGrid);
        add(grid);
    }

    public static Grid<Car> getGrid() {
        return grid;
    }
}
