package com.pavelshapel.randomizer.controller;

import com.pavelshapel.commonspringbootstarter.utils.randomizer.service.impl.AbstractRangeRandomizer;
import com.pavelshapel.randomizer.aop.LogMethodResult;
import org.apache.commons.lang3.Range;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collection;

public abstract class AbstractRangeRestController<T> extends AbstractRestController<T, Range<Long>> {
    protected AbstractRangeRestController(AbstractRangeRandomizer<T> randomizer) {
        super(randomizer);
    }

    @Override
    public AbstractRangeRandomizer<T> getRandomizer() {
        return ((AbstractRangeRandomizer<T>) super.getRandomizer());
    }

    @LogMethodResult(logLevel = "DEBUG")
    @GetMapping(PATH_VALUE_RANGE)
    protected ResponseEntity<T> getBoundedValue(
            @PathVariable long minValue,
            @PathVariable long maxValue) {
        final T randomValue = getRandomizer().randomizeBoundedValue(minValue, maxValue);

        return ResponseEntity.ok(randomValue);
    }

    @LogMethodResult(logLevel = "DEBUG")
    @GetMapping(PATH_VALUE_RANGE + PATH_COLLECTION)
    protected ResponseEntity<Collection<T>> getBoundedValueCollection(
            @PathVariable long minValue,
            @PathVariable long maxValue) {
        final Collection<T> randomCollection = getRandomizer().randomizeBoundedValueCollection(minValue, maxValue);

        return ResponseEntity.ok(randomCollection);
    }

    @LogMethodResult(logLevel = "DEBUG")
    @GetMapping(PATH_VALUE_RANGE + PATH_COLLECTION + PATH_COLLECTION_RANGE)
    protected ResponseEntity<Collection<T>> getBoundedValueBoundedCollection(
            @PathVariable long minValue,
            @PathVariable long maxValue,
            @PathVariable long minCollection,
            @PathVariable long maxCollection) {
        final Collection<T> randomCollection = getRandomizer().randomizeBoundedValueBoundedCollection(
                minValue, maxValue, minCollection, maxCollection);

        return ResponseEntity.ok(randomCollection);
    }
}