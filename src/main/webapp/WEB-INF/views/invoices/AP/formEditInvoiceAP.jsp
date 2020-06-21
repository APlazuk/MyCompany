<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Faktury</title>
</head>
<body>
<table width="100%">
    <th width="50%"><img src="/static/images/faktura.png" ></th>
    <th width="50%">


<form:form method="post" modelAttribute="editedDocument"><br/>
    <form:input path="companyName" placeholder="Nazwa Partnera Biznesowego"/><br/>
    <form:errors path="companyName" /><br/>
    <form:input path="clientNumber" placeholder="Numer Klienta"/><br/>
    <form:errors path="clientNumber" /><br/>
    <form:input type="date" path="date" placeholder="yyyy-MM-dd"/>
    <form:errors path="date" /><br/>
    <form:input path="documentNumber" placeholder="Numer Dokumentu"/><br/>
    <form:errors path="documentNumber" /><br/>
    <form:input path="amount" placeholder="Kwota"/><br/>
    <form:errors path="amount" /><br/>
    <form:input path="IBAN" placeholder="IBAN"/><br/>
    <form:errors path="IBAN" /><br/>
    <form:select path="status">
        <form:option value="Zmodyfikowany" label="Zmodyfikowany" />
        <form:options items="${statuses}" itemLabel="description" itemValue="description" />
    </form:select>
    <form:errors path="status" /><br/>
    <input type="submit">
</form:form>
    </th>
</table>
</body>
</html>