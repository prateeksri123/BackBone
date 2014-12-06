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
    	$('#cmdAddClient_1').click(userDetails.jqueryClicked);
    },
  
    jqueryClicked: function(event) {
        console.log("jQuery handler for " + this.outerHTML);
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
    
    events : {
        "click input[type=button]" : "handleRouting"
    },
});
var userDetails = new UserView; 