package ro.msg.learning.shop.exception;

public class ProductExistsException extends RuntimeException {

    public ProductExistsException(String msg) {
        super(msg);
    }
    public ProductExistsException() {
        super("There is a product with the same name");
    }

}
