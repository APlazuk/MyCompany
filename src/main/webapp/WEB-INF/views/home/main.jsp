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

<security:authorize access="isAuthenticated()">
<jsp:include page="../homepageParts/nav.jsp"/>


<section class="hero is-primary is-fullheight-with-navbar">
    <div class="hero-body is-fullwidth">

        <div class="container">
            <div class="columns is-multiline">
                <div class="column is-full">
                    <form:form method="post" modelAttribute="file" action="/addFile" enctype="multipart/form-data"><br/>

                        <div class="file is-boxed is-small">
                            <label class="file-label">
                            <input class="file-input" type="file" name="documentFile" accept="application/*">
                            <span class="file-cta">
                                <span class="file-icon">
                                     <i class="fas fa-upload"></i>
                                </span>
                            <span class="file-label">
                                 Wybierz plik...
                            </span>
                            </span>
                            </label>
                        </div>
                        <div>
                            <button class="button button is-dark is-small is-bottom-left" type="submit">Dodaj plik
                            </button>
                        </div>
                    </form:form>
                    <form:errors path="*"/>
                </div>
                <div class="column is-half">

                    <a class="box" href="/ap">
                        <p align="center">Faktury zakupowe ${countCreditInvoices} </p>
                    </a>
                </div>
                <div class="column is-half">

                    <a class="box" href="/ar">
                        <p align="center">Faktury sprzedażowe ${countVendorInvoices} </p>
                    </a>
                </div>
                <div class="column is-half">

                    <a class="box" href="/unspecified-document">
                        <p align="center">Dokumenty nieprzetworzone ${countUnspecifiedDocuments}</p>
                    </a>
                </div>
            </div>
        </div>
    </div>


    </security:authorize>

    <security:authorize access="!isAuthenticated()">
        <%-- tutaj treść strony dla niezalogowanych użytkowników --%>

    <h1 class="title">
        Hello World
    </h1>
    <p class="subtitle">
        My first website with <strong>Bulma</strong>!
    </p>
    </div>
</section>
</security:authorize>

</body>
</html>