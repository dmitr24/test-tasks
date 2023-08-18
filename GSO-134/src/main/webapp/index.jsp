<%@ taglib prefix="bean" uri="http://struts.apache.org/tags-bean" %>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html" %>
<%@ taglib prefix="html-el" uri="http://struts.apache.org/tags-html" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Calculator</title>
</head>
<body>
    <header>
        <h1>
            Calculator
        </h1>
    </header>
    <main>
        <html:form action="/calculate">
            <html:text property="firstNumber" />
            <html:select property="operation">
                <html:option value="MULTIPLY">*</html:option>
                <html:option value="DIVIDE">/</html:option>
                <html:option value="SUM">+</html:option>
                <html:option value="SUBTRACTION">-</html:option>
            </html:select>
            <html:text property="secondNumber" />
            <html:text property="result" readonly="true"/>
            <br/>
            <html:submit>count</html:submit>
            <br/>
            <html:errors property="firstNumber" />
            <br/>
            <html:errors property="operation" />
            <br/>
            <html:errors property="secondNumber" />
        </html:form>
    </main>
</body>
</html>