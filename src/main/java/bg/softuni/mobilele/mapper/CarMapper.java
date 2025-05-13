package bg.softuni.mobilele.mapper;

import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import bg.softuni.mobilele.model.dto.BrandDto;
import bg.softuni.mobilele.model.dto.ModelDto;
import bg.softuni.mobilele.model.entity.BrandEntity;

@Component
public class CarMapper {

    private final ModelMapper mapper;

    public CarMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public BrandDto map(BrandEntity brandEntity) {
        BrandDto brandDto = mapper.map(brandEntity, BrandDto.class);
        brandDto.getModels()
                .stream()
                .map(model -> {
                    ModelDto modelDto = mapper.map(model, ModelDto.class);
                    modelDto.setBrandName(brandEntity.getName());
                    return modelDto;
                })
                .collect(Collectors.toList());

        return brandDto;
    }

}
