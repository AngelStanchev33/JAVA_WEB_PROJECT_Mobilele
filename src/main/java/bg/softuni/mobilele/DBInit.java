package bg.softuni.mobilele;

import bg.softuni.mobilele.model.entity.*;
import bg.softuni.mobilele.model.entity.enums.CategoryEnum;
import bg.softuni.mobilele.model.entity.enums.EngineEnum;
import bg.softuni.mobilele.model.entity.enums.TransmissionEnum;
import bg.softuni.mobilele.model.entity.enums.UserRoleEnum;
import bg.softuni.mobilele.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class DBInit implements CommandLineRunner {

    private final BrandRepository brandRepository;
    private final ModelRepository modelRepository;
    private final OfferRepository offerRepository;
    private final RolesRepository repository;
    private final UserRepository userRepository;
    private  final PasswordEncoder passwordEncoder;

    public DBInit(BrandRepository brandRepository, ModelRepository modelRepository, OfferRepository offerRepository, RolesRepository repository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.brandRepository = brandRepository;
        this.modelRepository = modelRepository;
        this.offerRepository = offerRepository;
        this.repository = repository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        BrandEntity bmwBrand = new BrandEntity();
        bmwBrand.setName("BMW");
        BrandEntity hondaBrand = new BrandEntity();
        hondaBrand.setName("Honda");

        brandRepository.saveAll(List.of(bmwBrand, hondaBrand));

        ModelEntity bmeE90 = createBMWE90(bmwBrand);
        setAccord(hondaBrand);

        UserEntity user = createUser();

        create390Offer(bmeE90, user);
    }


    private ModelEntity createBMWE90(BrandEntity brandEntity) {
        ModelEntity model = new ModelEntity();
        model.setName("e90");
        model.setBrandEntity(brandEntity);
        model.setCategory(CategoryEnum.CAR);
        model.setStartYear(2006);
        model.setImageUrl("https://www.topgear.com/sites/default/files/news-listicle/image/g17p6682.jpg");

        modelRepository.save(model);

        return model;
    }

    private void setAccord(BrandEntity brandEntity) {
        ModelEntity model = new ModelEntity();
        model.setName("accord");
        model.setBrandEntity(brandEntity);
        model.setCategory(CategoryEnum.CAR);
        model.setStartYear(1976);
        model.setImageUrl("https://di-uploads-pod14.dealerinspire.com/hondaeastcincy" +
                "/uploads/2023/02/2302-Honda-Accord-Sport-L-Hybrid-Trim.jpg");

        modelRepository.save(model);

    }

    private void create390Offer(ModelEntity model, UserEntity user){
        OfferEntity offer = new OfferEntity();
        offer.setDescription("Top kola, nqma greshka!");
        offer.setEngine(EngineEnum.DIESEL);
        offer.setMileage(300000);
        offer.setModel(model);
        offer.setPrice(new BigDecimal("11200"));
        offer.setTransmission(TransmissionEnum.MANUAL);
        offer.setYear(2006);
        offer.setImageUrl(model.getImageUrl());
        offer.setSeller(user);

        offerRepository.save(offer);

    }

    public UserEntity  createUser(){
        UserRoleEntity adminRole = new UserRoleEntity();
        adminRole.setUserRole(UserRoleEnum.ADMIN);
        UserRoleEntity userRole = new UserRoleEntity();
        userRole.setUserRole(UserRoleEnum.USER);

        repository.saveAll(List.of(adminRole,userRole));

        UserEntity user = new UserEntity();
        user.setFirstName("Angel");
        user.setLastName("Stanchev");
        user.setPassword(passwordEncoder.encode("N!ce"));
        user.setUsername("Stancharo");
        user.setEmail("angelstanchev33@gmail.com");
        user.setRole(adminRole);
        user.setImageUrl("https://cdn.britannica.com/50/221250-138-7BA5F519/who-was-Joseph-Stalin.jpg?w=800&h=450&c=crop");
        user.setActive(true);

        userRepository.save(user);

        return user;
    }
}
