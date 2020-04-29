package ro.msg.learning.shop.strategy;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import ro.msg.learning.shop.entity.*;
import ro.msg.learning.shop.exception.StrategyException;
import ro.msg.learning.shop.service.StockService;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(MockitoJUnitRunner.class)
public class MostAbundantTest {

    @Mock
    private StockService stockService;
    @InjectMocks
    private MostAbundant mostAbundant;

    @Test
    @Transactional
    public void getLocationDone() {

        Supplier supplier = Supplier.builder().name("ALTEX").build();
        ProductCategory productCategory = ProductCategory.builder().name("Mouse Hama").build();
        Product product1 = Product.builder().id(1).productCategory(productCategory).supplier(supplier).name("Mouse wireless Hama AM-8000").price(BigDecimal.valueOf(29.99)).weight(0.33).build();
        Product product2 = Product.builder().id(2).productCategory(productCategory).supplier(supplier).name("Mouse gaming Hama uRage Reaper Ess").price(BigDecimal.valueOf(47.75)).weight(0.33).build();
        Product product3 = Product.builder().id(3).productCategory(productCategory).supplier(supplier).name("Mouse wireless gaming Hama uRage Unleashed").price(BigDecimal.valueOf(99.99)).weight(0.33).build();
        Location location1 = Location.builder().id(1).name("Altex Cluj Iulius Mall").city("Cluj-Napoca").country("Romania").county("Cluj").streetAddress("Strada Alexandru Voievod 53-55").build();
        Location location2 = Location.builder().id(2).name("Altex").city("Cluj-Napoca").country("Romania").county("Cluj").streetAddress("Galeriile Auchan, Bulevardul Muncii 1-15").build();
        Stock stock1 = Stock.builder().location(location1).product(product1).quantity(100).locationId(location1.getId()).productId(product1.getId()).build();
        Stock stock2 = Stock.builder().location(location1).product(product2).quantity(150).locationId(location1.getId()).productId(product2.getId()).build();
        Stock stock3 = Stock.builder().location(location2).product(product1).quantity(40).locationId(location2.getId()).productId(product1.getId()).build();
        Stock stock4 = Stock.builder().location(location2).product(product2).quantity(300).locationId(location2.getId()).productId(product2.getId()).build();
        Stock stock5 = Stock.builder().location(location2).product(product3).quantity(80).locationId(location2.getId()).productId(product3.getId()).build();

        Mockito.when(stockService.findStockByLocationAndProduct(location1, product1)).thenReturn(java.util.Optional.ofNullable(stock1));
        Mockito.when(stockService.findStockByLocationAndProduct(location2, product2)).thenReturn(java.util.Optional.ofNullable(stock4));
        Mockito.when(stockService.findStockByLocationAndProduct(location2, product3)).thenReturn(java.util.Optional.ofNullable(stock5));

        Map<Product, Integer> productQuantity = new HashMap<>();
        productQuantity.put(product1, 2);
        productQuantity.put(product2, 3);
        productQuantity.put(product3, 4);

        List<Stock> stocksProd1 = new ArrayList<>();
        stocksProd1.add(stock1);
        stocksProd1.add(stock3);
        List<Stock> stocksProd2 = new ArrayList<>();
        stocksProd2.add(stock2);
        stocksProd2.add(stock4);
        List<Stock> stocksProd3 = new ArrayList<>();
        stocksProd3.add(stock5);

        Mockito.when(stockService.findAllProduct(product1)).thenReturn(stocksProd1);
        Mockito.when(stockService.findAllProduct(product2)).thenReturn(stocksProd2);
        Mockito.when(stockService.findAllProduct(product3)).thenReturn(stocksProd3);

        Map<Location, Map<Product, Integer>> productLocationQuantity = new HashMap<>();
        Map<Product, Integer> productQuantityLoc1 = new HashMap<>();
        Map<Product, Integer> productQuantityLoc2 = new HashMap<>();
        productQuantityLoc1.put(product1, 2);
        productQuantityLoc2.put(product2, 3);
        productQuantityLoc2.put(product3, 4);
        productLocationQuantity.put(location1, productQuantityLoc1);
        productLocationQuantity.put(location2, productQuantityLoc2);

        assert (mostAbundant.getLocation(productQuantity).equals(productLocationQuantity));
    }

    @Test
    @Transactional
    public void getLocationThrowsException() {

        Supplier supplier = Supplier.builder().name("ALTEX").build();
        ProductCategory productCategory = ProductCategory.builder().name("Mouse Hama").build();
        Product product1 = Product.builder().id(1).productCategory(productCategory).supplier(supplier).name("Mouse wireless Hama AM-8000").price(BigDecimal.valueOf(29.99)).weight(0.33).build();
        Product product2 = Product.builder().id(2).productCategory(productCategory).supplier(supplier).name("Mouse gaming Hama uRage Reaper Ess").price(BigDecimal.valueOf(47.75)).weight(0.33).build();

        Location location1 = Location.builder().id(1).name("Altex Cluj Iulius Mall").city("Cluj-Napoca").country("Romania").county("Cluj").streetAddress("Strada Alexandru Voievod 53-55").build();
        Location location2 = Location.builder().id(2).name("Altex").city("Cluj-Napoca").country("Romania").county("Cluj").streetAddress("Galeriile Auchan, Bulevardul Muncii 1-15").build();
        Stock stock1 = Stock.builder().location(location1).product(product1).quantity(100).locationId(location1.getId()).productId(product1.getId()).build();
        Stock stock2 = Stock.builder().location(location1).product(product2).quantity(150).locationId(location1.getId()).productId(product2.getId()).build();
        Stock stock3 = Stock.builder().location(location2).product(product1).quantity(40).locationId(location2.getId()).productId(product1.getId()).build();
        Stock stock4 = Stock.builder().location(location2).product(product2).quantity(200).locationId(location2.getId()).productId(product2.getId()).build();

        Mockito.when(stockService.findStockByLocationAndProduct(location1, product1)).thenReturn(java.util.Optional.ofNullable(stock1));

        Map<Product, Integer> productQuantity = new HashMap<>();
        productQuantity.put(product1, 20);
        productQuantity.put(product2, 300);

        List<Stock> stocksProd1 = new ArrayList<>();
        stocksProd1.add(stock1);
        stocksProd1.add(stock3);
        List<Stock> stocksProd2 = new ArrayList<>();
        stocksProd2.add(stock2);
        stocksProd2.add(stock4);

        Mockito.when(stockService.findAllProduct(product1)).thenReturn(stocksProd1);
        Mockito.when(stockService.findAllProduct(product2)).thenReturn(stocksProd2);

        Throwable exception = assertThrows(StrategyException.class, () -> mostAbundant.getLocation(productQuantity));
        assert ("Cannot find a location using `Most Abundant` strategy!".equals(exception.getMessage()));
    }

}
