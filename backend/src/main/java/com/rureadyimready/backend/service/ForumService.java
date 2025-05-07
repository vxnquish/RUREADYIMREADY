package com.rureadyimready.backend.service;

import com.rureadyimready.backend.controller.dto.ForumResultDTO;
import com.rureadyimready.backend.domain.ForumContent;

public interface ForumService {
    ForumContent save(ForumContent forumContent);
    ForumContent save(String title, String content);
    ForumContent findById(long id);
    ForumResultDTO findAll(int page, int size);
    ForumResultDTO findByTitle(String title, int page, int size);
    ForumResultDTO findByContent(String content, int page, int size);
    ForumResultDTO findByTitleAndContent
            (String content, int page, int size);
}