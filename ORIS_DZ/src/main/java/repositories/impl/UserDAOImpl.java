package repositories.impl;

import models.User;
import repositories.UserDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    private static final String URL ="jdbc:postgresql://localhost:5432/ORIS_DZ";
    private static final String USER = "postgres";
    private static final String PASSWORD = "vimer_401";

    private static final String INSERT_USER_SQL = "insert into users(name,email,password) values(?,?,?)";
    private static final String UPDATE_USER_SQL = "update users set name=?, email=?, password=?,role=? where id=?";
    private static final String DELETE_USER_SQL = "delete from users where id = ?";
    private static final String SELECT_USER_SQL = "select * from users";
    private static final String SELECT_EMAIL_VALIDITY_SQL = "SELECT COUNT(*) FROM users WHERE email = ?";
    private static final String SELECT_USER_ID_BY_EMAIL = "select id from users where email = ?";
    private static final String SELECT_USER_BY_EMAIL = "select * from users where email = ?";
    private static final String SELECT_USER_BY_ID_SQL = "SELECT name, email,password, role FROM users WHERE id = ?";

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("PostgreSQL Driver not found. Include it in your library path.", e);
        }
    }


    @Override
    public void save(User user) {

       try(Connection connection = DriverManager.getConnection(URL,USER,PASSWORD);
           PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER_SQL)) {
           preparedStatement.setString(1, user.getName());
           preparedStatement.setString(2, user.getEmail());
           preparedStatement.setString(3, user.getPassword());
           preparedStatement.executeUpdate();
       } catch (SQLException e) {
           throw new RuntimeException(e);
       }
    }

    @Override
    public void update(User user) {

       try(Connection connection = DriverManager.getConnection(URL,USER,PASSWORD);
           PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_SQL)) {
           preparedStatement.setString(1, user.getName());
           preparedStatement.setString(2, user.getPassword());
           preparedStatement.setString(3, user.getEmail());
           preparedStatement.setString(4, user.getRole());
           preparedStatement.setInt(5, user.getId());
           preparedStatement.executeUpdate();

       } catch (SQLException e) {
           throw new RuntimeException(e);
       }
    }

    @Override
    public void delete(User user) {

        try(Connection connection = DriverManager.getConnection(URL,USER,PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER_SQL)){
            preparedStatement.setInt(1, user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        try(Connection connection = DriverManager.getConnection(URL,USER,PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_SQL)){
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                String role = resultSet.getString("role");
                users.add(User.builder()
                        .id(id)
                        .name(name)
                        .email(email)
                        .password(password)
                        .role(role)
                        .build());
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }
@Override
    public boolean isEmailValid(String email) {
        try(Connection connection = DriverManager.getConnection(URL,USER,PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_EMAIL_VALIDITY_SQL)){
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return resultSet.getInt(1)>0;
            }

        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;

    }

    @Override
    public int getUserIdByEmail(String email) {
        try(Connection connection = DriverManager.getConnection(URL,USER,PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_ID_BY_EMAIL)){
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return resultSet.getInt("id");
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return -1;
    }
@Override
    public User getUserByEmail(String email) {
        try(Connection connection = DriverManager.getConnection(URL,USER,PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_EMAIL)){
            preparedStatement.setString(1,email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return User.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .email(resultSet.getString("email"))
                        .password(resultSet.getString("password"))
                        .role(resultSet.getString("role"))
                        .build();
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;

    }

    @Override
    public User getUserById(int id) {
        User user = null;
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID_SQL)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                String role = resultSet.getString("role"); // Получаем роль
                user = User.builder()
                        .id(id)
                        .name(name)
                        .email(email)
                        .password(password)
                        .role(role)
                        .build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }


    }
