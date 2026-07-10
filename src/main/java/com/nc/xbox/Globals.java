package com.nc.xbox;

import java.util.Locale;
import javafx.scene.paint.Color;

public class Globals {

    public static boolean DEBUG = true;

    public static final Locale DEFAULT_LOCALE = Locale.US;

    public static final double HEIGHT = 500;//900.0f;
    public static final double WIDTH = 1950;

    public static final Color COLOR_XBOX = Color.web("#107C11");

    public static final int NO_SEL_VALUE = -9999;

    public static final String BUNDLE_PATH = "com.nc.xbox.bundle.xbox";
    public static final String LOG4J2_CONFIG_PATH = System.getProperty("user.dir") + "/config/log4j2.xml";
    public static final String XML_CONFIG_PATH = System.getProperty("user.dir") + "/config/config.xml";

    public static XMLPropertyManager propman;

    static {
        propman = new XMLPropertyManager(XML_CONFIG_PATH);
    }

    //Styling
    public static final String APP_LOGO_PATH = "/images/kdf.png";
    public static final String CSS_PATH = "/com/nc/xbox/style/style.css";

    public static final String FXML_PATH = "/com/nc/xbox/fxml/";

    public static final String FXML_MAIN_PATH = FXML_PATH + "main_app.fxml";
    public static final String FXML_PLAYER_PATH = FXML_PATH + "player.fxml";
}
