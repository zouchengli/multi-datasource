package site.clzblog.application.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import site.clzblog.application.config.base.BaseDatasourceConfig;
import site.clzblog.application.config.properties.SecondDatasourceProperties;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
@EnableConfigurationProperties(SecondDatasourceProperties.class)
@MapperScan(basePackages = "site.clzblog.application.second.mapper",sqlSessionTemplateRef = "secondSqlSessionTemplate")
public class SecondDatasourceConfig extends BaseDatasourceConfig {
    @Bean("secondDatasource")
    public DataSource secondDatasource(SecondDatasourceProperties properties) throws SQLException {
        return getSecondDataSource(properties);
    }

    @Bean("secondSqlSessionFactory")
    public SqlSessionFactory secondSqlSessionFactory(@Qualifier("secondDatasource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        return bean.getObject();
    }

    @Bean("secondSqlSessionTemplate")
    public SqlSessionTemplate secondSqlSessionTemplate(@Qualifier("secondSqlSessionFactory") SqlSessionFactory factory){
        return new SqlSessionTemplate(factory);
    }

    @Bean("secondDataSourceTransactionManager")
    public DataSourceTransactionManager secondDataSourceTransactionManager(@Qualifier("secondDatasource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
