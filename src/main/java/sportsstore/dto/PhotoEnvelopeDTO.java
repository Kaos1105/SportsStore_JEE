package sportsstore.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "PhotoEnvelopeDTO")

public class PhotoEnvelopeDTO {
    private List<PhotoDTO> photo;

    @XmlElement
    private Integer resultCount;

    public void setResultCount(Integer resultCount) {
        this.resultCount = resultCount;
    }

    public List<PhotoDTO> getPhoto() {
        return photo;
    }

    public void setPhoto(List<PhotoDTO> photo) {
        this.photo = photo;
    }

}