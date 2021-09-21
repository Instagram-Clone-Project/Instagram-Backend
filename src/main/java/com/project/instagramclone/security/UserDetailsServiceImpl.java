package com.project.instagramclone.security;

import com.project.instagramclone.domain.member.Member;
import com.project.instagramclone.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String userPk) throws UsernameNotFoundException {

        Member member = memberRepository.findById(Long.parseLong(userPk))
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다. " + userPk));

        return new UserDetailsImpl(member);
    }
}
