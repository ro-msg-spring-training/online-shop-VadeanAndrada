package ro.msg.learning.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.entity.*;
import ro.msg.learning.shop.service.*;

import javax.transaction.Transactional;
import java.math.BigDecimal;


@RestController
@Profile("test")
@RequestMapping("/test")
@RequiredArgsConstructor
public class OrderProfileController {
    private final StockService stockService;
    private final ProductCategoryService productCategoryService;
    private final ProductService productService;
    private final SupplierService supplierService;
    private final LocationService locationService;
    private final OrderService orderService;
    private final CustomerService customerService;
    private final OrderDetailService orderDetailService;

    @PostMapping(value = "/populate", produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public ResponseEntity<String> populateDb() {
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

        HttpHeaders headers = new HttpHeaders();
        headers.add("Accepted", MediaType.APPLICATION_JSON_VALUE);
        return ResponseEntity.accepted().headers(headers).body("Database created successfully!");
    }

    @DeleteMapping(value = "/deleteAll", produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public ResponseEntity<String> deleteAllFromDb() {
        orderDetailService.deleteAll();
        stockService.deleteAllStocks();
        locationService.deleteAll();
        customerService.deleteAll();
        orderDetailService.deleteAll();
        productService.deleteAll();
        supplierService.deleteAll();
        productCategoryService.deleteAll();
        orderService.deleteAll();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Accepted", MediaType.APPLICATION_JSON_VALUE);
        return ResponseEntity.accepted().headers(headers).body("Database deleted successfully!");
    }
}
