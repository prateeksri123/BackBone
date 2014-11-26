MainPageView = Backbone.View.extend({

    //template: templates.SubmissionItemViewTemplate,
    
    template : '#main-Page-Template',
    // wrap the view with `tr` instead of `div`
    tagname : 'div',

    events : {
        "click a" : "handleRouting"
    },

    handleRouting : function(e) {
        console.log(this.model);
        e.preventDefault();
        if (window.isCustomerPortal && window.isCustomerPortal == "true") {
            alert('Open Submission In New Window!!');
        } else {
            ums.App.vent.trigger('openSubmission', this.model);
        }
    },
    
     render: function(){
     	
     	//Make This working.
       // $.get("js/HomePage/Template/MainPage.html", function (template) {
        // //template = _.template(data, {});
        // console.log('XXX -> ' +  template);
        // $(this.el).append(template); 
    // });    
         //console.log("HTML ->> "+ html.responseText );
     	console.log(this.template);
     	var html= $(this.template).html();
        $(this.el).html(html);
    }
}); 