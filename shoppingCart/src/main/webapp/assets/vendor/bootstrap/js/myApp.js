$(function() {
	// solving the active menu
	switch (menu) {
	case 'About Us':
		$("#about").addClass('active');
		break;
	case 'Contact':
		$("#contact").addClass('active');
		break;
	case 'All Products':
		$("#listProducts").addClass('active');
		break;
	case 'Home':
		$("#home").addClass('active');
		break;
	default:
		$("#listProducts").addClass('active');
		$("#a_" + menu).addClass("active");
		break;
	}
});

// code for datatable
products =[
	[1,"ABC",'<span class="glyphicon glyphicon-eye-open"></span>'],
	[2,"XYZ", '<a href="/show/data/product" class="btn btn-primary">'+
		'<span class="glyphicon glyphicon-eye-open"></span></a>'+
		'<a href="/cart/add/data/product" class="btn btn-success">'+
		'<span class="glyphicon glyphicon-shopping-cart"></span></a>']
];
var $table = $('#productListTable');

// execute only if we hav table
if ($table.length) {
	// console.log('Inside the table!');

	var jsonUrl = '';
	if (window.categoryId == '') {
		jsonUrl = window.contextRoot + '/json/data/all/products';
	} else {
		jsonUrl = window.contextRoot + '/json/data/category/'
				+ window.categoryId + '/products';
	}

	$table.DataTable(
			{data: products}
			/*{
		lengthMenu : [ [ 3, 5, 10, -1 ],
				[ '3 Records', '5 Records', '10 Records', 'ALL' ] ],
		pageLength : 5,
		ajax : {
			url : jsonUrl,
			dataSrc : ''
		},
		columns : [ {
			data : 'name',
		}, {
			data : 'brand'
		}, {
			data : 'unitPrice',
			mRender: function(data, type, row){
				return '&#8377; '+data
			}
		}, {
			data : 'quantity'
		}, {
			data: 'id',
			mRender: function(data, type, row){
				return '<a href="'+window.contextRoot+'/show/'+data+'/product" class="btn btm-primary">'+
				'<span class="glyphicon glyphicon-eye-open"></span></a>'+
					'<a href="'+window.contextRoot+'/cart/add/'+data+'/product" class="btn btm-success">'+
					'<span class="glyphicon glyphicon-shopping-cart"></span></a>'
					
			}
		} ]
	}*/);
}