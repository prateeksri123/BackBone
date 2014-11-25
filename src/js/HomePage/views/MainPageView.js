MainPageView = Backbone.View.extend({

    //template: templates.SubmissionItemViewTemplate,
    
    template : '#mainPageTemplate',
    // wrap the view with `tr` instead of `div`
    tagName : 'div',

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
     	var html= _.template($('#mainPageTemplate'));
      $(this.el).append(html);
    }
}); 