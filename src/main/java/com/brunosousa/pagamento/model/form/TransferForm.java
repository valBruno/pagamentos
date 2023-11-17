package com.brunosousa.pagamento.model.form;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransferForm {

//    @NotNull @NotEmpty
//    private Long payerId;

    @NotNull @NotEmpty
    private Long payeeId;

    @NotNull
    @NotEmpty
    @Min(value = 0L, message = "The value must be positive")
    private Double value;

}
