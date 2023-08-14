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

    public ProductDao(String url, Properties connectionProps) {
        try {
            DriverManager.registerDriver(new Driver());
            for (int i = 0; i < 10; i++) {
                Connection newConnection = DriverManager.getConnection(url, connectionProps);
                newConnection.setAutoCommit(false);
                connectionPool.put(newConnection);
            }
        } catch (SQLException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @PreDestroy
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
        try {
            Connection connection = connectionPool.take();
            connection.setReadOnly(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
            PreparedStatement statement = connection.prepareStatement("INSERT INTO products VALUES (DEFAULT, ?, ?)");
            statement.setString(1, productEntity.getName());
            statement.setInt(2, productEntity.getPrice());
            statement.executeUpdate();
            statement.close();
            connection.commit();
            connectionPool.put(connection);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Collection<ProductEntity> getAll() {
        try {
            Connection connection = connectionPool.take();
            connection.setReadOnly(true);
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            PreparedStatement statement = connection.prepareStatement("SELECT code, name, price FROM products");
            ResultSet resultSet = statement.executeQuery();
            Collection<ProductEntity> productEntities = new ArrayList<>();
            while (resultSet.next()) {
                ProductEntity productEntity = new ProductEntity();
                productEntity.setCode(resultSet.getInt("code"));
                productEntity.setName(resultSet.getString("name"));
                productEntity.setPrice(resultSet.getInt("price"));
                productEntities.add(productEntity);
            }
            statement.close();
            connection.commit();
            connectionPool.put(connection);
            return productEntities;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(int code, ProductEntity productEntity) {
        try {
            Connection connection = connectionPool.take();
            connection.setReadOnly(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
            if (!exists(connection, code)) {
                connection.rollback();
                throw new ProductNotFoundException("Product with such code not exist - " + code);
            }
            PreparedStatement statement =
                    connection.prepareStatement("UPDATE products SET name = ?, price = ? WHERE  code = ?");
            statement.setString(1, productEntity.getName());
            statement.setInt(2, productEntity.getPrice());
            statement.setInt(3, code);
            statement.executeUpdate();
            statement.close();
            connection.commit();
            connectionPool.put(connection);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(Integer code) {
        try {
            Connection connection = connectionPool.take();
            connection.setReadOnly(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
            if (!exists(connection, code)) {
                connection.rollback();
                throw new ProductNotFoundException("Product with such code not exist - " + code);
            }
            PreparedStatement statement =
                    connection.prepareStatement("DELETE FROM products WHERE code = ?");
            statement.setInt(1, code);
            statement.executeUpdate();
            statement.close();
            connection.commit();
            connectionPool.put(connection);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean exists(Connection connection, int code) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("SELECT 1 FROM products WHERE code = ?");) {
            statement.setInt(1, code);
            return statement.executeQuery().next();
        }
    }
}
