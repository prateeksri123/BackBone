/**
 * @author Prateek.Srivastava
 */

UserDetail = Backbone.Model.extend({
	    url : "./rest/user",
		defaults : {
	        userName : null,
	        password : null,
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
		url : "./rest/user",
		initialize : function() {
			// Initialized Model Collection
		}
	});
