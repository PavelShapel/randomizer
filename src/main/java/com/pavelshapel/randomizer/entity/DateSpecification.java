package com.pavelshapel.randomizer.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public final class DateSpecification extends AbstractSpecification<Date> {
    public DateSpecification(Date min, Date max) {
        super(min.getTime(), max.getTime());
    }
}
