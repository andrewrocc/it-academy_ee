<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<title>Search result</title>
<h1>Search result</h1>

<table style="width:100%">
  <tr>
    <th>ID</th>
    <th>Name</th>
    <th>Price</th>
  </tr>
  <c:forEach items="${searchResult}" var="product">
  <tr>
    <td><c:out value="${product.id}"/></td>
    <td><c:out value="${product.name}"/></td>
    <td><c:out value="${product.price}"/></td>
  </tr>
  </c:forEach>
</table>

</form>
</html>