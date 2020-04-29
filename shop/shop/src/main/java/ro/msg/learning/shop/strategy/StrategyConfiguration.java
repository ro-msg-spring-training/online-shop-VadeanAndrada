package ro.msg.learning.shop.strategy;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ro.msg.learning.shop.exception.StrategyException;

@Configuration
public class StrategyConfiguration {

    @Bean
    public LocationStrategy createOrderStrategy(@Value("${strategy}") String strategy, MostAbundant mostAbundant, SingleLocation singleLocation) {
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
