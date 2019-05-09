package site.clzblog.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class ApplicationMultiDatasource {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationMultiDatasource.class, args);
    }
}
