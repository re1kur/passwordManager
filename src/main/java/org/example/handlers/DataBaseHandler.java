package org.example.handlers;

import org.example.assets.Configs;
import org.example.assets.Const;
import org.example.assets.Password;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DataBaseHandler extends Configs {
    private Connection dbConnection;

    private Connection getDbConnection(){
        try {
        String connectionString = "jdbc:mysql://" +dbHost + ":"
                + dbPort +"/" + dbName;
        Class.forName("com.mysql.jdbc.Driver");
            dbConnection = DriverManager.getConnection(connectionString,
                    dbUser, dbPass);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return dbConnection;
    }
    public void signUpUser(String login, String seed1, String seed2,
                           String seed3, String seed4, String seed5,
                           String seed6, String seed7, String seed8,
                           String seed9, String seed10) {
        String insert = "INSERT INTO " + Const.USER_TABLE + " ("
                + Const.USERS_LOGIN + "," + Const.USERS_SEED1 + ","
                + Const.USERS_SEED2 + "," + Const.USERS_SEED3 + ","
                + Const.USERS_SEED4 + "," + Const.USERS_SEED5 + ","
                + Const.USERS_SEED6 + "," + Const.USERS_SEED7 + ","
                + Const.USERS_SEED8 + "," + Const.USERS_SEED9 + ","
                + Const.USERS_SEED10 + ")" + "VALUES(?,?,?,?,?,?,?,?,?,?,?)";
        String create = "CREATE TABLE " + login +" (id INT NOT NULL AUTO_INCREMENT,"
                + "password VARCHAR(100) NOT NULL,"
                + "note VARCHAR(100) NOT NULL,"
                + "primary key (id))";
        try {
            try (PreparedStatement prst = getDbConnection().prepareStatement(insert);
                 PreparedStatement pstTable = getDbConnection().prepareStatement(create)) {
                prst.setString(1, login);
                prst.setString(2, seed1);
                prst.setString(3, seed2);
                prst.setString(4, seed3);
                prst.setString(5, seed4);
                prst.setString(6, seed5);
                prst.setString(7, seed6);
                prst.setString(8, seed7);
                prst.setString(9, seed8);
                prst.setString(10, seed9);
                prst.setString(11, seed10);
                prst.executeUpdate();
                pstTable.executeUpdate();

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public Boolean checkLogin (String login) {
        ResultSet loginSet;
        String select = "SELECT * FROM " + Const.USER_TABLE +
                " WHERE " + Const.USERS_LOGIN + "=?";
        try {
            try (PreparedStatement prst = getDbConnection().prepareStatement(select)) {
                prst.setString(1, login);
                loginSet = prst.executeQuery();
                if (loginSet.next()) {
                    return true;
                }

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
       return false;
    }

    public Boolean logInUser (String login, String seed, int number) {
        number ++;
        String add = "seed" + number;
        ResultSet loginSet;
        String select = "SELECT * FROM " + Const.USER_TABLE +
                " WHERE " + Const.USERS_LOGIN + "=? AND " + add
                + "=?";
        try {
            try (PreparedStatement prst = getDbConnection().prepareStatement(select)) {
                prst.setString(1, login);
                prst.setString(2, seed);
                loginSet = prst.executeQuery();
                if (loginSet.next()) {
                    return true;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
    public void addGeneratedPassword (String login, String len, String note) {
        System.out.println(login);
        String insert = "INSERT INTO " + login
                + " (password,note)VALUES (?,?)";
        System.out.println(insert);
        PasswordGenerator pg = new PasswordGenerator();
        PasswordEncryption pe = new PasswordEncryption();
        String password = pg.generatePassword(Integer.parseInt(len));
        System.out.println(password);
        String encrypt = pe.encrypt(password);
        try {
            try (PreparedStatement statement = getDbConnection().prepareStatement(insert)) {
                statement.setString(1, encrypt);
                statement.setString(2, note);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            System.err.println("SQL exception welcome. :)");
        }
    }
    public void addExistingPassword (String login, String password, String note) {
        System.out.println(login);
        String insert = "INSERT INTO " + login
                + " (password,note)VALUES (?,?)";
        System.out.println(insert);
        PasswordEncryption pe = new PasswordEncryption();
        System.out.println(password);
        String encrypt = pe.encrypt(password);
        System.out.println(encrypt);
        try {
            try (PreparedStatement statement = getDbConnection().prepareStatement(insert)) {
                statement.setString(1, encrypt);
                statement.setString(2, note);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            System.err.println("SQL exception welcome. :)");
        }
    }
    public List<Password> parsePasswords(String login) {
        PasswordEncryption pe = new PasswordEncryption();
        List<Password> passwords = new ArrayList<>();
        try {
            String query = "SELECT * FROM " + login;
            try (PreparedStatement statement = getDbConnection().prepareStatement(query)) {
                ResultSet res = statement.executeQuery();
                while (res.next()) {
                    passwords.add(new Password(
                            pe.decrypt(res.getString(2)),
                            res.getString(3)
                    ));
                }
            }
            return passwords;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return passwords;
    }
}
