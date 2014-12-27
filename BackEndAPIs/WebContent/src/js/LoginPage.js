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
			
			/*$.get("http://localhost:8080/JavaRESTExample/rest/user", function (data) {
				console.log("Login Response" +data);
				if (data == "true") {
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
			});*/
			
			var tmplogin = new UserDetail({
				name : $("#txtIdClient").val(),
				pwd : $("#txtNomClient").val()
			});
			tmplogin.fetch({
				success : function (collection, response) {
				console.log("Login Response" +response);
				if (response != undefined) {
					window.loggedInUser = response;
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
					 $('#loadingBar').modal('hide');
				}
			},
			error : function (collection, response) {
				console.log("login failed");
				$("#listeClient").html("<font size=5 color=green>Login Failed, Retry</font>");
				 $('#loadingBar').modal('hide');
			}

			});
			console.log(tmplogin.get('name'));
			//var ls = new Backbone.LocalStorage("store-name");
			
			//result = ls.findByNameAndPassword(tmplogin);
			

    
            
			 listeClients.fetch({
				success : function (collection, response) {
					console.log("Login Response" +response);
					if (response != undefined) {
						window.loggedInUser = response;
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
						 $('#loadingBar').modal('hide');
					}
				},
				error : function (collection, response) {
					console.log("login failed");
					$("#listeClient").html("<font size=5 color=green>Login Failed, Retry</font>");
					 $('#loadingBar').modal('hide');
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