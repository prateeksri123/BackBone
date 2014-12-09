/**
 * @author Prateek.Srivastava
 */
WishListItem = Backbone.Model.extend({
		defaults : {
			itemName : "",
			Brand : "",
			modelName : "",
			expectedPrice : 0,
			userId : 0
		},
		initialize : function() {
			// Initialized Model
		}
	});
WishList = Backbone.Collection.extend({
		model : WishListItem,
		localStorage : new Backbone.LocalStorage("WishList"),
		initialize : function() {
			// Initialized Model Collection
		}
	});