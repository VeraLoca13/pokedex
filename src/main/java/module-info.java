module com.example.pokedex {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.sql;
    requires mysql.connector.j;

    opens com.vera.pokedex to javafx.fxml;
    exports com.vera.pokedex;
}