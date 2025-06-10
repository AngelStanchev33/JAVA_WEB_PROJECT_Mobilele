package bg.softuni.mobilele.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import bg.softuni.mobilele.model.dto.ModelDto;
import bg.softuni.mobilele.model.dto.OfferDto;
import bg.softuni.mobilele.model.dto.UserDto;
import bg.softuni.mobilele.repository.OfferRepository;

@Service
public class OfferService {

    private final ModelMapper modelMapper;
    private final OfferRepository offerRepository;

    public OfferService(ModelMapper modelMapper, OfferRepository offerRepository) {
        this.modelMapper = modelMapper;
        this.offerRepository = offerRepository;
    }

    public List<OfferDto> getoffers() {
        return offerRepository.findAll()
                .stream()
                .map(offerEntity -> {
                    OfferDto offerDto = modelMapper.map(offerEntity, OfferDto.class);
                    ModelDto model = modelMapper.map(offerEntity.getModel(), ModelDto.class);
                    UserDto userDto = modelMapper.map(offerEntity.getSeller(), UserDto.class);

                    offerDto.setModel(model);
                    offerDto.setSeller(userDto);

                    return offerDto;
                })
                .toList();
    }

}
