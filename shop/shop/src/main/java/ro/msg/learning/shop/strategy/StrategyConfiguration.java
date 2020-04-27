package ro.msg.learning.shop.strategy;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ServiceLocatorFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ro.msg.learning.shop.exception.StrategyException;

@Configuration
@RequiredArgsConstructor
@Getter
public class StrategyConfiguration {

    private final BeanFactory beanFactory;

    @Value("${strategy}")
    private String strategy;

    @Bean
    public LocationStrategy createOrderStrategy(MostAbundant mostAbundant, SingleLocation singleLocation) {
        switch (strategy) {
            case "singleLocation":
                return singleLocation;
            case "mostAbundant":
                return mostAbundant;
            default:
                throw new StrategyException("Chose a valid strategy!");
        }
    }

}
