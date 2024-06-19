package org.example.sys_user.service.Impl;

import lombok.extern.slf4j.Slf4j;
import org.example.common.Const;
import org.example.common.exception.CustomException;
import org.example.common.R;
import org.example.rediscommon.util.RedisUtils;
import org.example.sys_user.common.MDA;
import org.example.rediscommon.common.RedisPrefix;
import org.example.sys_user.common.TokenInfo;
import org.example.sys_user.mapper.SysUserMapper;
import org.example.sys_user.model.SysUser;
import org.example.sys_user.feign.SysRoleFeign;
import org.example.sys_user.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    SysUserMapper mapper;
    @Autowired
    SysRoleFeign sysRoleFeign;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    RedisUtils redisUtils;
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Override
    public Map<String, String> login(String userName, String password) {
        TokenInfo tokenInfo = getToken(userName, password);
        if (tokenInfo == null) {
            throw new CustomException(500, "用户名或密码错误！");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", tokenInfo.getAccess_token());
        tokenMap.put("tokenHead", tokenHead);
        tokenMap.put("refreshToken",tokenInfo.getRefresh_token());
        String nickName = tokenInfo.getAdditionalInfo().get("nickName");
        tokenMap.put("userName", userName);
        tokenMap.put("nickName", nickName);

        /*用户登录成功后，将用户相关信息存入Redis，12小时后过期*/
        SysUser userInfo = mapper.getById(userName);
        redisUtils.set(RedisPrefix.USER_INFO +userName,userInfo,60*30, TimeUnit.SECONDS);

        return tokenMap;
    }

    @Override
    public void add(SysUser sysUser) {
        SysUser sysUserOld = mapper.getById(sysUser.getUserName());
        if (sysUserOld != null) {
            throw new CustomException(500, Const.ErrorMsg.SysUser.EXISTED);
        }
        sysUser.setPassword(new BCryptPasswordEncoder().encode(sysUser.getPassword()));
        try {
            mapper.add(sysUser);
        } catch (Exception e) {
            throw new CustomException(500, Const.ErrorMsg.SysUser.ADD, e.getMessage());
        }

    }

    @Override
    public void setRole(String userName, Long roleId, String checkWho) {
        //OpenFeign远程调用
        R result = sysRoleFeign.getById(roleId);
        if (!result.get("code").equals(200)) {
            throw new CustomException(500, (String)result.get("msg"));
        }
        if (result.get("code").equals(200)) {
            try {
                int rows = mapper.setRole(userName, roleId, checkWho);
                if (rows == 0) {
                    throw new CustomException(500, Const.ErrorMsg.SysUser.NOT_EXIST);
                }
            } catch (Exception e) {
                throw new CustomException(500, Const.ErrorMsg.SysUser.SET, e.getMessage());
            }
        }
    }

    /**
     * 获取token
     * @param userName
     * @param password
     * @return
     */
    private TokenInfo getToken(String userName, String password) {
        ResponseEntity<TokenInfo> response;
        try{
            //远程调用认证服务器 进行用户登陆
            response = restTemplate.exchange(MDA.OAUTH_LOGIN_URL, HttpMethod.POST, wrapOauthTokenRequest(userName,password), TokenInfo.class);
            TokenInfo tokenInfo = response.getBody();
            log.info("根据用户名:{}登陆成功:TokenInfo:{}",userName,tokenInfo);
            return tokenInfo;
        }catch (Exception e) {
            log.error("根据用户名:{}登陆异常:{}",userName,e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 方法实现说明:封装用户到认证中心的请求头 和请求参数
     * @author:smlz
     * @param userName 用户名
     * @param password 密码
     * @return:
     * @exception:
     * @date:2020/1/22 15:32
     */
    private HttpEntity<MultiValueMap<String, String>> wrapOauthTokenRequest(String userName, String password) {
        //封装oauth2 请求头 clientId clientSecret
        HttpHeaders httpHeaders = wrapHttpHeaders();
        //封装请求参数     oauth2 密码模式
        MultiValueMap<String, String> reqParams = new LinkedMultiValueMap<>();
        reqParams.add(MDA.USER_NAME,userName);
        reqParams.add(MDA.PASS,password);
        reqParams.add(MDA.GRANT_TYPE,MDA.PASS);
        reqParams.add(MDA.SCOPE,MDA.SCOPE_AUTH);
        //封装请求参数
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(reqParams, httpHeaders);
        return entity;
    }

    /**
     * 方法实现说明:封装刷新token的请求
     * @author:smlz
     * @param refreshToken:刷新token
     * @return: HttpEntity
     * @exception:
     * @date:2020/1/22 16:14
     */
    private HttpEntity<MultiValueMap<String, String>> wrapRefreshTokenRequest(String refreshToken) {
        HttpHeaders httpHeaders = wrapHttpHeaders();
        MultiValueMap<String,String> param = new LinkedMultiValueMap<>();
        param.add("grant_type","refresh_token");
        param.add("refresh_token",refreshToken);
        HttpEntity<MultiValueMap<String,String>> httpEntity = new HttpEntity<>(param,httpHeaders);
        return httpEntity;
    }

    /**
     * 方法实现说明:封装请求头
     * @author:smlz
     * @return:HttpHeaders
     * @exception:
     * @date:2020/1/22 16:10
     */
    private HttpHeaders wrapHttpHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        httpHeaders.setBasicAuth(MDA.CLIENT_ID,MDA.CLIENT_SECRET);
        return httpHeaders;
    }
}
