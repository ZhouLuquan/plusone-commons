package xyz.zhouxy.plusone.commons;

import static org.junit.jupiter.api.Assertions.assertEquals;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import xyz.zhouxy.plusone.commons.util.Enumeration;

import javax.annotation.Nonnull;
import java.util.Collection;

@Slf4j
class EnumerationTests {

    @Test
    void testEnumeration() {
        assertEquals(EntityStatus.AVAILABLE, EntityStatus.of(0));
        log.info(EntityStatus.constants().toString());
    }
}

final class EntityStatus extends Enumeration<EntityStatus> {

    private EntityStatus(int value, @Nonnull String name) {
        super(value, name);
    }

    // 常量
    public static final EntityStatus AVAILABLE = new EntityStatus(0, "正常");
    public static final EntityStatus DISABLED = new EntityStatus(1, "禁用");

    private static final ValueSet<EntityStatus> ENUMERATION_VALUES = new ValueSet<>(
            AVAILABLE, DISABLED);

    @Nonnull
    public static EntityStatus of(int value) {
        return ENUMERATION_VALUES.get(value);
    }

    @Nonnull
    public static Collection<EntityStatus> constants() {
        return ENUMERATION_VALUES.getValues();
    }
}
