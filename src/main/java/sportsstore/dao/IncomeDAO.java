package sportsstore.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import sportsstore.dto.IncomeDTO;
import sportsstore.dto.IncomeEnvelopeDTO;

public class IncomeDAO extends AbstractDAO {

    public IncomeDAO() throws Exception {

    }

    public IncomeDAO(Connection conn) {
        super(conn);
    }

    public void writeIncomeDTO(IncomeDTO incomeDTO, ResultSet rs) throws Exception {
        incomeDTO.setYear(rs.getInt("Year"));
        incomeDTO.setMonth(rs.getInt("Month"));
        incomeDTO.setTotalCost(rs.getLong("TotalCost"));
        incomeDTO.setTotalIncome(rs.getLong("TotalIncome"));
        incomeDTO.setProductCost(rs.getLong("p_Cost"));
        incomeDTO.setProductIncome(rs.getLong("p_Income"));
    }

    public IncomeEnvelopeDTO getIncomes(int offset, int limit, Date dateBegin, Date dateEnd, Integer productId)
            throws Exception {
        IncomeEnvelopeDTO incomeEnvelope = new IncomeEnvelopeDTO();
        List<IncomeDTO> incomeDTOList = new ArrayList<>();
        try {
            String query = "Exec USP_IncomeStatistic ? , ? , ?";
            ResultSet orderRs = IncomeDAO.super.ExecuteQuery(query, new Object[] { dateBegin, dateEnd, productId });

            while (orderRs.next()) {
                IncomeDTO incomeDTO = new IncomeDTO();
                writeIncomeDTO(incomeDTO, orderRs);
                incomeDTOList.add(incomeDTO);
            }

            incomeEnvelope.setResultCount(incomeDTOList.size());
            if (limit != 0)
                incomeDTOList = incomeDTOList.stream().skip(offset) // Equivalent to SQL's offset
                        .limit(limit) // Equivalent to SQL's limit
                        .collect(Collectors.toList());
            incomeEnvelope.setIncomes(incomeDTOList);

        } catch (Exception e) {
            e.printStackTrace();
            // throw e;
        }
        return incomeEnvelope;
    }
}