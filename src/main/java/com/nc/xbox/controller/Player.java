package com.nc.xbox.controller;

import com.nc.xbox.XboxController;
import com.nc.xbox.XboxControllerHandler;
import com.nc.xbox.custom.Polar;
import java.util.Map.Entry;
import javafx.application.Platform;
import javafx.scene.control.Button;

public class Player extends XboxControllerHandler {

    private PlayerController playerController;
    private int id;

    public Player(int id) {
        super(id);
        this.id = id;
    }

    public Player(PlayerController playerController, int id) {
        super(id);
        this.playerController = playerController;
        this.id = id;
    }

    @Override
    public void trigger(float left, float right) {
        XboxController.vibrate(id, left, right);

        Platform.runLater(() -> {

            triggerButton(playerController.getBtnRT(), right);
            triggerButton(playerController.getBtnLT(), left);
            playerController.getBarR().drawLevel(right);
            playerController.getBarL().drawLevel(left);

        });
    }

    private void triggerButton(Button button, float value) {
        if (value >= 0.0) {
            if (!button.getStyleClass().contains("xbox-green")) {
                button.getStyleClass().add("xbox-green");
            }
        } else {
            button.getStyleClass().remove("xbox-green");
        }
    }

    @Override
    public void leftStick(float x, float y) {
        Platform.runLater(() -> {
            playerController.getPolarLS().drawPosition(x, y);
        });
    }

    @Override
    public void rightStick(float x, float y) {
        Platform.runLater(() -> {
            playerController.getPolarRS().drawPosition(x, y);
        });
    }

    @Override
    public void buttonDown(int value) {
        for (Entry entry : playerController.getButtonMap().entrySet()) {
            int buttonMask = (int) entry.getKey();

            Platform.runLater(() -> {
                if ((value & buttonMask) == buttonMask) {
                    if (!((Button) entry.getValue()).getStyleClass().contains("xbox-green")) {
                        ((Button) entry.getValue()).getStyleClass().add("xbox-green");
                    }
                }

                handleThumbs(value, true);
            });
        }
    }

    private void handleThumbs(int value, boolean state) {
        int rt = XboxController.Button.RIGHT_THUMB.getBitmask();
        updatePolar(playerController.getPolarRS(), value, rt, state);

        int lt = XboxController.Button.LEFT_THUMB.getBitmask();
        updatePolar(playerController.getPolarLS(), value, lt, state);
    }

    private void updatePolar(Polar polar, int value, int mask, boolean state) {
        if ((value & mask) == mask) {
            polar.setThumb(state);
        }
    }

    @Override
    public void buttonReleased(int value) {
        for (Entry entry : playerController.getButtonMap().entrySet()) {
            int buttonMask = (int) entry.getKey();

            Platform.runLater(() -> {
                if ((value & buttonMask) == buttonMask) {
                    System.out.println("Released: " + buttonMask);
                    ((Button) entry.getValue()).getStyleClass().remove("xbox-green");
                }

                handleThumbs(value, false);
            });
        }
    }

    @Override
    public void buttonPressed(int i) {

    }

}
