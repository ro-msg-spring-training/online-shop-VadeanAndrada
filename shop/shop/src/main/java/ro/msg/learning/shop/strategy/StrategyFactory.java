package ro.msg.learning.shop.strategy;

public interface StrategyFactory {
    LocationStrategy getStrategy(String type);
}
