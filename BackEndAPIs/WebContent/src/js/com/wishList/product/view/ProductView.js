/**
 * @author Prateek
 */
ProductView = Backbone.View.extend({

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
					
	},

	render : function(product) {
		console.log(" test ");
		$.get("src/js/com/wishList/productCard/template/ProductTemplate.html", function(template) {

			html = _.template(template, {
				'model' : product
			});

			$('#homePageContent').html(html);
			$('#mainContentPage').height($(window).height() - $('#footer').height() - 60);
			productCardView.registerCustomEvents();
			 $('#loadingBar').modal('hide');
			$('.pull-down').each(function() {
				$(this).css('margin-top', '0px');
			});

		});
	}

});
var productView = new ProductView();
