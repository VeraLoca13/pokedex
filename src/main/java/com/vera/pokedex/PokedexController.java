package com.vera.pokedex;

import com.vera.pokedex.domain.Pokemon;
import com.vera.pokedex.util.AlertUtils;
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
import java.util.ArrayList;
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
    private TextField pkmnID;

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

    private DBCon con;
    private Pokemon pokemonActual;
    public void setDBCon(DBCon con){
        this.con = con;
    }
    public void loadData(){
        listPokemon.getItems().clear();
        try {
            ArrayList<Pokemon> pokedex = con.getPokedex();
            listPokemon.setItems(FXCollections.observableList(pokedex));
        }catch (SQLException sqle){
            AlertUtils.showError("Error al cargar los datos");
        }
    }
    public void cargarPokemon(){
        pokemonActual = listPokemon.getSelectionModel().getSelectedItem();
        nuevo();
        actualizarVista();
    }
    public void actualizarVista(){
        pkmnID.setText(String.valueOf(pokemonActual.getId()));
        pkmnName.setText(pokemonActual.getName());
        pv.setText(String.valueOf(pokemonActual.getHp()));
        attack.setText(String.valueOf(pokemonActual.getAttack()));
        defense.setText(String.valueOf(pokemonActual.getDefense()));
        special_attack.setText(String.valueOf(pokemonActual.getSpecial_attack()));
        special_defense.setText(String.valueOf(pokemonActual.getSpecial_defense()));
        speed.setText(String.valueOf(pokemonActual.getSpeed()));
        height.setText(String.valueOf(pokemonActual.getHeight()));
        weight.setText(String.valueOf(pokemonActual.getWeight()));
        type1.setText(pokemonActual.getType1());
        type2.setText(pokemonActual.getType2());
        description.setText(pokemonActual.getDescription());
    }
    public void nuevo(){
        pkmnID.setText("");
        pkmnName.setText("");
        pv.setText("");
        attack.setText("");
        defense.setText("");
        special_attack.setText("");
        special_defense.setText("");
        speed.setText("");
        height.setText("");
        weight.setText("");
        type1.setText("");
        type2.setText("");
        description.setText("");
    }
    public void guardar(){
        if(! (pkmnID.getText().equals(""))){
            Pokemon pokemon = new Pokemon(
                    Integer.parseInt(pkmnID.getText()),
                    pkmnName.getText(),
                    Integer.parseInt(pv.getText()),
                    Integer.parseInt(attack.getText()),
                    Integer.parseInt(defense.getText()),
                    Integer.parseInt(special_attack.getText()),
                    Integer.parseInt(special_defense.getText()),
                    Integer.parseInt(speed.getText()),
                    Integer.parseInt(height.getText()),
                    Integer.parseInt(weight.getText()),
                    type1.getText(),
                    type2.getText(),
                    description.getText()
            );
            try {
                con.savePokemon(pokemon);
            } catch (SQLException e) {
                AlertUtils.showError("PokedexController:  Error al guardar el pokemon: " + e);
            }
        }
        else {
            AlertUtils.showInfo("El ID no es valido");
        }
        loadData();
    }
    public void borrar(){
        if (pokemonActual != null) {
            try {
                con.deletePokemon(pokemonActual);
            } catch (SQLException e) {
                AlertUtils.showError("PokedexController:  Error al borrar el pokemon: " + e);
            }
        }else {
            AlertUtils.showInfo("No se ha seleccionado ningun pokemon");
        }
        loadData();
        nuevo();
    }
    public void cancelar(){
        if (pokemonActual != null) {
            actualizarVista();
        }else {
            AlertUtils.showInfo("No se ha seleccionado ningun pokemon");
        }
    }
    public void modificar(){
        if (pokemonActual != null) {
            Pokemon nuevoPokemon = new Pokemon(
                    Integer.parseInt(pkmnID.getText()),
                    pkmnName.getText(),
                    Integer.parseInt(pv.getText()),
                    Integer.parseInt(attack.getText()),
                    Integer.parseInt(defense.getText()),
                    Integer.parseInt(special_attack.getText()),
                    Integer.parseInt(special_defense.getText()),
                    Integer.parseInt(speed.getText()),
                    Integer.parseInt(height.getText()),
                    Integer.parseInt(weight.getText()),
                    type1.getText(),
                    type2.getText(),
                    description.getText()
            );
            try {
                con.modifyPokemon(pokemonActual, nuevoPokemon);
            } catch (SQLException e) {
                AlertUtils.showError("PokedexController:  Error al guardar el pokemon: " + e);
            }
        }else{
            AlertUtils.showInfo("No has seleccionado ningun pokemon");
        }
        loadData();
    }
}
