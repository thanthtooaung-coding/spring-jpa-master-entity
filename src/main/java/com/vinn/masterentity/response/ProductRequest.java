package com.vinn.masterentity.response;

import com.vinn.masterentity.constant.Status;

public record ProductRequest(
    String name,
    double price,
    Status status
) {}
