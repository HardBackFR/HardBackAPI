package fr.hardback.commons.data.sql;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.Consumer;
import java.util.function.Function;

public class DBAccess {

    protected final DBCredentials credentials;
    protected HikariDataSource hikariDataSource;

    public DBAccess(DBCredentials credentials) {
        this.credentials = credentials;
    }

    private void setupHikariCP(){
        final HikariConfig hikariConfig = new HikariConfig();

        hikariConfig.setMaximumPoolSize(10);
        hikariConfig.setJdbcUrl(this.credentials.toURL());
        hikariConfig.setUsername(this.credentials.getUser());
        hikariConfig.setPassword(this.credentials.getPassword());
        hikariConfig.setMaxLifetime(600000L);//10 minutes
        hikariConfig.setIdleTimeout(300000L);//5 minutes
        hikariConfig.setLeakDetectionThreshold(300000L);//5 minutes
        hikariConfig.setConnectionTimeout(1000L);//10 secondes

        this.hikariDataSource = new HikariDataSource(hikariConfig);
    }

    public void initPool(){
        setupHikariCP();
    }

    public void closePool(){
        this.hikariDataSource.close();
    }

    public Connection getConnection() throws SQLException {
        if(this.hikariDataSource == null){
            System.out.println("Not connected !");
            setupHikariCP();
        }
        return this.hikariDataSource.getConnection();
    }

    public void update(String qry){
        try (Connection c = getConnection();
             PreparedStatement s = c.prepareStatement(qry)) {
            s.executeUpdate();
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public Object query(String qry, Function<ResultSet, Object> consumer){
        try (Connection c = getConnection();
             PreparedStatement s = c.prepareStatement(qry);
             ResultSet rs = s.executeQuery()) {
            return consumer.apply(rs);
        } catch(SQLException e){
            throw new IllegalStateException(e.getMessage());
        }
    }

    public void query(String qry, Consumer<ResultSet> consumer){
        try (Connection c = getConnection();
             PreparedStatement s = c.prepareStatement(qry);
             ResultSet rs = s.executeQuery()) {
            consumer.accept(rs);
        } catch(SQLException e){
            throw new IllegalStateException(e.getMessage());
        }
    }
}
