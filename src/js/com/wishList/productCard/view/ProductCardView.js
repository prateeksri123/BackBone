/**
 * @author Prateek
 */
ProductCardView = Backbone.View.extend({

	tagname : 'div',

	initialize : function(event) {

	},

	handleRouting : function(e) {
	},

	registerCustomEvents : function() {
	},

	render : function(collection) {
		console.log(" test ");
		$.get("src/js/com/wishList/productCard/template/ProductCardTemplate.html", function(template) {
			
			html = _.template(template, {'collection' : collection, 'i' : 1});
			
			$('#homePageContent').html(html);
			
		});
	},
});
var productCardView = new ProductCardView(); 