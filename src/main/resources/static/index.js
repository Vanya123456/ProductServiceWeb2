angular.module('app', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/app';

    $scope.loadProducts = function () {
        $http.get(contextPath + '/products')
            .then(function (response) {
                $scope.ProductsList = response.data;
                console.log("Products")
            });
    };

    $scope.loadProducts();

    $scope.deleteProduct = function (productId) {
        $http.get(contextPath + '/products/delete/' + productId)
            .then(function (response) {
                $scope.loadProducts();
            });
    }

    $scope.filterProductsByCost = function () {
        console.log($scope.param);
        $http({
            url: contextPath + '/products/price_between',
            method: 'GET',
            params: {
                min: $scope.param.min,
                max: $scope.param.max
            }
        }).then(function (response) {
            console.log(response)
            $scope.ProductsList = response.data;
        });
    }
});