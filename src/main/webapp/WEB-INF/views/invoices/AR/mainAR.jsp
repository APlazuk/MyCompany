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
            Twoje faktury sprzedażowe
        </h1>
        <table class="table" >
            <th>Nazwa Partnera Biznesowego</th>
            <th>Numer Klienta</th>
            <th>Data</th>
            <th>Numer Dokumentu</th>
            <th>Kwota</th>
            <th>IBAN</th>
            <th>Status</th>

        <c:forEach items="${vendorInvoices}" var="vendorInvoice">
            <tr>
                <td>${vendorInvoice.companyName}</td>
                <td>${vendorInvoice.clientNumber}</td>
                <td>${vendorInvoice.date}</td>
                <td>${vendorInvoice.documentNumber}</td>
                <td>${vendorInvoice.amount}</td>
                <td>${vendorInvoice.IBAN}</td>
                <td>${vendorInvoice.status}</td>
                <td><a href="/ar/edit/${vendorInvoice.id}">Edytuj</a></td>
                <td><a href="/ar/confirm/delete/${vendorInvoice.id}">Usuń</a></td>
            </tr>
        </c:forEach>
        </table>
        <br/>
        <a href="/ar/addDocument">Dodaj fakturę</a>
        <a href="/">Wróc</a>
    </div>
</section>
</body>
</html>
