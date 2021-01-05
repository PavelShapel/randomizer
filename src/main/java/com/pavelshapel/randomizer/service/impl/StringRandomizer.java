package com.pavelshapel.randomizer.service.impl;

import com.pavelshapel.randomizer.entity.AbstractSpecification;
import com.pavelshapel.randomizer.entity.StringSpecification;
import com.pavelshapel.randomizer.service.BoundedRandomizer;
import lombok.NonNull;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

@Service
public class StringRandomizer implements BoundedRandomizer<String> {
    @Override
    public String randomize() {
        return randomize(new StringSpecification());
    }

    @Override
    public String randomize(@NonNull AbstractSpecification<String> specification) {
        return RandomStringUtils.randomAlphanumeric(
                Math.toIntExact(specification.getMin()),
                Math.toIntExact(specification.getMax())
        );
    }
}
