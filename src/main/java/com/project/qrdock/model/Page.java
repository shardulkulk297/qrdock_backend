package com.project.qrdock.model;

// src/main/java/com/example/qrinfo/domain/PageVisit.java

import jakarta.persistence.*;


import java.time.Instant;

@Entity
@Table(
    name = "page_visits",
    indexes = {
        @Index(name = "idx_page_visits_place_created_at", columnList = "place_id, created_at")
    }
)

public class Page {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Which place was viewed
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "place_id", nullable = false)
    private Place place;

    // (Optional) via which QR (if tracked)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "qr_code_id")
    private QrCode qrCode;

    @Column(name = "user_agent", length = 255)
    private String userAgent;

    @Column(name = "referrer", length = 255)
    private String referrer;

    @Column(name = "device_type", length = 50)
    private String deviceType; // "mobile", "desktop", etc.

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt = Instant.now(); // simple timestamp (or use auditing)
}