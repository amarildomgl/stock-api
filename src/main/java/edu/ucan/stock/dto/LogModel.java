package edu.ucan.stock.dto;

import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LogModel {
    private String message;
    private LocalDateTime dateTime;
    private Object data;
}
