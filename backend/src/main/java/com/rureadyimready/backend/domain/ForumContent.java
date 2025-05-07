package com.rureadyimready.backend.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ForumContent {
    public static final int TITLE_LENGTH = 96;

    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false, length = TITLE_LENGTH)
    private String title;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;
    @CreatedDate
    @Column(nullable = false, updatable = false, scale = 6)
    private LocalDateTime createdAt;
}