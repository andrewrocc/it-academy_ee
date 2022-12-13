<nav class="navbar navbar-expand-lg bg-light">
  <div class="container-fluid">
    <a class="navbar-brand" href="/web-mvc/welcome">web mvc</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="/web-mvc/welcome">Home</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">Example</a>
        </li>
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            Select action
          </a>
          <ul class="dropdown-menu">
            <li><a class="dropdown-item" href="/web-mvc/add_person">example</a></li>
          </ul>
        </li>
      </ul>
      <form class="d-flex" role="search" action="#" method="post">
        <input class="form-control me-2" type="search" name="pname" placeholder="Search" aria-label="Search">
        <button class="btn btn-outline-success" type="submit">Search</button>
      </form>
    </div>
  </div>
</nav>