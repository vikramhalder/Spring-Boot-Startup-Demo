<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<h2>Forms</h2>
<form action="/api/user" method="post">
    <label for="name">Name:</label><br>
    <input type="text" id="name" name="name" value=""><br>
    <label for="email">Email:</label><br>
    <input type="text" id="email" name="email" value=""><br><br>
    <input type="submit" value="Submit">
</form>
</body>
</html>
