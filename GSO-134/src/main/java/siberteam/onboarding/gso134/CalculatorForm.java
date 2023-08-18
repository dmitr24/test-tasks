package siberteam.onboarding.gso134;

import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

@Getter
@Setter
public class CalculatorForm extends ActionForm {
    private BigDecimal firstNumber;
    private String operation;
    private BigDecimal secondNumber;
    private BigDecimal result;
    private final Logger logger = LogManager.getRootLogger();

    @Override
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        logger.info("validation started");
        ActionErrors errors = new ActionErrors();
        if (firstNumber == null) {
            errors.add("firstNumber", new ActionMessage("error.null.number.first"));
            logger.warn("First number validation error occurred. Value is null");
        }
        if (!operation.equals("MULTIPLY") && !operation.equals("DIVIDE") &&
                !operation.equals("SUM") && !operation.equals("SUBTRACTION")) {
            errors.add("operation", new ActionMessage("error.validation.operation"));
            logger.warn("Operation validation error occurred. Value doesn't fit any.");
        }
        if (secondNumber == null) {
            errors.add("secondNumber", new ActionMessage("error.null.number.second"));
            logger.warn("Second number validation error occurred. Value is null");
        } else if (secondNumber.compareTo(BigDecimal.ZERO) == 0 && operation.equals("DIVIDE")) {
            errors.add("secondNumber", new ActionMessage("error.validation.number.zero"));
            logger.warn("Second number validation error occurred. Divider is zero");
        }
        if (errors.isEmpty()) {
            logger.info("validation passed");
        } else {
            logger.warn("validation failed");
        }
        return errors;
    }

    @Override
    public void reset(ActionMapping mp, HttpServletRequest req){
        this.firstNumber = null;
        this.secondNumber = null;
        this.result = null;
    }
}
