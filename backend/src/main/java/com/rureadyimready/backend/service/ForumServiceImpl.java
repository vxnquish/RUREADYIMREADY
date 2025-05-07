package com.rureadyimready.backend.service;

import com.rureadyimready.backend.controller.dto.ForumResultDTO;
import com.rureadyimready.backend.domain.ForumContent;
import com.rureadyimready.backend.repository.ForumRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ForumServiceImpl implements ForumService {
    private final ForumRepository forumRepository;

    public ForumContent save(ForumContent forumContent) {
        return forumRepository.save(forumContent);
    }

    public ForumContent save(String title, String content) {
        return save(ForumContent.builder().title(title).content(content).build());
    }

    public ForumContent findById(long id) {
        return forumRepository.findById(id).get();
    }

    public ForumResultDTO findAll(int page, int size) {
        return new ForumResultDTO(forumRepository.findAllWithoutContent(PageRequest.of(page, size,
                Sort.by("createdAt").descending())));
    }

    public ForumResultDTO findByTitle(String title, int page, int size) {
        return new ForumResultDTO(forumRepository.findByTitleContaining(title,
                PageRequest.of(page, size, Sort.by("createdAt").descending())));
    }

    public ForumResultDTO findByContent(String content, int page, int size) {
        return new ForumResultDTO(forumRepository.findByContentContaining(content,
                PageRequest.of(page, size, Sort.by("createdAt").descending())));
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
                PageRequest.of(page, size, Sort.by("createdAt").descending())));
    }
}