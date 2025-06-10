package bg.softuni.mobilele.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import bg.softuni.mobilele.service.OfferService;

@Controller
@RequestMapping("/offers")
public class OffersController {

    private final OfferService offerService;

    public OffersController(OfferService offerService) {
        this.offerService = offerService;
    }

    @GetMapping("/all")
    public String getOffers(Model model) {
        model.addAttribute("offers", offerService.getoffers());

        for (bg.softuni.mobilele.model.dto.OfferDto offer : offerService.getoffers()) {
            System.out.println("Description: " + offer.getDescription());
            System.out.println("Engine: " + offer.getEngine());
            System.out.println("Image URL: " + offer.getImageUrl());
            System.out.println("Mileage: " + offer.getMileage());
            System.out.println("Price: " + offer.getPrice());
            System.out.println("Transmission: " + offer.getTransmission());
            System.out.println("Year: " + offer.getYear());
            System.out.println("Model: " + offer.getModel().getName());
            System.out.println("Seller: " + offer.getSeller().getUsername());
            System.out.println("------------------------");
        }

        return "offers";
    }

}
