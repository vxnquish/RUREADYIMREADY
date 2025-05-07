package com.rureadyimready.backend.controller;

import com.rureadyimready.backend.controller.dto.ForumContentSaveDTO;
import com.rureadyimready.backend.controller.dto.ForumResultDTO;
import com.rureadyimready.backend.controller.dto.ForumSearchDTO;
import com.rureadyimready.backend.controller.dto.ForumSearchMode;
import com.rureadyimready.backend.domain.ForumContent;
import com.rureadyimready.backend.service.ForumService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/forum")
@CrossOrigin
@RequiredArgsConstructor
public class ForumController {
    private final ForumService forumService;
    public static final int DEFAULT_PAGE_SIZE = 15;

    /**
     * 게시글 ID를 받아 게시글 정보 제공
     * @param id 게시글의 ID
     * @return 게시글 전체(DTO로 바꿀 게 없어 원형 그대로 제공)
     */
    @GetMapping("/{id}")
    public ForumContent findById(@PathVariable long id) {
        return forumService.findById(id);
    }

    /**
     * 게시글 정보를 받아 등록
     * @param forum title, content
     * @return 등록된 게시글 정보, title 길이가 최대 길이를 넘으면 400 Bad Request 반환
     */
    @PostMapping("/save")
    public ForumContent save(@RequestBody @Valid ForumContentSaveDTO forum) {
        return forumService.save(forum.toEntity());
    }

    /**
     * @param page 페이지 번호(1부터)
     * @param size 페이지당 데이터의 양
     * @return 각 게시글의 id, 제목, 업로드 시간 List, 전체 페이지 수
     */
    @GetMapping("/list/{page}")
    public ForumResultDTO getList(@PathVariable int page, Integer size) {
        //size는 query parameter로 보내야 하며, 안 보내면 기본값 적용
        if (size == null) size = DEFAULT_PAGE_SIZE;
        return forumService.findAll(page - 1, size);
    }

    @PostMapping("/search")
    public ForumResultDTO search(@RequestBody ForumSearchDTO search) {
        if (search.getSize() == null) search.setSize(DEFAULT_PAGE_SIZE);
        if (search.getMode() == null) search.setMode(ForumSearchMode.TITLE_AND_CONTENT);
        if (search.getContent() == null) return getList(search.getPage(), search.getSize());
        else return switch (search.getMode()) {
            case TITLE ->
                    forumService.findByTitle(search.getContent(), search.getPage() - 1, search.getSize());
            case CONTENT ->
                    forumService.findByContent(search.getContent(), search.getPage() - 1, search.getSize());
            case TITLE_AND_CONTENT ->
                    forumService.findByTitleAndContent(search.getContent(), search.getPage() - 1, search.getSize());
        };
    }
}