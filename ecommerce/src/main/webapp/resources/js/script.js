$(function(){
	switch(menu){
	case 'About Us':
		$('#about').addClass('active');
		break;
	case 'Contact Us':
		$('#contact').addClass('active');
		break;
	case 'All Products':
		$('#listProducts').addClass('active');
		$('#cat_'+menu).addClass('active');
		break;
	default:
		$('#home').addClass('active');
	}
});

var $table = $('#productListTable');

if($table.length){
	
	var jsonUrl = '';
	if(window.categoryId == ''){
		jsonUrl = window.contextRoot + '/json/data/all/products';
	} else{
		jsonUrl = window.contextRoot + '/json/data/category/'+window.categoryId+'/products';
	}
	
	$table.DataTable({
		lengthMenu: [[5, 10, -1], ['5 Products', '10 Products', 'All Products']],
		pageLength: 5,
		ajax: {
			url: jsonUrl,
			dataSrc: ''
		},
		columns: [
			{
				data: 'code',
				mRender: function(code, type, row){
					return '<img src="'+window.contextRoot+'/resources/images/'+code+'.jpg" class="dataTableImg"/>';
				}
			},
			{
				data: 'name'
			},
			{
				data: 'brand'
			},
			{
				data: 'unitPrice',
				mRender: function(price, type, row){
					return '&#8377; '+ price;
				}
			},
			{
				data: 'quantity',
				mRender: function(quantity, type, row){
					if(quantity < 1)
						return '<span style="color: red;">Out of Stock!</span>';
					return quantity;
				}
			},
			{
				data: 'id',
				mRender: function(id, type, row){
					var urlString = '';
					urlString += '<a href="'+window.contextRoot+'/show/'+id+'/product"><i class="fa fa-eye"></i></a> &nbsp; &nbsp;';
					if(row.quantity < 1)
						urlString += '<a href="javascript: void(0)"><i class="fa fa-shopping-cart"></i></a>';
					else
						urlString += '<a href="'+window.contextRoot+'/cart/add/'+id+'/product"><i class="fa fa-shopping-cart"></i></a>';
					return urlString;
				},
				bSortable: false
			}
		]
	});
}
