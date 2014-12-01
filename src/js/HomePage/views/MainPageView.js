MainPageView = Backbone.View.extend({
    
    tagname : 'div',
    
    events : {
        "click a" : "handleRouting"
    },

    handleRouting : function(e) {
        console.log(this.model);
    },
  
     render: function(model){
     	this.model = model;
         $.get("js/HomePage/Template/MainPage.html", function (data) {
         	template = _.template(data, model);
	         $('#pageDiv').html(template);
	         var homePage = new HomePageRouter;
	        // window.href = "/displayUser";
	     });            
    },
    
    test: function(){
    	alert('test');
    }
}); 