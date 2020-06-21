<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
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
        <c:if test="${not empty param.logout}">
            <p>Zostałeś wylogowany!</p>
        </c:if>
        <c:if test="${not empty param.error}">
            <p>Błędne dane logowania!</p>
        </c:if>
        <div class="columns is-centered">
            <form method="post" action="/login">
                <div class="field">
                    <label class="label">Nazwa użytkownika
                    </label>
                    <div class="control has-icons-left">
                        <input type="text" name="username"/>
                    </div>
                </div>
                <div class="field">
                    <label class="label">Hasło
                    </label>
                    <div class="control has-icons-left">
                        <input type="password" name="password"/>
                    </div>
                </div>

                <div class="control">
                    <button class="button" type="submit">Zaloguj</button>
                </div>
        <security:csrfInput/>
        </form>
        </div>
    </div>
</section>
</body>
</html