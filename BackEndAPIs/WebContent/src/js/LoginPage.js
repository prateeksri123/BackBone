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
            var tmpUser = new UserDetail();
            tmpUser.set({
            	userName : $("#txtUserName").val(),
            	password : $("#txtPassword").val(),
				firstName : $("#txtFirstName").val(),
			    lastName : $("#txtLastName").val(),
			    email : $("#txtEmail").val()
			});
			
			if (tmpUser.isNew()) {
				var self = this;
				listeClients.create(tmpUser, {
					success: function() {
						app.navigate('wines/'+self.model.id, false);
					}
				});
			} else {
				tmpUser.save();
			}
			//if(tmpClient.get('name') != "" && tmpClient.get('pwd') != "" ){
				//listeClients.create(tmpClient);
                // $("#listeClient").html("<font size=5 color=green>User " +  tmpClient.get("name") + " is Successfully Registered, Now you can Login</font>");
			//}
			
           
		},
		login : function() {
			console.log("Login Button clicked");
			
			var userName = $("#txtIdClient").val();
			var pwd  = $("#txtNomClient").val();
			$.get("http://localhost:8080/JavaRESTExample/rest/user?userName=" + userName + "&pwd=" + pwd, function (data) {
				console.log("Login Response" +data);
				if (data != undefined) {
					
					var tmplogin = new UserDetail();
					window.loggedInUser = data;
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
			});
			
			
			/*tmplogin.fetch({
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

			},"json");
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
			});*/
		
		},
		addClientToList : function(model) {
			console.log('addClientToList');
			reg_name = model.get('name');
			reg_pass = model.get('pwd');
			
		},
		updateUser: function(updatedUser) {
			var tmpUserUpdation = new UserDetail();
			tmpUserUpdation.set({
            	userName : updatedUser.userName,
            	password : updatedUser.password,
				firstName :updatedUser.firstName,
			    lastName : updatedUser.lastName,
			    email : updatedUser.email,
			    userId : updatedUser.userId
			});
			tmpUserUpdation.save();
		}
	});
	var clientView = new ClientView({
		el : 'body'
	});

	Backbone.history.start();
})(jQuery); 