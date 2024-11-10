package umc.spring.domain.mapping;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserRegion is a Querydsl query type for UserRegion
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserRegion extends EntityPathBase<UserRegion> {

    private static final long serialVersionUID = -915906197L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserRegion userRegion = new QUserRegion("userRegion");

    public final umc.spring.domain.common.QBaseEntity _super = new umc.spring.domain.common.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final umc.spring.domain.QRegion region;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final umc.spring.domain.QUser user;

    public final StringPath userAddress = createString("userAddress");

    public QUserRegion(String variable) {
        this(UserRegion.class, forVariable(variable), INITS);
    }

    public QUserRegion(Path<? extends UserRegion> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserRegion(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserRegion(PathMetadata metadata, PathInits inits) {
        this(UserRegion.class, metadata, inits);
    }

    public QUserRegion(Class<? extends UserRegion> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.region = inits.isInitialized("region") ? new umc.spring.domain.QRegion(forProperty("region")) : null;
        this.user = inits.isInitialized("user") ? new umc.spring.domain.QUser(forProperty("user")) : null;
    }

}

