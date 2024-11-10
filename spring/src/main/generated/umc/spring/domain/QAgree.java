package umc.spring.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QAgree is a Querydsl query type for Agree
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAgree extends EntityPathBase<Agree> {

    private static final long serialVersionUID = 1141456256L;

    public static final QAgree agree = new QAgree("agree");

    public final umc.spring.domain.common.QBaseEntity _super = new umc.spring.domain.common.QBaseEntity(this);

    public final EnumPath<umc.spring.domain.enums.AgreeStatus> agreeStatus = createEnum("agreeStatus", umc.spring.domain.enums.AgreeStatus.class);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QAgree(String variable) {
        super(Agree.class, forVariable(variable));
    }

    public QAgree(Path<? extends Agree> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAgree(PathMetadata metadata) {
        super(Agree.class, metadata);
    }

}

