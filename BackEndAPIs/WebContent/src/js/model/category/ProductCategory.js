/**
 * @author Prateek.Srivastava
 */
ProductCategory = Backbone.Model.extend({
		defaults : {
			category_name : null,
			url : ""
		},
		initialize : function() {
			// Initialized Model
		}
	});
ProductCategorysCollection = Backbone.Collection.extend({
		model : ProductCategory,
		localStorage : new Backbone.LocalStorage("store-product-Category"),
		initialize : function() {
			// Initialized Model Collection
		}
	});