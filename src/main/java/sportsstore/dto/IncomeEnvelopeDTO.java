package sportsstore.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "IncomeEnvelopeDTO")
public class IncomeEnvelopeDTO {
    private List<IncomeDTO> incomes;

    @XmlElement
    private Integer resultCount;

    public void setResultCount(Integer resultCount) {
        this.resultCount = resultCount;
    }

    public List<IncomeDTO> getIncomes() {
        return incomes;
    }

    public void setIncomes(List<IncomeDTO> incomes) {
        this.incomes = incomes;
    }
}