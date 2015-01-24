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
		//$('#addWishList').click(productCardView.addToWishList);
		$("#thumbnail").on("click", ".addWishList", function (e) {
         alert("a");
         });
		$('#myModal').on('show.bs.modal', function (event) {
			  var button = $(event.currentTarget) // Button that triggered the modal
			  var recipient = $(event.relatedTarget).data('productid'); // Extract info from data-* attributes
			  // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
			  // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
			  var modal = $(this)
			  modal.find('.modal-title').text('Add to the Wishlist ');
			  modal.find('.modal-body #product').val($(event.relatedTarget).data('producttitle'));
			  document.getElementById('addWishList').dataset.productid = recipient;
			  
			})
	},

	render : function(collection,displayingWishList) {
		console.log(" test ");
		$.get("src/js/com/wishList/productCard/template/ProductCardTemplate.html", function(template) {

			html = _.template(template, {
				'collection' : collection,
				'i' : 1,
				'displayingWishList' : displayingWishList
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
var productCardView = new ProductCardView();
