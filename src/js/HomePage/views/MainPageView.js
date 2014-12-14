MainPageView = Backbone.View.extend({
    
    tagname : 'div',
    
    events : {
        "click a#category" : "handleRouting"
    },
    
    handleRouting : function(e) {
      console.log($(e.currentTarget).data('url'));
      mainPage.onCategoryClicked($(e.currentTarget).data('url'));
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
         	template = _.template(data, {'collection' : collection});
	         $('#rightHandSideMenu').html(template);
	         console.log($(window).height());
	         $('#rightHandSideMenu').height($(window).height() - $('#footer').height() - 60);
	         $('#homePageContent').height($(window).height() - $('#footer').height() - 60);
	         mainPage.registerCustomEvents();
	         
	     }); 
    },
    
    getProductCategoryList : function() {
    	   var headerObject = {};
    	   var ls = new Backbone.LocalStorage("store-product-Category");
			
			productCategoryList = ls.findAll();
			mainPage.loadRightSideMenuBar();
    	    /*$.get("src/js/HomePage/data/ProductCategory.json", function (data) {
         	console.log(data.apiGroups);
         	
         	$.each(eval(data.apiGroups.affiliate.apiListings), function(i, item) {
                 //console.log(item.availableVariants['v0.1.0'].get);
                 //console.log(item.apiName);
                 var res = item.apiName.replace("_", " ");
                 var tmpCategory = new ProductCategory({
				   category_name : res,
				   url: item.availableVariants['v0.1.0'].get,
				   id : item.apiName
			      });
		          //productCategoryList.create(tmpCategory);
               });
               mainPage.loadRightSideMenuBar();
	         });  
    	   var request = $.ajax({
             type: 'GET',
             url: 'https://affiliate-api.flipkart.net/affiliate/api/mywishlis.json',
             dataType: "json",
             headers : {
             	   'Fk-Affiliate-Id': 'mywishlis',
                   'Fk-Affiliate-Token': '22ba4f9fe89f4007ab51f45a777d4c7a',
                   'Access-Control-Allow-Origin' :  '*',
                   "Access-Control-Allow-Headers" : "Origin, X-Requested-With, Content-Type, Accept"
                   
             },
             success: function(data){
              console.log(data);
                }});*/
    	   
    },
    
    registerCustomEvents : function() {
    	console.log('Main Page Registered');
    	$('#viewAccount').click(mainPage.displayUser);
    	$('#editAccount').click(mainPage.editUser);
    	$('#addWishListItemBtn').click(mainPage.displayWishList);
    	$('#signOut').click(mainPage.logoutUser);
        $('label.tree-toggler').click(function () {
	    	$(this).parent().children('ul.tree').toggle(300);
     	});

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
    	$.get("src/js/HomePage/data/ProductList.json", function (data) {
         	
         	$.each(eval(data.productInfoList), function(i, item) {
         		
         		var tmpProduct = new Product({
				   id : item.productBaseInfo.productIdentifier.productId,
				   productTitle : item.productBaseInfo.productAttributes.title, 
				   productDescription : item.productBaseInfo.productAttributes.productDescription,
				   imageUrls : item.productBaseInfo.productAttributes.imageUrls
			      });
		         productList.create(tmpProduct);
                 //console.log(i);
         		});
         		mainPage.loadProductList();
       });
    },
    
    loadProductList: function() {
    	console.log('Display Product');
    	var lsProduct = new Backbone.LocalStorage("store-product");
			productList = lsProduct.findAll();
			
	     var productCardView = new ProductCardView;
	     productCardView.render(productList);
    }
});
var mainPage = new MainPageView();
var productCategoryList = new ProductCategorysCollection;
var productList = new ProductsCollection;
var productInfo = new Product;