package codetally.configuration;

import com.codetally.model.EventAdapter;
import org.springframework.beans.factory.serviceloader.ServiceListFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EventAdapterConfiguration {
    @Bean
    public ServiceListFactoryBean eventAdapterListFactoryBean() {
        ServiceListFactoryBean serviceListFactoryBean = new ServiceListFactoryBean();
        serviceListFactoryBean.setServiceType(EventAdapter.class);
        return serviceListFactoryBean;
    }
}
