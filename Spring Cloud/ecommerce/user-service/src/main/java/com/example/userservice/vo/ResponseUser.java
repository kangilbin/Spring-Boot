package com.example.userservice.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL) // Json으로 들어오는 데이터가 null인 데이터는 제외
public class ResponseUser {
    private String email;
    private String name;
    private String userId;

    private List<ResponseOrder> oders;
}
