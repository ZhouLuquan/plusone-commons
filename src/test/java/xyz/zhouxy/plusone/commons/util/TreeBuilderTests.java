package xyz.zhouxy.plusone.commons.util;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;

import lombok.ToString;

class TreeBuilderTests {

    private static final Logger log = LoggerFactory.getLogger(TreeBuilderTests.class);

    @Test
    void testBuildTree() {
        List<Menu> menus = Lists.newArrayList(
            MenuItem.of("A", "首页", "/home"),
            MenuList.of("B", "系统管理"),
                MenuItem.of("B", "B001", "功能管理", "/sys/function-mgmt"),
                MenuItem.of("B", "B002", "角色管理", "/sys/role-mgmt"),
                MenuItem.of("B", "B003", "账号管理", "/sys/account-mgmt"),
                MenuItem.of("B", "B004", "系统参数管理", "/sys/param-mgmt"),
            MenuList.of("C", "一级菜单C"),
                MenuList.of("C", "C1", "二级菜单C1"),
                    MenuItem.of("C1", "C1001", "三级菜单C1001", "/c/c1/c1001"),
                    MenuItem.of("C1", "C1002", "三级菜单C1002", "/c/c1/c1002"),
                MenuItem.of("C", "C2", "二级菜单C2", "/c/c2"),
                MenuItem.of("C", "C3", "二级菜单C3", "/c/c3")
        );
        List<Menu> menuTree = new TreeBuilder<>(
                menus,
                Menu::getMenuCode,
                Menu::getParentMenuCode,
                Menu::addChild)
                .buildTree();
        log.info("menuTree: {}", menuTree);
    }
}

@ToString
abstract class Menu {
    protected final String parentMenuCode;
    protected final String menuCode;
    protected final String title;

    public Menu(String parentMenuCode, String menuCode, String title) {
        this.parentMenuCode = parentMenuCode;
        this.menuCode = menuCode;
        this.title = title;
    }

    public String getMenuCode() {
        return menuCode;
    }

    public Optional<String> getParentMenuCode() {
        return Optional.ofNullable(parentMenuCode);
    }

    public String getTitle() {
        return title;
    }

    public abstract void addChild(Menu child);
}

@ToString(callSuper = true)
class MenuItem extends Menu {

    private final String url;

    private MenuItem(String parentMenuCode, String menuCode, String title, String url) {
        super(parentMenuCode, menuCode, title);
        this.url = url;
    }

    static MenuItem of(String parentMenuCode, String menuCode, String title, String url) {
        return new MenuItem(parentMenuCode, menuCode, title, url);
    }

    static MenuItem of(String menuCode, String title, String url) {
        return new MenuItem(null, menuCode, title, url);
    }

    public String getUrl() {
        return url;
    }

    @Override
    @Deprecated
    public void addChild(Menu child) {
        throw new UnsupportedOperationException("Unimplemented method 'addChild'");
    }
}

@ToString(callSuper = true)
class MenuList extends Menu {

    private List<Menu> children;

    private MenuList(String parentMenuCode, String menuCode, String title) {
        super(parentMenuCode, menuCode, title);
    }

    static MenuList of(String parentMenuCode, String menuCode, String title) {
        return new MenuList(parentMenuCode, menuCode, title);
    }

    static MenuList of(String menuCode, String title) {
        return new MenuList(null, menuCode, title);
    }

    static MenuList of(String menuCode, String title, Iterable<Menu> children) {
        return of(null, menuCode, title, children);
    }

    static MenuList of(String parentMenuCode, String menuCode, String title, Iterable<Menu> children) {
        MenuList instance = of(parentMenuCode, menuCode, title);
        for (Menu child : children) {
            instance.addChild(child);
        }
        return instance;
    }

    @Override
    public void addChild(Menu child) {
        if (this.children == null) {
            this.children = Lists.newArrayList();
        }
        this.children.add(child);
    }
}
