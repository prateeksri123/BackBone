/**
 * @author Prateek.Srivastava
 */
(function($) {
	var reg_name, reg_pass;
	var Client = Backbone.Model.extend({
		defaults : {
			name : null,
			pwd : null
		},
		initialize : function() {
			// Initialized Model
		}
	});
	var ClientsCollection = Backbone.Collection.extend({
		model : Client,
		localStorage : new Backbone.LocalStorage("store-name"),
		initialize : function() {
			// Initialized Model Collection
		}
	});

	var listeClients = new ClientsCollection;
	var listClients = new ClientsCollection;
	var ClientView = Backbone.View.extend({
		events : {
			'click #cmdAddClient' : 'cmdAddClient_Click',
			'click #login' : 'login'
		},

		initialize : function() {
			// Initialized View.
			var that = this;
		},

		cmdAddClient_Click : function() {
            console.log("Register Clicked");
			var tmpClient = new Client({
				name : $("#txtIdClient").val(),
				pwd : $("#txtNomClient").val(),
			});
			listeClients.create(tmpClient);
            $("#listeClient").html("<font size=5 color=green>User " +  tmpClient.get("name") + " is Successfully Registered, Now you can Login</font>");
		},
		login : function() {
			console.log("Login Button clicked");
			var tmplogin = new Client({
				name : $("#txtIdClient").val(),
				pwd : $("#txtNomClient").val(),
			});
			console.log(tmplogin.get('name'));

			listeClients.fetch({
				type : 'POST',
				headers : {
					'userName' : $('#txtIdClient').val(),
					password : $('#txtNomClient').val()
				},
				success : function(sessionToken, response) {
					console.log(response);
					for ( i = 0; i < response.length; i++) {
						if (response[i].name == tmplogin.get('name') && response[i].pwd == tmplogin.get('pwd')) {
							window.location.hash = "/#mainPage";
							$("#divClient").html("<font size=4 color=blue>Login sucessfull</font>");
							break;
						}
					}
					if (i == response.length) {
						$("#listeClient").html("<font size=5 color=green>Login Failed, Retry</font>");
					}

				},
				error : function(sessionToken, response) {
					console.log('login failed');
					alert('No user exist, Please register and then login');
					$("#listeClient").html("<font size=5 color=green>Failed Logged in, Retry</font>");
				}
			});
		},
		addClientToList : function(model) {
			console.log('addClientToList');
			reg_name = model.get('name');
			reg_pass = model.get('pwd');
			
		},
	});
	var clientView = new ClientView({
		el : $("#divClient")
	});

	Backbone.history.start();
})(jQuery); 