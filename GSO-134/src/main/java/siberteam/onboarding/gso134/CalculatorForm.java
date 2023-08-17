package siberteam.onboarding.gso134;

import lombok.Getter;
import lombok.Setter;
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

    @Override
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();
        if (firstNumber == null) {
            errors.add("firstNumber", new ActionMessage("error.null.number.first"));
        }
        if (secondNumber == null) {
            errors.add("secondNumber", new ActionMessage("error.null.number.second"));
            return errors;
        }
        if (secondNumber.compareTo(BigDecimal.ZERO) == 0 && operation.equals("DIVIDE")) {
            errors.add("secondNumber", new ActionMessage("error.validation.number.zero"));
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
