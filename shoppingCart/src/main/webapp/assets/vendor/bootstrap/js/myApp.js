$(function() {
	// solving the active menu
	switch(menu){
	case 'About Us':
		$("#about").addClass('active');
		break;
	case 'Contact':
		$("#contact").addClass('active');
		break;
	case 'Products':
		$("#listProducts").addClass('active');
		break;
	default:
		$("#home").addClass('active');
		break;
	}
});