package sportsstore.bo;

import sportsstore.dao.OrderDAO;
import sportsstore.dto.OrderDTO;
import sportsstore.dto.OrderEnvelopeDTO;
import sportsstore.dto.OrderedProductDTO;
import java.util.ArrayList;
import java.util.List;

public class OrderBO {

    public List<OrderDTO> getAllOrders() throws Exception {
        OrderDAO orderDAO = null;

        try {
            orderDAO = new OrderDAO();

            List<OrderDTO> result = orderDAO.getAll();
            for (OrderDTO orderDTO : result) {
                ArrayList<OrderedProductDTO> productsList = (ArrayList) orderDAO.getProductsInOrder(orderDTO.getId());
                orderDTO.setProducts(productsList);
            }

            return result;
        } catch (Exception e) {
            throw e;
        } finally {
            orderDAO.closeConnection();
        }
    }

    public OrderEnvelopeDTO getFilteredOrders(int offset, int limit) throws Exception {
        OrderDAO orderDAO = null;

        try {
            orderDAO = new OrderDAO();
            OrderEnvelopeDTO result = orderDAO.getFiltered(offset, limit);

            // set products in orders
            for (OrderDTO orderDTO : result.getOrders()) {
                ArrayList<OrderedProductDTO> productsList = (ArrayList) orderDAO.getProductsInOrder(orderDTO.getId());
                orderDTO.setProducts(productsList);
            }
            return result;
        } catch (Exception e) {
            throw e;
        } finally {
            orderDAO.closeConnection();
        }
    }

    public OrderDTO getOrderById(Integer id) throws Exception {
        OrderDAO orderDAO = null;

        try {
            orderDAO = new OrderDAO();
            ArrayList<OrderedProductDTO> productsList = (ArrayList) orderDAO.getProductsInOrder(id);

            OrderDTO result = orderDAO.get(id);
            result.setProducts(productsList);

            return result;
        } catch (Exception e) {
            throw e;
        } finally {
            orderDAO.closeConnection();
        }
    }

    public boolean createOrder(OrderDTO newProduct) throws Exception {
        OrderDAO orderDAO = null;
        try {
            orderDAO = new OrderDAO();
            return orderDAO.create(newProduct);
        } catch (Exception e) {
            throw e;
        } finally {
            orderDAO.closeConnection();
        }
    }

    public boolean editOrder(Integer id, OrderDTO order) throws Exception {
        OrderDAO orderDAO = null;
        try {
            order.setId(id);
            orderDAO = new OrderDAO();
            OrderDTO result = orderDAO.get(id);
            if (result == null)
                return false;
            return orderDAO.edit(order);
        } catch (Exception e) {
            throw e;
        } finally {
            orderDAO.closeConnection();
        }
    }

    public boolean removeOrder(Integer id) throws Exception {
        OrderDAO orderDAO = null;
        try {
            orderDAO = new OrderDAO();
            OrderDTO result = orderDAO.get(id);
            if (result == null)
                return false;
            return orderDAO.remove(id);
        } catch (Exception e) {
            throw e;
        } finally {
            orderDAO.closeConnection();
        }
    }
}
