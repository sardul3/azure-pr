package com.sagar.azurepr.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Url {
    @Id
    private String id;
    private String originalUrl;
    private String shortUrl;
    private String key;
    @CreationTimestamp
    private Date createdAt;
    private LocalDateTime expiresAt;
}
