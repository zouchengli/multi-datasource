package site.clzblog.application.config.base;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import com.mysql.cj.jdbc.MysqlXADataSource;
import site.clzblog.application.config.properties.PrimaryDatasourceProperties;
import site.clzblog.application.config.properties.SecondDatasourceProperties;

import javax.sql.DataSource;
import java.sql.SQLException;

public class BaseDatasourceConfig {
    protected DataSource getPrimaryDataSource(PrimaryDatasourceProperties properties) throws SQLException {
        return getDataSource(properties.getUrl(), properties.getPassword(), properties.getUsername(), "primaryDatasource", properties.getMinPoolSize(), properties.getMaxPoolSize(), properties.getMaxLifetime(), properties.getBorrowConnectionTimeout(), properties.getLoginTimeout(), properties.getMaintenanceInterval(), properties.getMaxIdleTime(), properties.getTestQuery());
    }

    protected DataSource getSecondDataSource(SecondDatasourceProperties properties) throws SQLException {
        return getDataSource(properties.getUrl(), properties.getPassword(), properties.getUsername(), "secondDatasource", properties.getMinPoolSize(), properties.getMaxPoolSize(), properties.getMaxLifetime(), properties.getBorrowConnectionTimeout(), properties.getLoginTimeout(), properties.getMaintenanceInterval(), properties.getMaxIdleTime(), properties.getTestQuery());
    }

    private DataSource getDataSource(String url, String password, String username, String name, int minPoolSize, int maxPoolSize, int maxLifetime, int borrowConnectionTimeout, int loginTimeout, int maintenanceInterval, int maxIdleTime, String testQuery) throws SQLException {
        MysqlXADataSource mysqlXADataSource = new MysqlXADataSource();
        mysqlXADataSource.setUrl(url);
        mysqlXADataSource.setPinGlobalTxToPhysicalConnection(true);
        mysqlXADataSource.setPassword(password);
        mysqlXADataSource.setUser(username);

        AtomikosDataSourceBean atomikosDataSourceBean = new AtomikosDataSourceBean();
        atomikosDataSourceBean.setXaDataSource(mysqlXADataSource);
        atomikosDataSourceBean.setUniqueResourceName(name);
        atomikosDataSourceBean.setMinPoolSize(minPoolSize);
        atomikosDataSourceBean.setMaxPoolSize(maxPoolSize);
        atomikosDataSourceBean.setMaxLifetime(maxLifetime);
        atomikosDataSourceBean.setBorrowConnectionTimeout(borrowConnectionTimeout);
        atomikosDataSourceBean.setLoginTimeout(loginTimeout);
        atomikosDataSourceBean.setMaintenanceInterval(maintenanceInterval);
        atomikosDataSourceBean.setMaxIdleTime(maxIdleTime);
        atomikosDataSourceBean.setTestQuery(testQuery);
        return atomikosDataSourceBean;
    }
}
