package com.vera.pokedex.util;

import javafx.scene.control.Alert;

public class AlertUtils {
    public static void showError(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setContentText(mensaje);
        alerta.show();
    }
    public static void showWarning(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.WARNING);
        alerta.setContentText(mensaje);
        alerta.show();
    }
    public static void showInfo(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setContentText(mensaje);
        alerta.show();
    }
}
