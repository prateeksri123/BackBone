MainPageView = Backbone.View.extend({
    template : '#main-Page-Template',
    tagname : 'div',
    
    events : {
        "click a" : "handleRouting"
    },

    handleRouting : function(e) {
        console.log(this.model);
    },
  
     render: function(model){
     	this.model = model;
     	//Make This working.
       // $.get("js/HomePage/Template/MainPage.html", function (template) {
        // //template = _.template(data, {});
        // console.log('XXX -> ' +  template);
        // $(this.el).append(template); 
    // });    
         //console.log("HTML ->> "+ html.responseText );
     	console.log(this.template);
     	this.template = _.template($(this.template).html());
     	//var html= ;
        $(this.el).html(this.template(model));
    }
}); 