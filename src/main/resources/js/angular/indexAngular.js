// Creating the module called parking
var headModule = angular.module("headAngularModule", [
		'ui.bootstrap', 'ngSanitize'
]);

headModule.controller("headAngularController", function($scope, $http) {
	
	
	var main = this;
	 
    main.links = [
        "<a href='http://google.com'>Google</a>",
        "<a href='http://odetocode.com'>OdeToCode</a>",
        "<a href='http://twitter.com'>Twitter</a>",
    ];

	$scope.getPageCarteiraView = function() {
		$http({
			method : 'GET',
			url : 'carteiraView'
		}).success(function(data, status, headers, config) {
//			toRenderPageAfterAjax(data);

		}).error(function(data, status, headers, config) {
			alert("error: " + data + " status: " + status);
		});

	};

	$scope.getPagePapelView = function() {
		$http({
			method : 'GET',
			url : 'papeisView'
		}).success(function(data, status, headers, config) {
			toRenderPageAfterAjax(data);

		}).error(function(data, status, headers, config) {
			alert("error: " + data + " status: " + status);

		});

	};

});