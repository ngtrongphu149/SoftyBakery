(function($) {
	"use strict";

	// Spinner
	var spinner = function() {
		setTimeout(function() {
			if ($('#spinner').length > 0) {
				$('#spinner').removeClass('show');
			}
		}, 1);
	};
	spinner();


	// Initiate the wowjs
	new WOW().init();


	// Fixed Navbar
	$('.fixed-top').css('top', $('.top-bar').height());
	$(window).scroll(function() {
		if ($(this).scrollTop()) {
			$('.fixed-top').addClass('bg-dark').css('top', 0);
		} else {
			$('.fixed-top').removeClass('bg-dark').css('top', $('.top-bar').height());
		}
	});


	// Back to top button
	$(window).scroll(function() {
		if ($(this).scrollTop() > 300) {
			$('.back-to-top').fadeIn('slow');
		} else {
			$('.back-to-top').fadeOut('slow');
		}
	});
	$('.back-to-top').click(function() {
		$('html, body').animate({ scrollTop: 0 }, 1500, 'easeInOutExpo');
		return false;
	});


	// Header carousel
	$(".header-carousel").owlCarousel({
		autoplay: false,
		smartSpeed: 1500,
		loop: true,
		nav: true,
		dots: false,
		items: 1,
		navText: [
			'<i class="bi bi-chevron-left"></i>',
			'<i class="bi bi-chevron-right"></i>'
		]
	});


	// Facts counter
	$('[data-toggle="counter-up"]').counterUp({
		delay: 10,
		time: 2000
	});


	// Testimonials carousel
	$(".testimonial-carousel").owlCarousel({
		autoplay: false,
		smartSpeed: 1000,
		margin: 25,
		loop: true,
		center: true,
		dots: false,
		nav: true,
		navText: [
			'<i class="bi bi-chevron-left"></i>',
			'<i class="bi bi-chevron-right"></i>'
		],
		responsive: {
			0: {
				items: 1
			},
			768: {
				items: 2
			},
			992: {
				items: 3
			}
		}
	});


})(jQuery);
var app = angular.module('app', ['ngRoute']);

let host = "http://localhost:8080/rest";

app.filter('vndCurrency', function() {
	return function(input) {
		if (!input) return '';
		return input.toLocaleString('vi-VN', { style: 'currency', currency: 'VND' });
	};
});
app.filter('dateFilter', function($filter) {
	return function(input) {
		if (input) {
			var date = new Date(input);
			var formattedDate = $filter('date')(date, 'dd-MM-yyyy');
			var formattedTime = $filter('date')(date, 'HH:mm');
			return formattedTime + ' ' + formattedDate;
		}
		return '';
	};
});
// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - 
app.controller('ProductController', function ($scope, $http) {
    $scope.products = [];
    
    $scope.pageNum = 0;
    $scope.totalPages = 0;
    $scope.totalProducts = 0;
    $scope.selectedProductId = 0;
    
    $scope.category = [];
    $scope.selectedCategoryId = 0;
    
    $scope.cart = [];
    $scope.cartLength = 0;
        
	$scope.imgData = "";
	
	$scope.productImages = [];
    function updateTotalProducts(count) {
        $scope.totalProducts = count;
    }
    
    function loadAllProducts() {
        return $http.get(`${host}/product`).then(function (resp) {
            return resp.data.length;
        });
    }

    function loadCategory() {
        return $http.get(`${host}/category`).then(function (resp) {
            $scope.category = resp.data;
        });
    }

    function paginationProducts(page) {
        var url = `${host}/product/?page=${page}`;
        url += $scope.selectedCategoryId ? `&c=${$scope.selectedCategoryId}` : '';
        return $http.get(url);
    }

    function filterAndPaginateProducts(products, name, minPrice, maxPrice, sort, page, size, categoryId) {
        let filteredProducts = products;

        if (name && name !== '') {
            filteredProducts = filterByName(filteredProducts, name);
        }
        if (minPrice !== null && maxPrice !== null && minPrice >= 0 && maxPrice >= 0 && maxPrice >= minPrice) {
            filteredProducts = filterByPriceRange(filteredProducts, minPrice, maxPrice);
        }
        if (categoryId && categoryId !== '') {
            filteredProducts = filterByCategory(filteredProducts, categoryId);
        }
        if (sort === 'ASC') {
            filteredProducts = sort(filteredProducts, true);
        } else if (sort === 'DESC') {
            filteredProducts = sort(filteredProducts, false);
        }
        const pageable = PageRequest.of(page, size);
        const totalItems = filteredProducts.length;
        const startIndex = pageable.getOffset();
        const endIndex = Math.min(startIndex + size, totalItems);
        const items = filteredProducts.slice(startIndex, endIndex);

        return new PageImpl(items, pageable, totalItems);
    }

    function filterByName(products, name) {
        return products.filter(product => product.productName.toLowerCase().includes(name.toLowerCase()));
    }

    function filterByPriceRange(products, minPrice, maxPrice) {
        return products.filter(product => product.price >= minPrice && product.price <= maxPrice);
    }

    function filterByCategory(products, categoryId) {
        return products.filter(product => product.category.categoryId === categoryId);
    }

    function sort(products, ascending) {
        const priceComparator = (a, b) => a.price - b.price;
        if (!ascending) {
            products.sort((a, b) => priceComparator(b, a));
        } else {
            products.sort(priceComparator);
        }
        return products;
    }
    $scope.loadPage = function (page) {
        $scope.pageNum = page;
        paginationProducts(page).then(function (resp) {
            $scope.products = resp.data;
            if ($scope.selectedCategoryId !== null) {
                const url = `${host}/product/c/${$scope.selectedCategoryId}`;
                $http.get(url).then(function (categoryResp) {
                    $scope.totalProducts = categoryResp.data.length;
                    $scope.totalPages = Math.ceil(25 / 4);
                });
            } else {
                $scope.totalPages = Math.ceil($scope.totalProducts / 4);
            }
            console.log('total product: '+$scope.totalProducts)
            console.log('total page: '+$scope.totalPages)
            console.log('page: '+page)
            
        });
    };

    $scope.disablePrev = function () {
        return $scope.pageNum === 0;
    };

    $scope.disableNext = function () {
        return $scope.pageNum === $scope.totalPages - 1;
    };

    $scope.selectCategory = function (categoryId) {
        $scope.key = '';
        $scope.selectedCategoryId = categoryId;
        $scope.loadPage(0);
    };
    $scope.getSelectedProductId = function() {
	    const url = `${host}/product/selectedProductId`;
	    return $http.get(url).then(resp => {
	        return resp.data;
	    });
	}
	$scope.setSelectedProductId = function() {
	    $scope.getSelectedProductId().then(function(productId) {
			$scope.selectedProductId = productId;
	    });
	}
	
// cart - cart - cart - cart - cart - cart - cart - cart - cart - cart - cart - cart - cart - cart
    function updateCartLocalStorage() {
    	localStorage.setItem('cart', JSON.stringify($scope.cart));
    }

    $scope.loadAllCart = function () {
        $http.get(`${host}/cart`).then(function (resp) {
            $scope.cart = resp.data;
            $scope.cartLength = resp.data.length;
            updateCartLocalStorage();
        });
    }

    $scope.modifyCart = function (id, method) {
        $http.get(`${host}/cart/${method}/${id}`).then(function () {
            $scope.loadAllCart();
        });
    };

    $scope.deleteCart = function (id) {
        $http.get(`${host}/cart/delete/${id}`).then(function () {
            $scope.loadAllCart();
        });
    };

    $scope.clearCart = function () {
        $http.get(`${host}/cart/clear`).then(function () {
            $scope.loadAllCart();
        });
    };

    $scope.getAmountCart = function () {
        return $scope.cart.reduce(function (total, product) {
            return total + product.price * product.quantity;
        }, 0);
    };

    function initCartFromLocalStorage() {
        $scope.cart = JSON.parse(localStorage.getItem('cart')) || [];
    }	
// product images - product images - product images - product images - product images - product images - product images
	$scope.loadAllProductImages = function () {
		$scope.getSelectedProductId().then(function(productId) {
			$scope.selectedProductId = productId;
			var url = "http://localhost:8080/rest/pi/";
			url += productId;
			$http.get(url).then(resp => {
				$scope.productImages = resp.data;
			})
	    })
	}
    $scope.setImgData = function (data) {
		$scope.imgData = data;
	}
    
    $scope.loadAllProductImages();
    
    
    
    // Khởi tạo dữ liệu ban đầu
    loadCategory().then(function () {
        return loadAllProducts();
    }).then(function (totalCount) {
        updateTotalProducts(totalCount);
        $scope.loadPage(0);
    });
    $scope.setSelectedProductId();
    $scope.loadAllCart();
    initCartFromLocalStorage();
});



// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - 
app.controller('AdminProductController', function($scope, $http) {
	$scope.products = [];
	$scope.category = [];
	$scope.form = {};
	
	$scope.loadAll = function() {
		const url = `${host}/product`;
		$http.get(url).then(resp => {
			$scope.products = resp.data;
		});
	}

	$scope.loadCategory = function() {
		const url = `${host}/category`;
		$http.get(url).then(resp => {
			$scope.category = resp.data;
		});
	}

	$scope.reset = function() {
		$scope.form = {};
		$scope.form.price = 0.00;
		$scope.topProductId();
	}

	$scope.add = function() {
		if ($scope.form != null) {
			const url = `${host}/product`;
			$http.post(url, $scope.form).then(function(resp) {
				console.log("Thêm sản phẩm thành công!");
				$scope.loadAll();
			}).catch(function(error) {
				console.error("Lỗi khi thêm sản phẩm:", error);
			});
		} else {
			console.log('Form rỗng!');
		}
	}

	$scope.update = function(productId) {
	    const url = `${host}/product/${productId}`;
	    if ($scope.form != null) {
	        $http.put(url, $scope.form).then(function(resp) {
	            console.log("Cập nhật sản phẩm thành công!");
	            $scope.loadAll();
	        }).catch(function(error) {
	            console.error("Lỗi khi cập nhật sản phẩm:", error);
	        });
	    } else {
	        console.log('Form rỗng!');
	    }
	}


	$scope.delete = function(productId) {
		const url = `${host}/product/${productId}`;
		$http.delete(url).then(function(resp) {
			console.log("Xóa sản phẩm thành công!");
			$scope.loadAll();
			$scope.reset();
		}).catch(function(error) {
			console.error("Lỗi khi xóa sản phẩm:", error);
		});
	}

	$scope.loadForm = function(productId) {
		const product = $scope.products.find(product => product.productId === productId);
		if (product) {
			$scope.form = angular.copy(product);
			$scope.form.price = parseFloat($scope.form.price);
			$scope.scrollToForm();
		} else {
			$scope.form = {};
		}
	}
	$scope.topProductId = function() {
		const url = `${host}/product/top`;
		$http.get(url).then(resp => {
			$scope.form.productId = resp.data;
		});
	}
	$scope.scrollToForm = function () {
        var formElement = document.getElementById('formProduct');
        if (formElement) {
            formElement.scrollIntoView({
                behavior: 'smooth',
                block: 'start',
                inline: 'nearest'
            });
            $window.scrollBy(500, -distanceFromTop);
        }
    };
	$scope.topProductId();
	$scope.loadAll();
	$scope.loadCategory();
	$scope.reset();
});
// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
app.controller('OrderController', function($scope, $http) {
	$scope.products = [];
	$scope.category = [];
	$scope.form = {};
});
// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - 
app.controller('UserController', function($scope, $http) {
    $scope.userInfo = {};
	
    $scope.getUser = function() {
        var url = `${host}/user`;
        $http.get(url).then(resp => {
			$scope.userInfo = resp.data;
		});
    }
    $scope.getUser();
});


// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - 
app.controller('AdminOrderController', function($scope, $http) {
	$scope.orders = [];
	$scope.oiList = [];

	$scope.loadAll = function() {
		const url = `${host}/order`;
		$http.get(url).then(resp => {
			$scope.orders = resp.data;
			console.log(resp.data);
		});
	}

	$scope.updateStatus = function(id) {
		const url = `${host}/order/${id}/updateStatus`;
		$http.put(url)
			.then(function(response) {
				console.log('Trạng thái của đơn hàng đã được cập nhật.');
				$scope.loadAll();
			})
			.catch(function(error) {
				console.error('Lỗi khi cập nhật trạng thái của đơn hàng:', error);
			});
	};

	$scope.getStatusClass = function(status) {
		switch (status) {
			case 'Đang chờ':
				return 'btn btn-primary';
			case 'Đã giao':
				return 'btn btn-success';
			case 'Đã hủy':
				return 'btn btn-secondary';
			default:
				return 'btn';
		}
	};

	$scope.getOrderItemByOrderId = function(orderId) {
		const url = `${host}/order/detail/${orderId}`;
		$http.get(url).then(resp => {
			$scope.oiList = resp.data;
			console.log(resp.data);
		});
	}
	$scope.loadAll();
});

