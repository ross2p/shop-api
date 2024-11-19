package ua.edu.lnu.shop_api.dto.auth;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ua.edu.lnu.shop_api.entity.Role;

import java.util.Collection;
import java.util.HashSet;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DefaultUserDetails implements UserDetails {
    private UUID id;
    private String email;
    private Role role;
    private String password;

    @Builder.Default
    private Collection<? extends GrantedAuthority> authorities = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
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
