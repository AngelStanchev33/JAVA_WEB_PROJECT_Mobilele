package bg.softuni.mobilele.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import bg.softuni.mobilele.model.dto.OfferDto;
import bg.softuni.mobilele.model.entity.OfferEntity;

@Component
public class OfferMapper {

    private final ModelMapper mapper;

    public OfferMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public OfferDto map(OfferEntity offerEntity) {
        OfferDto offerDto = mapper.map(offerEntity, OfferDto.class);
        offerDto.setModelName(offerEntity.getModel().getName());
        offerDto.setBrandName(offerEntity.getModel().getBrandEntity().getName());
      
        return offerDto;
    }
}
