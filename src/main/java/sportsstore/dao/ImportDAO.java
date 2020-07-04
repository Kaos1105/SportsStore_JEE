package sportsstore.dao;

import sportsstore.dto.ImportDTO;
import sportsstore.dto.ImportEnvelopeDTO;
import sportsstore.dto.ImportedProductDTO;
import sportsstore.dto.ProductDTO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ImportDAO extends AbstractDAO {

    public ImportDAO() throws Exception {

    }

    public ImportDAO(Connection conn) {
        super(conn);
    }

    public void writeImportDTO(ImportDTO importDTO, ResultSet imports) throws Exception {
        importDTO.setId(imports.getInt("id"));
        importDTO.setPlacementDate(imports.getDate("placementDate"));
        importDTO.setWholesalerName(imports.getString("wholesalerName"));
        importDTO.setWholesalerAddress(imports.getString("wholesalerAddress"));
        importDTO.setWholesalerPhone(imports.getString("wholesalerPhone"));
        importDTO.setStatus(imports.getString("status"));
    }

    public void writeImportedProductDTO(ImportedProductDTO importedProductDTO, ResultSet rs) throws Exception {
        importedProductDTO.setQuantity(rs.getInt("quantity"));

        ProductDTO productDTO = new ProductDTO();

        ProductDAO dummy = new ProductDAO();
        dummy.writeProductDTO(productDTO, rs);
        dummy.closeConnection();

        importedProductDTO.setProduct(productDTO);
    }

    public List<ImportedProductDTO> getProductsInImport(Integer id) throws Exception {
        List<ImportedProductDTO> productList = new ArrayList<>();
        try {
            String productListQuery = "EXEC USP_GetProductsInImport ?";
            ResultSet productListRs = ImportDAO.super.ExecuteQuery(productListQuery, new Object[] { id });

            while (productListRs.next()) {
                ImportedProductDTO importedProductDTO = new ImportedProductDTO();
                writeImportedProductDTO(importedProductDTO, productListRs);

                productList.add(importedProductDTO);
            }
        } catch (Exception e) {
            e.printStackTrace();
            // throw e;
        }
        return productList;
    }

    public List<ImportDTO> getAll() throws Exception {
        ArrayList<ImportDTO> importDTOArrayList = new ArrayList<>();
        try {
            String orderQuery = "SELECT * FROM [Import]";
            ResultSet orderRs = ImportDAO.super.ExecuteQuery(orderQuery, null);

            ImportDTO importDTO = new ImportDTO();
            while (orderRs.next()) {
                writeImportDTO(importDTO, orderRs);
            }
            importDTOArrayList.add(importDTO);
        } catch (Exception e) {
            e.printStackTrace();
            // throw e;
        }
        return importDTOArrayList;
    }

    public ImportEnvelopeDTO getFiltered(int offset, int limit, String name, String address, String phone,
            Date placementDate, String status) throws Exception {
        ImportEnvelopeDTO importEnvelopeDTO = new ImportEnvelopeDTO();
        List<ImportDTO> importDTOArrayList = new ArrayList<>();

        try {
            String query = "EXEC USP_FilterImport ? , ? , ? , ? , ?";
            ResultSet rs = ImportDAO.super.ExecuteQuery(query,
                    new Object[] { name, address, phone, placementDate, status });
            while (rs.next()) {
                ImportDTO importDTO = new ImportDTO();
                writeImportDTO(importDTO, rs);
                importDTOArrayList.add(importDTO);
            }
            importEnvelopeDTO.setResultCount(importDTOArrayList.size());
            if (limit != 0)
                importDTOArrayList = importDTOArrayList.stream().skip(offset) // Equivalent to SQL's offset
                        .limit(limit) // Equivalent to SQL's limit
                        .collect(Collectors.toList());
            importEnvelopeDTO.setImports(importDTOArrayList);
        } catch (Exception e) {
            e.printStackTrace();
            // throw e;
        }
        return importEnvelopeDTO;
    }

    public ImportDTO get(Integer id) throws Exception {
        ImportDTO importDTO = new ImportDTO();
        try {
            String query = "SELECT * FROM [Import] WHERE ID = " + id;
            ResultSet rs = ImportDAO.super.ExecuteQuery(query, null);

            if (rs.next()) {
                writeImportDTO(importDTO, rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
            // throw e;
        }
        return importDTO;
    }

    public boolean create(ImportDTO input) throws Exception {
        try {
            String query = "EXEC USP_InsertImport ? , ? , ? , ?";
            if (ImportDAO.super.ExecuteNonQuery(query, new Object[] { input.getPlacementDate(),
                    input.getWholesalerName(), input.getWholesalerAddress(), input.getWholesalerPhone(), }) == 1)
                return true;
        } catch (Exception e) {
            e.printStackTrace();
            // throw e;
        }
        return false;
    }

    public boolean createImportedProduct(ImportDTO input) throws Exception {
        try {
            boolean result = true;
            for (ImportedProductDTO product : input.getProducts()) {
                String productQuery = "EXEC USP_InsertImportedProduct ? , ? , ?";
                if (ImportDAO.super.ExecuteNonQuery(productQuery,
                        new Object[] { input.getId(), product.getProduct().getId(), product.getQuantity() }) != 1)
                    result = false;
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            // throw e;
        }
        return false;
    }

    public boolean edit(ImportDTO input) throws Exception {
        try {
            String query = "EXEC USP_UpdateImport ? , ? , ? , ? , ?";
            if (ImportDAO.super.ExecuteNonQuery(query, new Object[] { input.getId(), input.getPlacementDate(),
                    input.getWholesalerName(), input.getWholesalerAddress(), input.getWholesalerPhone() }) == 1)
                return true;
        } catch (Exception e) {
            e.printStackTrace();
            // throw e;
        }
        return false;
    }

    public boolean remove(Integer id) throws Exception {
        try {
            String query = "EXEC USP_DeleteImport ?";
            ImportDAO.super.ExecuteNonQuery(query, new Object[] { id });

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            // throw e;
        }
        return false;
    }

    public boolean removeImportedProduct(Integer id) throws Exception {
        try {
            String query = "EXEC USP_DeleteImportedProduct ?";
            ImportDAO.super.ExecuteNonQuery(query, new Object[] { id });

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            // throw e;
        }
        return false;
    }
}
