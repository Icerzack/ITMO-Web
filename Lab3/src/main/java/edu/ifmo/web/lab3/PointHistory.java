package edu.ifmo.web.lab3;

import org.primefaces.PrimeFaces;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.html.HtmlInputText;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Named
@ApplicationScoped
public class PointHistory implements Serializable {
    @Inject
    DataBaseManager dataBaseManager;
    private List<PointResults> pointResultsList;

    @PostConstruct
    public void initializeHits() {
        pointResultsList = dataBaseManager.getHits();
    }

    public void updateAll(){
        formhit.setDefaultValues();
        dataBaseManager.delBase();
        initializeHits();
        addStoredHitsToCanvas();
        formhit.setDefaultValues();
    }

    public List<PointResults> getHitResultList() {
        return pointResultsList;
    }

    @Inject FormHit formhit;
    public void addFromForm() {
        if(formhit.validateValues()){
            double x = formhit.getX();
            double y = formhit.getY();
            double r = formhit.getR();
            addHits(calculateHit(x,y,r));
        }
    }

    private PointResults calculateHit(double x, double y, double radius) {
        return new PointResults(x, y, radius, doesItHit(x, y, radius));
    }

    private boolean doesItHit(double x, double y, double radius) {
        if (x <= 0 && y <= 0 && y >= radius/2 - x - radius) {
            return true;
        }
        if (Math.pow(x, 2) + Math.pow(y, 2) <= Math.pow(radius/2, 2) && x >= 0 && y >= 0) {
            return true;
        }
        return -radius/2 <= x && x <= 0 && 0 <= y && y <= radius;
    }

    @Inject ChartHit chartHit;
    public void addFromChart() {
        if(1.0001<=formhit.getR() && formhit.getR()<=3.9999){
            PointResults pointResults = calculateHit(chartHit.getX(), chartHit.getY(), chartHit.getR());
            addHits(pointResults);
        }
    }

    private void addHits(PointResults hits) {
        if (dataBaseManager.addHits(hits)) {
            pointResultsList.add(hits);
            addHitsToCanvas(Collections.singletonList(hits));
        }
    }

    public void addStoredHitsToCanvas() {
        addHitsToCanvas(pointResultsList);
    }

    private void addHitsToCanvas(List<PointResults> hits) {
        String json = hits.stream()
            .map(hit -> "{" +
                " x: " + hit.getX() + "," +
                " y: " + hit.getY() + "," +
                " r: " + hit.getR() + "," +
                " doesHit: " + hit.isDoesHit() + " }"
            )
            .collect(Collectors.joining(", ", "[", "]"));
        PrimeFaces.current().executeScript("addHits(" + json + ")");
    }
}
