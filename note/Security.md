## mall整合SpringSecurity和JWT实现认证和授权
### SpringSecurity  
SpringSecurity是一个强大的可高度定制的认证和授权框架，对于Spring应用来说它是一套Web安全标准。SpringSecurity注重于为Java应用提供认证和授权功能，像所有的Spring项目一样，它对自定义需求具有强大的扩展性。

JWT  
JWT是JSON WEB TOKEN的缩写，它是基于 RFC 7519 标准定义的一种可以安全传输的的JSON对象，由于使用了数字签名，所以是可信任和安全的。
JWT的组成  
JWT token的格式：`header.payload.signature`  
header中用于存放签名的生成算法  
`{"alg": "HS512"}`  
payload中用于存放用户名、token的生成时间和过期时间  
`{"sub":"admin","created":1489079981393,"exp":1489684781}`  
signature为以header和payload生成的签名，一旦header和payload被篡改，验证将失败
```
//secret为加密算法的密钥
String signature = HMACSHA512(base64UrlEncode(header) + "." +base64UrlEncode(payload),secret)
Copy to clipboardErrorCopied
```
### JWT实现认证和授权的原理  
1.用户调用登录接口，登录成功后获取到JWT的`token`；  
2.之后用户每次调用接口都在http的header中添加一个叫`Authorization`的头，值为`JWT`的`token`；  
3.后台程序通过对`Authorization`头中信息的解码及数字签名校验来获取其中的用户信息，从而实现认证和授权。  

### 添加JWT token的工具类
用于生成和解析JWT token的工具类

相关方法说明：
generateToken(UserDetails userDetails) :用于根据登录用户信息生成token  
getUserNameFromToken(String token)：从token中获取登录用户的信息  
validateToken(String token, UserDetails userDetails)：判断token是否还有效  

### 添加SpringSecurity的配置类
```java
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UmsAdminService adminService;
    @Autowired
    private RestfulAccessDeniedHandler restfulAccessDeniedHandler;
    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf()// 由于使用的是JWT，我们这里不需要csrf
                .disable()
                .sessionManagement()// 基于token，所以不需要session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, // 允许对于网站静态资源的无授权访问
                        "/",
                        "/*.html",
                        "/favicon.ico",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js",
                        "/swagger-resources/**",
                        "/v2/api-docs/**"
                )
                .permitAll()
                .antMatchers("/admin/login", "/admin/register")// 对登录注册要允许匿名访问
                .permitAll()
                .antMatchers(HttpMethod.OPTIONS)//跨域请求会先进行一次options请求
                .permitAll()
//                .antMatchers("/**")//测试时全部运行访问
//                .permitAll()
                .anyRequest()// 除上面外的所有请求全部需要鉴权认证
                .authenticated();
        // 禁用缓存
        httpSecurity.headers().cacheControl();
        // 添加JWT filter
        httpSecurity.addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        //添加自定义未授权和未登录结果返回
        httpSecurity.exceptionHandling()
                .accessDeniedHandler(restfulAccessDeniedHandler)
                .authenticationEntryPoint(restAuthenticationEntryPoint);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService())
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        //获取登录用户信息
        return username -> {
            UmsAdmin admin = adminService.getAdminByUsername(username);
            if (admin != null) {
                List<UmsPermission> permissionList = adminService.getPermissionList(admin.getId());
                return new AdminUserDetails(admin,permissionList);
            }
            throw new UsernameNotFoundException("用户名或密码错误");
        };
    }

    @Bean
    public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter(){
        return new JwtAuthenticationTokenFilter();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
```

相关依赖及方法说明  
configure(HttpSecurity httpSecurity)：用于配置需要拦截的url路径、jwt过滤器及出异常后的处理器；  
configure(AuthenticationManagerBuilder auth)：用于配置UserDetailsService及PasswordEncoder；  
RestfulAccessDeniedHandler：当用户没有访问权限时的处理器，用于返回JSON格式的处理结果；  
RestAuthenticationEntryPoint：当未登录或token失效时，返回JSON格式的结果；  
UserDetailsService:SpringSecurity定义的核心接口，用于根据用户名获取用户信息，需要自行实现；  
UserDetails：SpringSecurity定义用于封装用户信息的类（主要是用户信息和权限），需要自行实现；  
PasswordEncoder：SpringSecurity定义的用于对密码进行编码及比对的接口，目前使用的是BCryptPasswordEncoder；  
JwtAuthenticationTokenFilter：在用户名和密码校验前添加的过滤器，如果有jwt的token，会自行根据token信息进行登录。  

