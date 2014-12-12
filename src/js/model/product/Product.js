/**
 * @author Prateek.Srivastava
 */
Product = Backbone.Model.extend({
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
ProductsCollection = Backbone.Collection.extend({
		model : Product,
		localStorage : new Backbone.LocalStorage("store-product"),
		initialize : function() {
			// Initialized Model Collection
		}
	});