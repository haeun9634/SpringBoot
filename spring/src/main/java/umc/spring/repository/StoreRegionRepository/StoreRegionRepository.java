package umc.spring.repository.StoreRegionRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import umc.spring.domain.mapping.StoreMap;

@Repository
public interface StoreRegionRepository extends JpaRepository<StoreMap, Long>, StoreRegionRepositoryCustom{

}