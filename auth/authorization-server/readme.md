授权服务介绍
----------

## 简介

## 启动

`mvn spring-boot:run`

## 测试

### 用户名密码

client_id:     test_client

client_secret: test_secret

username: admin

password: password

### client_credentials模式

主要

请求报文

```
POST /oauth/token?scope=read&grant_type=client_credentials HTTP/1.1
Host: localhost:8000
Authorization: Basic dGVzdF9jbGllbnQ6dGVzdF9zZWNyZXQ=
Cache-Control: no-cache
```
响应报文

```
{
  "access_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzY29wZSI6WyJyZWFkIl0sIm9yZ2FuaXphdGlvbiI6InRlc3RfY2xpZW50IiwiZXhwIjoxNTMxOTczMzAzLCJqdGkiOiI0NjBlYWRkNi1iNjU3LTRkNzAtYTFjZi00MWJjYWM5OTFkNzgiLCJjbGllbnRfaWQiOiJ0ZXN0X2NsaWVudCJ9.d_7f1N81hKWakA0eQeHOqW88-mYjYGgXHChMR_S6d6w",
  "token_type": "bearer",
  "expires_in": 43199,
  "scope": "read",
  "organization": "test_client",
  "jti": "460eadd6-b657-4d70-a1cf-41bcac991d78"
}
```

| key          |       备注         |
|--------------|--------------------|
| expires_in   | 过期时间，默认12小时 |
| token_type   | bearer             |
| scope        | read和write        |
| organization | 组织                |
| jti          | jwt token id       |
| access_token | token              |

### password模式

请求报文

```
POST /oauth/token?scope=read&grant_type=password HTTP/1.1
Host: localhost:8000
Authorization: Basic dGVzdF9jbGllbnQ6dGVzdF9zZWNyZXQ=
Cache-Control: no-cache
Content-Type: application/x-www-form-urlencoded

username=zhoutaoo&password=password
```
响应报文

```
{
  "access_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJ6aG91dGFvbyIsInNjb3BlIjpbInJlYWQiXSwib3JnYW5pemF0aW9uIjoiemhvdXRhb28iLCJleHAiOjE1MzE5NzM4MTgsImF1dGhvcml0aWVzIjpbIkFETUlOIiwiSVQiXSwianRpIjoiNTFiODY4ZDEtMGNlMS00ZmI4LTkwMWQtOWM3YmZmYzBhZGJiIiwiY2xpZW50X2lkIjoidGVzdF9jbGllbnQifQ.BlIryRbSL414rDv5EfzZSjpjvWybcX3hEJy3fV8l6Wo",
  "token_type": "bearer",
  "refresh_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJ6aG91dGFvbyIsInNjb3BlIjpbInJlYWQiXSwib3JnYW5pemF0aW9uIjoiemhvdXRhb28iLCJhdGkiOiI1MWI4NjhkMS0wY2UxLTRmYjgtOTAxZC05YzdiZmZjMGFkYmIiLCJleHAiOjE1MzQ1MjI2MTgsImF1dGhvcml0aWVzIjpbIkFETUlOIiwiSVQiXSwianRpIjoiMGU2N2Q5MDEtOThlMC00ZTk3LTkwNzgtODllMTBmZTRjOGI2IiwiY2xpZW50X2lkIjoidGVzdF9jbGllbnQifQ.zNtWWG8xxPsjTZKghOjyGNDjnhHqnPvikfqN1uynh3U",
  "expires_in": 43199,
  "scope": "read",
  "organization": "zhoutaoo",
  "jti": "51b868d1-0ce1-4fb8-901d-9c7bffc0adbb"
}
```
### refresh_token-刷新access_token

使用refresh_token更新access_token

请求报文

```
POST /oauth/token?scope=read&amp;grant_type=refresh_token&amp;refresh_token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJhZG1pbiIsInNjb3BlIjpbInJlYWQiXSwib3JnYW5pemF0aW9uIjoiYWRtaW4iLCJhdGkiOiJlODA5MDRkYi1mMDBkLTRkNDAtOGFlNS0xMWY2OTVlMzZjMTEiLCJleHAiOjE1MzQ1MjQzMTQsImF1dGhvcml0aWVzIjpbIkFETUlOIl0sImp0aSI6Ijk0OGUxZTYxLTBkOTUtNGYzMC04YWNlLWFmNDcyNjU2ZWNiNCIsImNsaWVudF9pZCI6InRlc3RfY2xpZW50In0.XrvwAi14NTJXm029CGFD3BsPgZdYQ7u1nszYlf42Eo8 HTTP/1.1
Host: host1:8000
Authorization: Basic dGVzdF9jbGllbnQ6dGVzdF9zZWNyZXQ=
Cache-Control: no-cache
Content-Type: multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW
```
响应报文

```
{
  "access_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJhZG1pbiIsInNjb3BlIjpbInJlYWQiXSwib3JnYW5pemF0aW9uIjoiYWRtaW4iLCJleHAiOjE1MzE5NzU2MjEsImF1dGhvcml0aWVzIjpbIkFETUlOIl0sImp0aSI6IjIzNDA4ZDM4LThjZGMtNDQ2MC1iZWFjLTI0Yzc2ZGM3NjI5YSIsImNsaWVudF9pZCI6InRlc3RfY2xpZW50In0.qawS1Z4j_h4vNx10GBC_Y_PHM1LLSQt64eniWLGzsJY",
  "token_type": "bearer",
  "refresh_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJhZG1pbiIsInNjb3BlIjpbInJlYWQiXSwib3JnYW5pemF0aW9uIjoiYWRtaW4iLCJhdGkiOiIyMzQwOGQzOC04Y2RjLTQ0NjAtYmVhYy0yNGM3NmRjNzYyOWEiLCJleHAiOjE1MzQ1MjQzMTQsImF1dGhvcml0aWVzIjpbIkFETUlOIl0sImp0aSI6Ijk0OGUxZTYxLTBkOTUtNGYzMC04YWNlLWFmNDcyNjU2ZWNiNCIsImNsaWVudF9pZCI6InRlc3RfY2xpZW50In0.eHRWxFZDJDq_2RDPL4DhyMy7IwF8GHn_LQU5j7ZkF8k",
  "expires_in": 43199,
  "scope": "read",
  "organization": "admin",
  "jti": "23408d38-8cdc-4460-beac-24c76dc7629a"
}
```
### authorization_code-授权码模式

先登录获取code,再获取token，暂未实现

### 简化模式

在redirect_uri 的Hash传递token; Auth客户端运行在浏览器中,如JS,Flash，暂未实现


