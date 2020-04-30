package ro.msg.learning.shop.strategy;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import ro.msg.learning.shop.entity.*;
import ro.msg.learning.shop.exception.StrategyException;
import ro.msg.learning.shop.service.LocationService;
import ro.msg.learning.shop.service.StockService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(MockitoJUnitRunner.class)
public class SingleLocationTest {

    @Mock
    private LocationService locationService;
    @Mock
    private StockService stockService;
    @InjectMocks
    private SingleLocation singleLocation;

    @Test
    public void getLocationDone() {
        Map<Product, Integer> productQuantity = new HashMap<>();
        Supplier supplier = Supplier.builder().name("ALTEX").build();
        ProductCategory productCategory = ProductCategory.builder().name("Mouse Hama").build();
        Product product1 = Product.builder().productCategory(productCategory).supplier(supplier).name("Mouse wireless Hama AM-8000").price(BigDecimal.valueOf(29.99)).weight(0.33).build();
        Product product2 = Product.builder().productCategory(productCategory).supplier(supplier).name("Mouse gaming Hama uRage Reaper Ess").price(BigDecimal.valueOf(47.75)).weight(0.33).build();
        productQuantity.put(product1, 2);
        productQuantity.put(product2, 3);
        Location location1 = Location.builder().name("Altex Cluj Iulius Mall").city("Cluj-Napoca").country("Romania").county("Cluj").streetAddress("Strada Alexandru Voievod 53-55").build();
        Location location2 = Location.builder().name("Altex").city("Cluj-Napoca").country("Romania").county("Cluj").streetAddress("Galeriile Auchan, Bulevardul Muncii 1-15").build();
        Stock stock1 = Stock.builder().location(location1).product(product1).quantity(100).build();
        Stock stock2 = Stock.builder().location(location1).product(product2).quantity(150).build();
        Stock stock3 = Stock.builder().location(location2).product(product1).quantity(40).locationId(location2.getId()).productId(product1.getId()).build();
        Stock stock4 = Stock.builder().location(location2).product(product2).quantity(200).locationId(location2.getId()).productId(product2.getId()).build();
        List<Location> locations = new ArrayList<>();
        locations.add(location1);
        locations.add(location2);
        Mockito.when(locationService.findAll()).thenReturn(locations);
        Mockito.when(stockService.findStockByLocationAndProduct(location1, product1)).thenReturn(java.util.Optional.ofNullable(stock1));
        Mockito.when(stockService.findStockByLocationAndProduct(location1, product2)).thenReturn(java.util.Optional.ofNullable(stock2));
        Mockito.when(stockService.findStockByLocationAndProduct(location2, product1)).thenReturn(java.util.Optional.ofNullable(stock3));
        Mockito.when(stockService.findStockByLocationAndProduct(location2, product2)).thenReturn(java.util.Optional.ofNullable(stock4));

        Map<Location, Map<Product, Integer>> productLocationQuantity = new HashMap<>();
        productLocationQuantity.put(location1, productQuantity);
        assertEquals(singleLocation.getLocation(productQuantity), productLocationQuantity);
    }

    @Test
    public void getLocationTrowsException() {
        Map<Product, Integer> productQuantity = new HashMap<>();
        Supplier supplier = Supplier.builder().name("ALTEX").build();
        ProductCategory productCategory = ProductCategory.builder().name("Mouse Hama").build();
        Product product1 = Product.builder().productCategory(productCategory).supplier(supplier).name("Mouse wireless Hama AM-8000").price(BigDecimal.valueOf(29.99)).weight(0.33).build();
        Product product2 = Product.builder().productCategory(productCategory).supplier(supplier).name("Mouse gaming Hama uRage Reaper Ess").price(BigDecimal.valueOf(47.75)).weight(0.33).build();
        productQuantity.put(product1, 30);
        productQuantity.put(product2, 400);
        Location location1 = Location.builder().name("Altex Cluj Iulius Mall").city("Cluj-Napoca").country("Romania").county("Cluj").streetAddress("Strada Alexandru Voievod 53-55").build();
        Location location2 = Location.builder().name("Altex").city("Cluj-Napoca").country("Romania").county("Cluj").streetAddress("Galeriile Auchan, Bulevardul Muncii 1-15").build();
        List<Location> locations = new ArrayList<>();
        locations.add(location1);
        locations.add(location2);
        Mockito.when(locationService.findAll()).thenReturn(locations);
        Throwable exception = assertThrows(StrategyException.class, () -> singleLocation.getLocation(productQuantity));
        assertEquals("Cannot find a location using `Single Location` strategy!", exception.getMessage());

    }

}
