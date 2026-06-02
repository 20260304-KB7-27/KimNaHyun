package org.scoula.database.trevelapp.src.main.java.org.scoula.travel.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TravelImageVO {
    private Long no;
    private String filename;
    private Long travelNo;
}