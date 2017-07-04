package com.myproject.realm;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import com.myproject.model.UserEntity;
import com.myproject.service.shiro.ShiroService;

public class JdbcRealm extends AuthorizingRealm{
	
	@Resource
	private ShiroService shiroService;
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		return null;
	}
	
	//认证，登陆
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		UserEntity user = shiroService.getUserByName(upToken.getUsername());
		if(user!=null){
			ByteSource salt = ByteSource.Util.bytes(user.getUsername());
			return new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), salt,getName());
		}
		return null;
	}

}
