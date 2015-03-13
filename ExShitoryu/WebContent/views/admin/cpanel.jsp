<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="t" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>.: Shitoryu :.</title>
		
        <spring:url value="/resources/js" var="js_url" />
        <spring:url value="/resources/jquery" var="jquery_url" />
        <spring:url value="/resources/jquery-ui" var="ui_url" />
        <spring:url value="/resources/jquery-menu" var="menu_url" />
        <spring:url value="/resources/jquery-datatable" var="datatable_url" />
        <spring:url value="/resources/jquery-validation" var="validation_url" />
        <spring:url value="/resources/jtable" var="jtable_url" />
        <spring:url value="/resources/css" var="css_url" />
        
        <spring:url value="/cats/list" var="cats_list_url" />
        <spring:url value="/cats/options" var="cats_options_url" />
        <spring:url value="/cats/subList" var="sub_list_url" />
        <spring:url value="/cats/save" var="cats_save_url" />
        <spring:url value="/cats/subSave" var="sub_save_url" />
        <spring:url value="/cats/del" var="cats_del_url" />
        <spring:url value="/cats/subDel" var="sub_del_url" />
        <spring:url value="/cats/details" var="sub_details_url" />
        
        <link href="${css_url}/style.css" rel="stylesheet" type="text/css"/>
        <link href="${ui_url}/jquery-ui.min.css" rel="stylesheet" type="text/css"/>
        <link href="${ui_url}/jquery-ui.structure.min.css" rel="stylesheet" type="text/css"/>
        <link href="${ui_url}/jquery-ui.theme.min.css" rel="stylesheet" type="text/css"/>
        <%-- <link href="${datatable_url}/dataTables.jqueryui.css" rel="stylesheet" type="text/css"/> --%>
        <link href="${menu_url}/menu.css" rel="stylesheet" type="text/css"/>
        <link href="${jtable_url}/themes/metro/darkgray/jtable.css" rel="stylesheet" type="text/css" />
        <link href="${jtable_url}/themes/metro/darkgray/jtable.min.css" rel="stylesheet" type="text/css" />
        <link href="${validation_url}/css/validationEngine.jquery.css" rel="stylesheet" type="text/css" />
        
        <style type="text/css">
        	#dialogBox {
	        	display: none;
	        }
			* { margin:0;
			    padding:0;
			}
			body { background:rgb(74,81,85); }
			div#menu { margin:5px auto; }
			div#copyright {
			    font:11px 'Trebuchet MS';
			    color:#222;
			    text-indent:30px;
			    padding:40px 0 0 0;
			}
			div#copyright a { color:#eee; }
			div#copyright a:hover { color:#222; }
			
			.child-opener-image {
				opacity: 0.5;
				cursor: pointer;
			}
			.child-opener-image:hover {
				opacity: 1;
				cursor: pointer;
			}
			.jtable-child-table-container {
				padding: 15px 15px 15px 15px !important;
				background:rgb(74,81,85);
			}
        </style>
        
        <script type="text/javascript" src="${jquery_url}/jquery-1.11.1.min.js"></script>
        <script type="text/javascript" src="${ui_url}/jquery-ui.min.js"></script>
        <script type="text/javascript" src="${menu_url}/menu.js"></script>
        <%-- <script type="text/javascript" src="${datatable_url}/jquery.dataTables.min.js"></script>
        <script type="text/javascript" src="${datatable_url}/dataTables.jqueryui.js"></script> --%>
        <script src="${jtable_url}/jquery.jtable.min.js" type="text/javascript"></script>
        <script src="${validation_url}/jquery.validationEngine.js" type="text/javascript"></script>
        <script src="${validation_url}/languages/jquery.validationEngine-en.js" type="text/javascript"></script>
        <script src="${validation_url}/languages/jquery.validationEngine-en.js" type="text/javascript"></script>
        
        <script type="text/javascript">
		    $(document).ready(function () {
		    	Categories.load();
		    });
		    
		    Categories = {
		    	load : function() {
		    		$('#tbCats').jtable({
			            title: 'Categories',
			            //paging: true, //Enable paging
			            //pageSize: 10, //Set page size (default: 10)
			            //sorting: true, //Enable sorting
			            //defaultSorting: 'type ASC', //Set default sorting
			            //selecting: true, //Enable selecting
			            //multiselect: true, //Allow multiple selecting
			            //selectingCheckboxes: true, //Show checkboxes on first column
			            columnResizable: true,
			            openChildAsAccordion: true,
			            actions: {
			                listAction: '${cats_list_url}',
			                createAction: '${cats_save_url}',
			                updateAction: '${cats_save_url}',
			                deleteAction: '${cats_del_url}'
			            },
			            formCreated: function(event, data) {
			            	$(data.form).css("width", "300px");
			            },
			            fields: {
			            	Phones: {
			                    title: '',
			                    width: '2%',
			                    sorting: false,
			                    edit: false,
			                    create: false,
			                    display: function (catsData) {
			                        //Create an image that will be used to open child table
			                        var $img = $('<img src="${jtable_url}/themes/metro/darkgray/list_metro.png" class="child-opener-image" title="Sub categories" />');
			                        //Open child table when user clicks the image
			                        $img.click(function () {
			                            $('#tbCats').jtable('openChildTable', $img.closest('tr'), {
	                                        title: catsData.record.name + ' - Sub categories',
	                                        showCloseButton: true,
	                                        actions: {
	                                            listAction: '${sub_list_url}?categoryId=' + catsData.record.id,
	                                            deleteAction: '${sub_del_url}',
	                                            updateAction: '${sub_save_url}',
	                                            createAction: '${sub_save_url}'
	                                        },
	                    		            formCreated: function(event, subData) {
	                    		            	$(data.form).css("width", "300px");
	                    		            },
	                                        fields: {
	                                            id: {
	                                                key: true,
	                                                create: false,
	                                                edit: false,
	                                                list: false
	                                            },
	                                            categoryId: {
	                                                list: false,
	                                                input: function(subData) {
	                                                	return '<input type="hidden" name="categoryId" value="' + catsData.record.id + '"/>';
	                                                }
	                                            },
	                                            catId: {
	                                                title: 'Category',
	                                                width: '20%',
	                                                display: function (subData) {
	                                                	return subData.record.category.name;
	                                                },
	                                                create: false,
	                                                update: false,
	                                                edit: false,
	                                                //dependsOn: "categoryId", //Countries depends on continentals. Thus, jTable builds cascade dropdowns!
	                                                //defaultValue: catsData.record.id,
	                                                //options: function (data) {
	                                                    //if (data.source == 'list') { alert(123);
	                                                        //Return url of all countries for optimization. 
	                                                        //This method is called for each row on the table and jTable caches options based on this url.
	                                                        //return '${cats_options_url}';
	                                                    //}
	                             
	                                                    //This code runs when user opens edit/create form or changes continental combobox on an edit/create form.
	                                                    //data.source == 'edit' || data.source == 'create'
	                                                    //return '${cats_options_url}';
	                                                //},
	                                            },
	                                            name: {
	                                                title: 'Sub Category name',
	                                                width: '80%',
	                                                inputClass: 'validate[required]'
	                                            }
	                                        },
	                    		            //Validate form when it is being submitted
	                    		            formSubmitting: function (event, subData) {
	                    		                return subData.form.validationEngine('validate');
	                    		            },
	                    		            recordsLoaded: function(subEvent, subData) {
	                    		            	//alert(JSON.stringify(subData.records[0].category.type))
	                    		                $('.jtable-child-table-container .jtable-data-row').click(function() {
	                    		                	//alert(JSON.stringify(subData.records[0].category.type));
	                    		                    var subId = $(this).attr('data-record-key');
	                    		                    CategoryDetails.load(subData.records[0].category.type, subId);
	                    		                });
	                    		            }
	                                    }, function (data) { //opened handler
	                                        data.childTable.jtable('load');
	                                    });
			                        });
			                        //Return image to show on the person row
			                        return $img;
			                    }
			                },
			                id: {
			                    key: true,
			                    list: false
			                },
			                type: {
			                    title: 'Category Type',
			                    width: '15%',
			                    options: { 'IMAGE': 'Images', 'VIDEO': 'Videos', 'BOOKS': 'Books' },
			                },
			                name: {
			                    title: 'Category Name',
			                    width: '85%',
			                    create: true,
			                    edit: true,
			                    inputClass: 'validate[required]'
			                    //type: 'date'
			                }
			            },
			            //Validate form when it is being submitted
			            formSubmitting: function (event, data) {
			                return data.form.validationEngine('validate');
			            },
			        });
			        $('#tbCats').jtable('load');
		    	}
		    }
		    
		    CategoryDetails = {
	    		load : function(catType, subId) { //alert(catType)
		    		$('#tbSubDetails').jtable({
			            title: catType,
			            columnResizable: true,
			            openChildAsAccordion: true,
			            actions: {
			                listAction: '${sub_details_url}?type=' + catType + '&subCategoryId=' + subId,
			                createAction: '${cats_save_url}',
			                updateAction: '${cats_save_url}',
			                deleteAction: '${cats_del_url}'
			            },
			            formCreated: function(event, data) {
			            	$(data.form).css("width", "300px");
			            },
			            fields: {
			                id: {
			                    key: true,
			                    list: false
			                },
			                name: {
			                    title: 'Name',
			                    width: '85%',
			                    create: true,
			                    edit: true,
			                    inputClass: 'validate[required]'
			                    //type: 'date'
			                },
                            thumb: {
								display: function(data) {
									return '<img src="' + data.record.thumb + '"/>';
								},
                                input: function(subData) {
                                	return '<input type="file" name="thumb"/>';
                                }
                            },
			            },
			            //Validate form when it is being submitted
			            formSubmitting: function (event, data) {
			                return data.form.validationEngine('validate');
			            },
			        });
		    		$('#tbSubDetails').jtable({
		    			actions: {
		    				listAction: '${sub_details_url}?type=' + catType + '&subCategoryId=' + subId
		    			}
		    		});
		    		//$('#tbSubDetails').jtable.actions.listAction = '${sub_details_url}?type=' + catType + '&subCategoryId=' + subId;
		    		//alert($('#tbSubDetails').jtable.actions.listAction)
			        $('#tbSubDetails').jtable('load');
	    		}
		    }
		</script>
	</head>
	<body>
		<center>
			<table cellpadding="0" cellspacing="0" width="1024" border="1">
				<tr>
					<td class="panel">
						<t:menu data="${menuData}"></t:menu>
					</td>
				</tr>
				<tr>
					<td class="panel">
						<div style="">
							<div id="tbCats"></div>
						</div>
					</td>
				</tr>
				<tr>
					<td class="panel">
						<div style="">
							<div id="tbSubDetails"></div>
						</div>
					</td>
				</tr>
				<tr><td><a href="http://apycom.com/">Apycom jQuery Menus</a></td></tr>
			</table>
		</center>
	</body>
</html>