package be.keivy.fleamarketapp.common.dtos.auth.requests;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SecondHandDealerRegisterRequest extends RegisterRequest {

    @NotNull(message = "There must be a birth date")
    private LocalDate birthDate;
}
