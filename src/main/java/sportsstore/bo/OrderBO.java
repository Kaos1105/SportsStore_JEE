package sportsstore.bo;

import sportsstore.dao.OrderDAO;
import sportsstore.dao.ProductDAO;
import sportsstore.dto.OrderDTO;
import sportsstore.dto.OrderEnvelopeDTO;
import sportsstore.dto.OrderedProductDTO;
import sportsstore.dto.ProductDTO;

import java.sql.Date;
import java.util.List;

public class OrderBO {

    public List<OrderDTO> getAllOrders() throws Exception {
        OrderDAO orderDAO = null;
        PhotoBO photoBO = null;

        try {
            orderDAO = new OrderDAO();
            photoBO = new PhotoBO();

            List<OrderDTO> result = orderDAO.getAll();
            for (OrderDTO orderDTO : result) {
                List<OrderedProductDTO> productsList = orderDAO.getProductsInOrder(orderDTO.getId());
                for (OrderedProductDTO productDTO : productsList) {
                    productDTO.getProduct()
                            .setImage(photoBO.getMainPhotoURL(productDTO.getProduct().getId().toString()));
                }
                orderDTO.setProducts(productsList);
            }

            return result;
        } catch (Exception e) {
            throw e;
        } finally {
            orderDAO.closeConnection();
        }
    }

    public OrderEnvelopeDTO getFilteredOrders(int offset, int limit, String name, String address, String phone,
            String date, String status) throws Exception {
        OrderDAO orderDAO = null;
        PhotoBO photoBO = null;
        Date placementDate = null;
        if (date != null)
            placementDate = Date.valueOf(date);

        try {
            orderDAO = new OrderDAO();
            photoBO = new PhotoBO();
            OrderEnvelopeDTO result = orderDAO.getFiltered(offset, limit, name, address, phone, placementDate, status);

            // set products in orders
            for (OrderDTO orderDTO : result.getOrders()) {
                List<OrderedProductDTO> productsList = orderDAO.getProductsInOrder(orderDTO.getId());
                for (OrderedProductDTO productDTO : productsList) {
                    productDTO.getProduct()
                            .setImage(photoBO.getMainPhotoURL(productDTO.getProduct().getId().toString()));
                }
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
        PhotoBO photoBO = null;

        try {
            orderDAO = new OrderDAO();
            photoBO = new PhotoBO();
            List<OrderedProductDTO> productsList = orderDAO.getProductsInOrder(id);
            for (OrderedProductDTO productDTO : productsList) {
                productDTO.getProduct().setImage(photoBO.getMainPhotoURL(productDTO.getProduct().getId().toString()));
            }

            OrderDTO result = orderDAO.get(id);
            result.setProducts(productsList);

            return result;
        } catch (Exception e) {
            throw e;
        } finally {
            orderDAO.closeConnection();
        }
    }

    public boolean createOrder(OrderDTO newOrder) throws Exception {
        OrderDAO orderDAO = null;
        try {
            orderDAO = new OrderDAO();
            return orderDAO.create(newOrder);
        } catch (Exception e) {
            throw e;
        } finally {
            orderDAO.closeConnection();
        }
    }

    public boolean createOrderedProduct(OrderDTO newOrder) throws Exception {
        OrderDAO orderDAO = null;
        try {
            orderDAO = new OrderDAO();
            return orderDAO.createOrderedProduct(newOrder);
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

    public boolean editOrderedProduct(Integer id, OrderDTO orderDTO) throws Exception {
        OrderDAO orderDAO = null;

        try {
            orderDTO.setId(id);
            orderDAO = new OrderDAO();
            OrderDTO result = orderDAO.get(id);
            if (result == null)
                return false;

            if (orderDAO.getProductsInOrder(id).isEmpty()) {
                if (orderDAO.createOrderedProduct(orderDTO)) {
                    if (!result.getStatus().equals("Canceled")) {
                        if (!EditOrderedProductQuantity(orderDTO, false))
                            return false;
                    }
                    return true;
                }
            } else {
                if (orderDAO.removeOrderedProduct(id)) {
                    if (orderDAO.createOrderedProduct(orderDTO)) {
                        if ((result.getStatus().equals("Processing") && orderDTO.getStatus().equals("Canceled"))) {
                            if (!EditOrderedProductQuantity(orderDTO, true))
                                return false;
                        }
                        return true;
                    }
                }
                return true;
            }
        } catch (Exception e) {
            throw e;
        } finally {
            orderDAO.closeConnection();
        }
        return false;
    }

    public boolean EditOrderedProductQuantity(OrderDTO orderDTO, boolean isPlus) throws Exception {
        ProductDAO productDAO = null;
        try {
            productDAO = new ProductDAO();

            for (OrderedProductDTO product : orderDTO.getProducts()) {
                ProductDTO updateProduct = productDAO.get(product.getProduct().getId());
                if (updateProduct == null)
                    return false;

                if (isPlus)
                    updateProduct.setStock(updateProduct.getStock() + product.getQuantity());
                else
                    updateProduct.setStock(updateProduct.getStock() - product.getQuantity());

                if (!productDAO.edit(updateProduct))
                    return false;
            }
        } catch (Exception e) {
            throw e;
        } finally {
            productDAO.closeConnection();
        }
        return true;
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
