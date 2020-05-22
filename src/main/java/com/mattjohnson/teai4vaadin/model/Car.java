package com.mattjohnson.teai4vaadin.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static com.mattjohnson.teai4vaadin.service.CarServiceImpl.countID;


public class Car {

    @NotNull
    @Min(1)
    private final long id;

    @NotNull(message = "mark cannot be null")
    @Size(min = 2)
    private String mark;


    @NotNull(message = "model cannot be null")
    @Size(min = 2)
    private String model;

    @NotNull(message = "color cannot be null")
    private Color color;


    public Car(String mark, String model, Color color) {
        this.id = countID;
        this.mark = mark;
        this.model = model;
        this.color = color;
        countID++;
    }

    public long getId() {
        return id;
    }


    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "   mark='" + mark + '\'' +
                ", model='" + model + '\'' +
                ", color=" + color;
    }
}
