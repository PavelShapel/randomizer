package com.pavelshapel.randomizer.entity.specification.value;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.annotation.PostConstruct;

@Getter
@ToString
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)
public abstract class AbstractSpecification<T, O> {
    @NonNull
    final Class<T> valueClass;
    O initialObject;

    @PostConstruct
    private void initialization() {
        this.initialObject = getDefaultInitialObject();
    }

    protected AbstractSpecification(@NonNull Class<T> valueClass) {
        this.valueClass = valueClass;
    }

    public void setInitialObject(O initialObject) {
        this.initialObject = getVerifiedInitialObject(initialObject);
    }

    public String getValueClassName() {
        return valueClass.getSimpleName().toLowerCase();
    }

    protected abstract O getVerifiedInitialObject(O initialObject);

    protected abstract O getDefaultInitialObject();
}
