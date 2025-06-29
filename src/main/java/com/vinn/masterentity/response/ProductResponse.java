package com.vinn.masterentity.response;

import com.vinn.masterentity.constant.Status;
import java.time.LocalDateTime;

public record ProductResponse(
    Long id,
    String name,
    double price,
    Status status,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {}
