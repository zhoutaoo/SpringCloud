1.目录规划

2. API URI design
API URI 设计最重要的一个原则： nouns (not verbs!) ，名词（而不是动词）。

CRUD 简单 URI：

GET /users- 获取用户列表
GET /users/1- 获取 Id 为 1 的用户
POST /users- 创建一个用户
PUT /users/1- 替换 Id 为 1 的用户
PATCH /users/1- 修改 Id 为 1 的用户
DELETE /users/1- 删除 Id 为 1 的用户
上面是对某一种资源进行操作的 URI，那如果是有关联的资源，或者称为级联的资源，该如何设计 URI 呢？比如某一用户下的产品：

GET /users/1/products- 获取 Id 为 1 用户下的产品列表
GET /users/1/products/2- 获取 Id 为 1 用户下 Id 为 2 的产品
POST /users1/products- 在 Id 为 1 用户下，创建一个产品
PUT /users/1/products/2- 在 Id 为 1 用户下，替换 Id 为 2 的产品
PATCH /users/1/products.2- 修改 Id 为 1 的用户下 Id 为 2 的产品
DELETE /users/1/products/2- 删除 Id 为 1 的用户下 Id 为 2 的产品