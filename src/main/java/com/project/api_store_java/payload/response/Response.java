package com.project.api_store_java.payload.response;

import lombok.Builder;

@Builder
public record Response(String message, Object data) {
}
