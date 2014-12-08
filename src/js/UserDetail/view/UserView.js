UserView = Backbone.View.extend({
    
    tagname : 'div',
    
    
    
    initialize: function (event) {
      
    },
    
     callback: function(eventType) {
        console.log("event type was " + eventType);
    },

    handleRouting : function(e) {
    	alert('handleRouting');
        //console.log(this.model);
    },
    
    registerCustomEvents : function() {
    	$('#okBtn').click(userDetails.closeMe);
    	$('#updateBtn').click(userDetails.updateDetails);
    	$('#cancelBtn').click(userDetails.closeMe);
    	$('#crossBtn').click(userDetails.closeMe);
    },
  
    closeMe: function(event) {
        //console.log("jQuery handler for " + this.outerHTML);
        $('#homePageContent').html('');
    },
    
    updateDetails: function(event) {
    	console.log("call update " + loggedInUser.id);
    	loggedInUser.firstName = $('#txtUpdatedFirstName').val();
    	loggedInUser.lastName = $('#txtUpdatedLastName').val();
    	loginPage.updateUser(loggedInUser);
        $('#homePageContent').html('');
    },
    
     render: function(model, editUser){
     	loggedInUser = model;
         $.get("js/UserDetail/template/UserDetailsTemplate.html", function (template) {
         	html = _.template(template,{'model' : model, 'displayPassword' : 'none', 'editAccount' : editUser});
	         $('#homePageContent').html(html);
	         userDetails.registerCustomEvents();
	     });    
	         
    },
    
    
});
var loggedInUser =  new Object;
var userDetails = new UserView;
var loginPage = new ClientView;
var homePage = new HomePageRouter;
Backbone.history.start();