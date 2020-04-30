package ro.msg.learning.shop;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import ro.msg.learning.shop.entity.*;
import ro.msg.learning.shop.exception.StrategyException;
import ro.msg.learning.shop.service.*;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class CreateOrderProfileIntegrationTest {
    @Autowired
    private StockService stockService;
    @Autowired
    private ProductCategoryService productCategoryService;
    @Autowired
    private ProductService productService;
    @Autowired
    private SupplierService supplierService;
    @Autowired
    private LocationService locationService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private CustomerService customerService;

    @Value("${strategy}")
    private String strategy;

    @Test
    public void creteOrderSuccessfully() {
        init();
        Map<Product, Integer> productQuantity = new HashMap<>();
        Customer customer = customerService.findById(1);
        Order order = Order.builder().id(1).customer(customer).county("Romania").county("Cluj").city("Cluj-Napoca").streetAddress("Strada Testelor, nr. 1").build();
        Supplier supplier = Supplier.builder().id(1).name("ALTEX").build();
        ProductCategory productCategory = ProductCategory.builder().id(1).name("Mouse Hama").build();
        Product product1 = Product.builder().id(1).productCategory(productCategory).supplier(supplier).name("Mouse wireless Hama AM-8000").price(BigDecimal.valueOf(29.99)).weight(0.33).build();
        Product product2 = Product.builder().id(2).productCategory(productCategory).supplier(supplier).name("Mouse gaming Hama uRage Reaper Ess").price(BigDecimal.valueOf(47.75)).weight(0.33).build();
        Product product3 = Product.builder().id(3).productCategory(productCategory).supplier(supplier).name("Mouse wireless gaming Hama uRage Unleashed").price(BigDecimal.valueOf(99.99)).weight(0.33).build();
        Location location2 = Location.builder().id(2).name("Altex").city("Cluj-Napoca").country("Romania").county("Cluj").streetAddress("Galeriile Auchan, Bulevardul Muncii 1-15").build();
        Location location1 = Location.builder().id(1).name("Altex Cluj Iulius Mall").city("Cluj-Napoca").country("Romania").county("Cluj").streetAddress("Strada Alexandru Voievod 53-55").build();
        productQuantity.put(product1, 4);
        productQuantity.put(product2, 2);
        productQuantity.put(product3, 3);
        List<Order> orders = orderService.createOrder(productQuantity, order);
        switch (strategy) {
            case "singleLocation":
                order.setLocation(location2);
                Assert.assertEquals(orders.size(), 1);
                Assert.assertEquals(order.toString(), orders.get(0).toString());
                Optional<Stock> stock1 = stockService.findStockByLocationAndProduct(location2, product1);
                Optional<Stock> stock2 = stockService.findStockByLocationAndProduct(location2, product2);
                Optional<Stock> stock3 = stockService.findStockByLocationAndProduct(location2, product3);
                if (stock1.isPresent() && stock2.isPresent() && stock3.isPresent()) {
                    Assert.assertEquals("36", stock1.get().getQuantity().toString());
                    Assert.assertEquals("33", stock2.get().getQuantity().toString());
                    Assert.assertEquals("77", stock3.get().getQuantity().toString());
                }

            case "mostAbundant":
                List<Order> expectedResult = new ArrayList<>();
                Order order2 = Order.builder().id(2).customer(customer).county("Romania").county("Cluj").city("Cluj-Napoca").streetAddress("Strada Testelor, nr. 1").location(location2).build();
                order.setLocation(location1);
                expectedResult.add(order);
                expectedResult.add(order2);
                Collections.sort(expectedResult);
                Collections.sort(orders);
                Assert.assertEquals(expectedResult, orders);
                stock1 = stockService.findStockByLocationAndProduct(location1, product1);
                stock2 = stockService.findStockByLocationAndProduct(location1, product2);
                stock3 = stockService.findStockByLocationAndProduct(location2, product3);
                if (stock1.isPresent() && stock2.isPresent() && stock3.isPresent()) {
                    Assert.assertEquals("96", stock1.get().getQuantity().toString());
                    Assert.assertEquals("148", stock2.get().getQuantity().toString());
                    Assert.assertEquals("77", stock3.get().getQuantity().toString());
                }
        }
    }

    @Test(expected = StrategyException.class)
    public void creteOrderThrowsException() throws StrategyException {
        init();
        Map<Product, Integer> productQuantity = new HashMap<>();
        Customer customer = customerService.findById(1);
        Order order = Order.builder().id(1).customer(customer).county("Romania").county("Cluj").city("Cluj-Napoca").streetAddress("Strada Testelor, nr. 1").build();
        Supplier supplier = Supplier.builder().id(1).name("ALTEX").build();
        ProductCategory productCategory = ProductCategory.builder().id(1).name("Mouse Hama").build();
        Product product1 = Product.builder().id(1).productCategory(productCategory).supplier(supplier).name("Mouse wireless Hama AM-8000").price(BigDecimal.valueOf(29.99)).weight(0.33).build();
        Product product2 = Product.builder().id(2).productCategory(productCategory).supplier(supplier).name("Mouse gaming Hama uRage Reaper Ess").price(BigDecimal.valueOf(47.75)).weight(0.33).build();
        Product product3 = Product.builder().id(3).productCategory(productCategory).supplier(supplier).name("Mouse wireless gaming Hama uRage Unleashed").price(BigDecimal.valueOf(99.99)).weight(0.33).build();
        Location location2 = Location.builder().id(2).name("Altex").city("Cluj-Napoca").country("Romania").county("Cluj").streetAddress("Galeriile Auchan, Bulevardul Muncii 1-15").build();
        Location location1 = Location.builder().id(1).name("Altex Cluj Iulius Mall").city("Cluj-Napoca").country("Romania").county("Cluj").streetAddress("Strada Alexandru Voievod 53-55").build();
        productQuantity.put(product1, 4);
        productQuantity.put(product2, 2);
        productQuantity.put(product3, 100);
        List<Order> orders = orderService.createOrder(productQuantity, order);
        switch (strategy) {
            case "singleLocation":
                order.setLocation(location2);
                Assert.assertEquals(orders.size(), 1);
                Assert.assertEquals(order.toString(), orders.get(0).toString());
                Optional<Stock> stock1 = stockService.findStockByLocationAndProduct(location2, product1);
                Optional<Stock> stock2 = stockService.findStockByLocationAndProduct(location2, product2);
                Optional<Stock> stock3 = stockService.findStockByLocationAndProduct(location2, product3);
                if (stock1.isPresent() && stock2.isPresent() && stock3.isPresent()) {
                    Assert.assertEquals("36", stock1.get().getQuantity().toString());
                    Assert.assertEquals("33", stock2.get().getQuantity().toString());
                    Assert.assertEquals("77", stock3.get().getQuantity().toString());
                }
            case "mostAbundant":
                List<Order> expectedResult = new ArrayList<>();
                Order order2 = Order.builder().id(2).customer(customer).county("Romania").county("Cluj").city("Cluj-Napoca").streetAddress("Strada Testelor, nr. 1").location(location2).build();
                order.setLocation(location1);
                expectedResult.add(order);
                expectedResult.add(order2);
                Collections.sort(expectedResult);
                Collections.sort(orders);
                Assert.assertEquals(expectedResult, orders);
                stock1 = stockService.findStockByLocationAndProduct(location1, product1);
                stock2 = stockService.findStockByLocationAndProduct(location1, product2);
                stock3 = stockService.findStockByLocationAndProduct(location2, product3);
                if (stock1.isPresent() && stock2.isPresent() && stock3.isPresent()) {
                    Assert.assertEquals("96", stock1.get().getQuantity().toString());
                    Assert.assertEquals("148", stock2.get().getQuantity().toString());
                    Assert.assertEquals("77", stock3.get().getQuantity().toString());
                }
        }
    }

    @Transactional
    private void init() {
        Customer customer = Customer.builder().id(1).firstName("George").lastName("Popescu").build();
        Supplier supplier = Supplier.builder().id(1).name("ALTEX").build();
        ProductCategory productCategory = ProductCategory.builder().id(1).name("Mouse Hama").build();
        Product product1 = Product.builder().id(1).productCategory(productCategory).supplier(supplier).name("Mouse wireless Hama AM-8000").price(BigDecimal.valueOf(29.99)).weight(0.33).build();
        Product product2 = Product.builder().id(2).productCategory(productCategory).supplier(supplier).name("Mouse gaming Hama uRage Reaper Ess").price(BigDecimal.valueOf(47.75)).weight(0.33).build();
        Product product3 = Product.builder().id(3).productCategory(productCategory).supplier(supplier).name("Mouse wireless gaming Hama uRage Unleashed").price(BigDecimal.valueOf(99.99)).weight(0.33).build();
        Location location1 = Location.builder().id(1).name("Altex Cluj Iulius Mall").city("Cluj-Napoca").country("Romania").county("Cluj").streetAddress("Strada Alexandru Voievod 53-55").build();
        Location location2 = Location.builder().id(2).name("Altex").city("Cluj-Napoca").country("Romania").county("Cluj").streetAddress("Galeriile Auchan, Bulevardul Muncii 1-15").build();
        Stock stock1 = Stock.builder().location(location1).product(product1).quantity(100).locationId(location1.getId()).productId(product1.getId()).build();
        Stock stock2 = Stock.builder().location(location1).product(product2).quantity(150).locationId(location1.getId()).productId(product2.getId()).build();
        Stock stock3 = Stock.builder().location(location2).product(product1).quantity(40).locationId(location2.getId()).productId(product1.getId()).build();
        Stock stock4 = Stock.builder().location(location2).product(product2).quantity(35).locationId(location2.getId()).productId(product2.getId()).build();
        Stock stock5 = Stock.builder().location(location2).product(product3).quantity(80).locationId(location2.getId()).productId(product3.getId()).build();
        Stock stock6 = Stock.builder().location(location1).product(product3).quantity(30).locationId(location1.getId()).productId(product3.getId()).build();

        customerService.saveCustomer(customer);
        supplierService.create(supplier);
        productCategoryService.saveProductCategory(productCategory);
        locationService.saveLocation(location1);
        locationService.saveLocation(location2);
        productService.create(product1);
        productService.create(product2);
        productService.create(product3);
        stockService.create(stock1);
        stockService.create(stock2);
        stockService.create(stock3);
        stockService.create(stock4);
        stockService.create(stock5);
        stockService.create(stock6);
    }

}
