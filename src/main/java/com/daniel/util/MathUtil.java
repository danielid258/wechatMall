package com.daniel.util;

import com.daniel.constant.SysConstant;

/**
 * on 6/8/2018.
 */
public class MathUtil {

    /**
     * 比较2个金额是否相等
     *
     * @param d1
     * @param d2
     * @return
     */
    public static Boolean equals(Double d1, Double d2) {
        Double result = Math.abs(d1 - d2);
        if (result < SysConstant.MONEY_RANGE) {
            return true;
        } else {
            return false;
        }
    }
}
