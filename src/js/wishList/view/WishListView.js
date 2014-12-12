/**
 * @author Prateek.Srivastava
 */
WishListView = Backbone.View.extend({
    
    tagname : 'div',
    
    initialize: function (event) {
      
    },
    
    handleRouting : function(e) {
    },
    
    registerCustomEvents : function() {
    },
     render: function(model, editUser){
     	window.loggedInUser = model;
         $.get("src/js/wishList/template/WishListTemplate.html", function (template) {
         	html = _.template(template,model);
	         $('#homePageContent').append(html);
	         console.log("WishListTemplate Loaded");
	         userDetails.registerCustomEvents();
	     });       
    },
});

var wishListView = new WishListView;