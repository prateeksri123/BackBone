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
	         
	         mainPage.registerCustomEvents();
	         mainPage.getProductCategoryList();
	        // window.href = "/displayUser";
	     });            
    },
    
    getProductCategoryList : function() {
    	   var headerObject = {};
    	   
    	    $.get("js/HomePage/data/ProductCategory.json", function (data) {
         	console.log(data);
	        // window.href = "/displayUser";
	     });  
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