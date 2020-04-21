package ro.msg.learning.shop.exception;

public class UpdateProductException extends RuntimeException {
    public UpdateProductException() {
        super("Cannot update the product, invalid input values!");
    }
}
