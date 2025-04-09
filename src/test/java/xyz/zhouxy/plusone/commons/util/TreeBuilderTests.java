/*
 * Copyright 2023-2025 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package xyz.zhouxy.plusone.commons.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.gson.Gson;

import cn.hutool.core.util.ObjectUtil;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@SuppressWarnings("null")
class TreeBuilderTests {

    private static final Logger log = LoggerFactory.getLogger(TreeBuilderTests.class);

    private final MenuItem A     = MenuItem.of("A", "首页", "/home", 1);
    private final MenuList B     = MenuList.of("B", "系统管理", 3);
    private final MenuItem B001  = /**/MenuItem.of("B", "B001", "功能管理", "/sys/function-mgmt", 4);
    private final MenuItem B002  = /**/MenuItem.of("B", "B002", "角色管理", "/sys/role-mgmt", 3);
    private final MenuItem B003  = /**/MenuItem.of("B", "B003", "账号管理", "/sys/account-mgmt", 2);
    private final MenuItem B004  = /**/MenuItem.of("B", "B004", "系统参数管理", "/sys/param-mgmt", 1);
    private final MenuList C     = MenuList.of("C", "一级菜单C", 2);
    private final MenuList C1    = /**/MenuList.of("C", "C1", "二级菜单C1", 3);
    private final MenuItem C1001 = /**//**/MenuItem.of("C1", "C1001", "三级菜单C1001", "/c/c1/c1001", 1);
    private final MenuItem C1002 = /**//**/MenuItem.of("C1", "C1002", "三级菜单C1002", "/c/c1/c1002", 2);
    private final MenuItem C2    = /**/MenuItem.of("C", "C2", "二级菜单C2", "/c/c2", 1);
    private final MenuItem C3    = /**/MenuItem.of("C", "C3", "二级菜单C3", "/c/c3", 2);

    private final List<Menu> menus = ImmutableList.of(B, C1002, A, B004, C3, B001, B003, C1, C1001, B002, C, C2);

    private final TreeBuilder<Menu, MenuList, String> treeBuilder = new TreeBuilder<>(
            Menu::getMenuCode,
            menu -> Optional.ofNullable(menu.parentMenuCode),
            MenuList::addChild,
            Comparator.comparing(Menu::getOrderNum));

    @Test
    void testBuildTreeAndSortedByOrderNum() {
        List<Menu> clonedMenus = menus.stream().map(ObjectUtil::clone).collect(Collectors.toList());
        List<Menu> menuTreeSortedByOrderNum = treeBuilder.buildTree(clonedMenus);
        log.info("menuTreeSortedByOrderNum: {}", new Gson().toJson(menuTreeSortedByOrderNum));

        assertEquals(
            clonedMenus.stream()
                .filter(menu -> menu.getParentMenuCode() == null)
                .sorted(Comparator.comparing(Menu::getOrderNum))
                .collect(Collectors.toList()),
            menuTreeSortedByOrderNum);

        Map<String, Menu> menuMap = new HashMap<>();
        for (Menu element : clonedMenus) {
            menuMap.put(element.getMenuCode(), element);
        }

        assertEquals(
            Arrays.stream(new Menu[] { B001, B002, B003, B004 })
                .sorted(Comparator.comparing(Menu::getOrderNum))
                .collect(Collectors.toList()),
            ((MenuList) menuMap.get("B")).children);

        assertEquals(
            Arrays.stream(new Menu[] { C1, C2, C3 })
                .sorted(Comparator.comparing(Menu::getOrderNum))
                .collect(Collectors.toList()),
            ((MenuList) menuMap.get("C")).children);

        assertEquals(
            Arrays.stream(new Menu[] { C1001, C1002 })
                .sorted(Comparator.comparing(Menu::getOrderNum))
                .collect(Collectors.toList()),
            ((MenuList) menuMap.get("C1")).children);

    }

    @Test
    void testBuildTreeAndSortedByMenuCode() {
        List<Menu> clonedMenus;

        clonedMenus = menus.stream().map(ObjectUtil::clone).collect(Collectors.toList());
        List<Menu> menuTreeSortedByMenuCode = treeBuilder
                .buildTree(clonedMenus, Comparator.comparing(Menu::getMenuCode));
        log.info("menuTreeSortedByMenuCode: {}", new Gson().toJson(menuTreeSortedByMenuCode));

        assertEquals(
            clonedMenus.stream()
                .filter(menu -> menu.getParentMenuCode() == null)
                .sorted(Comparator.comparing(Menu::getMenuCode))
                .collect(Collectors.toList()),
            menuTreeSortedByMenuCode);

        Map<String, Menu> menuMap = new HashMap<>();
        for (Menu element : clonedMenus) {
            menuMap.put(element.getMenuCode(), element);
        }

        assertEquals(ImmutableList.of(B001, B002, B003, B004),
                ((MenuList) menuMap.get("B")).children);

        assertEquals(ImmutableList.of(C1, C2, C3),
                ((MenuList) menuMap.get("C")).children);

        assertEquals(ImmutableList.of(C1001, C1002),
                ((MenuList) menuMap.get("C1")).children);
    }

    @ToString
    @EqualsAndHashCode
    private abstract static class Menu implements Serializable {
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

        private static final long serialVersionUID = 20240917181424L;
    }

    @ToString(callSuper = true)
    @EqualsAndHashCode(callSuper = true)
    private static final class MenuItem extends Menu {

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

        private static final long serialVersionUID = 20240917181910L;
    }

    @ToString(callSuper = true)
    private static final class MenuList extends Menu {

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

        @SuppressWarnings("unused")
        static MenuList of(String menuCode, String title, Iterable<Menu> children, int orderNum) {
            return of(null, menuCode, title, children, orderNum);
        }

        static MenuList of(String parentMenuCode, String menuCode, String title, Iterable<Menu> children,
                int orderNum) {
            final MenuList instance = of(parentMenuCode, menuCode, title, orderNum);
            children.forEach(instance::addChild);
            return instance;
        }

        public void addChild(Menu child) {
            if (this.children == null) {
                this.children = Lists.newArrayList();
            }
            this.children.add(child);
        }

        private static final long serialVersionUID = 20240917181917L;
    }
}
