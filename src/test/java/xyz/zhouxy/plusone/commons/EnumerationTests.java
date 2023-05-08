package xyz.zhouxy.plusone.commons;

import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import xyz.zhouxy.plusone.commons.util.Enumeration;

import java.util.ArrayList;
import java.util.Collection;

class EnumerationTests {

    private static final Logger log = LoggerFactory.getLogger(EnumerationTests.class);

    @Test
    void testEnumeration() {
        assertSame(EntityStatus.AVAILABLE, EntityStatus.of(0));
        assertSame(Result.SUCCESSFUL, Result.of(1));
        Collection<Comparable<? extends Enumeration<?>>> enums = new ArrayList<>();
        enums.addAll(EntityStatus.constants());
        enums.addAll(Result.constants());
        for (Comparable<? extends Enumeration<?>> anEnum : enums) {
            log.info(anEnum.toString());
        }
    }
}

final class EntityStatus extends Enumeration<EntityStatus> {

    private EntityStatus(int value, String name) {
        super(value, name);
    }

    // 常量
    public static final EntityStatus AVAILABLE = new EntityStatus(0, "正常");
    public static final EntityStatus DISABLED = new EntityStatus(1, "禁用");

    private static final ValueSet<EntityStatus> VALUE_SET = ValueSet.of(AVAILABLE, DISABLED);

    public static EntityStatus of(int value) {
        return VALUE_SET.get(value);
    }

    public static Collection<EntityStatus> constants() {
        return VALUE_SET.getValues();
    }
}

final class Result extends Enumeration<Result> {
    private Result(int id, String name) {
        super(id, name);
    }

    public static final Result SUCCESSFUL = new Result(1, "成功");
    public static final Result FAILURE = new Result(0, "失败");

    private static final ValueSet<Result> VALUE_SET = ValueSet.of(SUCCESSFUL, FAILURE);

    public static Result of(int id) {
        return VALUE_SET.get(id);
    }

    public static Collection<Result> constants() {
        return VALUE_SET.getValues();
    }
}
