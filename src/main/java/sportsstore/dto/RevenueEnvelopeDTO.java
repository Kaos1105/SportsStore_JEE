package sportsstore.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "RevenueEnvelopeDTO")
public class RevenueEnvelopeDTO {
    private List<RevenueDTO> revenues;

    @XmlElement
    private Integer resultCount;

    public void setResultCount(Integer resultCount) {
        this.resultCount = resultCount;
    }

    public List<RevenueDTO> getRevenues() {
        return revenues;
    }

    public void setRevenues(List<RevenueDTO> revenues) {
        this.revenues = revenues;
    }
}