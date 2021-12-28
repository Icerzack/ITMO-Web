package edu.ifmo.web.lab3;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Named
@ViewScoped
public class FormHit implements Serializable {
    private double x = 0;
    private double y = 0;
    private double r = 0;

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {return y;}

    public void setY(double y) {
        this.y = y;
    }

    public double getR(){return r;}

    public void setR(double r){this.r = r;}

}
