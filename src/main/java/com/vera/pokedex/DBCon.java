package com.vera.pokedex;

import  com.vera.pokedex.domain.Pokemon;
import com.vera.pokedex.util.R;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DBCon {
    private Connection con;

    public void connect() throws ClassNotFoundException, SQLException, IOException {
//        Properties configuration = new Properties();
//        System.out.println(R.getProperties("database.properties"));
//        configuration.load(R.getProperties("database.properties"));
//        String host = configuration.getProperty("host");
//        String port = configuration.getProperty("port");
//        String name = configuration.getProperty("name");
//        String username = configuration.getProperty("username");
//        String password = configuration.getProperty("password");

        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pokemon?serverTimezone=UTC",
                "vera", "vera");
    }

    public void disconnect() throws SQLException {
        con.close();
    }
    public void savePokemon(Pokemon pokemon) throws SQLException{
        String sql = "INSERT INTO pokemon (id,name,hp,attack,defense,special_attack,special_defense,speed,height,weight,type_1,type_2,description) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement sentencia = con.prepareStatement(sql);
        sentencia.setString(1, ("" + pokemon.getId()));
        sentencia.setString(2, pokemon.getName());
        sentencia.setString(3, "" + pokemon.getHp());
        sentencia.setString(4, "" + pokemon.getAttack());
        sentencia.setString(5, "" + pokemon.getDefense());
        sentencia.setString(6, "" + pokemon.getSpecial_attack());
        sentencia.setString(7, "" + pokemon.getSpecial_defense());
        sentencia.setString(8, "" + pokemon.getSpeed());
        sentencia.setString(9, "" + pokemon.getHeight());
        sentencia.setString(10, "" + pokemon.getWeight());
        sentencia.setString(11, pokemon.getType1());
        sentencia.setString(12, pokemon.getType2());
        sentencia.setString(13, pokemon.getDescription());
        sentencia.executeUpdate();
    }
    public void deletePokemon(Pokemon pokemon) throws SQLException{
        String sql = "DELETE FROM pokemon WHERE id = ?";
        PreparedStatement sentencia = con.prepareStatement(sql);
        sentencia.setString(1,String.valueOf(pokemon.getId()));
        sentencia.executeUpdate();
    }

    public void modifyPokemon(Pokemon oldPokemon,Pokemon newPokemon) throws SQLException{
        String sql = "UPDATE pokemon SET name=?,hp=?,attack=?,defense=?,special_attack=?,special_defense=?,speed=?,height=?,weight=?,type_1=?,type_2=?,description=? WHERE id=?";
        PreparedStatement sentencia = con.prepareStatement(sql);
        sentencia.setString(1, newPokemon.getName());
        sentencia.setString(2, "" + oldPokemon.getHp());
        sentencia.setString(3, "" + newPokemon.getAttack());
        sentencia.setString(4, "" + newPokemon.getDefense());
        sentencia.setString(5, "" + newPokemon.getSpecial_attack());
        sentencia.setString(6, "" + newPokemon.getSpecial_defense());
        sentencia.setString(7, "" + newPokemon.getSpeed());
        sentencia.setString(8, "" + newPokemon.getHeight());
        sentencia.setString(9, "" + newPokemon.getWeight());
        sentencia.setString(10, newPokemon.getType1());
        sentencia.setString(11, newPokemon.getType2());
        sentencia.setString(12, newPokemon.getDescription());
        sentencia.setString(13, "" + oldPokemon.getId());
        sentencia.executeUpdate();
    }
    public ArrayList<Pokemon> getPokedex() throws SQLException{
        String sql = "SELECT * FROM pokemon";
        PreparedStatement sentencia = con.prepareStatement(sql);
        ResultSet resultado = sentencia.executeQuery();
        ArrayList <Pokemon> pokedex = new ArrayList<>();
        while (resultado.next()){
            Pokemon pokemon = new Pokemon(
                    resultado.getInt(1),
                    resultado.getString(2),
                    resultado.getInt(3),
                    resultado.getInt(4),
                    resultado.getInt(5),
                    resultado.getInt(6),
                    resultado.getInt(7),
                    resultado.getInt(8),
                    resultado.getInt(9),
                    resultado.getInt(10),
                    resultado.getString(11),
                    resultado.getString(12),
                    resultado.getString(13)
            );
            pokedex.add(pokemon);
        }
        return pokedex;
    }
}
