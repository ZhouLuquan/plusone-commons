package xyz.zhouxy.plusone.commons;

import java.io.ObjectStreamClass;

import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;
import xyz.zhouxy.plusone.commons.exception.BaseRuntimeException;

@Slf4j
class SerialTests {

    @Test
    void testSerialVersionUID() {
        long uid = getSerialVersionUID(BaseRuntimeException.class);
        log.info("\n    private static final long serialVersionUID = {}L;", uid);
    }

    private long getSerialVersionUID(Class<?> cl) {
        ObjectStreamClass c = ObjectStreamClass.lookup(cl);
        return c.getSerialVersionUID();
    }
}
