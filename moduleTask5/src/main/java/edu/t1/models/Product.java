package edu.t1.models;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.lang.Long;

@Component
@Getter
@Setter
@AllArgsConstructor
@ToString
@NoArgsConstructor
//@Validated
public class Product {
    //@NotEmpty(message = "id не может быть пустым")
    Long id;
    //@NotEmpty(message = "userId не может быть пустым")
    Long userId;
    //@NotEmpty(message = "accNum не может быть пустым")
    String accNum;
    //@NotEmpty(message = "balance не может быть пустым")
    Long balance;
    //@NotEmpty(message = "type не может быть пустым")
    TypePruduct type;
}
