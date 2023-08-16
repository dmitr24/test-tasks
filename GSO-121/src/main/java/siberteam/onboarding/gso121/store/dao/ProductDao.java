package siberteam.onboarding.gso121.store.dao;

import org.postgresql.Driver;
import siberteam.onboarding.gso121.store.domain.ProductEntity;
import siberteam.onboarding.gso121.store.exception.ProductNotFoundException;
import javax.annotation.PreDestroy;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProductDao {
    private final BlockingQueue<Connection> connectionPool = new ArrayBlockingQueue<>(10);
    private final String dbUrl;
    private final Properties connectionProps;

    public ProductDao(String url, Properties connectionProps) {
        try {
            DriverManager.registerDriver(new Driver());
            for (int i = 0; i < 10; i++) {
                Connection newConnection = DriverManager.getConnection(url, connectionProps);
                newConnection.setAutoCommit(false);
                connectionPool.put(newConnection);
            }
            this.dbUrl = url;
            this.connectionProps = connectionProps;
        } catch (SQLException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void closeConnections() {
        connectionPool.forEach(conn -> {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void create(ProductEntity productEntity) {
        Connection connection = getConnection();
        try {
            connection.setReadOnly(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
            try (PreparedStatement statement =
                         connection.prepareStatement("INSERT INTO products VALUES (DEFAULT, ?, ?)")) {
                statement.setString(1, productEntity.getName());
                statement.setInt(2, productEntity.getPrice());
                statement.executeUpdate();
            }
            connection.commit();
        } catch (SQLException e) {
            rollback(connection);
            throw new RuntimeException(e);
        } finally {
            returnConnection(connection);
        }
    }

    public ProductEntity getByCode(int code) {
        Connection connection = getConnection();
        try {
            connection.setReadOnly(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
            if (!exists(connection, code)) {
                connection.rollback();
                throw new ProductNotFoundException("Product with such code not exist - " + code);
            }
            ProductEntity productEntity = null;
            try (PreparedStatement statement =
                         connection.prepareStatement("SELECT code, name, price FROM products WHERE code = ?")) {
                statement.setInt(1, code);
                ResultSet resultSet = statement.executeQuery();;
                if (resultSet.next()) {
                    productEntity = new ProductEntity();
                    productEntity.setCode(resultSet.getInt("code"));
                    productEntity.setName(resultSet.getString("name"));
                    productEntity.setPrice(resultSet.getInt("price"));
                }
            }
            connection.commit();
            return productEntity;
        } catch (SQLException e) {
            rollback(connection);
            throw new RuntimeException(e);
        } finally {
            returnConnection(connection);
        }
    }

    public Collection<ProductEntity> getAll() {
        Connection connection = getConnection();
        try {
            connection.setReadOnly(true);
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            Collection<ProductEntity> productEntities;
            try (PreparedStatement statement =
                         connection.prepareStatement("SELECT code, name, price FROM products")) {
                productEntities = new ArrayList<>();
                ResultSet resultSet = statement.executeQuery();;
                while (resultSet.next()) {
                    ProductEntity productEntity = new ProductEntity();
                    productEntity.setCode(resultSet.getInt("code"));
                    productEntity.setName(resultSet.getString("name"));
                    productEntity.setPrice(resultSet.getInt("price"));
                    productEntities.add(productEntity);
                }
            }
            connection.commit();
            return productEntities;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            returnConnection(connection);
        }
    }

    public void update(int code, ProductEntity productEntity) {
        Connection connection = getConnection();
        try {
            connection.setReadOnly(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
            if (!exists(connection, code)) {
                connection.rollback();
                throw new ProductNotFoundException("Product with such code not exist - " + code);
            }
            try (PreparedStatement statement =
                    connection.prepareStatement("UPDATE products SET name = ?, price = ? WHERE  code = ?")) {
                statement.setString(1, productEntity.getName());
                statement.setInt(2, productEntity.getPrice());
                statement.setInt(3, code);
                statement.executeUpdate();
            }
            connection.commit();
        } catch (SQLException e) {
            rollback(connection);
            throw new RuntimeException(e);
        } finally {
            returnConnection(connection);
        }
    }

    public void delete(int code) {
        Connection connection = getConnection();
        try {
            connection.setReadOnly(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
            if (!exists(connection, code)) {
                connection.rollback();
                throw new ProductNotFoundException("Product with such code not exist - " + code);
            }
            try (PreparedStatement statement =
                    connection.prepareStatement("DELETE FROM products WHERE code = ?")) {
                statement.setInt(1, code);
                statement.executeUpdate();
            }
            connection.commit();
        } catch (SQLException e) {
            rollback(connection);
            throw new RuntimeException(e);
        } finally {
            returnConnection(connection);
        }
    }

    private boolean exists(Connection connection, int code) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("SELECT 1 FROM products WHERE code = ?");) {
            statement.setInt(1, code);
            return statement.executeQuery().next();
        }
    }

    private Connection getConnection() {
        try {
            Connection connection = connectionPool.take();
            if (!connection.isValid(10)) {
                Connection newConnection = DriverManager.getConnection(dbUrl, connectionProps);
                newConnection.setAutoCommit(false);
                return newConnection;
            }
            return connection;
        } catch (InterruptedException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void rollback(Connection connection) {
        try {
            connection.rollback();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void returnConnection(Connection connection) {
        try {
            connectionPool.put(connection);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
