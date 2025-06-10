package bg.softuni.mobilele.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import bg.softuni.mobilele.model.enums.EngineEnum;
import bg.softuni.mobilele.model.enums.TransmissionEnum;

@Getter
@Setter
@Entity
@Table(name = "offers")
public class OfferEntity extends BaseEntity {
    
    @Column(columnDefinition = "TEXT")
    private String description;
    
    @Enumerated(EnumType.STRING)
    private EngineEnum engine;
    
    private String imageUrl;
    private int mileage;
    private BigDecimal price;
    
    @Enumerated(EnumType.STRING)
    private TransmissionEnum transmission;
    
    private int year;
    private LocalDateTime created;
    private LocalDateTime modified;
    
    @ManyToOne
    private ModelEntity model;
    
    @ManyToOne
    private UserEntity seller;
} 