package umc.spring.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QAlert is a Querydsl query type for Alert
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAlert extends EntityPathBase<Alert> {

    private static final long serialVersionUID = 1141593136L;

    public static final QAlert alert = new QAlert("alert");

    public final umc.spring.domain.common.QBaseEntity _super = new umc.spring.domain.common.QBaseEntity(this);

    public final EnumPath<umc.spring.domain.enums.AlertType> alertType = createEnum("alertType", umc.spring.domain.enums.AlertType.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath message = createString("message");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QAlert(String variable) {
        super(Alert.class, forVariable(variable));
    }

    public QAlert(Path<? extends Alert> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAlert(PathMetadata metadata) {
        super(Alert.class, metadata);
    }

}

