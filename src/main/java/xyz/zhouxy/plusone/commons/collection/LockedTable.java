package xyz.zhouxy.plusone.commons.collection;

import com.google.common.annotations.Beta;
import com.google.common.collect.ImmutableTable;
import com.google.common.collect.Table;
import xyz.zhouxy.plusone.commons.annotation.ReaderMethod;
import xyz.zhouxy.plusone.commons.annotation.WriterMethod;

import javax.annotation.concurrent.ThreadSafe;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 使用 {@link ReentrantReadWriteLock} 将 {@link Table} 包装为线程安全的集合。
 *
 * <p>
 * 可通过以下方式构建一个线程安全的 {@link Table}
 * </p>
 *
 * <pre>
 * LockedTable.of(HashBasedTable.create())
 * </pre>
 *
 * <p>
 * <b>NOTE: 如果 {@link Table} 不需要更改，请使用 {@link ImmutableTable}</b>
 * </p>
 *
 * @author <a href="http://zhouxy.xyz:3000/ZhouXY108/">ZhouXY</a>
 * @see Table
 * @see ImmutableTable
 * @since 0.1.0-SNAPSHOT
 */
@Beta
@ThreadSafe
public class LockedTable<R, C, V> implements Table<R, C, V> {

    private final Table<R, C, V> table;

    private final ReentrantReadWriteLock.ReadLock readLock;
    private final ReentrantReadWriteLock.WriteLock writeLock;

    private LockedTable(Table<R, C, V> table, boolean fair) {
        this.table = Objects.requireNonNull(table);
        ReentrantReadWriteLock rwl = new ReentrantReadWriteLock(fair);
        this.readLock = rwl.readLock();
        this.writeLock = rwl.writeLock();
    }

    public static <R, C, V> LockedTable<R, C, V> of(Table<R, C, V> table) {
        if (table instanceof LockedTable) {
            return (LockedTable<R, C, V>) table;
        } else {
            return new LockedTable<>(table, false);
        }
    }

    public static <R, C, V> LockedTable<R, C, V> of(Table<R, C, V> table, boolean fair) {
        if (table instanceof LockedTable) {
            return (LockedTable<R, C, V>) table;
        } else {
            return new LockedTable<>(table, fair);
        }
    }

    @Override
    @ReaderMethod
    public boolean contains(Object rowKey, Object columnKey) {
        readLock.lock();
        try {
            return this.table.contains(rowKey, columnKey);
        } finally {
            readLock.unlock();
        }
    }

    @Override
    @ReaderMethod
    public boolean containsRow(Object rowKey) {
        readLock.lock();
        try {
            return this.table.containsRow(rowKey);
        } finally {
            readLock.unlock();
        }
    }

    @Override
    @ReaderMethod
    public boolean containsColumn(Object columnKey) {
        readLock.lock();
        try {
            return this.table.containsColumn(columnKey);
        } finally {
            readLock.unlock();
        }
    }

    @Override
    @ReaderMethod
    public boolean containsValue(Object value) {
        readLock.lock();
        try {
            return this.table.containsValue(value);
        } finally {
            readLock.unlock();
        }
    }

    @Override
    @ReaderMethod
    public V get(Object rowKey, Object columnKey) {
        readLock.lock();
        try {
            return this.table.get(rowKey, columnKey);
        } finally {
            readLock.unlock();
        }
    }

    @Override
    @ReaderMethod
    public boolean isEmpty() {
        readLock.lock();
        try {
            return this.table.isEmpty();
        } finally {
            readLock.unlock();
        }
    }

    @Override
    @ReaderMethod
    public int size() {
        readLock.lock();
        try {
            return this.table.size();
        } finally {
            readLock.unlock();
        }
    }

    @Override
    @WriterMethod
    public void clear() {
        writeLock.lock();
        try {
            this.table.clear();
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    @WriterMethod
    public V put(R rowKey, C columnKey, V value) {
        writeLock.lock();
        try {
            return this.table.put(rowKey, columnKey, value);
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    @WriterMethod
    public void putAll(Table<? extends R, ? extends C, ? extends V> table) {
        writeLock.lock();
        try {
            this.table.putAll(table);
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    @WriterMethod
    public V remove(Object rowKey, Object columnKey) {
        writeLock.lock();
        try {
            return this.table.remove(rowKey, columnKey);
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    @ReaderMethod
    public Map<C, V> row(R rowKey) {
        readLock.lock();
        try {
            return this.table.row(rowKey);
        } finally {
            readLock.unlock();
        }
    }

    @Override
    @ReaderMethod
    public Map<R, V> column(C columnKey) {
        readLock.lock();
        try {
            return this.table.column(columnKey);
        } finally {
            readLock.unlock();
        }
    }

    @Override
    @ReaderMethod
    public Set<Cell<R, C, V>> cellSet() {
        readLock.lock();
        try {
            return this.table.cellSet();
        } finally {
            readLock.unlock();
        }
    }

    @Override
    @ReaderMethod
    public Set<R> rowKeySet() {
        readLock.lock();
        try {
            return this.table.rowKeySet();
        } finally {
            readLock.unlock();
        }
    }

    @Override
    @ReaderMethod
    public Set<C> columnKeySet() {
        readLock.lock();
        try {
            return this.table.columnKeySet();
        } finally {
            readLock.unlock();
        }
    }

    @Override
    @ReaderMethod
    public Collection<V> values() {
        readLock.lock();
        try {
            return this.table.values();
        } finally {
            readLock.unlock();
        }
    }

    @Override
    @ReaderMethod
    public Map<R, Map<C, V>> rowMap() {
        readLock.lock();
        try {
            return this.table.rowMap();
        } finally {
            readLock.unlock();
        }
    }

    @Override
    @ReaderMethod
    public Map<C, Map<R, V>> columnMap() {
        readLock.lock();
        try {
            return this.table.columnMap();
        } finally {
            readLock.unlock();
        }
    }
}
