<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>.: cPanel :.</title>
		
        <spring:url value="/resources/js" var="js_url" />
        <spring:url value="/resources/jquery-ui" var="ui_url" />
        <spring:url value="/resources/jquery-datatable" var="datatable_url" />
        <spring:url value="/resources/css" var="css_url" />
        
        <spring:url value="/user/login" var="login_url" />
        <spring:url value="/user/logout" var="logout_url" />
        <spring:url value="/user/list" var="user_list_url" />
        <spring:url value="/user/roles" var="user_roles_url" />
        <spring:url value="/permis/list" var="permis_list_url" />
        <spring:url value="/role/list" var="role_list_url" />
        <spring:url value="/role/edit" var="role_edit_url" />
        <spring:url value="/role/apply" var="role_apply_url" />
        <spring:url value="/news/list" var="news_list_url" />
        
        <link href="${css_url}/style.css" rel="stylesheet" type="text/css"/>
        <link href="${ui_url}/jquery-ui.min.css" rel="stylesheet" type="text/css"/>
        <link href="${datatable_url}/dataTables.jqueryui.css" rel="stylesheet" type="text/css"/>
        
        <style type="text/css">
        	#menu a {
        		cursor: pointer;
        		font-weight: bold;
        	}
        	#lbUserInfo {
	        	display: none;
	        	font-weight: lighter !important;
	        	font-style: italic;
	        }
        	#menuLogout {
	        	display: none;
	        }
        	#dialogBox {
	        	display: none;
	        }
	        
	        #tblUsersContent, #tblPermisContent, #tblRolesContent, #tblNewsContent {
	        	border: 1px solid #459e00;
	        }
	        caption {
	        	text-align: left;
	        }
        </style>
        
        <script type="text/javascript" src="${js_url}/jquery-1.11.1.min.js"></script>
        <script type="text/javascript" src="${ui_url}/jquery-ui.min.js"></script>
        <script type="text/javascript" src="${datatable_url}/jquery.dataTables.min.js"></script>
        <script type="text/javascript" src="${datatable_url}/dataTables.jqueryui.js"></script>
        
	</head>
	<body>
		<div id="menu">
			<a id="lbUserInfo"></a> <a id="menuLogin">Login</a><a id="menuLogout">Logout</a> |
			<a id="menuUsers">Users</a> |
			<a id="menuRoles">Roles</a> |
			<a id="menuPermis">Permissions</a> |
			<a id="menuNews">News</a> |
		</div>
		<table id="contents" width="75%" cellspacing="20" border="0">
			<tr><td id="tblUsersContent">. . .</td></tr>
			<tr><td id="tblRolesContent">. . .</td></tr>
			<tr><td id="tblPermisContent">. . .</td></tr>
			<tr><td id="tblNewsContent">. . .</td></tr>
		</table>
		<div id="dialogBox" title=".: Login :."></div>
		<!-- <table id="examples" class="display" cellspacing="0" width="100%"><thead><tr><th>Name</th><th>Position</th><th>Office</th><th>Salary</th></tr></thead><tbody><tr><td>Tiger Nixon</td><td>System Architect</td><td>Edinburgh</td><td>$320,800</td></tr><tr><td>Garrett Winters</td><td>Accountant</td><td>Tokyo</td><td>$170,750</td></tr><tr><td>Ashton Cox</td><td>Junior Technical Author</td><td>San Francisco</td><td>$86,000</td></tr><tr><td>Cedric Kelly</td><td>Senior Javascript Developer</td><td>Edinburgh</td><td>$433,060</td></tr><tr><td>Airi Satou</td><td>Accountant</td><td>Tokyo</td><td>$162,700</td></tr><tr><td>Brielle Williamson</td><td>Integration Specialist</td><td>New York</td><td>$372,000</td></tr><tr><td>Herrod Chandler</td><td>Sales Assistant</td><td>San Francisco</td><td>$137,500</td></tr><tr><td>Rhona Davidson</td><td>Integration Specialist</td><td>Tokyo</td><td>$327,900</td></tr><tr><td>Colleen Hurst</td><td>Javascript Developer</td><td>San Francisco</td><td>$205,500</td></tr><tr><td>Sonya Frost</td><td>Software Engineer</td><td>Edinburgh</td><td>$103,600</td></tr><tr><td>Jena Gaines</td><td>Office Manager</td><td>London</td><td>$90,560</td></tr><tr><td>Quinn Flynn</td><td>Support Lead</td><td>Edinburgh</td><td>$342,000</td></tr><tr><td>Charde Marshall</td><td>Regional Director</td><td>San Francisco</td><td>$470,600</td></tr><tr><td>Haley Kennedy</td><td>Senior Marketing Designer</td><td>London</td><td>$313,500</td></tr><tr><td>Tatyana Fitzpatrick</td><td>Regional Director</td><td>London</td><td>$385,750</td></tr><tr><td>Michael Silva</td><td>Marketing Designer</td><td>London</td><td>$198,500</td></tr><tr><td>Paul Byrd</td><td>Chief Financial Officer (CFO)</td><td>New York</td><td>$725,000</td></tr><tr><td>Gloria Little</td><td>Systems Administrator</td><td>New York</td><td>$237,500</td></tr><tr><td>Bradley Greer</td><td>Software Engineer</td><td>London</td><td>$132,000</td></tr><tr><td>Dai Rios</td><td>Personnel Lead</td><td>Edinburgh</td><td>$217,500</td></tr><tr><td>Jenette Caldwell</td><td>Development Lead</td><td>New York</td><td>$345,000</td></tr><tr><td>Yuri Berry</td><td>Chief Marketing Officer (CMO)</td><td>New York</td><td>$675,000</td></tr><tr><td>Caesar Vance</td><td>Pre-Sales Support</td><td>New York</td><td>$106,450</td></tr><tr><td>Doris Wilder</td><td>Sales Assistant</td><td>Sidney</td><td>$85,600</td></tr><tr><td>Angelica Ramos</td><td>Chief Executive Officer (CEO)</td><td>London</td><td>$1,200,000</td></tr><tr><td>Gavin Joyce</td><td>Developer</td><td>Edinburgh</td><td>$92,575</td></tr><tr><td>Jennifer Chang</td><td>Regional Director</td><td>Singapore</td><td>$357,650</td></tr><tr><td>Brenden Wagner</td><td>Software Engineer</td><td>San Francisco</td><td>$206,850</td></tr><tr><td>Fiona Green</td><td>Chief Operating Officer (COO)</td><td>San Francisco</td><td>$850,000</td></tr><tr><td>Shou Itou</td><td>Regional Marketing</td><td>Tokyo</td><td>$163,000</td></tr><tr><td>Michelle House</td><td>Integration Specialist</td><td>Sidney</td><td>$95,400</td></tr><tr><td>Suki Burks</td><td>Developer</td><td>London</td><td>$114,500</td></tr><tr><td>Prescott Bartlett</td><td>Technical Author</td><td>London</td><td>$145,000</td></tr><tr><td>Gavin Cortez</td><td>Team Leader</td><td>San Francisco</td><td>$235,500</td></tr><tr><td>Martena Mccray</td><td>Post-Sales support</td><td>Edinburgh</td><td>$324,050</td></tr><tr><td>Unity Butler</td><td>Marketing Designer</td><td>San Francisco</td><td>$85,675</td></tr><tr><td>Howard Hatfield</td><td>Office Manager</td><td>San Francisco</td><td>$164,500</td></tr><tr><td>Hope Fuentes</td><td>Secretary</td><td>San Francisco</td><td>$109,850</td></tr><tr><td>Vivian Harrell</td><td>Financial Controller</td><td>San Francisco</td><td>$452,500</td></tr><tr><td>Timothy Mooney</td><td>Office Manager</td><td>London</td><td>$136,200</td></tr><tr><td>Jackson Bradshaw</td><td>Director</td><td>New York</td><td>$645,750</td></tr><tr><td>Olivia Liang</td><td>Support Engineer</td><td>Singapore</td><td>$234,500</td></tr><tr><td>Bruno Nash</td><td>Software Engineer</td><td>London</td><td>$163,500</td></tr><tr><td>Sakura Yamamoto</td><td>Support Engineer</td><td>Tokyo</td><td>$139,575</td></tr><tr><td>Thor Walton</td><td>Developer</td><td>New York</td><td>$98,540</td></tr><tr><td>Finn Camacho</td><td>Support Engineer</td><td>San Francisco</td><td>$87,500</td></tr><tr><td>Serge Baldwin</td><td>Data Coordinator</td><td>Singapore</td><td>$138,575</td></tr><tr><td>Zenaida Frank</td><td>Software Engineer</td><td>New York</td><td>$125,250</td></tr><tr><td>Zorita Serrano</td><td>Software Engineer</td><td>San Francisco</td><td>$115,000</td></tr><tr><td>Jennifer Acosta</td><td>Junior Javascript Developer</td><td>Edinburgh</td><td>$75,650</td></tr><tr><td>Cara Stevens</td><td>Sales Assistant</td><td>New York</td><td>$145,600</td></tr><tr><td>Hermione Butler</td><td>Regional Director</td><td>London</td><td>$356,250</td></tr><tr><td>Lael Greer</td><td>Systems Administrator</td><td>London</td><td>$103,500</td></tr><tr><td>Jonas Alexander</td><td>Developer</td><td>San Francisco</td><td>$86,500</td></tr><tr><td>Shad Decker</td><td>Regional Director</td><td>Edinburgh</td><td>$183,000</td></tr><tr><td>Michael Bruce</td><td>Javascript Developer</td><td>Singapore</td><td>$183,000</td></tr><tr><td>Donna Snider</td><td>Customer Support</td><td>New York</td><td>$112,000</td></tr></tbody></table> -->
		<script type="text/javascript">
			$(function() {
				Authority.init('${login_url}', '${logout_url}');

				UserController.init('${user_list_url}', '${user_roles_url}');
				RoleController.init('${role_list_url}', '${role_edit_url}', '${role_apply_url}');
				PermisController.init('${permis_list_url}');
				NewsController.init('${news_list_url}');

				//UserController.list('${user_list_url}');
				//PermisController.list('${permis_list_url}');
				//RoleController.list('${role_list_url}');
				/* $('#examples').dataTable(); */
			});
			$(document).ajaxError(function( event, request, settings ) {
				Dialog.msg('Ajax error', settings.url);
			});
			
			Dialog = {
				open : function(url, data, title, width, height) {
	                $.ajax({
	                    type: 'GET',
	                    url: url,
	                    data: data,
	                    success: function(html) {
	                    	if (title != undefined && title != null) {
	                    		$("#dialogBox").attr('title', '.: ' + title + ' :.');
	                    	} else {
	                    		$("#dialogBox").attr('title', '.: ¿ :.');
	                    	}
	                    	$("#dialogBox").css('display', '').html(html).dialog({ minHeight: height, minWidth: width });
	                    }
	                });
				},
				
				close : function() {
					$("#dialogBox").dialog('close');
				},
				
				submit : function(formId, callbackFunction) {
					var form = $('#' + formId);
	                $.ajax({
	                    type: form.attr('method'),
	                    url: form.attr('action'),
	                    data: form.serialize(),
	                    success: function(data) {  
	                    	callbackFunction(data);
	                    },
	                });
				},
				
				msg : function(title, msg) {
					$('<i title=".: ' + title + ' :.">' + msg + '</i>').dialog({ minHeight: 100, minWidth: 300 });
				},
			}
			
			Authority = {
				init : function(loginUrl, logoutUrl) {
					Authority.login(loginUrl);
					Authority.logout(logoutUrl);
				},
				
				login : function(url) {
					$('#menuLogin').click(function() {
						Dialog.open(url, null, null, 360, 180);	
					});
					
					$('body').on('click', 'input[value="Login"]', function() {
						Dialog.submit('formLogin', function(data) {
							Dialog.close();
							if (data.header.code == 401) {
								Dialog.msg('Error(' + data.header.code + ')', data.error.msg);
							} else {
								$('#menuLogin').css('display', 'none');
								$('#menuLogout').css('display', 'inline');
								$('#lbUserInfo').css('display', 'inline').html('Welcome ' + data.body.data.username + '!');
							}
						});
					});
				},
				
				logout : function(url) {
					$('#menuLogout').click(function() {
		                $.ajax({
		                    type: 'POST',
		                    url: url,
		                    success: function(data) {  
								if (data.header.code == 401) {
									Dialog.msg('Error(' + data.header.code + ')', data.error.msg);
								} else {
									Dialog.msg('Logout', data.body.data);
									$('#menuLogin').css('display', 'inline');
									$('#menuLogout').css('display', 'none');
									$('#lbUserInfo').css('display', 'none');
								}
		                    },
		                });
					});
				}
			}
			
			UserController = {
				init : function(listUrl, rolesUrl) {
					$('#menuUsers').click(function() {
						UserController.list(listUrl);
					});
					
					UserController.roles(rolesUrl);
					
					$(document).bind('UserController.roles.save', function() {
						UserController.list(listUrl);
					});
				},
				
				list : function(url) {
	                $.ajax({
	                    type: 'POST',
	                    url: url,
	                    success: function(data) {  
							if (data.header.code == 200) {
								var tbl = $('<table id="tblUsers" subject="USER" class="display"><caption>Users [ <a id="fnAssignRole" href="#">Change roles</a> | <a name="fnApplyRoles" href="#">Apply roles</a> ]</caption><thead><tr><th width="10%">[]</th><th width="10%">Order</th><th width="20%">User name</th><th width="60%">Roles</th></tr></thead></table>');
								$(data.body.data).each(function(index) {
									var item = data.body.data[index];
									tbl.append('<tbody val="' + item.id + '"><tr><td><input type="radio" name="userId" value="' + item.id + '"/></td><td>' + (index + 1) + '</td><td>' + item.username + '</td><td>' + item.roles + '</td></tr></tbody>');
								}); 
								$('#tblUsersContent').html('').append(tbl.dataTable());
								$('#tblUsers').dataTable();
							} else {
								Dialog.msg('Error(' + data.header.code + ')', data.error.msg);
							}
	                    },
	                });
				},
				
				roles : function(url) {
					$('body').on('click', '#tblUsers #fnAssignRole', function() {
						var userId = $('#tblUsers input:checked').val();
						if (userId == undefined) {
							Dialog.msg('Alert', 'Please, choose one User!');
						} else {
							Dialog.open(url, {userId : userId}, 'Change Roles', 360, 220);	
						}	
					});
					/* $('body').on('click', '#tblUsers tbody', function() {
						Dialog.open(url, {userID : $(this).attr('val')}, 'Change permissions', 360, 220);	
					}); */
					$('body').on('click', '#formPermissions input[value="Save"]', function() {
						Dialog.submit('formPermissions', function(data) {
							Dialog.close();
							if (data.header.code == 401) {
								Dialog.msg('Error(' + data.header.code + ')', data.error.msg);
							} else {
								$(document).trigger('UserController.roles.save');
							}
						});
					});
				},
			}
			
			NewsController = {
				init : function(listUrl) {
					$('#menuNews').click(function() {
						NewsController.list(listUrl);
					});
				},
				
				list : function(url) {
	                $.ajax({
	                    type: 'GET',
	                    url: url,
	                    success: function(data) {  
							$('#tblNewsContent').html('').append(data);
							$('#tblNews').dataTable();
	                    },
	                });
				}
			}
			
			RoleController = {
				init : function(listUrl, editUrl, applyUrl) {
					$('#menuRoles').click(function() {
						RoleController.list(listUrl);
					});
					RoleController.add(editUrl);
					RoleController.apply(applyUrl);
					
					$(document).bind('RoleController.add.save', function() {
						RoleController.list(listUrl);
					});
				},
				
				list : function(url) {
	                $.ajax({
	                    type: 'POST',
	                    url: url,
	                    success: function(data) {  
							if (data.header.code == 200) {
								var tbl = $('<table id="tblRoles" subject="ROLE" class="display"><caption>Roles [ <a id="addRole" href="#">Add new</a> | <a name="fnApplyRoles" href="#">Apply roles</a>]</caption><thead><tr><th width="10%">[]</th><th width="10%">Order</th><th width="20%">Name</th><th width="60%">Permissions</th></tr></thead></table>');
								$(data.body.data).each(function(index) {
									var item = data.body.data[index];
									tbl.append('<tbody val="' + item.id + '"><tr><td><input type="radio" name="roleIDs" value="' + item.id + '"/></td><td>' + (index + 1) + '</td><td>' + item.name + '</td><td>' + item.permissions + '</td></tr></tbody>');
								}); 
								$('#tblRolesContent').html('').append(tbl.dataTable());
								$('#tblRoles').dataTable();
							} else {
								Dialog.msg('Error(' + data.header.code + ')', data.error.msg);
							}
	                    },
	                });
				},
				
				add : function(url) {
					$('body').on('click', '#addRole', function() {
						Dialog.open(url, null, 'Add new Role', 400, 160);
						return false;
					});
					
					$('body').on('click', '#formRoleEdit input[value="Save"]', function() {
						Dialog.submit('formRoleEdit', function(data) {
							Dialog.close();
							if (data.header.code == 401) {
								Dialog.msg('Error(' + data.header.code + ')', data.error.msg);
							} else {
								$(document).trigger('RoleController.add.save');
							}
						});
					});
				},
				
				apply : function(url) {
					$('body').on('click', 'a[name="fnApplyRoles"]', function() {
						var item = $(this).closest('table').find('input:checked');
						if (item.length == 0 || item.length > 1) {
							Dialog.msg("Alert", "Please, choose one item!");
						} else {
							var subject = $(this).closest('table').attr('subject');
							var subjectId = item.val();
							Dialog.open(url, {subject : subject, subjectId : subjectId}, 'Apply Roles', 360, 220);	
						}	
					});
					$('body').on('click', '#formRoleApply input[value="Apply"]', function() {
						Dialog.submit('formRoleApply', function(data) {
							Dialog.close();
							if (data.header.code == 403) {
								Dialog.msg('Error(' + data.header.code + ')', data.error.msg);
							} else {
								Dialog.msg('Done', 'Roles \'s been applied!');
							}
						});
					}); 
				},
			}

			PermisController = {
				init : function(listUrl) {
					$('#menuPermis').click(function() {
						PermisController.list(listUrl);
					});
				},
				
				list : function(url) {
	                $.ajax({
	                    type: 'POST',
	                    url: url,
	                    success: function(data) {  
							if (data.header.code == 200) {
								var tbl = $('<table id="tblPermis" subject="PERMIS" class="display"><caption>Permissions [ <a name="fnApplyRoles" href="#">Apply roles</a>]</caption><thead><tr><th width="10%">[]</th><th width="10%">Order</th><th width="40%">Code</th><th width="40%">Name</th></tr></thead></table>');
								$(data.body.data).each(function(index) {
									var item = data.body.data[index];
									tbl.append('<tbody val="' + item.id + '"><tr><td><input type="radio" name="permisIDs" value="' + item.id + '"/></td><td>' + (index + 1) + '</td><td>' + item.code + '</td><td>' + item.name + '</td></tr></tbody>');
								}); 
								$('#tblPermisContent').html('').append(tbl.dataTable());
								$('#tblPermis').dataTable();
							} else {
								Dialog.msg('Error(' + data.header.code + ')', data.error.msg);
							}
	                    },
	                });
				},
			}

		</script>
	</body>
</html>