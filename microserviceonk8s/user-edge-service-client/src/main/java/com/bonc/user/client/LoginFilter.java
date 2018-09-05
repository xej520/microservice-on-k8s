package com.bonc.user.client;

import com.bonc.thrift.dto.UserDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

public abstract class LoginFilter implements Filter {

    private static Cache<String, UserDTO> cache = CacheBuilder.newBuilder().maximumSize(10000).expireAfterWrite(3,TimeUnit.MINUTES).build();


    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        // 1、验证是否已经登陆了

        String token = request.getParameter("token");
        if(StringUtils.isBlank(token)) {
        //  2、好多情况下，是从cook里获取的
            Cookie[] cookies = request.getCookies();
            if(cookies!=null) {
                for(Cookie c : cookies) {
                    if(c.getName().equals("token")) {
                        token = c.getValue();
                    }
                }
            }
        }

        UserDTO userDTO = null;
        if(StringUtils.isNotBlank(token)) {
            //先从本地里获取，然后再去远程服务器里
            userDTO = cache.getIfPresent(token);
            if(userDTO==null) {
                userDTO = requestUserInfo(token);
                if(userDTO!=null) {
                    //添加到本地缓存里
                    cache.put(token, userDTO);
                }
            }
        }

        //如果为null的话，就跳转到登录页
        if(userDTO==null) {
            System.out.println("---------------------LoginFilter------------------------------");
            response.sendRedirect("http://user-edge-service:8082/user/login");
            return;
        }

        login(request, response, userDTO);

        filterChain.doFilter(request, response);



    }

    protected abstract void login(HttpServletRequest request, HttpServletResponse response, UserDTO userDTO);

    private UserDTO requestUserInfo(String token) {
//        String url = "http://"+userEdgeServiceAddr()+"/user/authentication";

//        String url = "http://127.0.0.1:8082/user/authentication";
        String url = "http://user-edge-service:8082/user/authentication";

//        通过http client来访问这个请求
        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);
        post.addHeader("token", token);

        InputStream inputStream = null;
        try {
            HttpResponse response = client.execute(post);
            if(response.getStatusLine().getStatusCode()!= HttpStatus.SC_OK) {
                throw new RuntimeException("request user info failed! StatusLine:"+response.getStatusLine());
            }
            inputStream = response.getEntity().getContent();
            byte[] temp = new byte[1024];
            StringBuilder sb = new StringBuilder();

            //读入的长度
            int len = 0;
            while((len = inputStream.read(temp))>0) {
                sb.append(new String(temp,0,len));
            }

            //将JSON转换成对象
            UserDTO userDTO = new ObjectMapper().readValue(sb.toString(), UserDTO.class);

            return userDTO;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭输入流
            if(inputStream!=null) {
                try{
                    inputStream.close();
                }catch(Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }


    public void destroy() {

    }
}
