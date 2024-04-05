# 旅游网站部署手册

## 项目源地：

小黄的项目地址：https://gitee.com/huang-ziw/projects

![image-20230722181317641](https://qny.luckyblank.cn/image-20230722181317641.png)



## 更新日志：

- **2023/07/22 :** 

> 1、将前端后端项目整合成一个父控项目，统一管理依赖和版本
>
> 2、Springboot版本升级至2.7.5
>
> 3、增加knife4j接口文档支持
>
> 4、调整前端vue打包配置，重写管理端的登录界面

- **后期优化中...**



## 前置环境：

> 后端环境：
>
> ​	配置好 Maven+Redis + Mysql8 
>
> 前端环境：
>
> ​	配置好 NodeJS



## 项目预览

#### 整体项目结构：

<img src="https://qny.luckyblank.cn/image-20230722170305571.png" alt="image-20230722170305571" style="zoom:80%;" />

#### 接口文档：

![image-20230722170551451](https://qny.luckyblank.cn/image-20230722170551451.png)



#### 管理端页面：

| ![image-20230722170719600](https://qny.luckyblank.cn/image-20230722170719600.png) | ![image-20230722170942948](https://qny.luckyblank.cn/image-20230722170942948.png) |
| :----------------------------------------------------------: | :----------------------------------------------------------: |
|                           登录页面                           |                             首页                             |



#### 用户端页面：

| ![image-20230722171139144](https://qny.luckyblank.cn/image-20230722171139144.png) | ![image-20230722171237937](https://qny.luckyblank.cn/image-20230722171237937.png) |
| :----------------------------------------------------------: | :----------------------------------------------------------: |
|                           登录页面                           |                             首页                             |



## 运行项目：

### 后端运行：



1、配置好maven + mysql8 + redis相关的环境

2、创建tourism数据库，将sql文件夹下的tourism.sql文件导入mysql数据库中

3、修改application-dev.yml中数据源为自己的账号密码

注：开发环境application.yml中激活配置为 **spring.profiles.active = dev**

```yaml
  # 数据源相关
  datasource:
    type: com.alibaba.druid.pool.DruidDataSource
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/tourism?serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true&rewriteBatchedStatements=true&allowMultiQueries=true
    username: root
    password: 123456
```

4、修改application-dev.yml中Redis为自己的账号密码(**没有密码就空着**)

```yaml
  # redis相关
  redis:
    host: localhost        # Redis 服务器主机名
    port: 6379             # Redis 服务器端口
    password:              # Redis 服务器密码（如果设置了密码）
    database: 0            # Redis 使用的数据库索引
    timeout: 5000          # Redis 连接超时时间（单位：毫秒）
```

5、启动TourismApplication主类



### 接口文档：

查看配置文件`application-dev.yml`

```yaml
server:
  port: 8083
  servlet:
    context-path: /tourism-api
```

浏览器访问接口文档：http://localhost:8083/tourism-api/doc.html



### 前端运行：

----------

#### 管理端运行：

1、进入到**tourism-ui  / admin**文件夹下

2、运行 **npm install**

3、运行 **npm run serve**

4、浏览器访问：http://localhost:8081/tourism/admin/



#### 用户端运行：

1、进入到**tourism-ui  / customer**文件夹下

2、运行 **npm install**

3、运行 **npm run serve**

4、浏览器访问：http://localhost:8082/tourism/customer/



## 部署项目：

### 后端部署：



1、配置好maven + mysql8 + redis相关的环境

2、创建tourism数据库，将sql文件夹下的tourism.sql文件导入mysql数据库中

3、修改application-prod.yml中数据源为自己的账号密码

注：开发环境application.yml中激活配置为 **spring.profiles.active = prod**

```yaml
  # 数据源相关
  datasource:
    type: com.alibaba.druid.pool.DruidDataSource
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/tourism?serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true&rewriteBatchedStatements=true&allowMultiQueries=true
    username: root
    password: 123456
```

4、修改application-prod.yml中Redis为自己的账号密码(**没有密码就空着**)

```yam
  # redis相关
  redis:
    host: localhost        # Redis 服务器主机名
    port: 6379             # Redis 服务器端口
    password:              # Redis 服务器密码（如果设置了密码）
    database: 0            # Redis 使用的数据库索引
    timeout: 5000          # Redis 连接超时时间（单位：毫秒）
```

5、用IDEA进行maven打包，在target目录下会生成tourism-api.war包



6、这里用Tomcat8.5作为服务器，webapps相当于网站根目录；将生成的tourism-api.war包放在webapps目录下

7、在webapps目录下创建以下目录结构：（创建**tourism / admin** 和 **tourism / customer**文件夹）

webapps
├─tourism
    └─admin
    ├─customer

注：

admin 用于存放管理端打包的文件；

customer用于存放用户端打包的文件

### 前端部署：

----------

#### 管理端部署：

1、进入到**tourism-ui  / admin**文件夹下

2、运行 **npm install**

3、运行 **npm run build**

4、打包完成会生成dist文件夹

6、将dist文件中的内容，全部拷贝至tomcat的webapps/tourism/admin文件夹中

7、浏览器访问：http://localhost:8080/tourism/admin/



#### 用户端部署：

1、进入到**tourism-ui  / customer**文件夹下

2、运行 **npm install**

3、运行 **npm run build**

4、打包完成会生成dist文件夹

6、将dist文件中的内容，全部拷贝至tomcat的webapps/tourism/customer文件夹中

7、浏览器访问：http://localhost:8080/tourism/customer/



最终部署效果：

![image-20230722181014747](https://qny.luckyblank.cn/image-20230722181014747.png)



## 后期展望：

- 没有展望，纯纯一时兴起。后面如果有好的idea可以集成进去，自行扩展。
