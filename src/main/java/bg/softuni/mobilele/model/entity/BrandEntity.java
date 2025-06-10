package bg.softuni.mobilele.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.ArrayList;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "brands")
public class BrandEntity extends BaseEntity {

    private String name;
    private LocalDateTime created;
    private LocalDateTime modified;

    @OneToMany(mappedBy = "brand", fetch = FetchType.EAGER)
    private List<ModelEntity> models;

    public BrandEntity() {
        this.models = new ArrayList<>();
    }

}