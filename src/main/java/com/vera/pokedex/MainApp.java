package com.vera.pokedex;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class MainApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        DBCon con = new DBCon();
        try {
            con.connect();

            FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("pokedex.fxml"));

            Parent root = fxmlLoader.load();
            PokedexController controller = fxmlLoader.getController();
            controller.setDBCon(con);
            controller.loadData();

            Scene scene = new Scene(root);
            stage.setTitle("Pokedex");
            stage.setScene(scene);
            stage.show();
        } catch (SQLException sqle){
            System.out.println("Error al conectar a la base de datos: "+sqle);
        }catch (ClassNotFoundException cnfe){
            System.out.println("Error al cargar la aplicacion: "+cnfe);
        }catch (IOException ioe){
            System.out.println("Error al cargar la configuracion: "+ioe);
        }
    }

    public static void main(String[] args) {
        launch();
    }
}