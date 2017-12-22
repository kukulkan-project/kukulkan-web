<#assign translateKey>${projectName}App.${entityCamelCase}</#assign>
<div>
    <h2 data-translate="${translateKey}.home.title">${entityCamelCasePlural}</h2>
    <jhi-alert></jhi-alert>
    <div class="container-fluid">
        <div class="row">
            <div class="col-xs-4 no-padding-left">
                <button class="btn btn-primary" ui-sref="${entityCamelCase}.new" >
                    <span class="glyphicon glyphicon-plus"></span>
                    <span class="hidden-xs-down" data-translate="${translateKey}.home.createLabel">
                        Create new ${entity}
                    </span>
                </button>
            </div>
            <div class="col-xs-8 no-padding-right">
                <form name="searchForm" class="form-inline">
                    <div class="input-group pull-right" >
                        <input type="text" class="form-control" ng-model="vm.searchQuery" id="searchQuery" placeholder="{{ '${translateKey}.home.search' | translate }}">
                        <span  class="input-group-btn width-min" >
                            <button class="btn btn-info" ng-click="vm.search(vm.searchQuery)">
                                <span class="glyphicon glyphicon-search"></span>
                            </button>
                        </span>
                        <span class="input-group-btn width-min" ng-if="vm.currentSearch">
                            <button class="btn btn-info" ng-click="vm.clear()">
                                <span class="glyphicon glyphicon-trash"></span>
                            </button>
                        </span>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <br/>
    <div class="table-responsive">
        <table class="jh-table table table-striped">
            <thead>
                <tr jh-sort="vm.predicate" ascending="vm.reverse" callback="vm.transition()">
                    <th jh-sort-by="id"><span data-translate="global.field.id">ID</span> <span class="glyphicon glyphicon-sort"></span></th>
                    <#list properties as property>
                    	<#if property.name?ends_with("ContentType") == false>
                    <th jh-sort-by="${property.name}"><span data-translate="${translateKey}.${property.name}">${property.name}</span> <span class="glyphicon glyphicon-sort"></span></th>
                    	</#if>
                    </#list>
                    <th></th>
                </tr>
            </thead>
            <tbody ng-repeat="${entityCamelCase} in vm.${entityCamelCasePlural} track by ${entityCamelCase}.id">
                    <td><a ui-sref="${entityCamelCase}-detail({id:${entityCamelCase}.id})">{{${entityCamelCase}.id}}</a></td>
				   <#list properties as property>
                    	<#if property.name?ends_with("ContentType") == false >
							<#if property.time == true>
                    			<#if property.zoneDateTime == true || property.instant == true>
					<td>{{${entityCamelCase}.${property.name} | date:'medium'}}</td>
								<#elseif property.localDate == true>
					<td>{{${entityCamelCase}.${property.name} | date:'mediumDate'}}</td>
							</#if>
					        <#elseif property.blob == true>
					        	<#if property.clob == true>
					<td>{{${entityCamelCase}.${property.name}}}</td>        
					        	<#else>
					<td>
						<a ng-if="${entityCamelCase}.${property.name}" ng-click="vm.openFile(${entityCamelCase}.${property.name}ContentType, ${entityCamelCase}.${property.name})" data-translate="entity.action.open">open</a>
                        <span ng-if="${entityCamelCase}.${property.name}">{{${entityCamelCase}.${property.name}ContentType}}, {{vm.byteSize(${entityCamelCase}.${property.name})}}</span> 
					</td>     
					        	</#if>
                    		<#else>
					<td>{{${entityCamelCase}.${property.name}}}</td>
							</#if>
                    	</#if>
                    </#list>
                    <td class="text-right">
                        <div class="btn-group flex-btn-group-container">
                            <button type="submit"
                                    ui-sref="${entityCamelCase}-detail({id:${entityCamelCase}.id})"
                                    class="btn btn-info btn-sm">
                                <span class="glyphicon glyphicon-eye-open"></span>
                                <span class="hidden-sm-down" data-translate="entity.action.view"></span>
                            </button>
                            <button type="submit"
                                    ui-sref="${entityCamelCase}.edit({id:${entityCamelCase}.id})"
                                    class="btn btn-primary btn-sm">
                                <span class="glyphicon glyphicon-pencil"></span>
                                <span class="hidden-sm-down" data-translate="entity.action.edit"></span>
                            </button>
                            <button type="submit"
                                    ui-sref="${entityCamelCase}.delete({id:${entityCamelCase}.id})"
                                    class="btn btn-danger btn-sm">
                                <span class="glyphicon glyphicon-remove-circle"></span>
                                <span class="hidden-sm-down" data-translate="entity.action.delete"></span>
                            </button>
                        </div>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
    <div class="text-center">
        <jhi-item-count page="vm.page" total="vm.queryCount" items-per-page="vm.itemsPerPage"></jhi-item-count>
        <uib-pagination class="pagination-sm" total-items="vm.totalItems" items-per-page="vm.itemsPerPage" ng-model="vm.page" ng-change="vm.transition()"></uib-pagination>
    </div>
</div>
