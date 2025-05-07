package com.rureadyimready.backend.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
public class ForumTags {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    private ForumContent forumContent;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    private Tags tags;
}