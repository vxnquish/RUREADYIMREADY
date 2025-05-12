package com.rureadyimready.backend.service;

import com.rureadyimready.backend.controller.dto.ForumContentDTO;
import com.rureadyimready.backend.controller.dto.ForumResultDTO;
import com.rureadyimready.backend.domain.ForumContent;
import com.rureadyimready.backend.domain.ForumTags;
import com.rureadyimready.backend.domain.Tags;
import com.rureadyimready.backend.repository.ForumRepository;
import com.rureadyimready.backend.repository.TagsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ForumServiceImpl implements ForumService {
    private final ForumRepository forumRepository;
    private final TagsRepository tagsRepository;

    @Transactional(readOnly = false)
    public ForumContent save(ForumContentDTO data) {
        List<Tags> tags = tagsRepository.findByValueIn(data.getTags());
        if (tags.size() != data.getTags().size())
            throw new NoSuchElementException("존재하지 않는 태그가 있습니다.");
        ForumContent entity = data.toEntity();
        entity.setForumTags(tags.stream()
                .map(tag -> ForumTags.builder().forumContent(entity).tags(tag).build())
                .toList());
        return forumRepository.save(entity);
    }

    public ForumContent findById(long id) {
        Optional<ForumContent> result = forumRepository.findByIdWithTags(id);
        if (result.isPresent()) return result.get();
        else throw new NoSuchElementException(id + "번 게시글이 없습니다.");
    }

    public ForumResultDTO findAll(int page, int size) {
        return new ForumResultDTO(forumRepository.findAllWithoutContent(PageRequest.of(page - 1, size,
                Sort.by("createdAt").descending())));
    }

    public ForumResultDTO findByTitle(String title, int page, int size) {
        return new ForumResultDTO(forumRepository.findByTitleContaining(title,
                PageRequest.of(page - 1, size, Sort.by("createdAt").descending())));
    }

    public ForumResultDTO findByContent(String content, int page, int size) {
        return new ForumResultDTO(forumRepository.findByContentContaining(content,
                PageRequest.of(page - 1, size, Sort.by("createdAt").descending())));
    }

    /**
     * 하나의 문자열에 대해 제목과 분문 모두 검사
     * @param content 검색 대상 문자열
     * @param page 페이지 수
     * @param size 페이지 크기
     * @return 검색 결과
     */
    public ForumResultDTO findByTitleAndContent
            (String content, int page, int size) {
        return new ForumResultDTO(forumRepository.findByTitleContainingAndContentContaining(content,
                PageRequest.of(page - 1, size, Sort.by("createdAt").descending())));
    }

    public ForumResultDTO findByTag(String tag, int page, int size) {
        return new ForumResultDTO(forumRepository.findByTagName(tag,
                PageRequest.of(page - 1, size, Sort.by("f.createdAt").descending())));
    }
}