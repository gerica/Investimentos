<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<link href="<c:url value="/resources/css/angular/style.css" />" rel="stylesheet" media="screen, projection">
<link href="<c:url value="/resources/css/angular/css/font-awesome.css" />" rel="stylesheet" media="screen, projection">
<link href="<c:url value="/resources/css/angular/angular.css" />" rel="stylesheet" media="screen, projection">
<script type="text/javascript" src="<c:url value="/resources/js/angular.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/angular/angular-animate.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/angular/app.js" />"></script>
<!-- DATA JQUERY         -->
<link href="<c:url value="/resources/css/datatables/jquery.dataTables.css" />" rel="stylesheet" media="screen, projection">
<!-- JQUERY -->
	<script type="text/javascript" src="<c:url value="/resources/js/jquery-1.11.2.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/datatables/jquery.dataTables.js" />"></script>
<!-- SCRIPT DA PAGINA -->
<script type="text/javascript" src="<c:url value="/resources/js/carteiraView.js" />"></script>


<div ng-app="taskManagerApp">
<div ng-controller="taskManagerController">
 <div id="task-panel" class="fadein fadeout showpanel panel"  ng-show="toggle"> 
  <div class="panel-heading">
   <i class="panel-title-icon fa fa-tasks"></i>
   <span class="panel-title">Empresa</span>
   <div class="panel-heading-controls">
    <button ng-click="toggle = !toggle" class="btn-panel">Adicionar</button>
   </div>
  </div>
 </div>
 
 <div id="add-task-panel" class="fadein fadeout addpanel panel" ng-hide="toggle">
  <div class="panel-heading">
   <i class="panel-title-icon fa fa-plus"></i>
   <span class="panel-title">Adicionar Empresa</span>
   <div class="panel-heading-controls">
    <button ng-click="toggle = !toggle" class="btn-panel">Fechar</button>
   </div>
  </div>
  <div class="panel-body">
   <div class="task" >
    <table class="add-task">
     <tr>
      <td>Nome:</td>
      <td><input type="text" ng-model="empresa"/></td>
     </tr>
     <tr>
      <td>
		<button ng-click="addEmpresa()" class="btn-panel-big">Gravar</button></td>
     </tr>
    </table>        
   </div>
  </div>
 </div>
</div>

<form:form action="" method="GET">
     <fieldset>
         <legend>Empresas</legend>
	<table width="70%" style="border: 3px;background: rgb(243, 244, 248);"><tr><td>
		<table id="listEmpresas" class="display" cellspacing="0" width="100%">
	        <thead>
	            <tr>
	                <th>Name</th>
	            </tr>
	        </thead>       
	    </table>
	    </td></tr></table>
	</fieldset>
</form:form>
</div>