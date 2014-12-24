/**
 * @author Prateek.Srivastava
 */
(function($){
	var ClientView = Backbone.View.extend({
        el: 'body', /* Utilisation de zepto pour lier ClientView au DOM */
        initialize: function () {
            var that = this;
             console.log("event 1");
        },
       
        events: {
            'click #cmdAddClient': 'cmdAddClient_Click',
            'click #login': 'login'
        },
        cmdAddClient_Click: function () {
        	 console.log("event 2");
            
            this.listeClients.add(tmpClient);
            
        },
        login: function () {
        	 console.log("event 3");
        	 var name= $("#txtIdClient").val();
        	 console.log(name);
        },
        addClientToList: function (model) {
            reg_name = model.get('name');
            reg_pass = model.get('pwd');
            $("#listeClient").html("<font size=5 color=green>You are Successfully Registered, Now you can Login</font>");
        },
        addLoginToList: function (model) {  ;
        	 console.log("login added");
            if (model.get('name') == reg_name && model.get('pwd') == reg_pass) {
                $("#divClient").html("<font size=4 color=blue>Login sucessfull</font>");
            }
            else {
                $("#listeClient").html("<font size=5 color=green>Failed Logged in, Retry</font>");
            }
        }
    });
    var clientView = new ClientView();
    Backbone.history.start();
})(jQuery);