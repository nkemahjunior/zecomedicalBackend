package com.zeco.zecomedical.auth.config;

import com.zeco.zecomedical.model.Users;
import com.zeco.zecomedical.model.Roles;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class UsersDetails implements UserDetails {


    private final String username;
    private final String password;
    private final Roles role;



    public UsersDetails(Users user){

        username = user.getUsername();
        password = user.getPassword();
         role = user.getRole();

    }


   /* public UserInfoUserDetails(UserInfo userInfo) {
        name=userInfo.getName();
        password=userInfo.getPassword();
        authorities= Arrays.stream(userInfo.getRoles().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }*/




    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return List.of(new SimpleGrantedAuthority(role.toString()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
