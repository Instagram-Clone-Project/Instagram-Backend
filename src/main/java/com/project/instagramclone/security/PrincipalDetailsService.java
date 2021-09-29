package com.project.instagramclone.security;

import com.project.instagramclone.domain.user.User;
import com.project.instagramclone.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PrincipalDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //System.out.println("loadUserByUsername 실행중");
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("\"" +username + "\" 사용자를 찾을 수 없습니다."));

        return new PrincipalDetails(user);
    }
}
