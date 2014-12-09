/**
 * @author Prateek.Srivastava
 */

UserDetail = Backbone.Model.extend({
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
		localStorage : new Backbone.LocalStorage("store-name"),
		initialize : function() {
			// Initialized Model Collection
		}
	});
