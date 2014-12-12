MainPageView = Backbone.View.extend({
    
    tagname : 'div',
    
    events : {
        "click a" : "handleRouting"
    },

    handleRouting : function(e) {
        console.log(this.model);
    },
    
     render: function(model){
     	window.loggedInUser = model;
         $.get("js/HomePage/Template/MainPage.html", function (data) {
         	template = _.template(data, model);
	         $('#pageDiv').html(template);
	         mainPage.getProductCategoryList();
	     });            
    },
    
    loadRightSideMenuBar:function() {
    	collection = productCategoryList.toJSON();
    	$.get("js/HomePage/Template/RightSideNavigation.html", function (data) {
         	template = _.template(data, {'collection' : collection});
	         $('#rightHandSideMenu').html(template);
	         
	         mainPage.registerCustomEvents();
	         
	     }); 
    },
    
    getProductCategoryList : function() {
    	   var headerObject = {};
    	    $.get("js/HomePage/data/ProductCategory.json", function (data) {
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
		          productCategoryList.create(tmpCategory);
               });
               mainPage.loadRightSideMenuBar();
	         });  
    	   /*var request = $.ajax({
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
    
    test: function(){
    	alert('test');
    }
});
var mainPage = new MainPageView();
var productCategoryList = new ProductCategorysCollection;