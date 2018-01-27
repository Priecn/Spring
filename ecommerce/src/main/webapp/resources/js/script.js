$(function() {
	switch (menu) {
	case 'About Us':
		$('#about').addClass('active');
		break;
	case 'Contact Us':
		$('#contact').addClass('active');
		break;
	case 'All Products':
		$('#listProducts').addClass('active');
		$('#cat_' + menu).addClass('active');
		break;
	case 'Manage Products':
		$('#manageProducts').addClass('active');
		break;
	default:
		$('#home').addClass('active');
	}

	var $table = $('#productListTable');

	if ($table.length) {

		var jsonUrl = '';
		if (window.categoryId == '') {
			jsonUrl = window.contextRoot + '/json/data/all/products';
		} else {
			jsonUrl = window.contextRoot + '/json/data/category/'
					+ window.categoryId + '/products';
		}

		$table
				.DataTable({
					lengthMenu : [ [ 5, 10, -1 ],
							[ '5 Products', '10 Products', 'All Products' ] ],
					pageLength : 5,
					ajax : {
						url : jsonUrl,
						dataSrc : ''
					},
					columns : [
							{
								data : 'code',
								mRender : function(code, type, row) {
									return '<img src="' + window.contextRoot
											+ '/resources/images/' + code
											+ '.jpg" class="dataTableImg"/>';
								}
							},
							{
								data : 'name'
							},
							{
								data : 'brand'
							},
							{
								data : 'unitPrice',
								mRender : function(price, type, row) {
									return '&#8377; ' + price;
								}
							},
							{
								data : 'quantity',
								mRender : function(quantity, type, row) {
									if (quantity < 1)
										return '<span style="color: red;">Out of Stock!</span>';
									return quantity;
								}
							},
							{
								data : 'id',
								mRender : function(id, type, row) {
									var urlString = '';
									urlString += '<a href="'
											+ window.contextRoot
											+ '/show/'
											+ id
											+ '/product"><i class="fa fa-eye"></i></a> &nbsp; &nbsp;';
									if (row.quantity < 1)
										urlString += '<a href="javascript: void(0)"><i class="fa fa-shopping-cart"></i></a>';
									else
										urlString += '<a href="'
												+ window.contextRoot
												+ '/cart/add/'
												+ id
												+ '/product"><i class="fa fa-shopping-cart"></i></a>';
									return urlString;
								},
								bSortable : false
							} ]
				});
	}

	// dismissing the alert after 3 seconds
	var $alert = $('.alert');
	if ($alert.length) {
		setTimeout(function() {
			$alert.fadeOut('slow');
		}, 3000);
	}

	// ADMIN DATA TABLE

	var $adminTable = $('#adminProductsTable');

	if ($adminTable.length) {

		var jsonUrl = '';
		jsonUrl = window.contextRoot + '/json/data/admin/all/products';

		$adminTable
				.DataTable({
					lengthMenu : [ [ 5, 10, -1 ],
							[ '5 Products', '10 Products', 'All Products' ] ],
					pageLength : 5,
					ajax : {
						url : jsonUrl,
						dataSrc : ''
					},
					columns : [
							{
								data : 'id'
							},
							{
								data : 'code',
								mRender : function(code, type, row) {
									return '<img src="'
											+ window.contextRoot
											+ '/resources/images/'
											+ code
											+ '.jpg" class="adminDataTableImg" alt="'
											+ row.name + '"/>';
								}
							},
							{
								data : 'name'
							},
							{
								data : 'quantity',
								mRender : function(quantity, type, row) {
									if (quantity < 1)
										return '<span style="color: red;">Out of Stock!</span>';
									return quantity;
								}
							},
							{
								data : 'unitPrice',
								mRender : function(price, type, row) {
									return '&#8377; ' + price;
								}
							},
							{
								data : 'active',
								mRender : function(active, type, row) {
									returnStr = '<label class="switch"><input type="checkbox" value="'
											+ row.id + '"';
									if (active)
										returnStr += 'checked="checked"';
									returnStr += ' /><div class="slider round"></div></label>';
									return returnStr;
								}
							},
							{
								data : 'id',
								mRender : function(id, type, row) {
									var urlString = '';
									urlString += '<a href="'
											+ window.contextRoot
											+ '/manage/'
											+ id
											+ '/product"><i class="fa fa-pencil-square-o"></i></a>';
									urlString += '&#160; &#160;<a href="'
										+ window.contextRoot
										+ '/manage/delete/'
										+ id
										+ '/product"><i class="fa fa-trash-o"></i></a>';
									return urlString;
								},
								bSortable : false
							} ],
					initComplete : function() {
						var api = this.api();
						api
								.$('.switch input[type=checkbox]')
								.on(
										'change',
										function() {
											var checkbox = $(this);
											console.log("checkbox");
											var checked = checkbox
													.prop('checked');
											var dMsg = (checked) ? 'You want to activate product?'
													: 'You want to deactivate product?';
											var value = checkbox.prop('value');
											bootbox
													.confirm({
														size : 'medium',
														title : 'Product activation / deactivation',
														message : dMsg,
														callback : function(
																confirmed) {
															if (confirmed) {
																console
																		.log(value);
																var activationUrl = window.contextRoot+'/manage/product/'+value+'/activation';
																$.post(activationUrl, function(data){
																	bootbox
																	.alert({
																		size : 'medium',
																		title : 'Information',
																		message : data
																	});
																})
																
															} else {
																checkbox
																		.prop(
																				'checked',
																				!checked);
															}
														}
													});
										});
					}
				});
	}
	
	$('#addCategoryButton').on('click', function(){
		$('#categoryModal').modal({
		    backdrop: 'static',
		    keyboard: false
		});
	});
	
	$categoryForm = $('#categoryForm');
	if($categoryForm.length){
		$categoryForm.validate({
			rules: {
				name: {
					required: true,
					minlength: 2
				},
				description: {
					required: true
				}
			},
			messages: {
				name: {
					required: 'Please add the category name!',
					minlength: 'length of name should not be less than 2 characters'
				},
				description: {
					required: 'Please add description!'
				}
			},
			errorElement: 'em',
			errorPlacement: function(error, element){
				error.addClass('help-block');
				error.insertAfter(element);
			}
		});
	}
});