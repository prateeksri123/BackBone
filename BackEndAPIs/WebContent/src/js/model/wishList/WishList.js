/**
 * @author Prateek.Srivastava
 */
WishListItem = Backbone.Model.extend({
	 url : "./rest/wishlist",
		defaults : {
			productId : 0,
			userId : 0,
			myExpectedPrice: 0
		},
		initialize : function() {
			// Initialized Model
		}
	});
WishList = Backbone.Collection.extend({
		model : WishListItem,
		//localStorage : new Backbone.LocalStorage("store-WishList"),
		url : "./rest/wishlist",
		initialize : function() {
			// Initialized Model Collection
		}
	});