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

	render : function(model) {
		console.log(" test " + model.get('title'));
		;
		$.get("src/js/com/wishList/productCard/template/ProductCardTemplate.html", function(data) {
			template = _.template(data, {'model' : model});
			$('#homePageContent').html(template);
		});
	},
});
var productCardView = new ProductCardView(); 