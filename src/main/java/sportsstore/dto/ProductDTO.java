/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportsstore.dto;

/**
 *
 * @author nguye
 */
public class ProductDTO {
    private int id;
    private String name;
    private String notes;

    public ProductDTO() {
    }

    public ProductDTO(int id, String name, String notes) {
        this.id = id;
        this.name = name;
        this.notes = notes;
    }
    
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getNotes() {
        return notes;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
    
}
