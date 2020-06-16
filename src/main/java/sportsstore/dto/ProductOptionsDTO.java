package sportsstore.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ProductOptionsDTO")
public class ProductOptionsDTO {
    private List<String> categoryOptions;
    private List<String> brandOptions;

    public List<String> getCategoryOptions() {
        return categoryOptions;
    }

    public List<String> getBrandOptions() {
        return brandOptions;
    }

    public void setBrandOptions(List<String> brandOptions) {
        this.brandOptions = brandOptions;
    }

    public void setCategoryOptions(List<String> categoryOptions) {
        this.categoryOptions = categoryOptions;
    }
}