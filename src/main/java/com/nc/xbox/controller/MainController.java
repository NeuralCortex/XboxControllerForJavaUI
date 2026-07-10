package com.nc.xbox.controller;

import com.nc.xbox.Globals;
import com.nc.xbox.Loader;
import com.nc.xbox.XboxController;
import com.nc.xbox.tools.HelperFunctions;
import java.net.URL;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MainController implements Initializable {

    @FXML
    private BorderPane borderPane;
    @FXML
    private Label lbStatus;
    @FXML
    private HBox hboxStatus;
    @FXML
    private Label lbInfo;
    @FXML
    private Menu menuFile;
    @FXML
    private Menu menuHelp;
    @FXML
    private MenuItem miClose;
    @FXML
    private MenuItem miAbout;
    @FXML
    private MenuBar menuBar;
    @FXML
    private SplitPane splitPane;

    private static final Logger _log = LogManager.getLogger(MainController.class);
    private final Stage stage;

    public MainController(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void initialize(URL location, ResourceBundle bundle) {
        init(bundle);

        borderPane.setPrefSize(Globals.WIDTH, Globals.HEIGHT);

        miClose.setOnAction(e -> {
            System.exit(0);
        });

        miAbout.setOnAction(e -> {
            showAboutDlg(bundle);
        });

    }

    private void showAboutDlg(ResourceBundle bundle) {
        HelperFunctions.showAlertDialog(bundle, Alert.AlertType.INFORMATION, bundle.getString("app.name") + "\n" + MessageFormat.format(bundle.getString("app.about"), LocalDate.now().getYear()));
    }

    private void init(ResourceBundle bundle) {
        hboxStatus.getStyleClass().add("fg");

        menuFile.setText(bundle.getString("menu.file"));
        menuHelp.setText(bundle.getString("menu.help"));

        miAbout.setText(bundle.getString("mi.about"));
        miClose.setText(bundle.getString("mi.close"));

        String programmer = bundle.getString("app.about");
        lbInfo.setText(MessageFormat.format(programmer, LocalDate.now().getYear()));

        PlayerController plc1 = new PlayerController();
        Node nodeLeft = HelperFunctions.loadFxmlNode(bundle, Globals.FXML_PLAYER_PATH, plc1);
        ((BorderPane) nodeLeft).setPadding(new Insets(0, 10, 0, 0));

        PlayerController plc2 = new PlayerController();
        Node nodeRight = HelperFunctions.loadFxmlNode(bundle, Globals.FXML_PLAYER_PATH, plc2);
        ((BorderPane) nodeRight).setPadding(new Insets(0, 0, 0, 10));

        splitPane.getItems().addAll(nodeLeft, nodeRight);

        if (Loader.initialize()) {
            XboxController controller = new XboxController();
            Thread thread = new Thread(() -> {
                controller.registerHandler(new Player(plc1, 1));
                plc1.getLbPlayer().setText("Player: 1");
                
                controller.registerHandler(new Player(plc2, 2));
                plc2.getLbPlayer().setText("Player: 2");
                
                controller.poll(60);
            }, "Xbox-Controller-Polling-Thread");
            thread.setDaemon(true); // So it shuts down when the app closes
            thread.start();

        }
    }

    public Stage getStage() {
        return stage;
    }

    public Label getLbStatus() {
        return lbStatus;
    }

    public Label getLbInfo() {
        return lbInfo;
    }

    public MenuBar getMenuBar() {
        return menuBar;
    }

}
