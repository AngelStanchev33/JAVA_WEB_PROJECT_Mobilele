package bg.softuni.mobilele.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import bg.softuni.mobilele.service.CarService;

@Controller
@RequestMapping("/brands")
public class CarsController {

    private final CarService carService;

    public CarsController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/all")
    public String allBrands(Model model) {

        model.addAttribute("cars", carService.showAllCars());

        return "brands";
    }

}
