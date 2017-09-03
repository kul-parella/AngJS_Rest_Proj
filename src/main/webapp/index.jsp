<!doctype html>
<html ng-app="myApp">
<head>
<title>My AngularJS App</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.6/angular.min.js"></script>
 <script src="app.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">

</head>
<body ng-controller="myAppController">
<p class="h4">Simple button to hit the default Rest service provided by the Jersey app</p>

<div>
  <span class="input-group-btn">
    <button class="btn btn-default" type="button" ng-click="getData()">Go!</button>
  </span>
</div>
<br/>
<p>{{resp}}</p>

</body>
</html>