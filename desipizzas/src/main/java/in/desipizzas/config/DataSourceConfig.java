package in.desipizzas.config;


import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:db.properties")
public class DataSourceConfig {
	
	Logger log = LoggerFactory.getLogger(getClass());
	
	private @Value("${db.jdbcurl}") String JDBC_URL;
	private @Value("${db.username}") String USER_NAME;
	private @Value("${db.password}") String PASSWORD;
	private @Value("${db.driverclassname}")String DRIVER_CLASS_NAME;
	
	@Bean
	public DataSource applicationDatabse() {
		log.info("database  bean created");
		BasicDataSource datasource = new BasicDataSource();
		datasource.setDriverClassName(DRIVER_CLASS_NAME);
		datasource.setPassword(PASSWORD);
		datasource.setUsername(USER_NAME);
		datasource.setUrl(JDBC_URL);
		return datasource;
	}
	
}
