package com.pavelshapel.randomizer.service.randomizer;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
import org.apache.commons.lang3.Range;

@Value
@Builder
public class GenericRandomizer {
    @NonNull
    Class<?> valueClass;
    Object initialObject;
    Range<Long> arrayRange;
}
