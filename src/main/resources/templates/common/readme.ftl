Con la finalidad de que funcione el proyecto, se deberá de incluir la siguiente información:

en la ruta: src/main/webapp/app/layouts/navbar/navbar.html
agregar lo siguiente:

<#list dataModelGroup as dmg>
	<#list dmg.domainModelElements as dme>
<li ui-sref-active="active" has-any-authority="ROLE_ADMIN,ROLE_LOCAL">
	<a ui-sref="${dme.camelCaseFormat}" ng-click="vm.collapseNavbar()">
		<span class="glyphicon glyphicon-book"></span>&nbsp;
		<span data-translate="global.menu.entities.${dme.camelCaseFormat}">${dme.name}</span>
	</a>
</li>    
	</#list>    
</#list>


en la ruta: src/main/webapp/i18n/en/global.json
            src/main/webapp/i18n/es/global.json

los siguiente:
.
.
.
"entities": {
	"main": "Entities",
<#list dataModelGroup as dmg>
	<#list dmg.domainModelElements as dme>
	"${dme.camelCaseFormat}": "${dme.name}",
	</#list>    
</#list>
    "jhipster-needle-menu-add-entry": "JHipster will add additional entities here (do not translate!)"
},
.
.
.
	