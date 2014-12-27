/**
 * @author Prateek.Srivastava
 */

UserDetail = Backbone.Model.extend({
	    url : "http://localhost:8080/JavaRESTExample/rest/user",
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
UserDetailsCollection = Backbone.Collection.extend({
		model : UserDetail,
		//localStorage : new Backbone.LocalStorage("store-name"),
		url : "http://localhost:8080/JavaRESTExample/rest/user",
		initialize : function() {
			// Initialized Model Collection
		}
	});
