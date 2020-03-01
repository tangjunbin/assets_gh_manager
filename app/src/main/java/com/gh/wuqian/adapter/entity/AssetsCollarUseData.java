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

public class AssetsCollarUseData {
    /**
     * 签字状态
     */
   private String sign;
    /**
     * 领用单号
     */
   private String collarBillNo;

    /**
     * 制单人
     */
    private String createUser;

    /**
     * 领用人
     */
    private String collarUser;

    /**
     * 领用数量
     */
    private String collarNum;

    /**
     * 领用日期
     */
    private String collarDate;

    /**
     * 领用后使用公司
     */
    private String useComplay;

    /**
     * 领用后区域
     */
    private String useRegion;

    /**
     * 领用后存放地点
     */
    private String usePlace;

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getCollarBillNo() {
        return collarBillNo;
    }

    public void setCollarBillNo(String collarBillNo) {
        this.collarBillNo = collarBillNo;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getCollarUser() {
        return collarUser;
    }

    public void setCollarUser(String collarUser) {
        this.collarUser = collarUser;
    }

    public String getCollarNum() {
        return collarNum;
    }

    public void setCollarNum(String collarNum) {
        this.collarNum = collarNum;
    }

    public String getCollarDate() {
        return collarDate;
    }

    public void setCollarDate(String collarDate) {
        this.collarDate = collarDate;
    }

    public String getUseComplay() {
        return useComplay;
    }

    public void setUseComplay(String useComplay) {
        this.useComplay = useComplay;
    }

    public String getUseRegion() {
        return useRegion;
    }

    public void setUseRegion(String useRegion) {
        this.useRegion = useRegion;
    }

    public String getUsePlace() {
        return usePlace;
    }

    public void setUsePlace(String usePlace) {
        this.usePlace = usePlace;
    }
}
