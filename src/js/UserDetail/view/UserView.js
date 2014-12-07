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
    	$('#crossBtn').click(userDetails.closeMe);
    },
  
    closeMe: function(event) {
        //console.log("jQuery handler for " + this.outerHTML);
        $('#homePageContent').html('');
    },
    
     render: function(model){
     	this.model = model;
         $.get("js/UserDetail/template/UserDetailsTemplate.html", function (template) {
         	html = _.template(template,{'model' : model, 'displayPassword' : 'none', 'editAccount' : false});
	         $('#homePageContent').html(html);
	         userDetails.registerCustomEvents();
	     });    
	         
    },
    
    
});
var userDetails = new UserView;
var homePage = new HomePageRouter;
Backbone.history.start();