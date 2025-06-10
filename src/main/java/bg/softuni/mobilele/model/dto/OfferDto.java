package bg.softuni.mobilele.model.dto;

import java.math.BigDecimal;

import bg.softuni.mobilele.model.enums.EngineEnum;
import bg.softuni.mobilele.model.enums.TransmissionEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OfferDto {

    private String description;
    private EngineEnum engine;
    private String imageUrl;
    private int mileage;
    private BigDecimal price;
    private TransmissionEnum transmission;
    private int year;
    private ModelDto model;
    private UserDto seller;
}
