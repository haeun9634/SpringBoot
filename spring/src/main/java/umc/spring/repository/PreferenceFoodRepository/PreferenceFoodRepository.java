package umc.spring.repository.PreferenceFoodRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.domain.PreferenceFood;
import umc.spring.domain.enums.FoodCategory;

import java.util.Optional;

public interface PreferenceFoodRepository extends JpaRepository<PreferenceFood, Long> {
    Optional<PreferenceFood> findByFoodCategory(FoodCategory foodCategory);
}
