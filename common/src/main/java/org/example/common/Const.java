package org.example.common;

public class Const {
    public static class SuccessMsg {
        public static class SysUser {
            public static final String ADD = "新增成功！";
            public static final String SET = "设置成功！";
            public static final String GET = "查询成功！";
            public static final String LOGIN = "登录成功！";
        }
        public static class SysRole {
            public static final String ADD = "新增成功！";
        }
        public static class BizInventory {
            public static final String DEDUCT = "扣减库存成功！";
            public static final String GET = "查询成功！";
        }
        public static class BasSku {
            public static final String GET = "查询成功！";
            public static final String DESC_STOCK = "扣减库存成功！";
        }
        public static class ShoppingCart {
            public static final String ADD = "加入购物车成功！";
        }
    }
    public static class ErrorMsg {
        public static class SysUser {
            public static final String EXISTED = "用户名已存在！";
            public static final String NOT_EXIST = "用户不存在！";
            public static final String ADD = "新增用户失败！";
            public static final String SET = "设置失败！";
        }

        public static class SysRole {
            public static final String EXISTED = "角色已存在！";
            public static final String NOT_EXIST = "角色不存在！";
            public static final String GET = "查询失败！";
            public static final String ADD = "新增角色失败！";
        }
        public static class BasSku {
            public static final String NOT_EXIST = "物料号不存在！";
            public static final String NOT_ENOUGH = "库存不足！";
            public static final String GET = "查询失败！";
        }
        public static class ShoppingCart {
            public static final String QTY_LEAST = "数量至少为1！";
            public static final String NOT_EXISTS = "商品在购物车中不存在！";
        }
    }
}
