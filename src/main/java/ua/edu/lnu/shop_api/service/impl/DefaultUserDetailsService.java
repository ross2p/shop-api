package ua.edu.lnu.shop_api.service.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.edu.lnu.shop_api.dto.auth.DefaultAuthority;
import ua.edu.lnu.shop_api.dto.auth.DefaultUserDetails;
import ua.edu.lnu.shop_api.entity.User;
import ua.edu.lnu.shop_api.repository.UserRepository;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class DefaultUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmailIgnoreCase(email)
                .orElseThrow(() -> new UsernameNotFoundException("User with email address '%s' not found".formatted(email)));
        return DefaultUserDetails.builder()
                .id(user.getId())
                .email(user.getEmail())
                .password(user.getPassword())
                .role(user.getRole())
                .authorities(Set.of(DefaultAuthority.builder()
                        .authority(user.getRole().getName())
                        .build()))
                .build();
    }

}
