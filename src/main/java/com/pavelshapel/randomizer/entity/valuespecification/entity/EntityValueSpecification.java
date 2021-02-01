package com.pavelshapel.randomizer.entity.valuespecification.entity;

import com.pavelshapel.randomizer.entity.Entity;
import com.pavelshapel.randomizer.entity.valuespecification.AbstractValueSpecification;
import com.pavelshapel.randomizer.entity.valuespecification.range.AbstractRangeValueSpecification;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@DependsOn({
        "booleanValueSpecification",
        "dateValueSpecification",
        "doubleValueSpecification",
        "longValueSpecification",
        "stringValueSpecification"
}
)
public class EntityValueSpecification extends AbstractValueSpecification<Entity, Entity> {
    @Autowired
    private List<AbstractRangeValueSpecification<?>> rangeValueSpecifications;

    protected EntityValueSpecification() {
        super(Entity.class);
    }

    protected EntityValueSpecification(Entity entity) {
        super(Entity.class, entity);
    }

    @Override
    protected Entity getVerifiedObject(Entity entity) {
        return getDefaultInitialObject();
    }

    @Override
    protected Entity getDefaultInitialObject() {
        final Map<String, Object> defaultMap = rangeValueSpecifications.stream()
                .collect(Collectors.toMap(
                        AbstractValueSpecification::getGenericClassName,
                        AbstractValueSpecification::getGenericClassName)
                );
        return new Entity(defaultMap);
    }
}
