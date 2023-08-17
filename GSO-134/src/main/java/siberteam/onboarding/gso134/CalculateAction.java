package siberteam.onboarding.gso134;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class CalculateAction extends Action {
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response) {
        CalculatorForm calculatorForm = (CalculatorForm) form;
        BigDecimal result = calculate(calculatorForm.getFirstNumber(),
                calculatorForm.getSecondNumber(), calculatorForm.getOperation());
        result = result.setScale(5, RoundingMode.HALF_UP);
        calculatorForm.setResult(result);
        return mapping.findForward("success");
    }

    private BigDecimal calculate(BigDecimal firstNumber, BigDecimal secondNumber, String operation) {
        switch (operation) {
            case "MULTIPLY":
                return firstNumber.multiply(secondNumber);
            case "DIVIDE":
                return firstNumber.divide(secondNumber, 5, RoundingMode.HALF_UP);
            case "SUM":
                return firstNumber.add(secondNumber);
            case "SUBTRACTION":
                return firstNumber.subtract(secondNumber);
            default:
                return BigDecimal.ZERO;
        }
    }
}
