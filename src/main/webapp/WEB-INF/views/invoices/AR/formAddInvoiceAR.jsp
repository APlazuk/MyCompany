<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="../../homepageParts/head.jsp">
    <jsp:param name="title" value="Twoja firma online"/>
</jsp:include>
<body>
<table width="100%">

<form:form method="post" modelAttribute="document" action="/ar/addDocument"><br/>
    <form:hidden path="documentTypeName" value="Faktura sprzedażowa" />
    <form:errors path="documentTypeName" /><br/>
    <form:input path="companyName" placeholder="Nazwa Partnera Biznesowego"/><br/>
    <form:errors path="companyName" /><br/>
    <form:input path="clientNumber" placeholder="Numer Klienta"/><br/>
    <form:errors path="clientNumber" /><br/>
    <form:input type="date" pattern="yyyy-MM-dd" path="date"/>
    <form:errors path="date" /><br/>
    <form:input path="documentNumber" placeholder="Numer Dokumentu"/><br/>
    <form:errors path="documentNumber" /><br/>
    <form:input path="amount" placeholder="Kwota"/><br/>
    <form:errors path="amount" /><br/>
    <form:input path="IBAN" placeholder="IBAN"/><br/>
    <form:errors path="IBAN" /><br/>
    <form:textarea path="notes" /><br/>
    <form:errors path="notes" /><br/>

    <form:select path="status" itemLabel="description" itemValue="description" items="${statuses}"/>
    <form:errors path="status" /><br/>
    <input type="submit">
</form:form>
    </th>
</table>
</body>
</html>