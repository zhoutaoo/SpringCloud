授权、认证子项目简介
---------

## 关键词

`OAuth2、JWT、Authentication（签权）、Authorization（授权）`

## 简介

本子项目采用spring-security和spring-security-oauth2实现授权与认证微服务，可供gateway实现微服务对外权限的动态管理与控制

| 服务名                     |   简介         |  默认地址                |
|---------------------------|----------------|-------------------------|
| authorization-server      | 授权服务        |  http://localhost:8000  |
| authentication-server     | 签权服务        |  http://localhost:8001  |
| authentication-client     | 签权客户端      |  jar包，简化调用签权服务   |
| db                        | 授权与签权DB脚本 |  ddl与dml               |

### OAuth2简介:

#### OAuth2的4种模式

客户端必须得到用户的授权（authorization grant），才能获得令牌（access token）。OAuth 2.0定义了四种授权方式。

* 密码模式（resource owner password credentials）：
用户向客户端提供自己的用户名和密码。客户端使用这些信息，向"服务商提供商"索要授权。

* 客户端模式（client credentials）：
指客户端以自己的名义，而不是以用户的名义，向"服务提供商"进行认证。严格地说，客户端模式并不属于OAuth框架所要解决的问题。在这种模式中，用户直接向客户端注册，客户端以自己的名义要求"服务提供商"提供服务，其实不存在授权问题。

* 授权码模式（authorization code）：
授权码模式，是功能最完整、流程最严密的授权模式。它的特点就是通过客户端的后台服务器，与"服务提供商"的认证服务器进行互动。

* 简化模式（implicit）：
不通过第三方应用程序的服务器，直接在浏览器中向认证服务器申请令牌，跳过了"授权码"这个步骤，因此得名。所有步骤在浏览器中完成，令牌对访问者是可见的，且客户端不需要认证。

具体请参考阮一峰老师的文章 [理解OAuth 2.0](http://www.ruanyifeng.com/blog/2014/05/oauth_2_0.html)，这里不展开讲

#### OAuth2的角色

* 资源拥有者(Resource Owner) - 例如：用户Tom

* 资源服务器(Resource Server) - 例如：微信

* 授权服务器(Authorization Server) - 这里是微信，因为微信有相关数据

* 客户端(Client) - 这里是某第三方App或某应用

#### Oauth2 Token内容简介

Token基本内容如下

* access_token：表示访问令牌，必选项。
* token_type：表示令牌类型，该值大小写不敏感，必选项，可以是Bearer类型或OAuth2类型。
* expires_in：表示过期时间，单位为秒。如果省略该参数，必须其他方式设置过期时间。
* refresh_token：表示更新令牌，用来获取下一次的访问令牌，可选项。
* scope：表示权限范围，如果与客户端申请的范围一致，此项可省略。

**密码模式使用的例子**

以某App登陆为例，用户请求获取授权信息

```
+-----------+                                     +-------------+
|           |       1-Request Authorization       |             |
|           |------------------------------------>|             |
|           |     grant_type&username&password    |             |--+
|           |                                     |Authorization|  | 2-Gen
|  Client   |                                     |Service      |  |   JWT
|           |       3-Response Authorization      |             |<-+
|           |<------------------------------------| Private Key |
|           |    access_token / refresh_token     |             |
|           |    token_type / expire_in / jti     |             |
+-----------+                                     +-------------+
```

### Spring OAuth2 + JWT Token简介

#### 本例中Spring OAuth2中Token结构简介

| key          |       备注                            |
|--------------|--------------------------------------|
| access_token | JWT Access Token，过期时间，默认12小时  |
| refresh_token| JWT Refresh Token，过期时间，默认30天   |
| expires_in   | 过期时间，单位秒     |
| token_type   | Bearer和OAuth2     |
| scope        | read和write        |

#### JWT(JSON Web Tokens)简介

>JWT是一种用于双方之间传递安全信息的简洁的、URL安全的表述性声明规范。JWT作为一个开放的标准（RFC 7519），定义了一种简洁的，自包含的方法用于通信双方之间以Json对象的形式安全的传递信息。因为数字签名的存在，这些信息是可信的，JWT可以使用HMAC算法或者是RSA的公私秘钥对进行签名。
>* 简洁(Compact): 可以通过URL，POST参数或者在HTTP header发送，因为数据量小，传输速度也很快。
>* 自包含(Self-contained)：负载中包含了所有用户所需要的信息，避免了多次查询数据库。

简短来说，用户请求时，将用户信息和授权范围序列化后放入一个JSON字符串，然后使用Base64进行编码，最终在授权服务器用私钥对这个字符串进行签名，得到一个JSON Web Token，我们可以像使用Access Token一样的直接使用它，假设其他所有的资源服务器都将持有一个RSA公钥。当资源服务器接收到这个在Http Header中存有Token的请求，资源服务器就可以拿到这个Token，并验证它是否使用正确的私钥签名（是否经过授权服务器签名，也就是验签）。验签通过，反序列化后就拿到OAuth 2的验证信息。


* Jwt Token包含了使用.分隔的三部分

`{Header 头部}.{Payload 负载}.{Signature 签名}`


* Header 头部

JWT包含了使用.分隔的三部分： 通常包含了两部分，token类型和采用的加密算法
```
{
   "alg": "HS256",
   "typ": "JWT"
 }
```

* Payload 负载

Token的第二部分是负载，它包含了claim， Claim是一些实体（通常指的用户）的状态和额外的元数据。
```
{
   "user_name": "admin", 
   "scope": [
       "read"
   ], 
   "organization": "admin", 
   "exp": 1531975621, 
   "authorities": [
       "ADMIN"
   ], 
   "jti": "23408d38-8cdc-4460-beac-24c76dc7629a", 
   "client_id": "test_client"
}
```

* Signature 签名

使用Base64编码后的header和payload以及一个秘钥，使用header中指定签名算法进行签名。

Jwt Token例子：

```
eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJhZG1pbiIsInNjb3BlIjpbInJlYWQiXSwib3JnYW5pemF0aW9uIjoiYWRtaW4iLCJleHAiOjE1MzE5NzU2MjEsImF1dGhvcml0aWVzIjpbIkFETUlOIl0sImp0aSI6IjIzNDA4ZDM4LThjZGMtNDQ2MC1iZWFjLTI0Yzc2ZGM3NjI5YSIsImNsaWVudF9pZCI6InRlc3RfY2xpZW50In0.qawS1Z4j_h4vNx10GBC_Y_PHM1LLSQt64eniWLGzsJY
```
可到http://www.bejson.com/enc/base64 解码，注意分3部分分别解，
也可使用官网解码工具[官网解码](https://jwt.io/)

### 表结构简介

#### Spring OAuth2表结构

| 表名                     |   简介                        |           默认地址                       |
|-------------------------|-------------------------------|-----------------------------------------|
| oauth_client_details    |   client持久化表               |  本例子中dml初使化了test_client           |
| oauth_client_token      |   用户客户端存储从服务端获取的token| 未使用，本例中均为服务端                  |
| oauth_access_token      |   access_token的持久表          |  未使用，本例中使用了jwt,无需持久化到服务器中|
| oauth_refresh_token     |   refresh_token的持久化表       |  本例中使用了jwt                         |
| oauth_approvals         |   授权码模式code持久化表         |  未调试实现                              |
| oauth_code              |   授权码模式code持久化表         |  未调试实现                              |

具体表结构请参考[spring-oauth-server 数据库表说明](http://andaily.com/spring-oauth-server/db_table_description.html)

#### 用户角色资源等表结构

| 表名        |   简介        |  备注                    |
|------------|---------------|-------------------------|
| users      |   用户表       |  使用应用的用户           |
| groups     |   组织表       |  通过users_groups_relation与users关联，多对多     |
| positions  |   岗位表       |  通过users_positions_relation与users关联，多对多  |
| roles      |   角色表       |  通过users_roles_relation与users关联，多对多      |
| menus      |   菜单表       |  通过roles_menus_relation与roles关联，多对多      |
| resources  |   资源表       |  通过roles_resources_relation与roles关联，多对多  |
