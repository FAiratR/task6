package edu.t1.models;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class Pay {
    Long product;
    Long count;
    Long user;
}
