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
    	$('#okBtn').click(userDetails.jqueryClicked);
    },
  
    jqueryClicked: function(event) {
        //console.log("jQuery handler for " + this.outerHTML);
        $('#homePageContent').html('');
    },
     render: function(){
     	//this.model = model;
         $.get("js/UserDetail/template/UserDetailsTemplate.html", function (template) {
         	html = _.template(template);
	         $('#homePageContent').html(html);
	         userDetails.handleRouting(); 
	         
	         userDetails.registerCustomEvents();
	     });    
	         
    },
    
    
});
var userDetails = new UserView;
var homePage = new HomePageRouter;
Backbone.history.start();