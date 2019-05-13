package site.clzblog.application.config;

import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.jta.JtaTransactionManager;
import site.clzblog.application.config.base.BaseDatasourceConfig;
import site.clzblog.application.config.properties.PrimaryDatasourceProperties;

import javax.sql.DataSource;
import javax.transaction.TransactionManager;
import javax.transaction.UserTransaction;
import java.sql.SQLException;

@Configuration
@EnableConfigurationProperties(PrimaryDatasourceProperties.class)
@MapperScan(basePackages = "site.clzblog.application.primary.mapper",sqlSessionTemplateRef = "primarySqlSessionTemplate")
public class PrimaryDatasourceConfig extends BaseDatasourceConfig {
    @Primary
    @Bean("primaryDatasource")
    @DependsOn("primaryTxManager")
    public DataSource primaryDatasource(PrimaryDatasourceProperties properties) throws SQLException {
        return getPrimaryDataSource(properties);
    }

    @Primary
    @Bean("primarySqlSessionFactory")
    public SqlSessionFactory primarySqlSessionFactory(@Qualifier("primaryDatasource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        return bean.getObject();
    }

    @Primary
    @Bean("primarySqlSessionTemplate")
    public SqlSessionTemplate primarySqlSessionTemplate(@Qualifier("primarySqlSessionFactory") SqlSessionFactory factory){
        return new SqlSessionTemplate(factory);
    }

    @Primary
    @Bean("primaryDataSourceTransactionManager")
    public DataSourceTransactionManager primaryDataSourceTransactionManager(@Qualifier("primaryDatasource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "primaryTxManager")
    @DependsOn({ "primaryUserTransaction", "primaryAtomikosTransactionManager" })
    public PlatformTransactionManager transactionManager() throws Throwable {
        UserTransaction userTransaction = userTransaction();
        TransactionManager atomikosTransactionManager = atomikosTransactionManager();
        return new JtaTransactionManager(userTransaction, atomikosTransactionManager);
    }

    @Bean(name = "primaryUserTransaction")
    public UserTransaction userTransaction() throws Throwable {
        UserTransactionImp userTransactionImp = new UserTransactionImp();
        userTransactionImp.setTransactionTimeout(10000);
        return userTransactionImp;
    }

    @Bean(name = "primaryAtomikosTransactionManager")
    public TransactionManager atomikosTransactionManager() {
        UserTransactionManager userTransactionManager = new UserTransactionManager();
        userTransactionManager.setForceShutdown(false);
        return userTransactionManager;
    }
}
