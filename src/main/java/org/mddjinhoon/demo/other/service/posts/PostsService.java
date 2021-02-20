package org.mddjinhoon.demo.other.service.posts;

import lombok.RequiredArgsConstructor;
import org.mddjinhoon.demo.other.domain.posts.Posts;
import org.mddjinhoon.demo.other.domain.posts.PostsRepository;
import org.mddjinhoon.demo.other.web.dto.PostsListResponseDto;
import org.mddjinhoon.demo.other.web.dto.PostsResponseDto;
import org.mddjinhoon.demo.other.web.dto.PostsSaveRequestDto;
import org.mddjinhoon.demo.other.web.dto.PostsUpdateRequestDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        posts.update(requestDto.getTitle(), requestDto.getContent());
        // 더티 체킹이라는 개념
        // 트랜잭션 안에서 디비 데이터를 가져오면, 이 데이터는 영속성 컨텍스트가 유지된 상태,
        // 이 상태에서 Entity 객체의 값만 변경하면 별도로 update 쿼리를 날릴 필요가 없다.
        return id;
    }

    // 여기에 @Transactional이 붙지 않은 이유를 설명해봐라. -> 데이터의 변경이 없기 때문에?
    public PostsResponseDto findById(Long id) {
       Posts entity = postsRepository.findById(id).orElseThrow(() ->
               new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

       return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true) // 트랜잭션 범위는 유지하되, 조회 기능만 남겨둠 -> 그니까, 해당 메소드에서 등록,수정,삭제 등이 이루어지지 않는 곳에 명시하면 속도가 빨라짐
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream().map(PostsListResponseDto::new).collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        postsRepository.delete(posts);
    }
}