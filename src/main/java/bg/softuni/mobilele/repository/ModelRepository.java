package bg.softuni.mobilele.repository;

import bg.softuni.mobilele.model.entity.ModelEntity;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModelRepository extends JpaRepository<ModelEntity, Long> {

    List<ModelEntity> findAllByBrand_Id(Long brandId);

    Optional<ModelEntity> findByName(String name);

}