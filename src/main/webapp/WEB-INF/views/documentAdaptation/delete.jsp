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
            Czy napewno chcesz usunąć plik</h1>

        </h1>
        <table class="table">
            <tr>
                <td>${fileToDelete.name}</td>
                <td>${fileToDelete.contentType}</td>
                <td>${fileToDelete.createdOn}</td>
                <td><a href="/unspecified-document/delete/${fileToDelete.id}">Potwierdź</a></td>
                <td><a href="/unspecified-document">Anuluj</a></td>
            </tr>
        </table>
    </div>
</section>
</body>
</html>
