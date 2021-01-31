package com.pavelshapel.randomizer.service.randomizer.primitive;

import com.pavelshapel.randomizer.service.randomizer.Randomizer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.Range;

@Log4j2
@Getter
@AllArgsConstructor
public abstract class PrimitiveRandomizer<T> implements Randomizer<T> {
    private final Class<T> genericClass;

    @Override
    public T randomize(Range<Long> range) {
        try {
            return implementRandomization(range);
        } catch (Exception exception) {
            log.error(RANDOMIZE_BY_DEFAULT, exception.toString());
            return randomize();
        }
    }

    protected abstract T implementRandomization(Range<Long> range);


}
