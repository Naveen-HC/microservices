package com.navi.gatewayserver.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class KeylockRoleConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

    @Override
    public Collection<GrantedAuthority> convert(Jwt source) {
        Map<String, Object> stringObjectMap = (Map<String, Object>) source.getClaims().get("realm_access");

        List<GrantedAuthority> grantedAuthorities= ((List<String>) stringObjectMap.get("roles"))
                .stream()
                .map(s -> "ROLE_"+s)
                .map(SimpleGrantedAuthority::new).collect(Collectors.toList());

        return grantedAuthorities;
    }
}
