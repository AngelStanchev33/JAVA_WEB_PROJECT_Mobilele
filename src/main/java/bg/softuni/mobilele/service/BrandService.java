package bg.softuni.mobilele.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import bg.softuni.mobilele.model.dto.BrandDto;
import bg.softuni.mobilele.model.dto.ModelDto;
import bg.softuni.mobilele.repository.BrandRepository;
import bg.softuni.mobilele.repository.ModelRepository;

@Service
public class BrandService {

    private final BrandRepository brandRepository;
    private final ModelRepository modelRepository;
    private final ModelMapper modelMapper;

    public BrandService(BrandRepository brandRepository, ModelMapper modelMapper, ModelRepository modelRepository) {
        this.brandRepository = brandRepository;
        this.modelRepository = modelRepository;
        this.modelMapper = modelMapper;
    }

    public List<BrandDto> brandAndModelDto() {
        return brandRepository.findAll()
                .stream()
                .map(brandEntity -> {
                    BrandDto brandAndModelDto = modelMapper.map(brandEntity, BrandDto.class);
                    brandAndModelDto.setModels(modelRepository.findAllByBrand_Id(brandEntity.getId())
                            .stream()
                            .map(modelEntity -> modelMapper.map(modelEntity, ModelDto.class))
                            .toList());
                    return brandAndModelDto;
                })
                .toList();
    }

}
