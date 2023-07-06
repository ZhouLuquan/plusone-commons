package xyz.zhouxy.plusone.commons.collection;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javax.annotation.concurrent.ThreadSafe;

import com.google.common.annotations.Beta;
import com.google.common.collect.ImmutableTable;
import com.google.common.collect.Table;

/**
 * 将 {@link Table} 包装为同步集合，线程安全。
 *
 * <p>
 * 可通过以下方式构建一个线程安全的 {@link Table}
 * </p>
 * 
 * <pre>
 * SynchronizedTable.of(HashBasedTable.create())
 * </pre>
 * 
 * <p>
 * <b>NOTE: 如果 {@link Table} 不需要更改，请使用 {@link ImmutableTable}</b>
 * </p>
 *
 * @author <a href="http://zhouxy.xyz:3000/ZhouXY108/">ZhouXY</a>
 * @since 0.1.0-SNAPSHOT
 * @see Table
 * @see ImmutableTable
 */
@Beta
@ThreadSafe
public class SynchronizedTable<R, C, V> implements Table<R, C, V>, Serializable {

    private static final long serialVersionUID = 3716653837549439569L;

    @SuppressWarnings("serial")
    private final Table<R, C, V> table;
    @SuppressWarnings("serial")
    private final Object mutex;

    private SynchronizedTable(Table<R, C, V> table) {
        this.table = Objects.requireNonNull(table);
        this.mutex = this;
    }

    private SynchronizedTable(Table<R, C, V> table, Object mutex) {
        this.table = Objects.requireNonNull(table);
        this.mutex = mutex;
    }

    public static <R, C, V> SynchronizedTable<R, C, V> of(Table<R, C, V> table) {
        if (table instanceof SynchronizedTable) {
            return (SynchronizedTable<R, C, V>) table;
        } else {
            return new SynchronizedTable<>(table);
        }
    }

    public static <R, C, V> SynchronizedTable<R, C, V> of(Table<R, C, V> table, Object mutex) {
        if (table instanceof SynchronizedTable) {
            SynchronizedTable<R, C, V> srcTable = (SynchronizedTable<R, C, V>) table;
            return new SynchronizedTable<>(srcTable.table, mutex);
        } else {
            return new SynchronizedTable<>(table);
        }
    }

    @Override
    public boolean contains(Object rowKey, Object columnKey) {
        synchronized (mutex) {
            return this.table.contains(rowKey, columnKey);
        }
    }

    @Override
    public boolean containsRow(Object rowKey) {
        synchronized (mutex) {
            return this.table.containsRow(rowKey);
        }
    }

    @Override
    public boolean containsColumn(Object columnKey) {
        synchronized (mutex) {
            return this.table.containsColumn(columnKey);
        }
    }

    @Override
    public boolean containsValue(Object value) {
        synchronized (mutex) {
            return this.table.containsValue(value);
        }
    }

    @Override
    public V get(Object rowKey, Object columnKey) {
        synchronized (mutex) {
            return this.table.get(rowKey, columnKey);
        }
    }

    @Override
    public boolean isEmpty() {
        synchronized (mutex) {
            return this.table.isEmpty();
        }
    }

    @Override
    public int size() {
        synchronized (mutex) {
            return this.table.size();
        }
    }

    @Override
    public void clear() {
        synchronized (mutex) {
            this.table.clear();
        }
    }

    @Override
    public V put(R rowKey, C columnKey, V value) {
        synchronized (mutex) {
            return this.table.put(rowKey, columnKey, value);
        }
    }

    @Override
    public void putAll(Table<? extends R, ? extends C, ? extends V> table) {
        synchronized (mutex) {
            this.table.putAll(table);
        }
    }

    @Override
    public V remove(Object rowKey, Object columnKey) {
        synchronized (mutex) {
            return this.table.remove(rowKey, columnKey);
        }
    }

    @Override
    public Map<C, V> row(R rowKey) {
        synchronized (mutex) {
            return this.table.row(rowKey);
        }
    }

    @Override
    public Map<R, V> column(C columnKey) {
        synchronized (mutex) {
            return this.table.column(columnKey);
        }
    }

    @Override
    public Set<Cell<R, C, V>> cellSet() {
        synchronized (mutex) {
            return this.table.cellSet();
        }
    }

    @Override
    public Set<R> rowKeySet() {
        synchronized (mutex) {
            return this.table.rowKeySet();
        }
    }

    @Override
    public Set<C> columnKeySet() {
        synchronized (mutex) {
            return this.table.columnKeySet();
        }
    }

    @Override
    public Collection<V> values() {
        synchronized (mutex) {
            return this.table.values();
        }
    }

    @Override
    public Map<R, Map<C, V>> rowMap() {
        synchronized (mutex) {
            return this.table.rowMap();
        }
    }

    @Override
    public Map<C, Map<R, V>> columnMap() {
        synchronized (mutex) {
            return this.table.columnMap();
        }
    }
}
