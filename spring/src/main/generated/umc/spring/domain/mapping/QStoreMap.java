package umc.spring.domain.mapping;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStoreMap is a Querydsl query type for StoreMap
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStoreMap extends EntityPathBase<StoreMap> {

    private static final long serialVersionUID = -797153657L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStoreMap storeMap = new QStoreMap("storeMap");

    public final umc.spring.domain.common.QBaseEntity _super = new umc.spring.domain.common.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final umc.spring.domain.QRegion region;

    public final umc.spring.domain.QStore store;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QStoreMap(String variable) {
        this(StoreMap.class, forVariable(variable), INITS);
    }

    public QStoreMap(Path<? extends StoreMap> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStoreMap(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStoreMap(PathMetadata metadata, PathInits inits) {
        this(StoreMap.class, metadata, inits);
    }

    public QStoreMap(Class<? extends StoreMap> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.region = inits.isInitialized("region") ? new umc.spring.domain.QRegion(forProperty("region")) : null;
        this.store = inits.isInitialized("store") ? new umc.spring.domain.QStore(forProperty("store")) : null;
    }

}

