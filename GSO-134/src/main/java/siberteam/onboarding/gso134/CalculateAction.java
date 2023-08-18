package siberteam.onboarding.gso134;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.math.RoundingMode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CalculateAction extends Action {
    private final Logger logger = LogManager.getRootLogger();

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response) {
        logger.info("calculation start");
        CalculatorForm calculatorForm = (CalculatorForm) form;
        BigDecimal result = calculate(calculatorForm.getFirstNumber(),
                calculatorForm.getSecondNumber(), calculatorForm.getOperation());
        result = result.setScale(5, RoundingMode.HALF_UP);
        calculatorForm.setResult(result);
        logger.info("calculation with request {} {} {} ended with result - {}", calculatorForm.getFirstNumber(),
                calculatorForm.getOperation(), calculatorForm.getSecondNumber(), result);
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
