package sportsstore.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "ImportEnvelopeDTO")
public class ImportEnvelopeDTO {
    private List<ImportDTO> imports;

    @XmlElement
    private Integer resultCount;

    public List<ImportDTO> getImports() {
        return imports;
    }

    public void setImports(List<ImportDTO> imports) {
        this.imports = imports;
    }

    public void setResultCount(Integer resultCount) {
        this.resultCount = resultCount;
    }

}