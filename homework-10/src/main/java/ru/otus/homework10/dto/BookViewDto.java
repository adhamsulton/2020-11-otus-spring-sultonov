package ru.otus.homework10.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookViewDto {
    private Long id;
    private String name;
    private List<String> authorNames;
    private String genreName;
}
