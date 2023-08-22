package greenatom.internship.task1;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Task1App {
    public static void main(String[] args) {
        Properties properties = loadProperties();

        String url = properties.getProperty("db.url");
        String user = properties.getProperty("db.user");
        String password = properties.getProperty("db.password");

        try {
            Connection connection = DriverManager.getConnection(url, user, password);

            createTable(connection);
            insertData(connection);
            String result = getSecondMaxExperienceSurname(connection);
            System.out.println("Фамилия сотрудника с вторым максимальным опытом: " + result);

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static Properties loadProperties() {
        Properties properties = new Properties();
        try (InputStream input = Task1App.class.getResourceAsStream("/database.properties")) {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    // Метод для создания таблицы, если она не существует
    private static void createTable(Connection connection) throws SQLException {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS public.task1 "
                + "(id bigint NOT NULL, "
                + "surname character varying COLLATE pg_catalog.\"default\", "
                + "experience smallint, "
                + "CONSTRAINT task1_pkey PRIMARY KEY (id))";

        try (Statement statement = connection.createStatement()) {
            statement.execute(createTableSQL);
        }
    }

    // Метод для вставки данных в таблицу
    private static void insertData(Connection connection) throws SQLException {
        String insertDataSQL = "INSERT INTO public.task1 (id, surname, experience) VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertDataSQL)) {
            preparedStatement.setLong(1, 1);
            preparedStatement.setString(2, "Иванов");
            preparedStatement.setInt(3, 10);
            preparedStatement.addBatch();

            preparedStatement.setLong(1, 2);
            preparedStatement.setString(2, "Петров");
            preparedStatement.setInt(3, 12);
            preparedStatement.addBatch();

            preparedStatement.setLong(1, 3);
            preparedStatement.setString(2, "Сидоров");
            preparedStatement.setInt(3, 14);
            preparedStatement.addBatch();

            preparedStatement.executeBatch();
        }
    }

    // Метод для получения фамилии сотрудника с вторым максимальным опытом работы
    private static String getSecondMaxExperienceSurname(Connection connection) throws SQLException {
        String selectSQL = "SELECT surname FROM public.task1 ORDER BY experience DESC LIMIT 1 OFFSET 1";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(selectSQL)) {
            if (resultSet.next()) {
                return resultSet.getString("surname");
            } else {
                return "Фамилия не найдена";
            }
        }
    }
}
