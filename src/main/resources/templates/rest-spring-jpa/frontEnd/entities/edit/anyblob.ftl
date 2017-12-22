			<div class="form-group" ngf-drop ngf-change="vm.set${property.name?cap_first}($file, vm.${entityCamelCase})">
				<label class="control-label" data-translate="${projectName}App.${entityCamelCase}.${property.name}"
					for="field_${property.name}">${property.name}</label>
				<div>
					<div ng-if="vm.${entityCamelCase}.${property.name}" class="help-block clearfix">
						<a class="pull-left"
							ng-click="vm.openFile(vm.${entityCamelCase}.${property.name}ContentType, vm.${entityCamelCase}.${property.name})"
							data-translate="entity.action.open">open</a>
						<br>
							<span class="pull-left">{{vm.${entityCamelCase}.${property.name}ContentType}},
								{{vm.byteSize(vm.${entityCamelCase}.${property.name})}}
							</span>
							<button type="button"
								ng-click="vm.${entityCamelCase}.${property.name}=null;vm.${entityCamelCase}.${property.name}ContentType=null;"
								class="btn btn-default btn-xs pull-right">
								<span class="glyphicon glyphicon-remove"></span>
							</button>
					</div>
					<button type="button" ngf-select class="btn btn-default btn-block"
						ngf-change="vm.set${property.name?cap_first}($file, vm.${entityCamelCase})" data-translate="entity.action.addblob">
						Add blob
					</button>
				</div>
				<input type="hidden" class="form-control" name="${property.name}" id="field_${property.name}"
					ng-model="vm.${entityCamelCase}.${property.name}" />
				<input type="hidden" class="form-control" name="${property.name}ContentType"
					id="field_${property.name}ContentType" ng-model="vm.${entityCamelCase}.${property.name}ContentType" />
			</div>
