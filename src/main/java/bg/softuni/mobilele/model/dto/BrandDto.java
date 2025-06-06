package bg.softuni.mobilele.model.dto;

import java.util.List;

public class BrandDto {
   
    private String name;
    private List<ModelDto> models;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ModelDto> getModels() {
        return models;
    }

    public void setModels(List<ModelDto> models) {
        this.models = models;
    }

    @Override
    public String toString() {
        return "BrandDto{" +
                "name='" + name + '\'' +
                ", models=" + models +
                '}';
    }
}
