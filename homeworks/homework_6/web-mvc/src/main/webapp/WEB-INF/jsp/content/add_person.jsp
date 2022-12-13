<section>
   <div class="container-fluid py-5">
      <div class="row">

         <div class="col-sm-4">
         </div>

         <div class="col-sm-4">
            <form method="post" action="/web-mvc/add_person" modelAttribute="person">
               <div class="container">
                  <div class="text-center align-items-center">
                     <div class="align-items-center py-2">
                        <label for="name" class="form-label">Person name</label>
                        <div class="col-sm-5 mx-auto">
                           <input type="text" name="name" class="form-control" id="name" aria-describedby="name-Help"
                              size="20">
                        </div>
                     </div>

                     <div class="align-items-center py-2">
                        <label for="surname" class="form-label">Person surname</label>
                        <div class="col-sm-5 mx-auto">
                           <input type="text" name="surname" class="form-control" id="surname"
                              aria-describedby="surname-Help" size="20">
                        </div>
                     </div>

                     <div class="align-items-center py-2">
                        <label for="age" class="form-label">Person age</label>
                        <div class="col-sm-5 mx-auto">
                           <input type="text" name="age" class="form-control" id="age" aria-describedby="age-Help"
                              size="20">
                        </div>
                     </div>

                        <div class="align-items-center py-2">
                           <button type="submit" class="btn btn-primary" id="toastbtn">Submit</button>
                           <div id="toastNotice" class="toast" role="alert" aria-live="assertive" aria-atomic="true"
                              style="position: absolute; top: 70px; right: 15px;">
                              <div class="toast-header">
                                 <strong class="me-auto">Info</strong>
                                 <button type="button" class="btn-close" data-bs-dismiss="toast"
                                    aria-label="Close"></button>
                              </div>
                              <div class="toast-body">
                                 <c:out value="${message}"/>
                              </div>
                           </div>
                        </div>

                     </div>
                  </div>
               </form>

            </div>
            <div class="col-sm-4">
            </div>
         </div>
      </div>
      <script>
         var option = {
            animation: true,
            delay: 3000
         };

         document.getElementById("toastbtn").onclick = function () {
            var mAlert = document.getElementById("toastNotice");
            var bsAlter = new bootstrap.Toast(mAlert, option);
            bsAlter.show();
         };
      </script>
   </section>