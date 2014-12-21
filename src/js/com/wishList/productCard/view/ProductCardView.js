/**
 * @author Prateek
 */
ProductCardView = Backbone.View.extend({

	tagname : 'div',

	initialize : function(event) {

	},
	
	events : {
        "click a#addWishList" : "handleRouting"
    },

	handleRouting : function(e) {
		alert("added");
	},

	registerCustomEvents : function() {
		$('#addWishList').click(productCardView.addToWishList);
		$("#thumbnail").on("click", ".addWishList", function (e) {
         alert("a");
         });
	},

	render : function(collection) {
		console.log(" test ");
		$.get("src/js/com/wishList/productCard/template/ProductCardTemplate.html", function(template) {

			html = _.template(template, {
				'collection' : collection,
				'i' : 1
			});

			$('#homePageContent').html(html);
			productCardView.registerCustomEvents();
			$('.pull-down').each(function() {
				$(this).css('margin-top', '0px');
			});

		});
	},
	
	
	
	addToWishList: function(e) {
		alert(' added 1');
		alert($(e.currentTarget).data('url'));
	}
});
var productCardView = new ProductCardView();
