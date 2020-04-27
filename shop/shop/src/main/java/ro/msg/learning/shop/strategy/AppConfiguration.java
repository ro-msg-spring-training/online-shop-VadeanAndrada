package ro.msg.learning.shop.strategy;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.ServiceLocatorFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class AppConfiguration {

    private final BeanFactory beanFactory;

    public ServiceLocatorFactoryBean serviceLocatorFactoryBean(){
        final ServiceLocatorFactoryBean locatorFactoryBean = new ServiceLocatorFactoryBean();
        locatorFactoryBean.setServiceLocatorInterface(StrategyFactory.class);
        locatorFactoryBean.setBeanFactory(beanFactory);
        return locatorFactoryBean;
    }

    @Bean
    public StrategyFactory strategyFactory(){
        final ServiceLocatorFactoryBean locatorFactoryBean = serviceLocatorFactoryBean();
        locatorFactoryBean.afterPropertiesSet();
        return ((StrategyFactory) locatorFactoryBean.getObject());
    }

}
