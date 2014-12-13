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
			console.log("template -> " + template);
			/*$.each(collection.models, function(i, item) {
				console.log(i + "  " + item.get('productTitle'));
			});*/
			html = _.template(template, {'collection' : collection});
			$('#homePageContent').html(html);
		});
	},
});
var productCardView = new ProductCardView(); 