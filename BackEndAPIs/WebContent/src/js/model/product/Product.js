/**
 * @author Prateek.Srivastava
 */
Product = Backbone.Model.extend({
		defaults : {
			productTitle : "",
			productDescription : "",
			imageUrls : "",
			maximumRetailPrice : "",
			sellingPrice : "",
			productUrl : "",
			productBrand : "",
			inStock : false,
			codAvailable : false,
			emiAvailable : false,
			discountPercentage : 0,
			cashBack : null,
			size : "",
			color : "",
			sizeUnit : "",
			sizeVariants: [],
			colorVariants: [],
			id: ""
		},
		initialize : function() {
			// Initialized Model
		}
	});
ProductsCollection = Backbone.Collection.extend({
		model : Product,
		//localStorage : new Backbone.LocalStorage("store-product"),
		initialize : function() {
			// Initialized Model Collection
		}
	});