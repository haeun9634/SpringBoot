package umc.spring.repository.StoreRegionRepository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import umc.spring.domain.QRegion;
import umc.spring.domain.QStore;
import umc.spring.domain.Region;
import umc.spring.domain.Store;
import umc.spring.domain.mapping.QStoreMap;
import umc.spring.domain.mapping.StoreMap;

@Repository
@RequiredArgsConstructor
@org.springframework.transaction.annotation.Transactional
public class StoreRegionRepositoryImpl implements StoreRegionRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;
    private final QStore qStore = QStore.store;
    private final QRegion qRegion = QRegion.region;
    private final QStoreMap qStoreMap = QStoreMap.storeMap;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public StoreMap dynamicQueryInsertStoreRegion(Long storeId, Long regionId) {

        Store store = jpaQueryFactory.selectFrom(qStore)
                .where(qStore.id.eq(storeId))
                .fetchOne();

        Region region = jpaQueryFactory.selectFrom(qRegion)
                .where(qRegion.id.eq(regionId))
                .fetchOne();

        StoreMap storeRegion = StoreMap.builder()
                .store(store)
                .region(region)
                .build();


        entityManager.persist(storeRegion);

        return storeRegion;
    }

    @Override
    public boolean existsByStoreAndRegion(Long storeId, Long regionId){

        Store store = jpaQueryFactory.selectFrom(qStore)
                .where(qStore.id.eq(storeId))
                .fetchOne();

        Region region = jpaQueryFactory.selectFrom(qRegion)
                .where(qRegion.id.eq(regionId))
                .fetchOne();

        StoreMap existing = jpaQueryFactory.selectFrom(qStoreMap)
                .where(qStoreMap.store.eq(store).and(qStoreMap.region.eq(region)))
                .fetchOne();

        if (existing != null) {
            return true;
        }

        return false;

    }


}
