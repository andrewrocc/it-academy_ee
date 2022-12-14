<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h1>People</h1>

<table style="width:100%" class="table">
  <tr>
    <th>Name</th>
    <th>Surname</th>
    <th>Age</th>
  </tr>
  <c:forEach items="${persons}" var="person">
  <tr>
    <td><c:out value="${person.name}"/></td>
    <td><c:out value="${person.surname}"/></td>
    <td><c:out value="${person.age}"/></td>
  </tr>
  </c:forEach>
</table>