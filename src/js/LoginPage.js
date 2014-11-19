/**
 * @author Prateek.Srivastava
 */
(function($){
 var reg_name, reg_pass;
    var Client = Backbone.Model.extend({
        defaults: {
            name: null,
            pwd: null
        },
        initialize: function () {
            console.log("initialize client");
        }
    });
    var ClientsCollection = Backbone.Collection.extend({
        model: Client,
        url:'/rest/Session',
        initialize: function () {
            console.log("initialize clients collection");
            //this.bind("add", function (model) { console.log("Add", model.get('id'), model); });
            //this.bind("remove", function (el) { console.log("Remove", el.get('id'), el); });
            console.log("initialized");
        }
    });
    var ClientView = Backbone.View.extend({
        el: 'body', /* Utilisation de zepto pour lier ClientView au DOM */
        initialize: function () {
            var that = this;
            this.listeClients = new ClientsCollection();
            this.listClients = new ClientsCollection();
            this.listeClients.bind("add", function (model) {
                that.addClientToList(model);
            });
            this.listClients.bind("add", function (model) {
                that.addLoginToList(model);
            });
             console.log("event 1");
        },
       
        events: {
            'click #cmdAddClient': 'cmdAddClient_Click',
            'click #login': 'login'
        },
        cmdAddClient_Click: function () {
        	 console.log("event 2");
            var tmpClient = new Client({
                name: $("#txtIdClient").val(),
                pwd: $("#txtNomClient").val(),
            });
            this.listeClients.add(tmpClient);
            //this.listeClients.fetch();
            console.log('addClientToList 3');
        },
        login: function () {
        	 console.log("event 3");
            var tmplogin = new Client({
                name: $("#txtIdClient").val(),
                pwd: $("#txtNomClient").val(),
            });
            console.log(tmplogin.get('name'));
            this.listClients.add(tmplogin);
            this.listeClients.fetch( {
            	type: 'POST',													
				headers: { 'userName':  $('#txtIdClient').val(), password : $('#txtNomClient').val()},
				success: function(sessionToken, response) {		
					console.log(response.payload);
					window.location.hash = "/#mainPage";
				},error: function(sessionToken, response) {									
					console.log('login failed');			
					alert('login failed');
				}
            });
        },
        addClientToList: function (model) {
        	console.log('addClientToList');
            reg_name = model.get('name');
            reg_pass = model.get('pwd');
            $("#listeClient").html("<font size=5 color=green>You are Successfully Registered, Now you can Login</font>");
           console.log('addClientToList ends'); 
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