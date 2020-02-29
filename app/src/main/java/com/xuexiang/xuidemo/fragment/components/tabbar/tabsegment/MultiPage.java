/*
 * Copyright (C) 2018 xuexiangjys(xuexiangjys@163.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.xuexiang.xuidemo.fragment.components.tabbar.tabsegment;

/**
 * @author wuqian
 * @since 2020年1月6日 10点36分
 */
public enum MultiPage {

    资产列表(0),
    领用(1),
    退库(2),
    借用(3),
    清理报废(4);

    private final int position;

    MultiPage(int pos) {
        position = pos;
    }

    public static MultiPage getPage(int position) {
        return MultiPage.values()[position];
    }

    public static int size() {
        return MultiPage.values().length;
    }

    public static String[] getPageNames() {
        MultiPage[] pages = MultiPage.values();
        String[] pageNames = new String[pages.length];
        for (int i = 0; i < pages.length; i++) {
            pageNames[i] = pages[i].name();
        }
        return pageNames;
    }

    public int getPosition() {
        return position;
    }

}
