/**
 * @author Prateek.Srivastava
 */
(function($) {
	var reg_name, reg_pass;
	
	var Client = Backbone.Model.extend({
		defaults : {
			name : null,
			pwd : null,
			firstName : "",
			lastName : "",
			email : ""
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
	var loggedInUser = new Client;
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
		render: function() {
			
		},


		cmdAddClient_Click : function() {
			console.log("Register Clicked");
            
			var tmpClient = new Client({
				name : $("#txtUserName").val(),
				pwd : $("#txtPassword").val(),
				firstName : $("#txtFirstName").val(),
			    lastName : $("#txtLastName").val(),
			    email : $("#txtEmail").val()
			});
			if(tmpClient.get('name') != "" && tmpClient.get('pwd') != "" ){
				listeClients.create(tmpClient);
                 $("#listeClient").html("<font size=5 color=green>User " +  tmpClient.get("name") + " is Successfully Registered, Now you can Login</font>");
			}
			 var homePage = new MainPageView();
			 homePage.render();
           
		},
		login : function() {
			console.log("Login Button clicked");
			var tmplogin = new Client({
				name : $("#txtIdClient").val(),
				pwd : $("#txtNomClient").val(),
			});
			
			console.log(tmplogin.get('name'));
			var ls = new Backbone.LocalStorage("store-name");
			result = ls.findByNameAndPassword(tmplogin);
			if (result != undefined) {
				loggedInUser = result;
				$("#divClient").html("<font size=4 color=blue>Login sucessfull, Welcome " + loggedInUser.firstName + " " + loggedInUser.lastName + "!!</font>");

			} else {
				$("#listeClient").html("<font size=5 color=green>Login Failed, Retry</font>");
			}

            var homePage = new MainPageView({el:'body'});
			 homePage.render();
				
			/* listeClients.fetch({
				type : 'POST',
				model : tmplogin,
				success : function(sessionToken, response) {
					console.log(response);
					for ( i = 0; i < response.length; i++) {
						if (response[i].name == tmplogin.get('name') && response[i].pwd == tmplogin.get('pwd')) {
							window.location.hash = "/#mainPage";
							loggedInUser = response[i];
							$("#divClient").html("<font size=4 color=blue>Login sucessfull, Welcome " +  loggedInUser.firstName + " " + loggedInUser.lastName + "!!</font>");
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
			});*/
		},
		addClientToList : function(model) {
			console.log('addClientToList');
			reg_name = model.get('name');
			reg_pass = model.get('pwd');
			
		},
	});
	var clientView = new ClientView({
		el : 'body'
	});

	Backbone.history.start();
})(jQuery); 