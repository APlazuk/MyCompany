<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<jsp:include page="../../homepageParts/head.jsp">
    <jsp:param name="title" value="Twoja firma online"/>
</jsp:include>
<body>
<section class="section">
    <div class="container">
        <h1 class="title">
            Twoje faktury zakupowe
        </h1>
        <table class="table" >
            <th>Nazwa Partnera Biznesowego</th>
            <th>Numer Klienta</th>
            <th>Data</th>
            <th>Numer Dokumentu</th>
            <th>Kwota</th>
            <th>IBAN</th>
            <th>Status</th>

        <c:forEach items="${creditInvoices}" var="creditInvoice">
            <tr>
                <td>${creditInvoice.companyName}</td>
                <td>${creditInvoice.clientNumber}</td>
                <td>${creditInvoice.date}</td>
                <td>${creditInvoice.documentNumber}</td>
                <td>${creditInvoice.amount}</td>
                <td>${creditInvoice.IBAN}</td>
                <td>${creditInvoice.status}</td>
                <td><a href="/ap/edit/${creditInvoice.id}">Edytuj</a></td>
                <td><a href="/ap/confirm/delete/${creditInvoice.id}">Usuń</a></td>
            </tr>
        </c:forEach>
        </table>
        <br/>
        <a href="/ap/addDocument">Dodaj fakturę</a>
        <a href="/">Wróc</a>
    </div>
</section>
</body>
</html>
