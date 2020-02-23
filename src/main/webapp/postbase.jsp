<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
  <div class="container-fluid">
	   <div class="container">
		   <h4 class="my-4">Browse Categories</h4>
		   <hr class="my-4">
		   <div class="row">
          
          <div class="col-md-4">
            <h5>Locations<span class="badge badge-primary float-right">16</span></h5>
            <ul class="list-unstyled text-small">
              <li><a class="text-muted" href="#">Secret Shopping</a></li>
              <li><a class="text-muted" href="#">Secret Restaurants</a></li>
              <li><a class="text-muted" href="#">Landmarks</a></li>
            </ul>
          </div>
          <div class="col-md-4">
            <h5>People<span class="badge badge-primary float-right">275</span></h5>
            <ul class="list-unstyled text-small">
              <li><a class="text-muted" href="#">Spouses</a></li>
              <li><a class="text-muted" href="#">Celebrities</a></li>
              
            </ul>
          </div>
          <div class="col-md-4">
            <h5>Events<span class="badge badge-primary float-right">14565</span></h5>
            <ul class="list-unstyled text-small">
              <li><a class="text-muted" href="#">Concerts</a></li>
              <li><a class="text-muted" href="#">Live Sports</a></li>
              <li><a class="text-muted" href="#">Conferences</a></li>
            </ul>
          </div>
		<div class="col-md-4">
            <h5>Automotive<span class="badge badge-primary float-right">565</span></h5>
            <ul class="list-unstyled text-small">
              <li><a class="text-muted" href="#">Pre-sale inspections</a></li>
              <li><a class="text-muted" href="#">New Vehicles</a></li>
              <li><a class="text-muted" href="#">Auto parts</a></li>
            </ul>
          </div>
		<div class="col-md-4">
            <h5>Software<span class="badge badge-primary float-right">145</span></h5>
            <ul class="list-unstyled text-small">
              <li><a class="text-muted" href="#">Code Reviews</a></li>
              <li><a class="text-muted" href="#">Website Investigations</a></li>
              <li><a class="text-muted" href="#">Hacking</a></li>
            </ul>
          </div>
		<div class="col-md-4">
            <h5>Testing<span class="badge badge-primary float-right">65</span></h5>
            <ul class="list-unstyled text-small">
              <li><a class="text-muted" href="#">Water</a></li>
              <li><a class="text-muted" href="#">Soil</a></li>
              <li><a class="text-muted" href="#">Air Quality</a></li>
            </ul>
          </div>
		<div class="col-md-4">
            <h5>Financial<span class="badge badge-primary float-right">14</span></h5>
            <ul class="list-unstyled text-small">
              <li><a class="text-muted" href="#">Account investigation</a></li>
              <li><a class="text-muted" href="#">Public filings</a></li>
              <li><a class="text-muted" href="#">Credit checks</a></li>
            </ul>
          </div>
				<div class="col-md-4">
            <h5>Home<span class="badge badge-primary float-right">5</span></h5>
            <ul class="list-unstyled text-small">
              <li><a class="text-muted" href="#">Home investigtions</a></li>
              <li><a class="text-muted" href="#">Title investigtions</a></li>
              <li><a class="text-muted" href="#">Drone flyovers</a></li>
            </ul>
          </div>
        </div>
      <footer class="pt-4 my-md-5 pt-md-5 border-top">
        <div class="row">
          <div class="col-12 col-md">
            <img class="mb-2" src="https://getbootstrap.com/assets/brand/bootstrap-solid.svg" alt="" width="24" height="24">
            <small class="d-block mb-3 text-muted">&copy; 2017-2018</small>
          </div>
          <div class="col-6 col-md">
            <h5>Features</h5>
            <ul class="list-unstyled text-small">
              <li><a class="text-muted" href="#">Cool stuff</a></li>
              <li><a class="text-muted" href="#">Random feature</a></li>
              <li><a class="text-muted" href="#">Team feature</a></li>
              <li><a class="text-muted" href="#">Stuff for developers</a></li>
              <li><a class="text-muted" href="#">Another one</a></li>
              <li><a class="text-muted" href="#">Last time</a></li>
            </ul>
          </div>
          <div class="col-6 col-md">
            <h5>Resources</h5>
            <ul class="list-unstyled text-small">
              <li><a class="text-muted" href="#">Resource</a></li>
              <li><a class="text-muted" href="#">Resource name</a></li>
              <li><a class="text-muted" href="#">Another resource</a></li>
              <li><a class="text-muted" href="#">Final resource</a></li>
            </ul>
          </div>
          <div class="col-6 col-md">
            <h5>About</h5>
            <ul class="list-unstyled text-small">
              <li><a class="text-muted" href="#">Team</a></li>
              <li><a class="text-muted" href="#">Locations</a></li>
              <li><a class="text-muted" href="#">Privacy</a></li>
              <li><a class="text-muted" href="#">Terms</a></li>
            </ul>
          </div>
        </div>
      </footer>
	   </div>
    </div>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCWJzZiPS_HlplCRQy8seNyVb_3g6B03gs&libraries=places"></script>
<script src="https://www.google.com/recaptcha/api.js" async defer></script>
<script type="text/javascript" src="/resources/js/autocomplete.js"></script>
</body>
</html>


