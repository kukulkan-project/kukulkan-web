			<div class="form-group" ngf-drop ngf-change="vm.set${entityCamelCase?cap_first}($file, vm.${entityCamelCase})"
				ngf-pattern="'image/*'">
				<label class="control-label" data-translate="${projectName}App.${entityCamelCase}.${property.name}"
					for="field_${property.name}">${property.name}</label>
				<div>
					<img
						data-ng-src="{{'data:' + vm.${entityCamelCase}.${property.name}ContentType + ';base64,' + vm.${entityCamelCase}.${property.name}}}"
						style="max-height: 100px;" ng-if="vm.${entityCamelCase}.${property.name}" alt="${property.name} image" />
					<div ng-if="vm.${entityCamelCase}.${property.name}" class="help-block clearfix">
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
						ngf-change="vm.set${entityCamelCase?cap_first}($file, vm.${property.name})" accept="image/*"
						data-translate="entity.action.addimage">
						Add image
					</button>
				</div>
				<input type="hidden" class="form-control" name="${property.name}" id="field_${property.name}"
					ng-model="vm.${entityCamelCase}.${property.name}" />
				<input type="hidden" class="form-control" name="${property.name}ContentType"
					id="field_${property.name}ContentType" ng-model="vm.${entityCamelCase}.${property.name}ContentType" />
			</div>
