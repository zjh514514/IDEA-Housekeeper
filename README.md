# HouseKeeper

### 1.介绍
创新实践2项目——管家婆

### 2.技术栈

##### **语言**
- java

##### **框架**
- Struts2 2.5.12
- Spring 4.3.8
- Hibernate 5.2.11

##### 插件
- FastJson
- C3P0 数据库连接池

##### 其他
- SHA256 加密

### 3.API

> #### item部分
- 查询

```
url:zjh.hduzjh.cn/HouseKeeper/item-query
接口参数：
{
    which//String，i为查询父类，o查询子类
    type//int，0为查询收入类，1为查询支出类，若查询子类则为空
    id//int，子类所属的父类id
}
返回结果：
//查询父类：
{
    "data": [
        {
            "itemId": 15,
            "itemName": "职业收入",
            "type": 0
        }
    ],
    "status": 200
}
//查询子类：
{
    "data": [
        {
            "id": {
                "itemId": 13,
                "subitemId": 46,
                "subitemName": "银行手续"
            }
        }
    ],
    "status": 200
}
```
> #### account部分
- 查询

```
url:zjh.hduzjh.cn/HouseKeeper/account-query
返回结果：
{
    "data": [
        {
            "accountId": 1,
            "accountName": "现金"
        }
    ],
    "status": 200
}
```
> #### cash部分
- 增加

```
url:zjh.hduzjh.cn/HouseKeeper/cash-save
接口参数：
{
    which//String，i为保存收入，o保存支出
    time//String，时间戳
    site//String，位置
    people//String，有关人员
    money//double，金额
    remark//String，备注
    memberId//int，保存的成员id
    itemId//int，父类的id
    subItemId//int，子类的id
    accountId//int，账户的id
}
返回结果：
{
    "status":200
}
```
- 删除某条记录

```
url:zjh.hduzjh.cn/HouseKeeper/operator-delete
接口参数：
{
    which//String，ci为删除收入，co删除支出
    id//int，删除的记录的id
}
返回结果：
{
    "status":200
}
```
- 修改某条记录

```
url:zjh.hduzjh.cn/HouseKeeper/cash-update
接口参数：
{
    which//String，i修改收入，o修改支出
    time//String，时间戳
    site//String，位置
    people//String，有关人员
    money//double，金额
    remark//String，备注
    memberId//int，保存的成员id
    itemId//int，父类的id
    subItemId//int，子类的id
    accountId//int，账户的id
}
返回结果：
{
    "status":200
}
```

- 查询某一成员收支记录

```
url:zjh.hduzjh.cn/HouseKeeper/cash-query
接口参数：
{
    which//mi查询收入，mo查询支出
    memberId//用户id
}
返回结果：
{
    "data": [
        {
            "account": "现金",
            "balance": 48495.54,
            "item": "衣服饰品",
            "itemId": 1,
            "money": 123.45,
            "remark": "wwwww",
            "site": "wwww",
            "subItem": "衣服裤子",
            "subItemId": 12,
            "time": 1513613220000
        }
    ],
    "status": 200
}
```
- 查询某一成员父类收支

```
url:zjh.hduzjh.cn/HouseKeeper/cash-query
接口参数：
{
    which//String，ti为查询收入，to查询支出
    itemId//int，父类id
    memberId//int，成员id
}
返回结果：
{
    "data": [
        {
            "account": "现金",
            "balance": 48495.54,
            "item": "衣服饰品",
            "itemId": 1,
            "money": 123.45,
            "remark": "wwwww",
            "site": "wwww",
            "subItem": "衣服裤子",
            "subItemId": 12,
            "time": 1513613220000
        }
    ],
    "status": 200
}
```
- 查询某一成员子类收支

```
url:zjh.hduzjh.cn/HouseKeeper/cash-query
接口参数：
{
    which//String，si为查询收入，so查询支出
    subItemId//int，父类id
    memberId//int，成员id
}
返回结果：
{
    "data": [
        {
            "account": "现金",
            "balance": 48495.54,
            "item": "衣服饰品",
            "itemId": 1,
            "money": 123.45,
            "remark": "wwwww",
            "site": "wwww",
            "subItem": "衣服裤子",
            "subItemId": 12,
            "time": 1513613220000
        }
    ],
    "status": 200
}
```
- 查询某一成员账户收支查询

```
url:zjh.hduzjh.cn/HouseKeeper/cash-query
接口参数：
{
    which//String，ai为查询收入，ao查询支出
    accountId//int，账户id
    memberId//int，成员id
}
返回结果：
{
    "data": [
        {
            "account": "现金",
            "balance": 48495.54,
            "item": "衣服饰品",
            "itemId": 1,
            "money": 123.45,
            "remark": "wwwww",
            "site": "wwww",
            "subItem": "衣服裤子",
            "subItemId": 12,
            "time": 1513613220000
        }
    ],
    "status": 200
}
```
- 查询个人收支总览
```
url:zjh.hduzjh.cn/HouseKeeper/cash-yearSum
接口参数：
{
    memberId//成员id
    year//年份
}
返回结果：
{
    "date": {
        "i": {
            "1": 0,
            "2": 0,
            "3": 0,
            "4": 0,
            "5": 0,
            "6": 0,
            "7": 0,
            "8": 0,
            "9": 0,
            "10": 22,
            "11": 0,
            "12": 15010
        },
        "o": {
            "1": 0,
            "2": 0,
            "3": 0,
            "4": 0,
            "5": 0,
            "6": 0,
            "7": 0,
            "8": 0,
            "9": 0,
            "10": 1156.56,
            "11": 0,
            "12": 379.9
        },
        "sum": {
            "1": 0,
            "2": 0,
            "3": 0,
            "4": 0,
            "5": 0,
            "6": 0,
            "7": 0,
            "8": 0,
            "9": 0,
            "10": -1134.56,
            "11": 0,
            "12": 14630.1
        }
    },
    "status": 200
}
```

> #### 家庭成员部分
- 登陆

```
url:zjh.hduzjh.cn/HouseKeeper/login-login
接口参数：
{
    which//String，m为成员登陆，f为家庭登陆
    username//String，用户名
    password//String，密码
}
返回结果：
家庭：
{
    "data":
        {   
            "familyId":1,
            "familyName":"老张家",
            "password":"8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92",
            "username":"zjh123"
        },
    "status":200
}
成员：
{
    "data": {
        "id": {
            "balance": 50000,
            "familyId": 1,
            "familyName": "老张家",
            "memberId": 3,
            "name": "张家豪",
            "password": "8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92",
            "power": 1,
            "role": "爸爸",
            "username": "zjh"
        }
    },
    "status": 200
}
```
- 获取某一家庭或成员信息

```
url:zjh.hduzjh.cn/HouseKeeper/login-query
接口参数：
{
    which//String，f获取家庭信息，m获取成员信息，fm获取家庭中的成员
    id//int，获取的成员或家庭id
}
返回结果：
{
    "data": [
        {
            "id": {
                "balance": 354321.06,
                "familyId": 1,
                "familyName": "老张家",
                "memberId": 1,
                "name": "俞有成",
                "password": "8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92",
                "power": 0,
                "role": "叔叔",
                "username": "yyc"
            }
        }
    ],
    "status": 200
}
```
