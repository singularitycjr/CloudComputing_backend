package com.example.cloudcomputing2023.query;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.sql.Date;

@Data
public class StatisticsQuery extends PageQuery{
    private Integer productId;
    private String productName;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date startTime;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date  endTime;
}
