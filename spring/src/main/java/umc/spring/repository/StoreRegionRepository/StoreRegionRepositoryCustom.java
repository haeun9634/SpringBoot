package umc.spring.repository.StoreRegionRepository;

import umc.spring.domain.mapping.StoreMap;

public interface StoreRegionRepositoryCustom {
    StoreMap dynamicQueryInsertStoreRegion(Long storeId, Long regionId);
    boolean existsByStoreAndRegion(Long storeId, Long regionId);
}
