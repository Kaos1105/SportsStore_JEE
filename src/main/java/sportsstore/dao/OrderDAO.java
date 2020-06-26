package sportsstore.dao;

import sportsstore.dto.OrderDTO;
import sportsstore.dto.OrderEnvelopeDTO;
import sportsstore.dto.OrderedProductDTO;
import sportsstore.dto.ProductDTO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OrderDAO extends AbstractDAO {

    public OrderDAO() throws Exception {

    }

    public OrderDAO(Connection conn) {
        super(conn);
    }

    public void writeOrderDTO(OrderDTO orderDTO, ResultSet orderRs) throws Exception {
        orderDTO.setId(orderRs.getInt("id"));
        orderDTO.setPlacementDate(orderRs.getDate("placementDate"));
        orderDTO.setRecipientAddress(orderRs.getString("recipientAddress"));
        orderDTO.setRecipientName(orderRs.getString("recipientName"));
        orderDTO.setRecipientPhone(orderRs.getString("recipientPhone"));
    }

    public void writeOrderedProductDTO(OrderedProductDTO orderedProductDTO, ResultSet rs) throws Exception {
        orderedProductDTO.setQuantity(rs.getInt("quantity"));

        ProductDTO productDTO = new ProductDTO();
        (new ProductDAO()).writeProductDTO(productDTO, rs);

        orderedProductDTO.setProduct(productDTO);
    }

    public List<OrderedProductDTO> getProductsInOrder(Integer id) throws Exception {
        List<OrderedProductDTO> productList = new ArrayList<>();
        try {
            String productListQuery = "EXEC USP_GetProductsInOrder ?";
            ResultSet productListRs = OrderDAO.super.ExecuteQuery(productListQuery, new Object[] { id });

            while (productListRs.next()) {
                OrderedProductDTO orderedProductDTO = new OrderedProductDTO();
                writeOrderedProductDTO(orderedProductDTO, productListRs);

                productList.add(orderedProductDTO);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            // throw e;
        }
        return productList;
    }

    public List<OrderDTO> getAll() throws Exception {
        ArrayList<OrderDTO> orderDTOList = new ArrayList<>();
        try {
            String orderQuery = "SELECT * FROM [ORDER]";
            ResultSet orderRs = OrderDAO.super.ExecuteQuery(orderQuery, null);

            OrderDTO orderDTO = new OrderDTO();
            while (orderRs.next()) {
                writeOrderDTO(orderDTO, orderRs);
            }
            orderDTOList.add(orderDTO);
        } catch (Exception e) {
            System.out.println(e.toString());
            // throw e;
        }
        return orderDTOList;
    }

    public OrderEnvelopeDTO getFiltered(int offset, int limit, String name, String address, String phone,
            Date placementDate) throws Exception {
        OrderEnvelopeDTO orderEnvelope = new OrderEnvelopeDTO();
        List<OrderDTO> orderDTOList = new ArrayList<>();

        try {
            String query = "EXEC USP_FilterOrder ? , ? , ? , ?";
            ResultSet rs = OrderDAO.super.ExecuteQuery(query, new Object[] { name, address, phone, placementDate });
            while (rs.next()) {
                OrderDTO orderDTO = new OrderDTO();
                writeOrderDTO(orderDTO, rs);
                orderDTOList.add(orderDTO);
            }
            orderEnvelope.setResultCount(orderDTOList.size());
            if (limit != 0)
                orderDTOList = orderDTOList.stream().skip(offset) // Equivalent to SQL's offset
                        .limit(limit) // Equivalent to SQL's limit
                        .collect(Collectors.toList());
            orderEnvelope.setProducts(orderDTOList);
        } catch (Exception e) {
            System.out.println(e.toString());
            // throw e;
        }
        return orderEnvelope;
    }

    public OrderDTO get(Integer id) throws Exception {
        OrderDTO orderDTO = new OrderDTO();
        List<OrderedProductDTO> orderedProductDTOList = new ArrayList<>();
        try {
            String orderQuery = "SELECT * FROM [ORDER] WHERE ID = " + id;
            ResultSet orderRs = OrderDAO.super.ExecuteQuery(orderQuery, null);

            if (orderRs.next()) {
                writeOrderDTO(orderDTO, orderRs);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            // throw e;
        }
        return orderDTO;
    }

    public boolean create(OrderDTO input) throws Exception {
        try {
            String query = "EXEC USP_InsertOrder ? , ? , ? , ?";
            ResultSet rs = OrderDAO.super.ExecuteQuery(query, new Object[] { input.getPlacementDate(),
                    input.getRecipientName(), input.getRecipientAddress(), input.getRecipientPhone(), });
            int createdId = -1;
            if (rs.next()) {
                createdId = rs.getInt("id");
            }
            if (createdId == -1)
                throw new Exception();

            for (OrderedProductDTO product : input.getProducts()) {
                String productQuery = "EXEC USP_InsertOrderedProduct ? , ? , ?";
                OrderDAO.super.ExecuteNonQuery(productQuery,
                        new Object[] { product.getProduct().getId(), createdId, product.getQuantity() });
            }
            return true;
        } catch (Exception e) {
            System.out.println(e.toString());
            // throw e;
            return false;
        }
    }

    public boolean edit(OrderDTO input) throws Exception {
        try {
            String query = "EXEC USP_UpdateOrder ? , ? , ? , ? , ?";
            OrderDAO.super.ExecuteNonQuery(query, new Object[] { input.getId(), input.getPlacementDate(),
                    input.getRecipientName(), input.getRecipientAddress(), input.getRecipientPhone() });

            for (OrderedProductDTO product : input.getProducts()) {
                String productQuery = "EXEC USP_UpdateOrderedProduct ? , ? , ?";
                OrderDAO.super.ExecuteNonQuery(query,
                        new Object[] { product.getProduct().getId(), input.getId(), product.getQuantity() });
            }
            return true;
        } catch (Exception e) {
            System.out.println(e.toString());
            // throw e;
            return false;
        }
    }

    public boolean remove(Integer id) throws Exception {
        try {
            String query = "EXEC USP_DeleteOrder ?";
            OrderDAO.super.ExecuteNonQuery(query, new Object[] { id });

            return true;
        } catch (Exception e) {
            System.out.println(e.toString());
            // throw e;
            return false;
        }
    }
}
