package xyz.zhouxy.plusone.commons;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import xyz.zhouxy.plusone.commons.util.Enumeration;

import javax.annotation.Nonnull;
import java.util.Collection;

@Slf4j
class EnumerationTests {

    @Test
    void testEnumeration() {
        assertTrue(EntityStatus.AVAILABLE == EntityStatus.of(0));
        assertTrue(Result.SUCCESSFUL == Result.of(1));
        Collection<Comparable<? extends Enumeration<?>>> enums = Lists.newArrayList();
        enums.addAll(EntityStatus.constants());
        enums.addAll(Result.constants());
        for (Comparable<? extends Enumeration<?>> anEnum : enums) {
            log.info(anEnum.toString());
        }
    }
}

final class EntityStatus extends Enumeration<EntityStatus> {

    private EntityStatus(int value, @Nonnull String name) {
        super(value, name);
    }

    // 常量
    public static final EntityStatus AVAILABLE = new EntityStatus(0, "正常");
    public static final EntityStatus DISABLED = new EntityStatus(1, "禁用");

    private static final ValueSet<EntityStatus> VALUE_SET = new ValueSet<>(
            AVAILABLE, DISABLED);

    @Nonnull
    public static EntityStatus of(int value) {
        return VALUE_SET.get(value);
    }

    @Nonnull
    public static Collection<EntityStatus> constants() {
        return VALUE_SET.getValues();
    }
}

final class Result extends Enumeration<Result> {
    private Result(int id, @Nonnull String name) {
        super(id, name);
    }

    public static final Result SUCCESSFUL = new Result(1, "成功");
    public static final Result FAILURE = new Result(0, "失败");

    private static final ValueSet<Result> VALUE_SET = new ValueSet<>(SUCCESSFUL, FAILURE);

    @Nonnull
    public static Result of(int id) {
        return VALUE_SET.get(id);
    }

    @Nonnull
    public static Collection<Result> constants() {
        return VALUE_SET.getValues();
    }
}
