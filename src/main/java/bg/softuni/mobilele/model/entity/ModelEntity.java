package bg.softuni.mobilele.model.entity;

import java.time.LocalDateTime;

import bg.softuni.mobilele.model.enums.CategoryEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "models")
public class ModelEntity extends BaseEntity {
    
    private String name;

    @Enumerated(EnumType.STRING)
    private CategoryEnum category;
    
    @Size(min = 8, max = 512)
    private String imageUrl;
    
    private int startYear;
    private int endYear;
    private LocalDateTime created;
    private LocalDateTime modified;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private BrandEntity brand;
} 