var HomePageRouter = Backbone.Router.extend({
        routes: {
            "displayUser": "displayuser" // matches http://example.com/#anything-here
        },
        
        displayuser:function() {
        	alert("displayUser");
        }
    });