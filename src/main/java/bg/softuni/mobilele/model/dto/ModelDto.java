package bg.softuni.mobilele.model.dto;

import bg.softuni.mobilele.model.enums.CategoryEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ModelDto {

    private int startYear;
    private int endYear;
    private String name;
    private CategoryEnum category;
    private String imageUrl;
    private BrandDto brand;

}
