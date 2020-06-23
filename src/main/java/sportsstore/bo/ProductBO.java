/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportsstore.bo;

import java.util.List;

import sportsstore.dao.PhotoDAO;
import sportsstore.dao.ProductDAO;
import sportsstore.dao.ProductOptionsDAO;
import sportsstore.dto.PhotoDTO;
import sportsstore.dto.ProductDTO;
import sportsstore.dto.ProductEnvelopeDTO;
import sportsstore.dto.ProductOptionsDTO;

/**
 *
 * @author Max
 */
public class ProductBO {

    public List<ProductDTO> getAllProducts() throws Exception {
        ProductDAO productDAO = null;
        PhotoDAO photoDAO = null;

        try {
            productDAO = new ProductDAO();
            photoDAO = new PhotoDAO();

            List<ProductDTO> result = productDAO.getAll();

            // set product photos, image
            for (ProductDTO productDTO : result) {
                productDTO.setPhotos(photoDAO.getAll(productDTO.getId().toString()));
                if (!productDTO.getPhotos().isEmpty()) {
                    for (PhotoDTO photoDTO : productDTO.getPhotos()) {
                        if (photoDTO.isMain())
                            productDTO.setImage(photoDTO.getUrl());
                    }
                }
            }
            return result;
        } catch (Exception e) {
            throw e;
        } finally {
            productDAO.closeConnection();
        }
    }

    public ProductEnvelopeDTO getFilteredProducts(int offset, int limit, String name, String brand, String category,
            int stock) throws Exception {
        ProductDAO productDAO = null;
        PhotoDAO photoDAO = null;

        try {
            productDAO = new ProductDAO();
            photoDAO = new PhotoDAO();

            ProductEnvelopeDTO result = productDAO.getFiltered(offset, limit, name, brand, category, stock);

            // set product photos, image
            for (ProductDTO productDTO : result.getProducts()) {
                productDTO.setPhotos(photoDAO.getAll(productDTO.getId().toString()));
                if (!productDTO.getPhotos().isEmpty()) {
                    for (PhotoDTO photoDTO : productDTO.getPhotos()) {
                        if (photoDTO.isMain())
                            productDTO.setImage(photoDTO.getUrl());
                    }
                }
            }
            return result;
        } catch (Exception e) {
            throw e;
        } finally {
            productDAO.closeConnection();
        }
    }

    public ProductOptionsDTO getProductOptions() throws Exception {
        ProductOptionsDAO productOptionsDAO = null;
        try {
            productOptionsDAO = new ProductOptionsDAO();
            return productOptionsDAO.get();
        } catch (Exception e) {
            throw e;
        } finally {
            productOptionsDAO.closeConnection();
        }

    }

    public ProductDTO getProductById(Integer id) throws Exception {
        ProductDAO productDAO = null;
        PhotoDAO photoDAO = null;

        try {
            productDAO = new ProductDAO();
            photoDAO = new PhotoDAO();

            ProductDTO result = productDAO.get(id);
            // set product photos, image
            result.setPhotos(photoDAO.getAll(result.getId().toString()));
            if (!result.getPhotos().isEmpty()) {
                for (PhotoDTO photoDTO : result.getPhotos()) {
                    if (photoDTO.isMain())
                        result.setImage(photoDTO.getUrl());
                }
            }

            return result;
        } catch (Exception e) {
            throw e;
        } finally {
            productDAO.closeConnection();
        }
    }

    public boolean createProduct(ProductDTO newProduct) throws Exception {
        ProductDAO productDAO = null;
        try {
            productDAO = new ProductDAO();
            return productDAO.create(newProduct);
        } catch (Exception e) {
            throw e;
        } finally {
            productDAO.closeConnection();
        }
    }

    public boolean editProduct(Integer id, ProductDTO product) throws Exception {
        ProductDAO productDAO = null;
        try {
            product.setId(id);
            productDAO = new ProductDAO();
            ProductDTO result = productDAO.get(id);
            if (result.getName() == null)
                return false;
            return productDAO.edit(product);
        } catch (Exception e) {
            throw e;
        } finally {
            productDAO.closeConnection();
        }
    }

    public boolean removeProduct(Integer id) throws Exception {
        ProductDAO productDAO = null;
        try {
            productDAO = new ProductDAO();
            ProductDTO result = productDAO.get(id);
            if (result.getName() == null)
                return false;
            return productDAO.remove(id);
        } catch (Exception e) {
            throw e;
        } finally {
            productDAO.closeConnection();
        }
    }
}
