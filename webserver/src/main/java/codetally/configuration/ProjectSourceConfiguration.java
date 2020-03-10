package codetally.configuration;

import com.codetally.plugin.ProjectSource;
import org.springframework.beans.factory.serviceloader.ServiceListFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProjectSourceConfiguration {
    @Bean
    public ServiceListFactoryBean projectSourceListFactoryBean() {
        ServiceListFactoryBean serviceListFactoryBean = new ServiceListFactoryBean();
        serviceListFactoryBean.setServiceType(ProjectSource.class);
        return serviceListFactoryBean;
    }
}
