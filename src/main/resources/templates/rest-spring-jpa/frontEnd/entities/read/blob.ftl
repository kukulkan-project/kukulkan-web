        <dt><span data-translate="${projectName}App.${entityCamelCase}.${property.name}">${property.name}</span></dt>
        <dd>            
            <div ng-if="vm.${entityCamelCase}.${property.name}">
                <a ng-click="vm.openFile(vm.${entityCamelCase}.${property.name}ContentType, vm.${entityCamelCase}.${property.name})" data-translate="entity.action.open">open</a>
                {{vm.${entityCamelCase}.${property.name}ContentType}}, {{vm.byteSize(vm.${entityCamelCase}.${property.name})}}
            </div>
		<dd>
