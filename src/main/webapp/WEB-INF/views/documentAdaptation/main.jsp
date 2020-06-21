<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<jsp:include page="../homepageParts/head.jsp">
    <jsp:param name="title" value="Twoja firma online"/>
</jsp:include>
<body>
<section class="section">
    <div class="container">
        <h1 class="title">
            Twoje dokumenty
        </h1>
        <table class="table" >
            <th>Nazwa dokumentu</th>
            <th>Typ dokumentu</th>
            <th>Data utworzenia</th>

            <c:forEach items="${yourFiles}" var="yourFile">
                <tr>
                    <td>${yourFile.name}</td>
                    <td>${yourFile.contentType}</td>
                    <td>${yourFile.createdOn}</td>
                    <td><a href="/unspecified-document/addDocument/${yourFile.id}">Opisz dokument</a></td>
                    <td><a href="/unspecified-document/confirm/delete/${yourFile.id}">Usuń</a></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</section>
</body>
</html>
