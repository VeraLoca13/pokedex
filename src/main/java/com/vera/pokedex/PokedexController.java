package com.vera.pokedex;

import com.vera.pokedex.domain.Pokemon;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class PokedexController {

    @FXML
    private TextField attack;

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnModify;

    @FXML
    private Button btnNew;

    @FXML
    private Button btnSave;

    @FXML
    private TextField defense;

    @FXML
    private Tab descripcion;

    @FXML
    private TextArea description;

    @FXML
    private TextField height;

    @FXML
    private ImageView image;

    @FXML
    private ListView<Pokemon> listPokemon;

    @FXML
    private TextField pkmnName;

    @FXML
    private TextField pv;

    @FXML
    private TextField special_attack;

    @FXML
    private TextField special_defense;

    @FXML
    private TextField speed;

    @FXML
    private TextField type1;

    @FXML
    private TextField type2;

    @FXML
    private TextField weight;

    private final DBCon con;
    public PokedexController(){
        con = new DBCon();
        try {
            con.connect();
        } catch (SQLException sqle){
            System.out.println("Error al conectar a la base de datos: "+sqle);
        }catch (ClassNotFoundException cnfe){
            System.out.println("Error al cargar la aplicacion: "+cnfe);
        }catch (IOException ioe){
            System.out.println("Error al cargar la configuracion: "+ioe);
        }
    }
    public void loadData(){
        listPokemon.getItems().clear();
        try {
            Pokemon[] pokedex = con.getPokedex();
            listPokemon.setItems(FXCollections.observableList(Arrays.stream(pokedex).toList()));
        }catch (SQLException sqle){
            System.out.println("Error al acceder a la BBDD: "+sqle);
        }
    }
}
