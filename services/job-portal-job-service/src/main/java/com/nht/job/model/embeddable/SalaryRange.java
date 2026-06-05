package com.nht.job.model.embeddable;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SalaryRange {

    private BigDecimal minSalary;
    private BigDecimal maxSalary;

}
