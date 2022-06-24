package jdbc.apache;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.util.Properties;

public class ConnectionPool {

    private static BasicDataSource dataSource = null;

    private ConnectionPool() {}

    synchronized public static DataSource getDataSource() throws Exception {

        try {
            Properties property = new Properties();
            property.load(new FileInputStream("src/jdbc/apache/database.properties"));

            if (dataSource == null) {
                System.out.println("new instance of datasource");
                dataSource = new BasicDataSource();
            }

            dataSource.setUrl(property.getProperty("DB_URL"));
            dataSource.setUsername(property.getProperty("DB_USERNAME"));
            dataSource.setPassword(property.getProperty("DB_PASSWORD"));
            dataSource.setMaxTotal(4);
            dataSource.setMaxOpenPreparedStatements(5);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataSource;
    }
}
