package bg.softuni.mobilele.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import bg.softuni.mobilele.model.dto.OfferDto;
import bg.softuni.mobilele.service.OfferService;

@Controller
@RequestMapping("/offers")
public class OfferController {

    private final OfferService offerService;

    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    @GetMapping("/all")
    public String allOffers(Model model) {
        List<OfferDto> offers = offerService.showOffers();
        offers.forEach(offer -> {
            System.out.println("Offer details:");
            System.out.println("Mileage: " + offer.getMileage());
            System.out.println("Price: " + offer.getPrice());
            System.out.println("Engine: " + offer.getEngine());
            System.out.println("Transmission: " + offer.getTransmission());
            System.out.println("Year: " + offer.getYear());
            System.out.println("Model: " + offer.getModelName());
            System.out.println("Brand: " + offer.getBrandName());
            System.out.println("Image URL: " + offer.getImageUrl());
            System.out.println("------------------------");
        });
        
        model.addAttribute("offers", offers);

        return "offers";
    }

    @GetMapping("/add")
    public String addOffer() {
        return "offer-add";
    }
}
