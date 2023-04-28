package com.example.mephim.request;

import lombok.Data;

import java.util.List;

@Data
public class AddRoomDto {
    private String name;
    private Integer width;
    private Integer height;
    private List<Integer> rowVIP;
}