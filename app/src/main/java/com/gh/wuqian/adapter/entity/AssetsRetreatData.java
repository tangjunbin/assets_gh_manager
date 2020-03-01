/*
 * Copyright (C) 2020 xuexiangjys(xuexiangjys@163.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.gh.wuqian.adapter.entity;

public class AssetsRetreatData {
    /**
     * 签字状态
     */
   private String sign;
    /**
     * 退库单号
     */
   private String retreatBillNo;

    /**
     * 制单人
     */
    private String createUser;


    /**
     * 退库数量
     */
    private String retreatNum;

    /**
     * 退库日期
     */
    private String retreatDate;

    /**
     * 退库后使用公司
     */
    private String useComplay;

    /**
     * 退库后区域
     */
    private String retreatRegion;

    /**
     * 退库后存放地点
     */
    private String retreatPlace;

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getRetreatBillNo() {
        return retreatBillNo;
    }

    public void setRetreatBillNo(String retreatBillNo) {
        this.retreatBillNo = retreatBillNo;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getRetreatNum() {
        return retreatNum;
    }

    public void setRetreatNum(String retreatNum) {
        this.retreatNum = retreatNum;
    }

    public String getRetreatDate() {
        return retreatDate;
    }

    public void setRetreatDate(String retreatDate) {
        this.retreatDate = retreatDate;
    }

    public String getUseComplay() {
        return useComplay;
    }

    public void setUseComplay(String useComplay) {
        this.useComplay = useComplay;
    }

    public String getRetreatRegion() {
        return retreatRegion;
    }

    public void setRetreatRegion(String retreatRegion) {
        this.retreatRegion = retreatRegion;
    }

    public String getRetreatPlace() {
        return retreatPlace;
    }

    public void setRetreatPlace(String retreatPlace) {
        this.retreatPlace = retreatPlace;
    }
}
