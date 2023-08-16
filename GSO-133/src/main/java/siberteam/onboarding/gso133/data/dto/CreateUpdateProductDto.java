package siberteam.onboarding.gso133.data.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUpdateProductDto {
    @NotBlank(message = "name can't be null or empty")
    private String name;

    @Min(value = 1, message = "price can't be less then 1")
    @NotNull(message = "price is required")
    private Integer price;
}
