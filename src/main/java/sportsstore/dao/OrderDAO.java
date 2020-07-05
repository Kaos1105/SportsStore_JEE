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

    public void writeOrderDTO(OrderDTO orderDTO, ResultSet orders) throws Exception {
        orderDTO.setId(orders.getInt("id"));
        orderDTO.setPlacementDate(orders.getDate("placementDate"));
        orderDTO.setRecipientAddress(orders.getString("recipientAddress"));
        orderDTO.setRecipientName(orders.getString("recipientName"));
        orderDTO.setRecipientPhone(orders.getString("recipientPhone"));
        orderDTO.setStatus(orders.getString("status"));
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
            e.printStackTrace();
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
            e.printStackTrace();
            // throw e;
        }
        return orderDTOList;
    }

    public OrderEnvelopeDTO getFiltered(int offset, int limit, String name, String address, String phone,
            Date placementDate, String status) throws Exception {
        OrderEnvelopeDTO orderEnvelope = new OrderEnvelopeDTO();
        List<OrderDTO> orderDTOList = new ArrayList<>();

        try {
            String query = "EXEC USP_FilterOrder ? , ? , ? , ? , ?";
            ResultSet rs = OrderDAO.super.ExecuteQuery(query,
                    new Object[] { name, address, phone, placementDate, status });
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
            e.printStackTrace();
            // throw e;
        }
        return orderEnvelope;
    }

    public OrderDTO get(Integer id) throws Exception {
        OrderDTO orderDTO = new OrderDTO();
        try {
            String orderQuery = "SELECT * FROM [ORDER] WHERE ID = " + id;
            ResultSet orderRs = OrderDAO.super.ExecuteQuery(orderQuery, null);

            if (orderRs.next()) {
                writeOrderDTO(orderDTO, orderRs);
            }
        } catch (Exception e) {
            e.printStackTrace();
            // throw e;
        }
        return orderDTO;
    }

    public boolean create(OrderDTO input) throws Exception {
        try {
            String query = "EXEC USP_InsertOrder ? , ? , ? , ? , ?";
            if (OrderDAO.super.ExecuteNonQuery(query, new Object[] { input.getPlacementDate(), input.getRecipientName(),
                    input.getRecipientAddress(), input.getRecipientPhone(), input.getStatus() }) == 1)
                return true;
        } catch (Exception e) {
            e.printStackTrace();
            // throw e;
        }
        return false;
    }

    public boolean createOrderedProduct(OrderDTO input) throws Exception {
        try {
            boolean result = true;
            for (OrderedProductDTO product : input.getProducts()) {
                String productQuery = "EXEC USP_InsertOrderedProduct ? , ? , ?";
                if (OrderDAO.super.ExecuteNonQuery(productQuery,
                        new Object[] { product.getProduct().getId(), input.getId(), product.getQuantity() }) != 1)
                    result = false;
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            // throw e;
        }
        return false;
    }

    public boolean edit(OrderDTO input) throws Exception {
        try {
            String query = "EXEC USP_UpdateOrder ? , ? , ? , ? , ? , ?";
            if (OrderDAO.super.ExecuteNonQuery(query,
                    new Object[] { input.getId(), input.getPlacementDate(), input.getRecipientName(),
                            input.getRecipientAddress(), input.getRecipientPhone(), input.getStatus() }) == 1)
                return true;
        } catch (Exception e) {
            e.printStackTrace();
            // throw e;
        }
        return false;
    }

    public boolean remove(Integer id) throws Exception {
        try {
            String query = "EXEC USP_DeleteOrder ?";
            OrderDAO.super.ExecuteNonQuery(query, new Object[] { id });

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            // throw e;
        }
        return false;
    }

    public boolean removeOrderedProduct(Integer id) throws Exception {
        try {
            String query = "EXEC USP_DeleteOrderedProduct ?";
            OrderDAO.super.ExecuteNonQuery(query, new Object[] { id });

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            // throw e;
        }
        return false;
    }
}
