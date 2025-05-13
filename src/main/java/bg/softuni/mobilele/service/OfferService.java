package bg.softuni.mobilele.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import bg.softuni.mobilele.mapper.OfferMapper;
import bg.softuni.mobilele.model.dto.OfferDto;
import bg.softuni.mobilele.repository.OfferRepository;

@Service
public class OfferService {

    private final OfferMapper offerMapper;
    private final OfferRepository offerRepository;

    public OfferService(OfferMapper offerMapper, OfferRepository offerRepository) {
        this.offerMapper = offerMapper;
        this.offerRepository = offerRepository;
    }

    public List<OfferDto> showOffers() {
        return offerRepository.findAll()
                .stream()
                .map(entity -> offerMapper.map(entity))
                .collect(Collectors.toList());
    }

}
