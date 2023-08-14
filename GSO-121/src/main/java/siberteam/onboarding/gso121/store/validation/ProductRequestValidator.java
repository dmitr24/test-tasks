package siberteam.onboarding.gso121.store.validation;

import siberteam.onboarding.gso121.store.data.dto.CreateUpdateProductDto;
import siberteam.onboarding.gso121.store.exception.ValidationException;
import javax.servlet.http.HttpServletRequest;

public class ProductRequestValidator {
    public void validateCodeParam(HttpServletRequest request) {
        String codeParam = request.getParameter("code");
        if (codeParam == null) {
            throw new ValidationException("code is required");
        }
        try {
            Integer.valueOf(codeParam);
        } catch (NumberFormatException exception) {
            throw new ValidationException("Code is not int value");
        }
    }

    public void validateDto(CreateUpdateProductDto createUpdateProductDto) {
        validateName(createUpdateProductDto);
        validatePrice(createUpdateProductDto);
    }

    private void validateName(CreateUpdateProductDto createUpdateProductDto) {
        String name = createUpdateProductDto.getName();
        if (name == null || name.trim().length() == 0) {
            throw new ValidationException("name is required and must be not empty");
        }
    }

    private void validatePrice(CreateUpdateProductDto createUpdateProductDto) {
        Integer price = createUpdateProductDto.getPrice();
        if (price == null || price < 1) {
            throw new ValidationException("Price is required and must be bigger then 0");
        }
    }
}
