package ro.msg.learning.shop.exception;

public class ProductExistsException extends RuntimeException {

    public ProductExistsException(String msg) {
        super(msg);
    }

}
