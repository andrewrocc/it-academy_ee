<jsp:include page="_header.jsp"/>

<form method="post" action="/hello/add-employee.html">
  <!-- first name -->
  <div class="mb-3">
    <label for="firstName" class="form-label">First Name</label>
    <input type="text" name="firstName" class="form-control" id="firstName" aria-describedby="nameHelp">
    <div id="nameHelp" class="form-text">Enter first name</div>
  </div>

  <!-- last name -->
  <div class="mb-3">
      <label for="lastName" class="form-label">Last Name</label>
      <input type="text" name="lastName" class="form-control" id="lastName" aria-describedby="nameHelp">
      <div id="nameHelp" class="form-text">Enter last name</div>
  </div>

  <!-- birthday -->
    <div class="mb-3">
        <label for="birthday" class="form-label">Birthday Name</label>
        <input type="text" name="birthday" class="form-control" id="birthday" aria-describedby="nameHelp">
        <div id="nameHelp" class="form-text">Enter birthday name</div>
    </div>

  <!-- employeeDetail.city -->
    <div class="mb-3">
        <label for="employeeDetail.city" class="form-label">City name</label>
        <input type="text" name="employeeDetail.city" class="form-control" id="employeeDetail.city" aria-describedby="nameHelp">
        <div id="nameHelp" class="form-text">Enter city name</div>
    </div>

  <!-- employeeDetail.street -->
    <div class="mb-3">
        <label for="employeeDetail.street" class="form-label">Street name</label>
        <input type="text" name="employeeDetail.street" class="form-control" id="employeeDetail.street" aria-describedby="nameHelp">
        <div id="nameHelp" class="form-text">Enter street name</div>
    </div>

  <button type="submit" class="btn btn-primary">Submit</button>
</form>
<jsp:include page="_footer.jsp"/>