var taskManagerModule = angular.module('olaMundoApp', [ 'ngAnimate' ]);

taskManagerModule.controller('olaMundoController', function($scope, $http) {

	var urlBase = "";
	$scope.toggle = true;
	$scope.valor = "";
	$scope.selection = [];
	$http.defaults.headers.post["Content-Type"] = "application/json";

	function findAll() {
		// get all tasks and display initially
		$http.get(urlBase + '/rest/olas').success(function(data) {
			$scope.selection = [];
			if (data != undefined) {
				$scope.valores = data;
			} else {
				$scope.valores = [];
			}

			for (var i = 0; i < $scope.valores.length; i++) {
				$scope.selection.push({
					valor : $scope.valores[i].valor
				});
			}
		});
	}

	findAll();

	// add a new task
	$scope.addValor = function addValor() {
		if ($scope.valor == "") {
			alert("Informe o valor!!!");
		} else {
			$http.post(urlBase + '/olas', {
				valor : $scope.valor
			}).success(
					function(data, status, headers) {
						alert("Valor adicionado");
						var newTaskUri = headers()["location"];
						console.log("Might be good to GET " + newTaskUri
								+ " and append the task.");
						findAll();
					});
		}
	};

});

// Angularjs Directive for confirm dialog box
taskManagerModule.directive('ngConfirmClick', [ function() {
	return {
		link : function(scope, element, attr) {
			var msg = attr.ngConfirmClick || "Are you sure?";
			var clickAction = attr.confirmedClick;
			element.bind('click', function(event) {
				if (window.confirm(msg)) {
					scope.$eval(clickAction);
				}
			});
		}
	};
} ]);