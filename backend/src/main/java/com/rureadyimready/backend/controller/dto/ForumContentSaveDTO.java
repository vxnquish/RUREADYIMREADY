package com.rureadyimready.backend.controller.dto;

import com.rureadyimready.backend.domain.ForumContent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ForumContentSaveDTO {
    @NotBlank(message = "제목은 비어 있을 수 없습니다.")
    @Size(max = 96, message = "제목의 길이는 96자 이하이어야 합니다.")
    private String title;
    @NotBlank(message = "본문은 비어 있을 수 없습니다.")
    private String content;

    public ForumContent toEntity() {
        return ForumContent.builder()
                .title(title)
                .content(content).build();
    }
}