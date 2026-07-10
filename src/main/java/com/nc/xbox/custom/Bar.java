package com.nc.xbox.custom;

import com.nc.xbox.Globals;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class Bar extends AnchorPane {

    private final int OFFSET_HOR = 5;
    private final int OFFSET_VER = 5;
    private int percent = 0;

    public Bar() {
        setMinHeight(0);
        widthProperty().addListener((ov, o, n) -> drawBar());
        heightProperty().addListener((ov, o, n) -> drawBar());
    }

    private void drawBar() {
        getChildren().clear();

        double height = getHeight();
        // Prevent drawing if the height hasn't been initialized yet
        if (height <= 0) {
            return;
        }

        Rectangle rect = new Rectangle(30, height);
        rect.setFill(Color.TRANSPARENT);
        rect.setStroke(Globals.COLOR_XBOX);

        getChildren().addAll(rect);
    }

    public void drawLevel(double value) {
        drawBar();

        double totalHeight = getHeight();
        double rectHeight = totalHeight * value;
        percent = (int) (value * 100.0);

        if (rectHeight < 0) {
            return;
        }

        Rectangle rect = new Rectangle(30, rectHeight);

        rect.setY(totalHeight - rectHeight);

        rect.setFill(Globals.COLOR_XBOX);
        rect.setStroke(Color.TRANSPARENT);

        Text text = new Text(percent + "%");
        text.setTextAlignment(TextAlignment.CENTER);
        text.setX(2);
        text.setY(totalHeight / 2.0);

        getChildren().addAll(rect, text);
    }
}
