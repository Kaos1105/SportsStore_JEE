package sportsstore.dto;

public class OrderedProductDTO {
    private ProductDTO product;
    private int quantity;

    public ProductDTO getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
    }
}
