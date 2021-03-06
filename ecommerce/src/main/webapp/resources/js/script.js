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
	case 'Cart':
		$('#profileDropDown').addClass('active');
		break;
	default:
		$('#home').addClass('active');
	}

	// for csrf
	var token = $('meta[name="_csrf"]').attr('content');
	var header = $('meta[name="_csrf_header"]').attr('content');

	if (token.length > 0 && header.length > 0) {
		// set the csrf token and header for ajax request
		$(document).ajaxSend(function(event, xhr, options) {
			xhr.setRequestHeader(header, token);
		});
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
											+ '/product" class="btn btn-info btn-sm"><i class="fa fa-eye"></i></a> &nbsp; &nbsp;';
									if (window.userRole == 'USER') {
										if (row.quantity < 1)
											urlString += '<a href="javascript: void(0)" class="btn btn-primary btn-sm"><i class="fa fa-shopping-cart"></i></a>';
										else
											urlString += '<a href="'
													+ window.contextRoot
													+ '/cart/add/'
													+ id
													+ '/product" class="btn btn-primary btn-sm"><i class="fa fa-shopping-cart"></i></a>';
									} else if (window.userRole == 'ADMIN')
										urlString += '<a href="'
												+ window.contextRoot
												+ '/manage/'
												+ id
												+ '/product" class="btn btn-warning btn-sm"><i class="fa fa-edit"></i></a>';
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
											+ '/product" class="btn btn-warning btn-sm"><i class="fa fa-pencil-square-o"></i></a>';
									urlString += '&#160; &#160;<a href="'
											+ window.contextRoot
											+ '/manage/delete/'
											+ id
											+ '/product" class="btn btn-denger btn-sm"><i class="fa fa-trash-o"></i></a>';
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
																var activationUrl = window.contextRoot
																		+ '/manage/product/'
																		+ value
																		+ '/activation';
																$
																		.post(
																				activationUrl,
																				function(
																						data) {
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

	$('#addCategoryButton').on('click', function() {
		$('#categoryModal').modal({
			backdrop : 'static',
			keyboard : false
		});
	});

	$categoryForm = $('#categoryForm');
	if ($categoryForm.length) {
		$categoryForm
				.validate({
					rules : {
						name : {
							required : true,
							minlength : 2
						},
						description : {
							required : true
						}
					},
					messages : {
						name : {
							required : 'Please add the category name!',
							minlength : 'length of name should not be less than 2 characters'
						},
						description : {
							required : 'Please add description!'
						}
					},
					errorElement : 'em',
					errorPlacement : function(error, element) {
						error.addClass('help-block');
						error.insertAfter(element);
					}
				});
	}

	$loginForm = $('#loginForm');
	if ($loginForm.length) {
		$loginForm.validate({
			rules : {
				username : {
					required : true,
					email : true
				},
				password : {
					required : true
				}
			},
			messages : {
				username : {
					required : 'Please enter username!',
					email : 'please enter valid email'
				},
				password : {
					required : 'Please enter password!'
				}
			},
			errorElement : 'em',
			errorPlacement : function(error, element) {
				error.addClass('help-block');
				error.insertAfter(element);
			}
		});
	}

	// handling click event of refresh cart
	$('button[name="refreshCart"]')
			.click(
					function() {
						var cartLineId = $(this).attr('value');
						var countElement = $('#count_' + cartLineId);
						var originalCount = countElement.attr('value');
						var currentCount = countElement.val();
						// work only when count has changed
						if (currentCount !== originalCount) {
							// validate new count
							if (currentCount < 1 || currentCount > 3) {
								countElement.val(originalCount);
								bootbox
										.alert({
											size : 'medium',
											title : 'Error!',
											message : 'Product Count should be between 1 and 3 !'
										})
							} else {
								var updateUrl = window.contextRoot + '/cart/'
										+ cartLineId + '/update?count='
										+ currentCount;
								//forward it to the controller
								window.location.href = updateUrl;
								
							}
						}
					});
});