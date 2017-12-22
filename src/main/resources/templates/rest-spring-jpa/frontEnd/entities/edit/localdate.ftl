			<div class="form-group">
				<label class="control-label" data-translate="${projectName}App.${entityCamelCase}.${property.name}"
					for="field_${property.name}">${property.name}</label>
				<div class="input-group">
					<input id="field_${property.name}" type="text" class="form-control"
						name="${property.name}" uib-datepicker-popup="{{dateformat}}" ng-model="vm.${entityCamelCase}.${property.name}"
						is-open="vm.datePickerOpenStatus.${property.name}" 
						<#if property.constraint.nullable==false>
						required</#if> />
					<span class="input-group-btn">
						<button type="button" class="btn btn-default" ng-click="vm.openCalendar('${property.name}')">
							<i class="glyphicon glyphicon-calendar"></i>
						</button>
					</span>
				</div>
			<#if property.constraint.nullable==false>
				<div ng-show="editForm.${property.name}.$invalid">
	                <p class="help-block"
	                    ng-show="editForm.${property.name}.$error.required" data-translate="entity.validation.required">
	                    This field is required.
	                </p>
            	</div>
			</#if>           
			</div>
