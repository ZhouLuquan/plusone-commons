package xyz.zhouxy.plusone.commons;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import xyz.zhouxy.plusone.commons.util.Enumeration;

class EnumerationTests {

    @Test
    void testEnumeration() {
        assertEquals(EntityStatus.AVAILABLE, EntityStatus.of(0));
    }
}

final class EntityStatus extends Enumeration<EntityStatus> {

    private EntityStatus(int value, String name) {
        super(value, name);
    }

    // 常量
    public static final EntityStatus AVAILABLE = new EntityStatus(0, "正常");
    public static final EntityStatus DISABLED = new EntityStatus(1, "禁用");

    private static final EnumerationValuesHolder<EntityStatus> ENUMERATION_VALUES = new EnumerationValuesHolder<>(
            AVAILABLE,
            DISABLED);

    public static EntityStatus of(int value) {
        return ENUMERATION_VALUES.get(value);
    }

    @Override
    public String toString() {
        return "EntityStatus" + super.toString();
    }
}