package umc.spring.domain.mapping;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserAlert is a Querydsl query type for UserAlert
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserAlert extends EntityPathBase<UserAlert> {

    private static final long serialVersionUID = -322132987L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserAlert userAlert = new QUserAlert("userAlert");

    public final umc.spring.domain.common.QBaseEntity _super = new umc.spring.domain.common.QBaseEntity(this);

    public final umc.spring.domain.QAlert alert;

    public final EnumPath<umc.spring.domain.enums.AgreeStatus> alertAgree = createEnum("alertAgree", umc.spring.domain.enums.AgreeStatus.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isread = createBoolean("isread");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final umc.spring.domain.QUser user;

    public QUserAlert(String variable) {
        this(UserAlert.class, forVariable(variable), INITS);
    }

    public QUserAlert(Path<? extends UserAlert> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserAlert(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserAlert(PathMetadata metadata, PathInits inits) {
        this(UserAlert.class, metadata, inits);
    }

    public QUserAlert(Class<? extends UserAlert> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.alert = inits.isInitialized("alert") ? new umc.spring.domain.QAlert(forProperty("alert")) : null;
        this.user = inits.isInitialized("user") ? new umc.spring.domain.QUser(forProperty("user")) : null;
    }

}

