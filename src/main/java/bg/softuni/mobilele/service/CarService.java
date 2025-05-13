package bg.softuni.mobilele.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import bg.softuni.mobilele.mapper.CarMapper;
import bg.softuni.mobilele.model.dto.BrandDto;
import bg.softuni.mobilele.repository.BrandRepository;

@Service
public class CarService {

    private final BrandRepository brandRepository;
    private final CarMapper carMapper;

    public CarService(BrandRepository brandRepository, CarMapper carMapper) {
        this.brandRepository = brandRepository;
        this.carMapper = carMapper;
    }

    public List<BrandDto> showAllCars() {
        return brandRepository.findAll()
                .stream()
                .map(entity -> carMapper.map(entity))
                .collect(Collectors.toList());
    }

}
