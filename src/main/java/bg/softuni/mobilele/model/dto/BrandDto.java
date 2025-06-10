package bg.softuni.mobilele.model.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BrandDto {

    private String name;;
    private List<ModelDto> models;
}
