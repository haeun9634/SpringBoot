package umc.spring.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPreferenceFood is a Querydsl query type for PreferenceFood
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPreferenceFood extends EntityPathBase<PreferenceFood> {

    private static final long serialVersionUID = -215909499L;

    public static final QPreferenceFood preferenceFood = new QPreferenceFood("preferenceFood");

    public final umc.spring.domain.common.QBaseEntity _super = new umc.spring.domain.common.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final EnumPath<umc.spring.domain.enums.FoodCategory> foodCategory = createEnum("foodCategory", umc.spring.domain.enums.FoodCategory.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QPreferenceFood(String variable) {
        super(PreferenceFood.class, forVariable(variable));
    }

    public QPreferenceFood(Path<? extends PreferenceFood> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPreferenceFood(PathMetadata metadata) {
        super(PreferenceFood.class, metadata);
    }

}

