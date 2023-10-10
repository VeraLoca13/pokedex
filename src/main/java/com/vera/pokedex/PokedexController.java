package com.vera.pokedex;

import com.vera.pokedex.util.AlertUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.sql.SQLException;

public class PokedexController {

    private final DBCon dbCon;

    public PokedexController(){
        dbCon = new DBCon();
        try {
            dbCon.connect();
        } catch (SQLException sqle) {
            AlertUtils.showError("Error al conectar a la base de datos");
        } catch (ClassNotFoundException cnfe){
            AlertUtils.showError("Error al iniciar la aplicación");
        } catch (IOException ioe){
            AlertUtils.showError("Error al cargar la configuración");
        }
    }

    @FXML
    private TextArea description;

    @FXML
    private ImageView image;

    @FXML
    private Label name;

    @FXML
    private TextArea stats;

}
