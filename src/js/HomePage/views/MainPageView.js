MainPageView = Backbone.View.extend({
    
    tagname : 'div',
    
    events : {
        "click a" : "handleRouting"
    },

    handleRouting : function(e) {
        console.log(this.model);
    },
    
     render: function(model){
     	loggedInUser = model;
         $.get("js/HomePage/Template/MainPage.html", function (data) {
         	template = _.template(data, model);
	         $('#pageDiv').html(template);
	         
	         mainPage.registerCustomEvents();
	        // window.href = "/displayUser";
	     });            
    },
    
    registerCustomEvents : function() {
    	console.log('Main Page Registered');
    	$('#viewAccount').click(mainPage.displayUser);
    	$('#signOut').click(mainPage.logoutUser);
    },
    
    displayUser: function(){
    	console.log('Display User');
	     var userDetails = new UserView;
	     userDetails.render(loggedInUser);
    },
    
    logoutUser: function(){
    	$('#pageDiv').html('');
    },
    
    test: function(){
    	alert('test');
    }
});
var mainPage = new MainPageView();
var loggedInUser = new Object;