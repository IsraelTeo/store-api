package com.project.api_store_java.exception;

import lombok.Builder;

import java.util.List;

@Builder
public record ErrorResponse(String description, List<String> reasons) {
}
