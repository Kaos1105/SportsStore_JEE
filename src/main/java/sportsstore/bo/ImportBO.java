package sportsstore.bo;

import sportsstore.dao.ImportDAO;
import sportsstore.dao.ProductDAO;
import sportsstore.dto.ImportDTO;
import sportsstore.dto.ImportEnvelopeDTO;
import sportsstore.dto.ImportedProductDTO;
import sportsstore.dto.ProductDTO;

import java.sql.Date;
import java.util.List;

public class ImportBO {

    public List<ImportDTO> getAllImports() throws Exception {
        ImportDAO importDAO = null;
        PhotoBO photoBO = null;

        try {
            importDAO = new ImportDAO();
            photoBO = new PhotoBO();

            List<ImportDTO> result = importDAO.getAll();
            for (ImportDTO importDTO : result) {
                List<ImportedProductDTO> productsList = importDAO.getProductsInImport(importDTO.getId());
                for (ImportedProductDTO productDTO : productsList) {
                    productDTO.getProduct()
                            .setImage(photoBO.getMainPhotoURL(productDTO.getProduct().getId().toString()));
                }
                importDTO.setProducts(productsList);
            }

            return result;
        } catch (Exception e) {
            throw e;
        } finally {
            importDAO.closeConnection();
        }
    }

    public ImportEnvelopeDTO getFilteredImports(int offset, int limit, String name, String address, String phone,
                                               String date) throws Exception {
        ImportDAO importDAO = null;
        PhotoBO photoBO = null;
        Date placementDate = null;
        if (date != null)
            placementDate = Date.valueOf(date);

        try {
            importDAO = new ImportDAO();
            photoBO = new PhotoBO();
            ImportEnvelopeDTO result = importDAO.getFiltered(offset, limit, name, address, phone, placementDate);

            // set products in orders
            for (ImportDTO importDTO : result.getImports()) {
                List<ImportedProductDTO> productsList = importDAO.getProductsInImport(importDTO.getId());
                for (ImportedProductDTO productDTO : productsList) {
                    productDTO.getProduct()
                            .setImage(photoBO.getMainPhotoURL(productDTO.getProduct().getId().toString()));
                }
                importDTO.setProducts(productsList);
            }
            return result;
        } catch (Exception e) {
            throw e;
        } finally {
            importDAO.closeConnection();
        }
    }

    public ImportDTO getImportById(Integer id) throws Exception {
        ImportDAO importDAO = null;
        PhotoBO photoBO = null;

        try {
            importDAO = new ImportDAO();
            photoBO = new PhotoBO();
            List<ImportedProductDTO> productsList = importDAO.getProductsInImport(id);
            for (ImportedProductDTO productDTO : productsList) {
                productDTO.getProduct().setImage(photoBO.getMainPhotoURL(productDTO.getProduct().getId().toString()));
            }

            ImportDTO result = importDAO.get(id);
            result.setProducts(productsList);

            return result;
        } catch (Exception e) {
            throw e;
        } finally {
            importDAO.closeConnection();
        }
    }

    public boolean createImport(ImportDTO newImport) throws Exception {
        ImportDAO importDAO = null;
        try {
            importDAO = new ImportDAO();
            return importDAO.create(newImport);
        } catch (Exception e) {
            throw e;
        } finally {
            importDAO.closeConnection();
        }
    }

    public boolean createImportedProduct(ImportDTO newImport) throws Exception {
        ImportDAO importDAO = null;
        try {
            importDAO = new ImportDAO();

            return importDAO.createImportedProduct(newImport);
        } catch (Exception e) {
            throw e;
        } finally {
            importDAO.closeConnection();
        }
    }

    public boolean editImport(Integer id, ImportDTO importDTO) throws Exception {
        ImportDAO importDAO = null;
        try {
            importDTO.setId(id);
            importDAO = new ImportDAO();
            ImportDTO result = importDAO.get(id);
            if (result == null)
                return false;
            return importDAO.edit(importDTO);
        } catch (Exception e) {
            throw e;
        } finally {
            importDAO.closeConnection();
        }
    }

    public boolean editImportedProduct(Integer id, ImportDTO importDTO) throws Exception {
        ImportDAO importDAO = null;
        ProductDAO productDAO = null;
        try {
            importDTO.setId(id);
            importDAO = new ImportDAO();
            productDAO = new ProductDAO();
            if (importDAO.get(id) == null)
                return false;
            if (importDAO.removeImportedProduct(id)) {
                if (importDAO.createImportedProduct(importDTO))
                {
                    for (ImportedProductDTO product : importDTO.getProducts())
                    {
                        ProductDTO updateProduct = productDAO.get(product.getProduct().getId());
                        if (updateProduct == null)
                            return false;

                        updateProduct.setStock(updateProduct.getStock() + product.getQuantity());
                        if (!productDAO.edit(updateProduct))
                            return false;
                    }

                    return true;
                }
            }
        } catch (Exception e) {
            throw e;
        } finally {
            importDAO.closeConnection();
            productDAO.closeConnection();
        }
        return false;
    }

    public boolean removeImport(Integer id) throws Exception {
        ImportDAO importDAO = null;
        try {
            importDAO = new ImportDAO();
            ImportDTO result = importDAO.get(id);
            if (result == null)
                return false;
            return importDAO.remove(id);
        } catch (Exception e) {
            throw e;
        } finally {
            importDAO.closeConnection();
        }
    }
}
