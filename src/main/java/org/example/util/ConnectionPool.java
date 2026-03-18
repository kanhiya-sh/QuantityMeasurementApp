package org.example.util;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;

public class ConnectionPool {
    private static HikariDataSource dataSource;
    static {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(ApplicationConfig.getProperty("db.url"));
        config.setUsername(ApplicationConfig.getProperty("db.username"));
        config.setPassword(ApplicationConfig.getProperty("db.password"));
        config.setMaximumPoolSize(
                Integer.parseInt(ApplicationConfig.getProperty("pool.maximumPoolSize"))
        );
        dataSource = new HikariDataSource(config);
    }
    public static DataSource getDataSource() {
        return dataSource;
    }
}