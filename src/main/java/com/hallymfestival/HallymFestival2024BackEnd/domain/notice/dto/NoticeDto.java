package com.hallymfestival.HallymFestival2024BackEnd.domain.notice.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoticeDto {
    private Integer id;
    private String title;
    private String content;

}