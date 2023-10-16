# Snack-Overflow


# JWT 簡易測試
```
💡 簡易 JWT Token Test。

- 未連接 DB，使用者資訊存在 in-memory DB
- 用相同的 Secret Key 加密
```

### Postman

**登入**

登入後可獲得 token，有效時間為 20 秒。
- method：Post
- url：localhost://8080/login
- body：

```java
{
	"account":"test",
	"password": "123456"
}
```

![](/doc_img/jwt/jwt_postman_1.png)

**驗證 token**
- method：Post
- url：localhost://8080/testToken
- 在 Headers 的部分新增 Authorization 欄位，並將上面步驟產生的 token 貼進去，發送 request

![](/doc_img/jwt/jwt_postman_2.png)

### Swagger

> 測試網址：http://localhost:8080/swagger-ui/index.html#/

**登入**

登入後可獲得 token，有效時間為 20 秒。

- url：localhost://8080/login
- body：
    
    ```java
    {
    	"account":"test",
    	"password": "123456"
    }
    ```
    

![](/doc_img/jwt/jwt_swagger_1.png)

![](/doc_img/jwt/jwt_swagger_2.png)

**驗證 token**

點開 API 右上角鎖的 icon。
![](/doc_img/jwt/jwt_swagger_3.png)

貼上上一個步驟產生的 token，並按下「Authorize」。
   
![](/doc_img/jwt/jwt_swagger_4.png)
    
之後會看到 API 的鎖已經鎖起來了，代表有帶 token。
![](/doc_img/jwt/jwt_swagger_5.png)

結果。
![](/doc_img/jwt/jwt_swagger_6.png)

## Jenkins
2023-08-26 新增 Jenkins UT 環境 pipeline。

