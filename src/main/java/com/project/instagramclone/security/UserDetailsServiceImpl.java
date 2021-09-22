package com.project.instagramclone.security;

import com.project.instagramclone.domain.member.User;
import com.project.instagramclone.domain.member.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userPk) throws UsernameNotFoundException {

        User user = userRepository.findById(Long.parseLong(userPk))
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다. " + userPk));

        return new UserDetailsImpl(user);
    }
}
