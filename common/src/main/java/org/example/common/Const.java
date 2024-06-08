package org.example.common;

public class Const {
    public static class SuccessMsg {
        public static class SysUser {
            public static String ADD = "新增成功！";
            public static String SET = "设置成功！";
            public static String GET = "查询成功！";
        }
        public static class SysRole {
            public static String ADD = "新增成功！";
        }
        public static class BizInventory {
            public static String DEDUCT = "扣减库存成功！";
            public static String GET = "查询成功！";
        }
        public static class BasSku {
            public static String GET = "查询成功！";
        }
    }
    public static class ErrorMsg {
        public static class SysUser {
            public static String EXISTED = "用户名已存在！";
            public static String NOT_EXIST = "用户不存在！";
            public static String ADD = "新增用户失败！";
            public static String SET = "设置失败！";
        }

        public static class SysRole {
            public static String EXISTED = "角色已存在！";
            public static String NOT_EXIST = "角色不存在！";
            public static String GET = "查询失败！";
            public static String ADD = "新增角色失败！";
        }
        public static class BizInventory {
            public static String NOT_ENOUGH = "库存不足！";
            public static String DEDUCT = "扣减库存失败！";
        }
        public static class BasSku {
            public static String NOT_EXIST = "物料号不存在！";
            public static String GET = "查询失败！";
        }
    }
}
