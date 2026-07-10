package com.nc.xbox.controller;

import com.nc.xbox.XboxController;
import com.nc.xbox.custom.Bar;
import com.nc.xbox.custom.Polar;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.util.Duration;
import org.girod.javafx.svgimage.SVGLoader;

public class PlayerController implements Initializable {

    @FXML
    private GridPane gridLS;
    @FXML
    private GridPane gridRS;
    @FXML
    private BorderPane borderPaneControls;
    @FXML
    private Button btnB;
    @FXML
    private Button btnA;
    @FXML
    private Button btnX;
    @FXML
    private Button btnY;
    @FXML
    private Button btnRT;
    @FXML
    private Button btnRB;
    @FXML
    private Button btnLT;
    @FXML
    private Button btnLB;
    @FXML
    private Button btnMenu;
    @FXML
    private Button btnView;
    @FXML
    private Button dUp;
    @FXML
    private Button dRight;
    @FXML
    private Button dDown;
    @FXML
    private Button dLeft;
    @FXML
    private ImageView logo;
    @FXML
    private Label lbPlayer;

    private Polar polarLS;
    private Polar polarRS;

    private Bar barR;
    private Bar barL;

    private HashMap<Integer, Button> buttonMap = new HashMap<>();

    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        init(bundle);
    }

    private void init(ResourceBundle bundle) {
        btnX.setGraphic(loadSVG("Xbox_button_X.svg"));
        btnY.setGraphic(loadSVG("Xbox_button_Y.svg"));
        btnA.setGraphic(loadSVG("Xbox_button_A.svg"));
        btnB.setGraphic(loadSVG("Xbox_button_B.svg"));

        buttonMap.put(XboxController.Button.B.getBitmask(), btnB);
        buttonMap.put(XboxController.Button.A.getBitmask(), btnA);
        buttonMap.put(XboxController.Button.X.getBitmask(), btnX);
        buttonMap.put(XboxController.Button.Y.getBitmask(), btnY);
        buttonMap.put(XboxController.Button.RIGHT_SHOULDER.getBitmask(), btnRB);
        buttonMap.put(XboxController.Button.LEFT_SHOULDER.getBitmask(), btnLB);
        buttonMap.put(XboxController.Button.DPAD_UP.getBitmask(), dUp);
        buttonMap.put(XboxController.Button.DPAD_RIGHT.getBitmask(), dRight);
        buttonMap.put(XboxController.Button.DPAD_DOWN.getBitmask(), dDown);
        buttonMap.put(XboxController.Button.DPAD_LEFT.getBitmask(), dLeft);
        buttonMap.put(XboxController.Button.START.getBitmask(), btnMenu);
        buttonMap.put(XboxController.Button.BACK.getBitmask(), btnView);
        buttonMap.put(XboxController.Button.RIGHT_THUMB.getBitmask(), new Button());
        buttonMap.put(XboxController.Button.LEFT_THUMB.getBitmask(), new Button());

        Image img = new Image(getClass().getResourceAsStream("/images/xbox_logo.png"), 100, 100, true, true);
        logo.setImage(img);

        ColorAdjust colorAdjust = new ColorAdjust();
        logo.setEffect(colorAdjust);

        Timeline brightnessPulse = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(colorAdjust.brightnessProperty(), 0.0)),
                new KeyFrame(Duration.seconds(1.0), new KeyValue(colorAdjust.brightnessProperty(), 0.4))
        );
        brightnessPulse.setAutoReverse(true);
        brightnessPulse.setCycleCount(Animation.INDEFINITE);
        brightnessPulse.play();

        ScaleTransition sizePulse = new ScaleTransition(Duration.seconds(1.0), logo);
        sizePulse.setToX(1.2);
        sizePulse.setToY(1.2);
        sizePulse.setAutoReverse(true);
        sizePulse.setCycleCount(Animation.INDEFINITE);
        sizePulse.play();

        barL = new Bar();
        barR = new Bar();

        borderPaneControls.setLeft(barL);
        borderPaneControls.setRight(barR);

        polarLS = new Polar();
        gridLS.add(polarLS, 1, 1);

        polarRS = new Polar();
        gridRS.add(polarRS, 1, 1);

        GridPane.setHgrow(polarLS, Priority.ALWAYS);
        GridPane.setVgrow(polarLS, Priority.ALWAYS);

        GridPane.setHgrow(polarRS, Priority.ALWAYS);
        GridPane.setVgrow(polarRS, Priority.ALWAYS);
    }

    private Node loadSVG(String fileName) {
        URL resourceUrl = getClass().getResource("/images/" + fileName);

        if (resourceUrl != null) {
            Node svgFromResource = SVGLoader.load(resourceUrl);

            svgFromResource.setScaleX(1.0);
            svgFromResource.setScaleY(1.0);

            Group scaledGroup = new Group(svgFromResource);

            return scaledGroup;
        }

        return null;
    }

    public Button getBtnRT() {
        return btnRT;
    }

    public Button getBtnLT() {
        return btnLT;
    }

    public Bar getBarR() {
        return barR;
    }

    public Bar getBarL() {
        return barL;
    }

    public Polar getPolarLS() {
        return polarLS;
    }

    public Polar getPolarRS() {
        return polarRS;
    }

    public HashMap<Integer, Button> getButtonMap() {
        return buttonMap;
    }

    public Label getLbPlayer() {
        return lbPlayer;
    }

}
