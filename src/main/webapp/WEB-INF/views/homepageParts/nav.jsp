<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!-- Main container -->
<%--<nav class="level">--%>
<nav class="navbar is-light">
    <div class="navbar-item">
    <p class="subTitle" align="center" width="112" height="28">
        Witaj ${username}
    </p>
    </div>

    <!-- Left side -->
    <div class="navbar-menu">
        <div class="navbar-start">
            <hr class="navbar-divider">
            <div class="navbar-item">
                <div class="field has-addons">
                    <p class="control">
                        <input class="input" type="text" placeholder="Find a document">
                    </p>
                    <p class="control">
                        <button class="button">
                            Search
                        </button>
                    </p>
                </div>
            </div>
        </div>
    </div>




    <!-- Right side -->

    <div class="navbar-end">
        <div class="navbar-item has-dropdown is-active">

            <form:form method="get" action="/login">
                <p class="level-item">
                    <button class="button is-success" type="submit">Wyloguj</button>
                </p>
            </form:form>
        </div>
    </div>

</nav>