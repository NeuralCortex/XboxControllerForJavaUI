package com.nc.xbox.custom;

import com.nc.xbox.Globals;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Polar extends AnchorPane {

    private final int OFFSET_VER = 0;
    private boolean thumb = false;

    public Polar() {
        setMinHeight(0.0);
        setMinWidth(0.0);

        widthProperty().addListener((ov, o, n) -> drawPolar());
        heightProperty().addListener((ov, o, n) -> drawPolar());
    }

    protected void drawPolar() {
        getChildren().clear();

        double width = getWidth();
        double height = getHeight();
        
        if (width <= 0 || height <= 0) {
            return;
        }

        double centerX = width / 2.0;
        double centerY = height / 2.0;

        double maxRadius = (Math.min(width, height) / 2.0);// - OFFSET_VER;
        if (maxRadius < 0) {
            maxRadius = 0;
        }

        Circle circle = new Circle();
        circle.setFill(Color.TRANSPARENT);
        circle.setStroke(Globals.COLOR_XBOX);
        circle.setCenterX(centerX);
        circle.setCenterY(centerY);
        circle.setRadius(maxRadius);

        Circle circleT = new Circle();
        if (thumb) {
            circleT.setFill(Globals.COLOR_XBOX);
            circleT.setStroke(Globals.COLOR_XBOX);
            circleT.setCenterX(centerX);
            circleT.setCenterY(centerY);
            circleT.setRadius(maxRadius - 10);
        }

        getChildren().addAll(circle, circleT);
    }

    public void drawPosition(double x, double y) {
        double magnitude = Math.sqrt(x * x + y * y);
        
        if (magnitude > 1.0) {
            x /= magnitude;
            y /= magnitude;
        }

        drawPolar();

        double width = getWidth();
        double height = getHeight();

        if (width <= 0 || height <= 0) {
            return;
        }

        double centerX = width / 2.0;
        double centerY = height / 2.0;
        double maxRadius = Math.min(width, height) / 2.0;

        double xpos = x * maxRadius;
        double ypos = y * maxRadius;

        Circle circle = new Circle();
        circle.setCenterX(centerX + xpos);
        circle.setCenterY(centerY - ypos); // Invert Y for standard math orientation
        circle.setRadius(3.0);
        circle.setFill(thumb ? Color.RED : Globals.COLOR_XBOX);

        getChildren().add(circle);
    }

    public void setThumb(boolean thumb) {
        this.thumb = thumb;
    }

}
