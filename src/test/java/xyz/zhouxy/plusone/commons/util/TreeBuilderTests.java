package xyz.zhouxy.plusone.commons.util;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;
import com.google.gson.Gson;

import lombok.ToString;

class TreeBuilderTests {

    private static final Logger log = LoggerFactory.getLogger(TreeBuilderTests.class);
    private final TreeBuilder<Menu, MenuList, String> treeBuilder = new TreeBuilder<>(
            Menu::getMenuCode,
            menu -> Optional.ofNullable(menu.parentMenuCode),
            MenuList::addChild,
            (a, b) -> Integer.compare(a.getOrderNum(), b.getOrderNum()));

    @Test
    void testBuildTree() {
        List<Menu> menus = Lists.newArrayList(
                MenuList.of("B", "系统管理", 3),
                MenuItem.of("A", "首页", "/home", 1),
                /**/MenuItem.of("B", "B002", "角色管理", "/sys/role-mgmt", 3),
                /**/MenuItem.of("B", "B001", "功能管理", "/sys/function-mgmt", 4),
                /**/MenuItem.of("B", "B004", "系统参数管理", "/sys/param-mgmt", 1),
                /**/MenuItem.of("B", "B003", "账号管理", "/sys/account-mgmt", 2),
                MenuList.of("C", "一级菜单C", 2),
                /**/MenuItem.of("C", "C3", "二级菜单C3", "/c/c3", 2),
                /**/MenuList.of("C", "C1", "二级菜单C1", 2),
                /**//**/MenuItem.of("C1", "C1001", "三级菜单C1001", "/c/c1/c1001", 1),
                /**//**/MenuItem.of("C1", "C1002", "三级菜单C1002", "/c/c1/c1002", 2),
                /**/MenuItem.of("C", "C2", "二级菜单C2", "/c/c2", 1));

        List<Menu> menuTreeSortedByOrderNum = treeBuilder.buildTree(menus);
        log.info("menuTreeSortedByOrderNum: {}", new Gson().toJson(menuTreeSortedByOrderNum));

        List<Menu> menuTreeSortedByMenuCode = treeBuilder.buildTree(
                menus,
                (a, b) -> a.getMenuCode().compareTo(b.getMenuCode())
        );
        log.info("menuTreeSortedByMenuCode: {}", new Gson().toJson(menuTreeSortedByMenuCode));
    }
}

@ToString
abstract class Menu {
    protected final String parentMenuCode;
    protected final String menuCode;
    protected final String title;
    protected final int orderNum;

    public Menu(String parentMenuCode, String menuCode, String title, int orderNum) {
        this.parentMenuCode = parentMenuCode;
        this.menuCode = menuCode;
        this.title = title;
        this.orderNum = orderNum;
    }

    public String getMenuCode() {
        return menuCode;
    }

    public String getParentMenuCode() {
        return parentMenuCode;
    }

    public String getTitle() {
        return title;
    }

    public int getOrderNum() {
        return orderNum;
    }
}

@ToString(callSuper = true)
class MenuItem extends Menu {

    private final String url;

    private MenuItem(String parentMenuCode, String menuCode, String title, String url, int orderNum) {
        super(parentMenuCode, menuCode, title, orderNum);
        this.url = url;
    }

    static MenuItem of(String parentMenuCode, String menuCode, String title, String url, int orderNum) {
        return new MenuItem(parentMenuCode, menuCode, title, url, orderNum);
    }

    static MenuItem of(String menuCode, String title, String url, int orderNum) {
        return new MenuItem(null, menuCode, title, url, orderNum);
    }

    public String getUrl() {
        return url;
    }
}

@ToString(callSuper = true)
class MenuList extends Menu {

    private List<Menu> children;

    private MenuList(String parentMenuCode, String menuCode, String title, int orderNum) {
        super(parentMenuCode, menuCode, title, orderNum);
    }

    static MenuList of(String parentMenuCode, String menuCode, String title, int orderNum) {
        return new MenuList(parentMenuCode, menuCode, title, orderNum);
    }

    static MenuList of(String menuCode, String title, int orderNum) {
        return new MenuList(null, menuCode, title, orderNum);
    }

    static MenuList of(String menuCode, String title, Iterable<Menu> children, int orderNum) {
        return of(null, menuCode, title, children, orderNum);
    }

    static MenuList of(String parentMenuCode, String menuCode, String title, Iterable<Menu> children, int orderNum) {
        MenuList instance = of(parentMenuCode, menuCode, title, orderNum);
        for (Menu child : children) {
            instance.addChild(child);
        }
        return instance;
    }

    public void addChild(Menu child) {
        if (this.children == null) {
            this.children = Lists.newArrayList();
        }
        this.children.add(child);
    }
}
