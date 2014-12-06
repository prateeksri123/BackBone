UserView = Backbone.View.extend({
    
    tagname : 'div',
    
    events : {
        "click #cmdAddClient_1" : "handleRouting"
    },

    handleRouting : function(e) {
    	alert('handleRouting');
        //console.log(this.model);
    },
  
     render: function(model){
     	this.model = model;
         $.get("js/UserDetail/template/UserDetailsTemplate.html", function (data) {
         	template = _.template(data, model);
	         $('#homePageContent').html(template);
	        var userDetails1 = new UserView;
	         	userDetails1.handleRouting();
	         $('#cmdAddClient_1').on("click", userDetails1.handleRouting1);
	     });    
	         
    }
}); 