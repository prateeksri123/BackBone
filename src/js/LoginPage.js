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
        localStorage: new Backbone.LocalStorage("store-name"),
        initialize: function () {
            console.log("initialize clients collection");
            //this.bind("add", function (model) { console.log("Add", model.get('id'), model); });
            //this.bind("remove", function (el) { console.log("Remove", el.get('id'), el); });
            console.log("initialized");
        }
    });
    
 //var storeVar = localStorage.getItem("store-name");
 //console.log("Store Var " + storeVar);
 var listeClients = new ClientsCollection;
 var listClients = new ClientsCollection;
 //listeClients.reset(storeVar);
 //listClients.reset(storeVar);
    var ClientView = Backbone.View.extend({
        events: {
            'click #cmdAddClient': 'cmdAddClient_Click',
            'click #login': 'login'
        },
        
        initialize: function () {
            var that = this;
            //this.listeClients = new ClientsCollection();
            //this.listClients = new ClientsCollection();
            
             console.log("view initialize complete");
        },
       
        
        cmdAddClient_Click: function () {
        	 console.log("Register button clicked");
            var tmpClient = new Client({
                name: $("#txtIdClient").val(),
                pwd: $("#txtNomClient").val(),
            });
            listeClients.create(tmpClient);
            //this.listeClients.fetch();
            console.log('addClientToList 3');
        },
        login: function () {
        	 console.log("Login Button clicked");
            var tmplogin = new Client({
                name: $("#txtIdClient").val(),
                pwd: $("#txtNomClient").val(),
            });
            console.log(tmplogin.get('name'));
           // listClients.add(tmplogin);
            listeClients.fetch( {
            	type: 'POST',													
				headers: { 'userName':  $('#txtIdClient').val(), password : $('#txtNomClient').val()},
				success: function(sessionToken, response) {		
					console.log(response);
					for (i = 0; i < response.length; i++) {
						console.log(response[i].name + " " + tmplogin.get('name') + " " + response[i].pwd + " " + tmplogin.get('pwd'));
					 if (response[i].name == tmplogin.get('name') && response[i].pwd == tmplogin.get('pwd')) {
                        window.location.hash = "/#mainPage";
					    $("#divClient").html("<font size=4 color=blue>Login sucessfull</font>");
					    break;
                     }
					}
					if(i == response.length) {
						$("#listeClient").html("<font size=5 color=green>Failed Logged in, Retry</font>");
					}
					
				},error: function(sessionToken, response) {									
					console.log('login failed');			
					alert('login failed');
					 $("#listeClient").html("<font size=5 color=green>Failed Logged in, Retry</font>");
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
               
            }
            else {
               
            }
        }
    });
    var clientView = new ClientView({ el: $("#divClient") });
    //clientView.render();
    Backbone.history.start();
})(jQuery);