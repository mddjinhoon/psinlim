package org.mddjinhoon.demo.other.service.posts;

import lombok.RequiredArgsConstructor;
import org.mddjinhoon.demo.other.domain.posts.PostsRepository;
import org.mddjinhoon.demo.other.web.dto.PostsSaveRequestDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }
}
