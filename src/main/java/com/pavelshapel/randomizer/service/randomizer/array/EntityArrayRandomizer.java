package com.pavelshapel.randomizer.service.randomizer.array;

import com.pavelshapel.randomizer.entity.Entity;
import com.pavelshapel.randomizer.service.randomizer.EntityRandomizer;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public final class EntityArrayRandomizer extends ArrayRandomizer<Entity> implements EntityRandomizer<Entity[]> {
    public EntityArrayRandomizer() {
        super(Entity[].class);
    }

    @Override
    public Entity[] randomize(Entity entity) {
        return randomize(entity, null);
    }

    @Override
    protected Entity[] collectionToArray(Collection<Entity> collection) {
        return collection.toArray(new Entity[0]);
    }
}
