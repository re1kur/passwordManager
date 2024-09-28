package org.example.handlers;

import org.example.classes.Password;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DataBaseHandler {
    private static Connection dbConnection;
    protected static final String user = "postgres";
    protected static final String pass = "123456778";
    protected static final String url = "jdbc:postgresql://localhost:5432/passwordManager";


    private static Connection getDbConnection() {
        try {
            dbConnection = DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            System.err.println("Could not connect to database:\n"
                    + e.getMessage());
        }
        return dbConnection;
    }

    private static void closeConnection() {
        try {
            dbConnection.close();
        } catch (SQLException e) {
            System.err.println("Could not close database connection:\n"
                    + e.getMessage());
        }
    }

    public static void createTables() {
        try {
            getDbConnection();
            String query = "CREATE TABLE users (" +
                    "idusers SERIAL PRIMARY KEY NOT NULL," +
                    "login VARCHAR(20) NOT NULL UNIQUE," +
                    "seed1 VARCHAR(45) NOT NULL," +
                    "seed2 VARCHAR(45) NOT NULL," +
                    "seed3 VARCHAR(45) NOT NULL," +
                    "seed4 VARCHAR(45) NOT NULL," +
                    "seed5 VARCHAR(45) NOT NULL," +
                    "seed6 VARCHAR(45) NOT NULL," +
                    "seed7 VARCHAR(45) NOT NULL," +
                    "seed8 VARCHAR(45) NOT NULL," +
                    "seed9 VARCHAR(45) NOT NULL," +
                    "seed10 VARCHAR(45) NOT NULL" +
                    ");";
            getDbConnection().prepareStatement(query).executeUpdate();
            System.err.println("Tables created successfully.");
        } catch (SQLException e) {
            System.err.println("Table exists.");
        } finally {
            closeConnection();
        }
    }

    public static void signUpUser(String login, String seed1, String seed2,
                                  String seed3, String seed4, String seed5,
                                  String seed6, String seed7, String seed8,
                                  String seed9, String seed10) {
        getDbConnection();
        String insert = "INSERT INTO users (" +
                "login, seed1, seed2, seed3, seed4, seed5, seed6," +
                "seed7, seed8, seed9, seed10) " +
                "VALUES(?,?,?,?,?,?,?,?,?,?,?);";
        String create = "CREATE TABLE " + login + " (id SERIAL NOT NULL PRIMARY KEY,"
                + "password VARCHAR(100) NOT NULL,"
                + "note VARCHAR(100) NOT NULL);";
        try {
            PreparedStatement prst = getDbConnection().prepareStatement(insert);
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
            closeConnection();
        } catch (SQLException e) {
            System.err.println("Could not insert login " + login + ":\n"
                    + e.getMessage());
        }
        try {
            PreparedStatement pstTable = getDbConnection().prepareStatement(create);
            pstTable.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Could not create table " + login + ":\n"
                    + e.getMessage());
        } finally {
            closeConnection();
        }
    }

    public static Boolean checkLogin(String login) {
        getDbConnection();
        ResultSet loginSet;
        String select = "SELECT * FROM users WHERE login =?;";
        try {
            try (PreparedStatement prst = getDbConnection().prepareStatement(select)) {
                prst.setString(1, login);
                loginSet = prst.executeQuery();
                if (loginSet.next()) {
                    return true;
                }
            }
        } catch (SQLException e) {
            System.err.println("Could not check login:\n"
                    + e.getMessage());
        } finally {
            closeConnection();
        }
        return false;
    }

    public static Boolean logInUser(String login, String seed, int number) {
        getDbConnection();
        number++;
        ResultSet loginSet;
        String select = "SELECT * FROM users WHERE login = ? AND seed"
                + number + " = ?;";
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
            System.err.println("Could not log in user:\n"
                    + e.getMessage());
        } finally {
            closeConnection();
        }
        return false;
    }

    public static void addGeneratedPassword(String login, String len, String note) {
        getDbConnection();
        String insert = "INSERT INTO " + login
                + " (password,note) VALUES (?, ?);";
        PasswordGenerator pg = new PasswordGenerator();
        PasswordEncryption pe = new PasswordEncryption();
        String password = pg.generatePassword(Integer.parseInt(len));
        String encrypt = pe.encrypt(password);
        try {
            try (PreparedStatement statement = getDbConnection().prepareStatement(insert)) {
                statement.setString(1, encrypt);
                statement.setString(2, note);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            System.err.println("Could not add generated password:\n"
                    + e.getMessage());
        } finally {
            closeConnection();
        }
    }

    public static void addExistingPassword(String login, String password, String note) {
        getDbConnection();
        System.out.println(login);
        String insert = "INSERT INTO " + login
                + " (password,note) VALUES (?, ?);";
        PasswordEncryption pe = new PasswordEncryption();
        String encrypt = pe.encrypt(password);
        try {
            try (PreparedStatement statement = getDbConnection().prepareStatement(insert)) {
                statement.setString(1, encrypt);
                statement.setString(2, note);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            System.err.println("Could not add existing password:\n"
                    + e.getMessage());
        } finally {
            closeConnection();
        }
    }

    public static List<Password> parsePasswords(String login) {
        getDbConnection();
        PasswordEncryption pe = new PasswordEncryption();
        List<Password> passwords = new ArrayList<>();
        try {
            String query = "SELECT * FROM " + login + ";";
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
            System.err.println("Could not parse passwords:\n"
                    + e.getMessage());
        } finally {
            closeConnection();
        }
        return passwords;
    }

    public static void deletePassword(String login, String password) {
        getDbConnection();
        String delete = "DELETE FROM " + login + " WHERE password = ?;";
        PasswordEncryption pe = new PasswordEncryption();
        try (PreparedStatement statement = getDbConnection().prepareStatement(delete)) {
            statement.setString(1, pe.encrypt(password));
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Could not delete password:\n"
                    + e.getMessage());
        } finally {
            closeConnection();
        }
    }
}
