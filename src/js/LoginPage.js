/**
 * @author Prateek.Srivastava
 */
(function($) {
	var reg_name, reg_pass;
	var listeClients = new UserDetailsCollection;
	var listClients = new UserDetailsCollection;
	ClientView = Backbone.View.extend({
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
            
			var tmpClient = new UserDetail({
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
			
           
		},
		login : function() {
			console.log("Login Button clicked");
			var tmplogin = new UserDetail({
				name : $("#txtIdClient").val(),
				pwd : $("#txtNomClient").val(),
			});
			
			console.log(tmplogin.get('name'));
			var ls = new Backbone.LocalStorage("store-name");
			
			result = ls.findByNameAndPassword(tmplogin);
			
			if (result != undefined) {
				window.loggedInUser = result;
				//$("#pageDiv").html("<font size=4 color=blue>Login sucessfull, Welcome " + window.loggedInUser.firstName + " " + window.loggedInUser.lastName + "!!</font>");
				console.log(window.loggedInUser.firstName + " " + window.loggedInUser.lastName);
				if (window.loggedInUser.firstName == undefined) {
					window.loggedInUser.firstName = "Guest";
				}

				if (window.loggedInUser.lastName == undefined) {
					window.loggedInUser.lastName = "User";
				}
				var homePage = new MainPageView({
					el : $("#pageDiv")
				});
				homePage.render(window.loggedInUser);

			} else {
				$("#listeClient").html("<font size=5 color=green>Login Failed, Retry</font>");
			}

            
			 listeClients.fetch({
				type : 'POST',
				model : tmplogin,
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
		updateUser: function(updatedUser) {
			var ls = new Backbone.LocalStorage("store-name");
			ls.update(updatedUser);
		}
	});
	var clientView = new ClientView({
		el : 'body'
	});

	Backbone.history.start();
})(jQuery); 