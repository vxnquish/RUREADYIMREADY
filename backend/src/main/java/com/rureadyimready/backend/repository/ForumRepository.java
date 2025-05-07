package com.rureadyimready.backend.repository;

import com.rureadyimready.backend.domain.ForumContent;
import com.rureadyimready.backend.controller.dto.ForumTitleDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ForumRepository extends JpaRepository<ForumContent, Long> {
    @Query("select new com.rureadyimready.backend.controller.dto.ForumTitleDTO(f.id, f.title, f.createdAt)" +
            "from ForumContent f")
    Page<ForumTitleDTO> findAllWithoutContent(Pageable pageable);
    @Query("select new com.rureadyimready.backend.controller.dto.ForumTitleDTO(f.id, f.title, f.createdAt)" +
            "from ForumContent f where f.title like concat('%', :title, '%')")
    Page<ForumTitleDTO> findByTitleContaining(@Param(value = "title") String title, Pageable pageable);
    @Query("select new com.rureadyimready.backend.controller.dto.ForumTitleDTO(f.id, f.title, f.createdAt)" +
            "from ForumContent f where f.content like concat('%', :content, '%')")
    Page<ForumTitleDTO> findByContentContaining(@Param(value = "content") String content, Pageable pageable);
    @Query("select new com.rureadyimready.backend.controller.dto.ForumTitleDTO(f.id, f.title, f.createdAt)" +
            "from ForumContent f where f.title like concat('%', :content, '%') or f.content like concat('%', :content, '%')")
    Page<ForumTitleDTO> findByTitleContainingAndContentContaining
            (@Param(value = "content") String content, Pageable pageable);
}