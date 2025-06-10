package bg.softuni.mobilele.config;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import bg.softuni.mobilele.model.entity.BrandEntity;
import bg.softuni.mobilele.model.entity.ModelEntity;
import bg.softuni.mobilele.model.entity.OfferEntity;
import bg.softuni.mobilele.model.entity.UserEntity;
import bg.softuni.mobilele.model.entity.UserRoleEntity;
import bg.softuni.mobilele.model.enums.CategoryEnum;
import bg.softuni.mobilele.model.enums.EngineEnum;
import bg.softuni.mobilele.model.enums.RoleEnum;
import bg.softuni.mobilele.model.enums.TransmissionEnum;
import bg.softuni.mobilele.repository.*;

@Component
public class DataInitializer implements CommandLineRunner {

    private final OfferRepository offerRepository;

    private final UserRoleRepository userRoleRepository;
    private final BrandRepository brandRepository;
    private final ModelRepository modelRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UserRoleRepository userRoleRepository, BrandRepository brandRepository,
            ModelRepository modelRepository, UserRepository userRepository, PasswordEncoder passwordEncoder,
            OfferRepository offerRepository) {
        this.userRoleRepository = userRoleRepository;
        this.brandRepository = brandRepository;
        this.modelRepository = modelRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.offerRepository = offerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        try {

            initRoles();

            initUsers();

            initBrandsAndModels();

            initOffers();

            System.out.println("Database initialization completed.");
        } catch (Exception e) {
            System.err.println("Error during database initialization: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void initUsers() {
        if (userRepository.count() == 0) {
            UserEntity user = createUser("Sanchez", "12345",
                    "Angel", "Stanchev",
                    true, null,
                    LocalDateTime.now(), null);
            userRepository.save(user);
        }
    }

    private void initRoles() {
        if (userRoleRepository.count() == 0) {
            for (RoleEnum role : RoleEnum.values()) {
                UserRoleEntity userRole = new UserRoleEntity();
                userRole.setName(role);
                userRoleRepository.save(userRole);
            }
        }
    }

    private void initBrandsAndModels() {
        if (brandRepository.count() == 0) {
            // Create BMW brand and model
            BrandEntity bmw = createBrand("BMW");
            brandRepository.save(bmw);

            ModelEntity bmwModel = createModel("e90", CategoryEnum.CAR,
                    "https://s13emagst.akamaized.net/products/66196/66195381/images/res_86117755898630c4829544c7e265c677.jpg",
                    2004, 2009, bmw);
            modelRepository.save(bmwModel);

            // Create Honda brand and model
            BrandEntity honda = createBrand("Honda");
            brandRepository.save(honda);

            ModelEntity hondaModel = createModel("Civic", CategoryEnum.CAR,
                    "https://cars.honda.bg/wp-content/uploads/sites/5/site//2/unnamed-file-202-scaled.jpeg",
                    2006, 2011, honda);
            modelRepository.save(hondaModel);
        }
    }

    private UserEntity createUser(String username, String password, String firstName, String lastName,
            boolean isActive, String imageUrl, LocalDateTime created, LocalDateTime modified) {

        try {
            System.out.println("Creating user: " + username);
            UserEntity user = new UserEntity();
            user.setUsername(username);
            user.setPassword(passwordEncoder.encode(password));
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setActive(isActive);
            user.setImageUrl(imageUrl);
            user.setCreated(created);
            user.setModified(modified);

            try {
                UserRoleEntity adminRole = userRoleRepository.findByName(RoleEnum.ADMIN)
                        .orElseThrow(() -> new RuntimeException("Admin role not found"));
                UserRoleEntity userRole = userRoleRepository.findByName(RoleEnum.USER)
                        .orElseThrow(() -> new RuntimeException("User role not found"));

                user.setRoles(List.of(adminRole, userRole));
            } catch (RuntimeException e) {
                System.err.println("Error finding roles: " + e.getMessage());
                throw e;
            }

            return user;
        } catch (Exception e) {
            System.err.println("Error creating user: " + e.getMessage());
            throw new RuntimeException("Failed to create user", e);
        }
    }

    private void initOffers() {
        if (offerRepository.count() == 0) {

            Optional<UserEntity> seller = userRepository.findByUsername("Sanchez");
            Optional<ModelEntity> model = modelRepository.findByName("e90");

            OfferEntity offerEntity = createOffer(
                    "BMW E90 320d - Идеално състояние, пълен пакет, сервизна книжка, 2008г. Автомобилът е в отлично състояние, с пълен сервиз, нови гуми, нови спирачки. Консумация 5.5л/100км. Цената е фиксирана.",
                    EngineEnum.DIESEL,
                    "https://s13emagst.akamaized.net/products/66196/66195381/images/res_86117755898630c4829544c7e265c677.jpg",
                    180000, new BigDecimal("12000"),
                    TransmissionEnum.MANUAL, 2006, model.orElseThrow(), seller.orElseThrow());

            offerRepository.save(offerEntity);

        }
    }

    private OfferEntity createOffer(String description, EngineEnum engine, String imageUrl,
            int mileage, BigDecimal price, TransmissionEnum transmission, int year,
            ModelEntity model, UserEntity seller) {
        OfferEntity offer = new OfferEntity();
        offer.setDescription(description);
        offer.setEngine(engine);
        offer.setImageUrl(imageUrl);
        offer.setMileage(mileage);
        offer.setPrice(price);
        offer.setTransmission(transmission);
        offer.setYear(year);
        offer.setModel(model);
        offer.setSeller(seller);
        offer.setCreated(LocalDateTime.now());
        offer.setModified(LocalDateTime.now());
        return offer;
    }

    private BrandEntity createBrand(String name) {
        BrandEntity brand = new BrandEntity();
        brand.setName(name);
        brand.setCreated(LocalDateTime.now());
        return brand;
    }

    private ModelEntity createModel(String name, CategoryEnum category, String imageUrl,
            int startYear, int endYear, BrandEntity brand) {
        ModelEntity model = new ModelEntity();
        model.setName(name);
        model.setCategory(category);
        model.setImageUrl(imageUrl);
        model.setStartYear(startYear);
        model.setEndYear(endYear);
        model.setCreated(LocalDateTime.now());
        model.setBrand(brand);
        return model;
    }
}