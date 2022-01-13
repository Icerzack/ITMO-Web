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
    private double x = 1;
    private double y = 2;
    private double r = 2;

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

    public boolean validateValues(){
        boolean passed = true;

        if(!(1.0001<=getR() && getR()<=2.9999)){
            passed = false;
        }
        if(!(-3.0001<=getY() && getY()<=3.9999)){
            passed = false;
        }
        if(!(getX()==-2||getX()==-1.5||getX()==-1||getX()==-0.5||getX()==0||getX()==0.5||getX()==1)){
            passed = false;
        }
        return passed;
    }

    public void setDefaultValues(){
        setX(1.0);
        setY(2.0);
        setR(2.0);
    }
}
