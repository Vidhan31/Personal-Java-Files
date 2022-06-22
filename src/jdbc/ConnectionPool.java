package jdbc;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConnectionPool {

    private static final String URL = "jdbc:mysql://localhost:3306/student";
    private static final String username = "root";
    private static final String password = "";

    private static Properties properties = null;
    private static BasicDataSource dataSource;

    static {
        try {
            properties = new Properties();
            properties.load(new FileInputStream("src/database.properties"));

            dataSource = new BasicDataSource();
            dataSource.setUrl(URL);
            dataSource.setUsername(username);
            dataSource.setPassword(password);

            dataSource.setMinIdle(10);
            dataSource.setMaxIdle(20);
            

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
