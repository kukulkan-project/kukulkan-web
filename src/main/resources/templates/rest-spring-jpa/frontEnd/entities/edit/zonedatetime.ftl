			<div class="form-group">
				<label class="control-label" data-translate="${projectName}App.${entityCamelCase}.${property.name}"
					for="field_${property.name}">${property.name}</label>
				<div class="input-group">
					<input id="field_${property.name}" type="text" class="form-control"
						name="${property.name}" datetime-picker="{{dateformat}}" ng-model="vm.${entityCamelCase}.${property.name}"
						is-open="vm.datePickerOpenStatus.${property.name}" 
						<#if property.constraint.nullable==false>
						required</#if> />
					<span class="input-group-btn">
						<button type="button" class="btn btn-default"
							ng-click="vm.openCalendar('${property.name}')">
							<i class="glyphicon glyphicon-calendar"></i>
						</button>
					</span>
				</div>
				<div ng-show="editForm.${property.name}.$invalid">
				<#if property.constraint.nullable==false>
	                <p class="help-block"
	                    ng-show="editForm.${property.name}.$error.required" data-translate="entity.validation.required">
	                    This field is required.
	                </p>
	        	</#if>
	                <p class="help-block"
	                    ng-show="editForm.${property.name}.$error.ZonedDateTimelocal" data-translate="entity.validation.ZonedDateTimelocal">
	                    This field should be a date and time.
	                </p>
            	</div>
			</div>
