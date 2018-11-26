package com.man.demo;

/**
 * @description:
 * @author: manxiaoqing
 * @create: 2018-11-09 16:39
 **/
class Solution {
    public static int[] twoSum(int[] nums, int target) {
        int[] nums_new = new int[nums.length];
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= target) {
                nums_new[j++] = nums[i];
            }
        }

        int[] result = null;
        for (int i = 0; i < nums_new.length; i++) {
            for (int k = i + 1 ; k < nums_new.length ; k++) {
                if (nums_new[i] + nums_new[k] == target) {
                    result = new int[2];
                    result[0] = nums_new[i];
                    result[1] = nums_new[k];
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] a = {2, 4, 8, 9, 11};
        int target = 13;

        int[] ret = twoSum(a, target);
        if (ret == null) {
            System.out.println("不存在");
            return;
        }
        for (int i : ret) {
            System.out.println(i);
        }

        System.out.println(isNum("2ef10"));

        System.out.println(1 >>> 16);
    }

    static boolean isNum(String str) {
        try {
            Double.valueOf(str);
        } catch (Exception e) {
            System.out.println("转换错误");
            return false;
        }
        return true;
    }
}


