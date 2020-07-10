package sportsstore.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import sportsstore.dto.RevenueDTO;

public class RevenueDAO extends AbstractDAO {
    public RevenueDAO() throws Exception {

    }

    public RevenueDAO(Connection conn) {
        super(conn);
    }

    public void writeRevenueDTO(RevenueDTO revenueDTO, ResultSet rs) throws Exception {
        revenueDTO.setYear(rs.getInt("Year"));
        revenueDTO.setMonth(rs.getInt("Month"));
        revenueDTO.setTotalQuantity(rs.getLong("QUANTITY"));
        revenueDTO.setTotalRevenue(rs.getLong("REVENUE"));
        revenueDTO.setProductQuantity(rs.getLong("p_QUANTITY"));
        revenueDTO.setProductRevenue(rs.getLong("p_REVENUE"));
    }

    public List<RevenueDTO> getRevenues(Date dateBegin, Date dateEnd, Integer productId) throws Exception {
        ArrayList<RevenueDTO> revenueDTOList = new ArrayList<>();
        try {
            String query = "Exec USP_RevenueStatistic ? , ? , ?";
            ResultSet orderRs = RevenueDAO.super.ExecuteQuery(query, new Object[] { dateBegin, dateEnd, productId });

            while (orderRs.next()) {
                RevenueDTO revenueDTO = new RevenueDTO();
                writeRevenueDTO(revenueDTO, orderRs);
                revenueDTOList.add(revenueDTO);
            }

        } catch (Exception e) {
            e.printStackTrace();
            // throw e;
        }
        return revenueDTOList;
    }
}