(function ($) {
	"use strict";

	// Spinner
	var spinner = function () {
		setTimeout(function () {
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
	$(window).scroll(function () {
		if ($(this).scrollTop()) {
			$('.fixed-top').addClass('bg-dark').css('top', 0);
		} else {
			$('.fixed-top').removeClass('bg-dark').css('top', $('.top-bar').height());
		}
	});


	// Back to top button
	$(window).scroll(function () {
		if ($(this).scrollTop() > 300) {
			$('.back-to-top').fadeIn('slow');
		} else {
			$('.back-to-top').fadeOut('slow');
		}
	});
	$('.back-to-top').click(function () {
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

app.filter('vndCurrency', function () {
	return function (input) {
		if (!input) return '';
		return input.toLocaleString('vi-VN', { style: 'currency', currency: 'VND' });
	};
});
app.filter('dateFilter', function ($filter) {
	return function (input) {
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
	$scope.category = [];

	$scope.pageNum = 0;
	$scope.totalPages = 0;
	$scope.totalProducts = 0;
	$scope.selectedProductId = 0;


	$scope.selectedCategoryId = 0;
	$scope.key = '';


	$scope.cart = [];
	$scope.cartLength = 0;

	$scope.imgData = "";
	$scope.productImages = [];

	$scope.reviewList = [];
	$scope.formReview = {};
	$scope.rating = 0;
	$scope.stars = new Array(5);
	$scope.isHovered = new Array(5).fill(false);

	$scope.userInfo = {};
	

	function loadCategory() {
		return $http.get(`${host}/category`).then(function (resp) {
			$scope.category = resp.data;
		});
	}

	// pagination and filters - pagination and filters - pagination and filters - pagination and filters - pagination and filters

	$scope.loadPage = function (page) {
		$scope.pageNum = page;
		var url = `${host}/product/?page=`+page;
		$http.get(url).then(function (resp) {
			if ($scope.selectedCategoryId != 0 && $scope.selectedCategoryId != null) {
				
				
				
				$http.get(`${host}/product/c/${$scope.selectedCategoryId}`).then(function (resp) {
					$scope.totalProducts = resp.data.length;
					$scope.totalPages = Math.ceil($scope.totalProducts / 4);
				})
				$http.get(url +"&c="+$scope.selectedCategoryId).then(resp => {
					$scope.products = resp.data;
				})
			} else {
				$http.get(`${host}/product`).then(resp => {
					$scope.totalPages = Math.ceil(resp.data.length / 4);
				})
				$http.get(url).then(resp => {
					$scope.products = resp.data;
				})
				
			}
		});
	};
	// pagination and filters - pagination and filters - pagination and filters - pagination and filters - pagination and filters

	$scope.disablePrev = function () {
		return $scope.pageNum === 0;
	};

	$scope.disableNext = function () {
		return $scope.pageNum === $scope.totalPages - 1;
	};

	$scope.setCategory = function (categoryId) {
		$scope.selectedCategoryId = categoryId;
		$scope.loadPage(0);
	};
	$scope.getprdId = function () {
		const url = `${host}/product/selectedProductId`;
		return $http.get(url).then(resp => {
			return resp.data;
		});
	}
	$scope.setSelectedProductId = function () {
		$scope.getprdId().then(function (productId) {
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
	// cart - cart - cart - cart - cart - cart - cart - cart - cart - cart - cart - cart - cart - cart

	// product images - product images - product images - product images - product images - product images - product images
	$scope.loadAllProductImages = function () {
		$scope.getprdId().then(function (productId) {
			$scope.selectedProductId = productId;
			var url = "http://localhost:8080/rest/pi/";
			url += productId;
			$http.get(url).then(resp => {
				$scope.productImages = resp.data;
			})
			$scope.loadReviews(productId);
		})
		
	}
	$scope.setImgData = function (data) {
		$scope.imgData = data;
	}
	// product images - product images - product images - product images - product images - product images - product images 

	// review - review - review - review - review - review - review - review - review - review - review - review - review
	
	$scope.loadReviews = function () {
		const url = `${host}/review/${$scope.selectedProductId}`;
		$http.get(url).then(resp => {
			$scope.reviewList = resp.data;
		})
	}
	
	function getUser() {
		var url = `${host}/user`;
		$http.get(url).then(resp => {
			$scope.userInfo.accountId = resp.data.accountId;
			$scope.userInfo.username = resp.data.username;
			$scope.userInfo.password = resp.data.password;
			$scope.userInfo.email = resp.data.email;
			$scope.userInfo.fullName = resp.data.fullName;
			$scope.userInfo.address = resp.data.address;
			$scope.userInfo.addressDetail = resp.data.addressDetail;
			$scope.userInfo.phoneNumber = resp.data.phoneNumber;
			$scope.userInfo.photo = resp.data.photo;
			$scope.userInfo.admin = resp.data.admin;
		})
	}
	$scope.postReview = function() {
		$scope.formReview.rating = $scope.rating;
		const url = `${host}/review/${$scope.selectedProductId}`;
		$http.post(url, $scope.formReview).then(() => {
			$scope.loadReviews();
			$scope.reviewForm = [];
		})
	}
	
	$scope.setHovered = function(index, hovered) {
	  for (var i = 0; i <= index; i++) {
		$scope.isHovered[i] = hovered;
	  }
	};
	$scope.setRating = function(rating) {
	  $scope.rating = rating;
	};
	// review - review - review - review - review - review - review - review - review - review - review - review - review
	
	



	// Khởi tạo dữ liệu ban đầu
	loadCategory();
	$scope.loadPage(0);
	$scope.setSelectedProductId();
	$scope.loadAllCart();
	initCartFromLocalStorage();

	$scope.loadAllProductImages();

	getUser();
});




// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
app.controller('OrderController', function ($scope, $http) {
	$scope.products = [];
	$scope.category = [];
	$scope.form = {};
});
// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - 
app.controller('UserController', function ($scope, $http) {
	$scope.userInfo = {};
	$scope.form = {};

    $scope.cities = [];
    $scope.districts = [];
    $scope.wards = [];

    $scope.city = null;
    $scope.district = null;
    $scope.ward = null;


	const url = `${host}/address/`;
	const urlUser = `${host}/user`;
	
	function getUser() {
		var url = `${host}/user`;
		$http.get(url).then(resp => {
			$scope.userInfo = resp.data;
			$scope.form.accountId = $scope.userInfo.accountId;
			$scope.form.username = $scope.userInfo.username;
			$scope.form.password = $scope.userInfo.password;
			$scope.form.email = $scope.userInfo.email;
			$scope.form.fullName = resp.data.fullName;
			$scope.form.address = $scope.userInfo.address;
			$scope.form.addressDetail = $scope.userInfo.addressDetail;
			$scope.form.phoneNumber = $scope.userInfo.phoneNumber;
			$scope.form.photo = $scope.userInfo.photo;
			$scope.form.admin = $scope.userInfo.admin;
		})
	}
	$scope.test = function() {
		console.log($scope.form);
	}
	$scope.putUser = function() {
		$http.put(urlUser, $scope.form)
        .then(function (resp) {
            // Xử lý phản hồi thành công từ máy chủ
            $scope.form = resp.data;
        })
        .catch(function (error) {
            // Xử lý lỗi
            console.error("Error:", error);
            // Hiển thị thông báo lỗi cho người dùng (tùy bạn)
        });
	}
    $scope.loadCities = function () {
        $http.get(url+"cities").then(function (response) {
            $scope.cities = response.data;
        });
    };

    $scope.loadDistricts = function () {
        if ($scope.city !== null && $scope.city === '') {
			$scope.address = $scope.city.name;
            $scope.districts = [];
            $scope.wards = [];
            return;
        }

        $http.get(url+$scope.city.code+"/districts").then(function (response) {
            $scope.districts = response.data;
            $scope.district = null;
            $scope.wards = [];	
			$scope.setAddress();		
        });

    };

    $scope.loadWards = function () {
        if (!$scope.district) {
            $scope.wards = [];
            return;
        }
        $http.get(url+$scope.district.code+"/wards").then(function (response) {
            $scope.wards = response.data;
			$scope.setAddress();		
        });
    };

    $scope.setAddress = function() {
		$scope.form.address = '';
		if($scope.city != null) {
			$scope.form.address = $scope.city.name_with_type;
			if($scope.district != null) {
				$scope.form.address = $scope.district.path_with_type;
				if($scope.ward != null) {
					$scope.form.address = $scope.ward.path_with_type;
				}
			}
		}
	}
	
	



	getUser();
	$scope.loadCities();
});


// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - 
app.controller('AdminProductController', function ($scope, $http) {
	$scope.products = [];
	$scope.category = [];
	$scope.form = {};

	$scope.loadAll = function () {
		const url = `${host}/product`;
		$http.get(url).then(resp => {
			$scope.products = resp.data;
		});
	}

	$scope.loadCategory = function () {
		const url = `${host}/category`;
		$http.get(url).then(resp => {
			$scope.category = resp.data;
		});
	}

	$scope.reset = function () {
		$scope.form = {};
		$scope.form.price = 0.00;
		$scope.topProductId();
	}

	$scope.add = function () {
		if ($scope.form != null) {
			const url = `${host}/product`;
			$http.post(url, $scope.form).then(function (resp) {
				console.log("Thêm sản phẩm thành công!");
				$scope.loadAll();
			}).catch(function (error) {
				console.error("Lỗi khi thêm sản phẩm:", error);
			});
		} else {
			console.log('Form rỗng!');
		}
	}

	$scope.update = function (productId) {
		const url = `${host}/product/${productId}`;
		if ($scope.form != null) {
			$http.put(url, $scope.form).then(function (resp) {
				console.log("Cập nhật sản phẩm thành công!");
				$scope.loadAll();
			}).catch(function (error) {
				console.error("Lỗi khi cập nhật sản phẩm:", error);
			});
		} else {
			console.log('Form rỗng!');
		}
	}


	$scope.delete = function (productId) {
		const url = `${host}/product/${productId}`;
		$http.delete(url).then(function (resp) {
			console.log("Xóa sản phẩm thành công!");
			$scope.loadAll();
			$scope.reset();
		}).catch(function (error) {
			console.error("Lỗi khi xóa sản phẩm:", error);
		});
	}

	$scope.loadForm = function (productId) {
		const product = $scope.products.find(product => product.productId === productId);
		if (product) {
			$scope.form = angular.copy(product);
			$scope.form.price = parseFloat($scope.form.price);
			$scope.scrollToForm();
		} else {
			$scope.form = {};
		}
	}
	$scope.topProductId = function () {
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
		}
	};
	$scope.topProductId();
	$scope.loadAll();

	$scope.loadCategory();
	$scope.reset();
});

// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - 
app.controller('AdminOrderController', function ($scope, $http) {
	$scope.orders = [];
	$scope.oiList = [];

	$scope.loadAll = function () {
		const url = `${host}/order`;
		$http.get(url).then(resp => {
			$scope.orders = resp.data;
			console.log(resp.data);
		});
	}

	$scope.updateStatus = function (id) {
		const url = `${host}/order/${id}/updateStatus`;
		$http.put(url)
			.then(function (response) {
				console.log('Trạng thái của đơn hàng đã được cập nhật.');
				$scope.loadAll();
			})
			.catch(function (error) {
				console.error('Lỗi khi cập nhật trạng thái của đơn hàng:', error);
			});
	};

	$scope.getStatusClass = function (status) {
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

	$scope.getOrderItemByOrderId = function (orderId) {
		const url = `${host}/order/detail/${orderId}`;
		$http.get(url).then(resp => {
			$scope.oiList = resp.data;
			console.log(resp.data);
		});
	}
	$scope.loadAll();
});

