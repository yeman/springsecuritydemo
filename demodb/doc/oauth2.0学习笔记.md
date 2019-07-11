 /oauth/authorize：授权端点
/oauth/token：令牌端点
/oauth/confirm_access：用户确认授权提交端点
/oauth/error：授权服务错误信息端点
/oauth/check_token：用于资源服务访问的令牌解析端点
/oauth/token_key：提供公有密匙的端点，如果你使用 JWT 令牌的话

------

oAuth 2.0 定义了四种授权方式。

implicit：简化模式，不推荐使用
authorization code：授权码模式
resource owner password credentials：密码模式
client credentials：客户端模式

内存授权配置

```
@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

		clients.inMemory()
	        .withClient("my-trusted-client")
            .authorizedGrantTypes("password", "authorization_code", "refresh_token", "implicit")
            .authorities("ROLE_CLIENT", "ROLE_TRUSTED_CLIENT")
            .scopes("read", "write", "trust")
            .secret("secret")
            .accessTokenValiditySeconds(120).//Access token is only valid for 2 minutes.
            refreshTokenValiditySeconds(600);//Refresh token is only valid for 10 minutes.
	}

```





SpringSecurity grantType 有 password  authorization_code  refresh_token  implicit

授权码模式

http://localhost:8081/oauth/authorize?client_id=clientId&response_type=code

postman 获取token 

curl -X POST -H "Content-Type: application/x-www-form-urlencoded" -d 'grant_type=authorization_code&code=mymJ1A' "http://clientId:secret@localhost:8081/oauth/token"

返回token {
    "access_token": "521f0bce-c4dd-404e-95fa-c38dfc5916bf",
    "token_type": "bearer",
    "expires_in": 119,
    "scope": "all"
}



------

```

