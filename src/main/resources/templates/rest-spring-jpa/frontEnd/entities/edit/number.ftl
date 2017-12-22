			<div class="form-group">
				<label class="control-label" data-translate="${projectName}App.${entityCamelCase}.${property.name}"
					for="field_${property.name}">${property.name}</label>
				<input type="number" class="form-control" name="${property.name}" id="field_${property.name}"
					ng-model="vm.${entityCamelCase}.${property.name}"
<#if property.constraint.nullable==false>
					required</#if> <#if property.constraint.minNumber??>min="${property.constraint.minNumber}"</#if> <#if property.constraint.minNumber??>max="${property.constraint.maxNumber}"</#if>
					/>
				<div ng-show="editForm.${property.name}.$invalid">
				<#if property.constraint.nullable==false>
	                <p class="help-block"
	                    ng-show="editForm.${property.name}.$error.required" data-translate="entity.validation.required">
	                    This field is required.
	                </p>
	            </#if>
				<#if property.constraint.minNumber??>
	                <p class="help-block"
	                    ng-show="editForm.${property.name}.$error.min" data-translate="entity.validation.min" translate-value-min="${property.constraint.minNumber}">
	                    This field should be at least ${property.constraint.minNumber}.
	                </p>
				</#if>
				<#if property.constraint.maxNumber??>
	                <p class="help-block"
	                    ng-show="editForm.${property.name}.$error.max" data-translate="entity.validation.max" translate-value-max="${property.constraint.maxNumber}">
	                    This field cannot be more than ${property.constraint.maxNumber}.
	                </p>
				</#if>
	                <p class="help-block"
	                    ng-show="editForm.${property.name}.$error.number" data-translate="entity.validation.number">
	                    This field should be a number.
	                </p>
            	</div>	
			</div>
