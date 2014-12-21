/**
 * @author Prateek.Srivastava
 */
WishListItem = Backbone.Model.extend({
		defaults : {
			productId : 0,
			userId : 0
		},
		initialize : function() {
			// Initialized Model
		}
	});
WishList = Backbone.Collection.extend({
		model : WishListItem,
		localStorage : new Backbone.LocalStorage("store-WishList"),
		initialize : function() {
			// Initialized Model Collection
		}
	});