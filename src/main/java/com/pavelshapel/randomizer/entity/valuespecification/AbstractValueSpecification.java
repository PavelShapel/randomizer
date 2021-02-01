package com.pavelshapel.randomizer.entity.valuespecification;

import lombok.NonNull;
import org.apache.commons.lang3.Range;

public abstract class AbstractValueSpecification<T, O> {
    @NonNull
    Class<T> genericClass;
    O initialObject;
    Range<Long> arrayRange;

    public String getGenericClassName() {
        return genericClass.getSimpleName().toLowerCase();
    }

    protected abstract O getVerifiedObject(O initialObject);

    protected abstract O getDefaultInitialObject();
}
