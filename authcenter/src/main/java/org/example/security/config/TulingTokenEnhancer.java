package org.example.security.config;

import org.example.security.domain.TestUserDetails;
import org.example.security.entity.SysUser;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

/**
 * @vlog: 高于生活，源于生活
 * @desc: 类的描述:jwt自定义增强器(根据自己的业务需求添加非敏感字段)
 * @author: smlz
 * @createDate: 2020/1/21 21:51
 * @version: 1.0
 */
public class TulingTokenEnhancer implements TokenEnhancer {
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        TestUserDetails user = (TestUserDetails) authentication.getPrincipal();

        final Map<String, Object> additionalInfo = new HashMap<>();
        final Map<String, Object> retMap = new HashMap<>();

        //这里暴露memberId到Jwt的令牌中,后期可以根据自己的业务需要 进行添加字段
        additionalInfo.put("userName",user.getUsername());
        additionalInfo.put("nickName", user.getUser().getNickName());
        retMap.put("additionalInfo",additionalInfo);
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(retMap);

        return accessToken;
    }
}
