/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportsstore.dto;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author nguyenThanhDanh
 */
@XmlRootElement
public class ProductDTO {
    private int id;
    private String name;
    private String brand;
    private String category;
    private double price;
    private double importPrice;
    private int stock;
    private Date dateAdded;

    public ProductDTO() {
    }

    public ProductDTO(final int id, final String name, final String brand, final String category, final double price,
            final double importPrice, final int stock, final Date dateAdded) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.category = category;
        this.price = price;
        this.importPrice = importPrice;
        this.stock = stock;
        this.dateAdded = dateAdded;
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(final String brand) {
        this.brand = brand;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(final String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(final double price) {
        this.price = price;
    }

    public double getImportPrice() {
        return importPrice;
    }

    public void setImportPrice(final double importPrice) {
        this.importPrice = importPrice;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
