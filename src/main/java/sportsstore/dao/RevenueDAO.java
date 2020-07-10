package sportsstore.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import sportsstore.dto.RevenueDTO;
import sportsstore.dto.RevenueEnvelopeDTO;

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

    public RevenueEnvelopeDTO getRevenues(int offset, int limit, Date dateBegin, Date dateEnd, Integer productId)
            throws Exception {
        RevenueEnvelopeDTO revenueEnvelope = new RevenueEnvelopeDTO();
        List<RevenueDTO> revenueDTOList = new ArrayList<>();
        try {
            String query = "Exec USP_RevenueStatistic ? , ? , ?";
            ResultSet orderRs = RevenueDAO.super.ExecuteQuery(query, new Object[] { dateBegin, dateEnd, productId });

            while (orderRs.next()) {
                RevenueDTO revenueDTO = new RevenueDTO();
                writeRevenueDTO(revenueDTO, orderRs);
                revenueDTOList.add(revenueDTO);
            }

            revenueEnvelope.setResultCount(revenueDTOList.size());
            if (limit != 0)
                revenueDTOList = revenueDTOList.stream().skip(offset) // Equivalent to SQL's offset
                        .limit(limit) // Equivalent to SQL's limit
                        .collect(Collectors.toList());
            revenueEnvelope.setRevenues(revenueDTOList);

        } catch (Exception e) {
            e.printStackTrace();
            // throw e;
        }
        return revenueEnvelope;
    }
}