//package com.pavelshapel.randomizer.service.randomizer.primitive;
//
//import com.pavelshapel.randomizer.entity.Entity;
//import com.pavelshapel.randomizer.service.randomizer.EntityRandomizer;
//import com.pavelshapel.randomizer.service.randomizer.Randomizer;
//import org.apache.commons.lang3.Range;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.PostConstruct;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collector;
//import java.util.stream.Collectors;
//
//@Service
//public final class EntityPrimitiveRandomizer extends PrimitiveRandomizer<Entity> implements EntityRandomizer<Entity> {
//    @Autowired
//    private List<Randomizer<?>> randomizers;
//
//    public EntityPrimitiveRandomizer() {
//        super(Entity.class);
//    }
//
//    @PostConstruct
//    private void postConstruct() {
//        randomizers.removeIf(randomizer -> randomizer instanceof EntityRandomizer);
//    }
//
//    @Override
//    public Entity randomize() {
//        return createDefaultEntity();
//    }
//
//    @Override
//    protected Entity implementRandomization(Range<Long> range) {
//        return createDefaultEntity();
//    }
//
//    private Entity createDefaultEntity() {
//        final Map<String, Object> map = randomizers.stream()
//                .collect(Collectors.toMap(
//                        this::getGenericClassName,
//                        Randomizer::randomize
//                ));
//
//        return new Entity(map);
//    }
//
//    @Override
//    public Entity randomize(Entity entity) {
//        final Entity result = new Entity();
//        entity.forEach((key, value) -> result.put(key, getRandomValueByClassName(value.toString())));
//
//        return result;
//    }
//
//    private Object getRandomValueByClassName(String className) {
//        return randomizers.stream()
//                .filter(randomizer -> className.equalsIgnoreCase(getGenericClassName(randomizer)))
//                .map(Randomizer::randomize)
//                .collect(toSingleton());
//    }
//
//    private <T> Collector<T, ?, T> toSingleton() {
//        return Collectors.collectingAndThen(
//                Collectors.toList(),
//                list -> {
//                    if (list.size() != 1) {
//                        throw new IllegalStateException();
//                    }
//                    return list.get(0);
//                }
//        );
//    }
//
//    private String getGenericClassName(Randomizer<?> randomizer) {
//        return randomizer.getGenericClass().getSimpleName().toLowerCase();
//    }
//}
