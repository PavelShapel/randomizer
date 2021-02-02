package com.pavelshapel.randomizer.entity.specification.value.entity;

import com.pavelshapel.randomizer.entity.Entity;
import com.pavelshapel.randomizer.entity.specification.value.AbstractSpecification;
import com.pavelshapel.randomizer.entity.specification.value.range.AbstractRangeSpecification;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class EntitySpecification extends AbstractSpecification<Entity, Entity> {
    @Autowired
    private List<AbstractRangeSpecification<?>> rangeValueSpecifications;

    protected EntitySpecification() {
        super(Entity.class);
    }

    @Override
    protected Entity getVerifiedInitialObject(Entity entity) {
        try {
            return entity;
        } catch (Exception exception) {
            return getDefaultInitialObject();
        }
    }

    @Override
    protected Entity getDefaultInitialObject() {
        final Map<String, Object> defaultMap = rangeValueSpecifications.stream()
                .collect(Collectors.toMap(
                        AbstractSpecification::getValueClassName,
                        AbstractSpecification::getValueClassName)
                );
        return new Entity(defaultMap);
    }
}
