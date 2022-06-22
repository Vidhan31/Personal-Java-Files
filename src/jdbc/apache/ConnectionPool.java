package jdbc.apache;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConnectionPool {

    private static BasicDataSource dataSource = null;

    static {
        try {
            Properties property = new Properties();
            property.load(new FileInputStream("src/jdbc/apache/database.properties"));
            dataSource = new BasicDataSource();
            dataSource.setUrl(property.getProperty("DB_URL"));
            dataSource.setUsername(property.getProperty("DB_USERNAME"));
            dataSource.setPassword(property.getProperty("DB_PASSWORD"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        dataSource.setMaxTotal(4);
        dataSource.setMaxOpenPreparedStatements(5);
    }

    public static DataSource getDataSource() {
        return dataSource;
    }
}
