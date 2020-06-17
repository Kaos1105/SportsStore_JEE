package sportsstore.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "PhotoDTO")
public class PhotoDTO {
    private String id;
    private String url;
    private boolean isMain;
    private String productId;

    public PhotoDTO() {
    }

    public PhotoDTO(String id, String url, String productId) {
        this.id = id;
        this.url = url;
        this.productId = productId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getId() {
        return id;
    }

    public boolean isMain() {
        return isMain;
    }

    public void setMain(boolean isMain) {
        this.isMain = isMain;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setId(String id) {
        this.id = id;
    }
}