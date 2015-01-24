(function($) {

MainPageView = Backbone.View.extend({

    tagname : 'div',


    events : {
        "click a#category" : "handleRouting",
        "click a#addWishList" : "addToWishList",
        "click a#removeWishList" : "removeFromToWishList"
    },

    initialize: function () {
    this.productList = new ProductsCollection();

  },

    handleRouting : function(e) {
      console.log($(e.currentTarget).data('url'));
      mainPage.onCategoryClicked($(e.currentTarget).data('url'));
    },

    addToWishList: function(e) {
		console.log($(e.currentTarget).data('productid'));

		var wishListItem = new WishListItem({
				   productId : $(e.currentTarget).data('productid'),
			       userId : window.loggedInUser.userId
			      });
	    if(wishListCollection == undefined) {
	    	wishListCollection = new WishList();
	    }
		wishListCollection.create(wishListItem);
		$('#successMessage').show();
	},


	removeFromToWishList: function(e) {
		console.log($(e.currentTarget).data('productid'));
		var lsProduct = new Backbone.LocalStorage("store-WishList");
	    var tmpWishListCollection = lsProduct.findAll();
	    var productId = $(e.currentTarget).data('productid');
	    $.each(eval(tmpWishListCollection), function(i, item) {
	    	if(window.loggedInUser.id == item.userId && productId == item.productId){
	    		lsProduct.destroy(item);
	    	}

	    });
		mainPage.displayWishList();
		$('#alertLabel').value = "Item removed from Wishlist";
		$('#successMessage').show();
	},


     render: function(model){
     	window.loggedInUser = model;
         $.get("src/js/HomePage/Template/MainPage.html", function (data) {
         	template = _.template(data, model);
	         $('#pageDiv').html(template);
	         mainPage.getProductCategoryList();
	     });
    },

    loadRightSideMenuBar:function() {
    	collection = productCategoryList;
    	$.get("src/js/HomePage/Template/RightSideNavigation.html", function (data) {
         	template = _.template(data, {'collection' : collection.toJSON()});
	         $('#rightHandSideMenu').html(template);
	         console.log($(window).height());
	         $('#rightHandSideMenu').height($(window).height() - $('#footer').height() - 60);
	         $('#homePageContent').height($(window).height() - $('#footer').height() - 60);
	         mainPage.registerCustomEvents();
	         $('#loadingBar').modal('hide');
	     });
    },

    getProductCategoryList : function() {
    	   var headerObject = {};
    	    $.get("./rest/hello", function (data) {		
            data = JSON.parse(data);
            var catergoryArray = [];
            categorysArray = data;
         	$.each(categorysArray, function(i, item) {
                 var tmpCategory = new ProductCategory({
				   category_name : item.category_name,
				   url: item.url,
				   id : item.id
			      });
                 if(productCategoryList == undefined) {
                	 productCategoryList = new ProductCategorysCollection();
          		}
                 productCategoryList.add(tmpCategory);

               });
               mainPage.loadRightSideMenuBar();
	         });
    },

    registerCustomEvents : function() {
    	console.log('Main Page Registered');
    	$('#viewAccount').click(mainPage.displayUser);
    	$('#editAccount').click(mainPage.editUser);
    	$('#addWishListItemBtn').click(mainPage.displayWishList);
    	$('#signOut').click(mainPage.logoutUser);
    	$('#hideSuccessMessage').click(mainPage.removeAlertMessage);
        $('label.tree-toggler').click(function () {
	    	$(this).parent().children('ul.tree').toggle(300);
     	});

    },

    removeAlertMessage: function(){
    	$('#successMessage').hide();
    },

    displayUser: function(){
    	console.log('Display User');
	     var userDetails = new UserView;
	     userDetails.render(window.loggedInUser, false);

    },

    displayWishList: function(){
    	console.log('Display WishList');
	     var wishList = new WishListView;
	     wishList.render(window.loggedInUser);
	     mainPage.getUserWishList();
    },

    getUserWishList: function() {
    	$.get("./rest/wishlist?userId=" + window.loggedInUser.userId, function (data) {
    		mainPage.loadProductList(data,true);
    	});
	    
	    

	},

    editUser: function(){
    	console.log('Edit User');
	     var userDetails = new UserView;
	     userDetails.render(window.loggedInUser,true);
    },

    logoutUser: function(){
    	$('#pageDiv').html('');
    },

    onCategoryClicked: function(url) {
    	url = url.replace("&", "~~");
    	 $('#loadingBar').modal('show');
    	$.get("./rest/hello1?url="+url, function (data) {
             data  = $.parseJSON(data);
             productList = new ProductsCollection();

             productList.comparator =function(productList) {
            	  return -productList.get('sellingPrice'); // Note the minus!
             }; ;
         	$.each((data), function(i, item) {

         		var tmpProduct = new Product({
				   id : item.id,
				   productTitle : item.productTitle,
				   productDescription : item.productDescription,
				   imageUrls : item.imageUrls,
				   productUrl : item.productUrl,
				   inStock : item.inStock,
				   sellingPrice : item.maximumRetailPrice
			      });

		         productList.add(tmpProduct);
                 //console.log(i);
         		});
         	    productList.sort();
         		mainPage.loadProductList(productList.toJSON(),false);
        });
         		//var lsProduct = new Backbone.LocalStorage("store-product");
			    //productList = lsProduct.findAll();

			   //$('#homePageContent').html(data);


    },

    loadProductList: function(tmpProductList,displayWishlist) {
    	console.log('Display Product');
    	var productCardView = new ProductCardView;
	    productCardView.render(tmpProductList,displayWishlist);
    }
});
var mainPage = new MainPageView();
var productCategoryList = new ProductCategorysCollection;
var wishListCollection = new WishList();
var productList = new ProductsCollection();
var productInfo = new Product();
})(jQuery);