module com.example.pokedex {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.example.pokedex to javafx.fxml;
    exports com.example.pokedex;
}